package com.tute.wjl.net;

import com.tute.wjl.context.DataContext;
import com.tute.wjl.context.FrameContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
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
                // 群聊(局部分组)
                case Constants.ALL:
                    break;
                // 私聊
                case Constants.PRIVATE:
                // 收到共享屏幕请求
                case Constants.SHARE:
                    dataContext.setTeacherId(message.getFromId());
                    new RequestFrame(dataContext).setVisible(false);
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
                    break;
                // 结束上课
                case Constants.END:
                    break;
                // 用户业务
                case Constants.USER:
                    doUser(message,ctx);
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

        getLoginInfo(message,user);
    }

    // 获取登陆信息
    private void getLoginInfo(Message message,User user){
        // 如果登陆成功或注册成功
        if(message.getToId().equals(Constants.LOGIN_SUCCESS)||message.getToId().equals(Constants.REGISTER_SUCCESS)){
            System.out.println("登陆成功！\n"+user);
            dataContext.setUser(user);
            context.getLoginFrame().setVisible(false);
            if(user.isTeacher()){
                context.getTeacherFrame().setVisible(true);
            }else{
                // 学生不能请求他人共享屏幕等
                context.getCloseButton().setEnabled(false);
                context.getRequestButton().setEnabled(false);
                context.getShareButton().setEnabled(false);
                context.getEndButton().setText("退出课堂");
                context.getStudentFrame().setVisible(true);
            }
        }
//        context.getShareFrame().setVisible(true);
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
        context.getShareFrame().setVisible(true);
        dataContext.setShareGroupName(message.getToId());
    }

    // 加入课程成功，在列表中显示自己
    private void addSuccess(){
//        context.getUserList().getModel().
//        context.getUserList().setListData();
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

}
