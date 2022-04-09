package com.tute.wjl.net;

import com.tute.wjl.entity.Course;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.service.ChatService;
import com.tute.wjl.service.CourseService;
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
    private final CourseService courseService;

    public NettyServerHandler() {
        this.serverHandlerPool= ThreadPoolUtil.ThreadPool(NettyServer.class.getSimpleName());
        chatService = ChatService.getInstance();
        groupService = GroupService.getInstance();
        userService = UserService.getInstance();
        courseService = CourseService.getInstance();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {

        serverHandlerPool.execute(()->{
            switch (message.getMessageType()){
                // 图片
                case Constants.PICTURE:
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
                // 结束课程
                case Constants.END:
                    groupService.destroyGroup(message,ctx);
                    break;
                // 加入课程
                case Constants.ADD:
                    groupService.addGroup(message,ctx);
                    break;
                // 退出群组
                case Constants.QUIT:
                    groupService.quitGroup(message,ctx);
                    break;
                // 用户业务
                case Constants.USER:
                    doUser(message,ctx);
                    break;
                case Constants.COURSE:
                    doCourse(message,ctx);
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
    public void channelActive(ChannelHandlerContext ctx) {
        // 处于活动状态 已连接上
        LOGGER.info(">>>>>>>channel active"+ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        LOGGER.info(">>>>>>>channel is not active"+ctx);
    }

    private void doUser(Message message, ChannelHandlerContext ctx){
        String target = message.getToId();
        if(message.getContent() instanceof User){
            switch (target) {
                case Constants.LOGIN:
                    // 登陆
                    userService.doLogin(message, ctx);
                    break;
                case Constants.REGISTER:
                    userService.doRegister(message, ctx);
                    break;
                case Constants.USER_UPDATE:
                    userService.updateUser(message, ctx);
                    break;
            }
        }
    }

    private void doCourse(Message message, ChannelHandlerContext ctx) {

        switch (message.getToId()) {
            case Constants.COURSE_CLASS:
                courseService.getCourseByClass(message);
                break;
            case Constants.COURSE_DELETE:
                courseService.delete(message);
                break;
            case Constants.COURSE_SEARCH:
                courseService.getCourseByName(message);
                break;
            case Constants.COURSE_NEW:
                courseService.insert(message);
                break;
            case Constants.COURSE_TEACHER:
                courseService.getCourseByTeacher(message);
                break;
            case Constants.COURSE_UPDATE:
                courseService.update(message);
                break;
            default:
                break;
        }
        System.out.println(message);
        ctx.writeAndFlush(message);

    }

}
