package com.tute.wjl.net;

import com.tute.wjl.context.DataContext;
import com.tute.wjl.context.FrameContext;
import com.tute.wjl.entity.Course;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.ui.ErrorTips;
import com.tute.wjl.ui.RequestFrame;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.GzipUtils;
import com.tute.wjl.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class NettyClientHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger LOGGER= LoggerFactory.getLogger(NettyClientHandler.class);
    private ThreadPoolExecutor clientHandlerPool;
    private FrameContext context;
    private DataContext dataContext;

    public NettyClientHandler(FrameContext context,DataContext dataContext){
        clientHandlerPool = ThreadPoolUtil.ThreadPool(NettyServerHandler.class.getSimpleName());
        this.context = context;
        this.dataContext = dataContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
//        clientHandlerPool.execute(()->{
            switch (message.getMessageType()){
                // 图片
                case Constants.PICTURE:
                    // 进行图片资源的还原
                    getPicture(message);
                    break;
                // 群聊(局部分组) 收到群聊消息
                case Constants.ALL:
                    receiveGroupMessage(message);
                    break;
                // 私聊
                case Constants.PRIVATE:
                    receivePrivateMessage(message);
                    break;
                // 收到共享屏幕请求
                case Constants.SHARE:
                    dataContext.setTeacherId(message.getFromId());
                    new RequestFrame(dataContext,context.getCloseButton(),context.getShareButton()).setVisible(true);
                    break;
                // 收到学生的接受共享屏幕
                case Constants.SHARE_RECEIVE:
                    DataContext.needStop = true;
                    context.getLabel().setText("学生已接受。已停止您的屏幕共享");
                    break;
                // 开课成功
                case Constants.CREATE_SUCCESS:
                    createGroupSuccess(message);
                    break;
                // 开始上课
                case Constants.START:
                    break;
                // 加入课程成功
                case Constants.ADD_SUCCESS:
                    joinGroup(message);
                    break;
                // 退出群组
                case Constants.QUIT:
                    quitGroup(message);
                    break;
                // 结束上课
                case Constants.END:
                    new ErrorTips("老师已下课！");
                    context.getShareFrame().dispose();
                    break;
                // 用户业务
                case Constants.USER:
                    doUser(message,ctx);
                    break;
                case Constants.COURSE:
                    doCourse(message);
                    break;
                case Constants.ERROR:
                    errorMsg(message);
                    break;
                default:
                    System.out.println(message);
                    LOGGER.info("message type is not defined");

            }
//            ctx.writeAndFlush(message);
//        });


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(">>>>>>>>>>>rpc netty client caught exception",cause);
        ctx.close();
    }

    private void doUser(Message message,ChannelHandlerContext ctx){
        User user;
        if(!(message.getContent() instanceof User)){
            System.out.println("对象错误");
            return;
        }
        user = (User) message.getContent();
        String toId = message.getToId();
        // 如果登陆成功或注册成功
        if(toId.equals(Constants.LOGIN_SUCCESS)||toId.equals(Constants.REGISTER_SUCCESS)){
            getLoginInfo(message,user);
        }else if(toId.equals(Constants.USER_UPDATE_SUCCESS)) { // 更新成功
            dataContext.setUser((User) message.getContent());
            new ErrorTips("更新成功",message.getContent().toString());
        }
    }

    // 获取登陆信息
    private void getLoginInfo(Message message, User user) {
        System.out.println("登陆成功！\n" + user);
        dataContext.setUser(user);
        // 关闭登陆窗口
        context.getLoginFrame().dispose();
        dataContext.setTeacher(user.isTeacher());
        if (user.isTeacher()) {
            dataContext.setTeacherId(user.getUserAccount());
            context.getTeacherFrame().setVisible(true);
        } else {
            // 学生不能请求他人共享屏幕等
            context.getCloseButton().setEnabled(false);
            context.getRequestButton().setEnabled(false);
            context.getShareButton().setEnabled(false);
            context.getEndButton().setText("退出课堂");
            context.getStudentFrame().setVisible(true);
        }

    }

    private void errorMsg(Message message){
        context.getError().getErrorMsg().setText(message.getContent().toString());
        context.getError().setVisible(true);
    }

    // 开课成功 打开授课页面
    private void createGroupSuccess(Message message){
        context.getShareFrame().setVisible(true);
        dataContext.setShareGroupName(message.getToId());
    }

    // 进入课堂
    private void joinGroup(Message message){
        setUserList(message);
        context.getShareFrame().setVisible(true);
        dataContext.setShareGroupName(message.getToId());
    }
    private void setUserList(Message message){
        List res = (List) message.getContent();
        String []array = new String[res.size()];
        for(int i=0;i<res.size();i++){
            array[i] = res.get(i).toString();
        }
        context.getUserList().setListData(array);
    }

    // 有人退出课堂
    private void quitGroup(Message message){
        setUserList(message);
    }

    private void getPicture(Message message){
        // 如果成功打开了页面
        if(context.getShareFrame().isVisible()){
            // 进行图片资源的还原
            byte[] pic = new byte[0];
            try {
                pic = GzipUtils.ungzip(message.getFileContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //图片等比压缩
            ImageIcon icon = new ImageIcon(pic);
            Image img = icon.getImage();// 获得此图标的Image
            int width = context.getLabel().getWidth();
            int height = context.getLabel().getHeight();
            img = img.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
            icon.setImage(img);// 将图标设置为压缩后的图像
            context.getLabel().setIcon(icon);
        }
    }

    private void receiveGroupMessage(Message message){
        // 如果是自己发的
        String content = context.getLittleChatArea().getText()+"\n\n";
        String self = content+"（我）:\n  "+message.getContent();
        String all = content+" 来自【"+message.getFromName()+"】所有人消息:\n  "+message.getContent();
        if(message.getFromId().equals(dataContext.getUser().getUserAccount())){
            context.getLittleChatArea().setText(self);
            context.getBigChatArea().setText(self);
        }else{
            context.getLittleChatArea().setText(all);
            context.getBigChatArea().setText(all);
        }
    }

    private void receivePrivateMessage(Message message){
        String content = context.getLittleChatArea().getText()+"\n\n 来自【"+message.getFromName()+"】私聊消息:\n  "+message.getContent();
        context.getBigChatArea().setText(content);
        context.getLittleChatArea().setText(content);
    }

    private void doCourse(Message message){
        switch (message.getToId()){
            case Constants.COURSE_SEARCH_SUCCESS:
            case Constants.COURSE_CLASS_SUCCESS:
            case Constants.COURSE_TEACHER_SUCCESS:
                updateJList(message);
                break;
            case Constants.COURSE_DELETE_SUCCESS:
                new ErrorTips("成功！","成功删除课程【"+message.getContent()+"】");
                break;
            case Constants.COURSE_UPDATE_SUCCESS:
                new ErrorTips("成功！","成功修改课程【"+message.getContent()+"】");
                break;
            case Constants.COURSE_NEW_SUCCESS:
                new ErrorTips("成功！","成功创建新课程【"+message.getContent()+"】");
                break;
            default:
                System.out.println(message);
        }
    }

    private void updateJList(Message message){
        List res = (List) message.getContent();
        dataContext.setData(res);
        String []array = new String[res.size()];
        for(int i=0;i<res.size();i++){
            if(res.get(i) instanceof Course){
                String name = ((Course) res.get(i)).getCourseName()+"-"+((Course) res.get(i)).getCourseClass();
                array[i]=name;
            }
        }
        // 老师
        if(dataContext.isTeacher()){
            context.getResList().setListData(array);

        }else{
            context.getResList2().setListData(array);
        }
    }

}
