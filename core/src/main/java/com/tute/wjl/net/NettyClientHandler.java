package com.tute.wjl.net;

import com.tute.wjl.context.DataContext;
import com.tute.wjl.context.FrameContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.GzipUtils;
import com.tute.wjl.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
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
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception{
//        clientHandlerPool.execute(()->{
            switch (message.getMessageType()){
                // 图片
                case Constants.PICTURE:
                    // 进行图片资源的还原
                    byte[] pic = new byte[0];
                    try {
                        pic = GzipUtils.ungzip(message.getFileContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    context.getLabel().setIcon(new ImageIcon(pic));
                    break;
                // 群聊(局部分组)
                case Constants.ALL:
                    break;
                // 私聊
                case Constants.PRIVATE:
                    // 发送共享屏幕请求
                case Constants.SHARE:
                    break;
                // 创建一个局部分组(开始上课)
                case Constants.CREATE:
                    // 开始上课
                case Constants.START:
                    break;
                // 加入课程
                case Constants.ADD:
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
        if(message.getToId().equals(Constants.LOGIN_SUCCESS)){
            getLoginInfo(user);
            if(user.isTeacher()){
                createGroup(ctx);
            }else{
                addGroup(ctx);
            }
        }
    }

    // 获取登陆信息
    private void getLoginInfo(User user){
        System.out.println("登陆成功！\n"+user);
        dataContext.setUser(user);
        context.getLoginFrame().setVisible(false);
        context.getShareFrame().setVisible(true);
    }

    private void errorMsg(Message message){
        context.getError().getErrorMsg().setText(message.getContent().toString());
        context.getError().setVisible(true);
    }

    // 开课
    private void createGroup(ChannelHandlerContext ctx){
        Message message = initMessage(Constants.CREATE);
        message.setToId("网络工程");
        ctx.writeAndFlush(message);
    }

    // 加入课程
    private void addGroup(ChannelHandlerContext ctx){
        Message message = initMessage(Constants.ADD);
        message.setToId("网络工程");
        ctx.writeAndFlush(message);
    }

    private Message initMessage(String messageType){
        Message message = new Message();
        User user = dataContext.getUser();
        message.setFromId(user.getUserAccount());
        message.setMessageType(messageType);
        return message;
    }
}
