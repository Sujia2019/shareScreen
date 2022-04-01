package com.tute.wjl.net;

import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.service.ChatService;
import com.tute.wjl.service.GroupService;
import com.tute.wjl.service.UserService;
import com.tute.wjl.utils.Constants;
import com.tute.wjl.utils.ThreadPoolUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ThreadPoolExecutor;

@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger LOGGER= LoggerFactory.getLogger(NettyServerHandler.class);
    private final ThreadPoolExecutor serverHandlerPool;
    private final ChatService chatService;
    private final GroupService groupService;
    private final UserService userService;

    public NettyServerHandler() {
        this.serverHandlerPool= ThreadPoolUtil.ThreadPool(NettyServer.class.getSimpleName());
        chatService = ChatService.getInstance();
        groupService = GroupService.getInstance();
        userService = UserService.getInstance();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {

        serverHandlerPool.execute(()->{
            switch (message.getMessageType()){
                // 图片
                case Constants.PICTURE:
//                    NettyServer.group.writeAndFlush(message);
                    // 群聊(局部分组)
                case Constants.ALL:
                    chatService.sendMessageToGroup(message);
                    break;
                // 私聊
                case Constants.PRIVATE:
                // 发送共享屏幕请求
                case Constants.SHARE:
                // 对方接受共享屏幕，此时告诉老师可以停止屏幕共享
                case Constants.SHARE_RECEIVE:
                    chatService.sendMessageToOne(message);
                    break;
                // 创建一个局部分组(开始上课)
                case Constants.CREATE:
                // 开始上课
                case Constants.START:
                    groupService.createGroup(message,ctx);
                    break;
                // 加入课程
                case Constants.ADD:
                    groupService.addGroup(message,ctx);
                    break;
                // 退出群组
                case Constants.QUIT:
                    groupService.quitGroup(message);
                    break;
                // 结束上课
                case Constants.END:
                    groupService.destoryGroup(message);
                    break;
                // 用户业务
                case Constants.USER:
                    doUser(message,ctx);
                    break;
                default:
                    LOGGER.info("message type is not defined");


            }

//            ctx.writeAndFlush(message);


        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(">>>>>>>>>>>>> rpc provider netty server caught exception.",cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>rpc provider netty server close an old channel.");
        }else{
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info(">>>>>>>channel active"+ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        LOGGER.info(">>>>>>>channel is not active"+ctx);
    }

    private void doUser(Message message, ChannelHandlerContext ctx){
        Object obj = message.getContent();
        if(message.getContent() instanceof User){
            User user = (User) obj;
            if(message.getToId().equals(Constants.LOGIN)){
                user = userService.doLogin(user);
                if(user!=null){
                    // 登陆成功，将自己注册到map当中
                    Message res = loginSuccess(ctx, user);
                    res.setToId(Constants.LOGIN_SUCCESS);
                    res.setFromId("SERVER");
                    ctx.writeAndFlush(res);
                }else{
                    Message res = new Message("登陆失败,用户名或密码错误");
                    ctx.writeAndFlush(res);
                }
            }else if(message.getToId().equals(Constants.REGISTER)){
                user = userService.doRegister(user);
                if(user!=null){
                    // 注册成功，将自己注册到map当中
                    Message res = loginSuccess(ctx, user);
                    res.setToId(Constants.REGISTER_SUCCESS);
                    res.setFromId("SERVER");
                    ctx.writeAndFlush(res);
                }else{
                    Message res = new Message("注册失败，账号已存在");
                    ctx.writeAndFlush(res);
                }
            }
//            GroupService.userMap.put(user.getUserAccount(),ctx.channel().id());
        }
    }

    private Message loginSuccess(ChannelHandlerContext ctx, User user) {
        GroupService.userMap.put(user.getUserAccount(),ctx.channel().id());
        Message res = new Message();
        res.setContent(user);
        res.setMessageType(Constants.USER);
        return res;
    }

}
