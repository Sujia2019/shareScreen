package com.tute.wjl;

import com.tute.wjl.context.DataContext;
import com.tute.wjl.context.FrameContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.net.NettyClient;
import com.tute.wjl.ui.*;

// client 启动项
public class ClientApplication {
    private FrameContext context;
    private DataContext dataContext;
    private static NettyClient nettyClient;
    private ErrorTips errorTips;

    ClientApplication(){
        context = new FrameContext();
        dataContext = new DataContext();
        nettyClient = new NettyClient(context,dataContext);
        errorTips = new  ErrorTips();
        context.setError(errorTips);
    }

    // 加载界面
    private void init() {
        ShareFrame shareFrame = new ShareFrame(dataContext);
        shareFrame.getSystem().setVisible(false);

        LoginFrame loginFrame = new LoginFrame(dataContext);
        loginFrame.setVisible(true);

        TeacherMainFrame teacherFrame = new TeacherMainFrame(dataContext);
        teacherFrame.setVisible(false);

        StudentMainFrame studentFrame = new StudentMainFrame(dataContext);
        studentFrame.setVisible(false);

        context.setLoginFrame(loginFrame);
        context.setShareFrame(shareFrame.getSystem());
        context.setLabel(shareFrame.getScreen());
        context.setUserList(shareFrame.getUserList());
        context.setStudentFrame(studentFrame);
        context.setTeacherFrame(teacherFrame);
        context.setEndButton(shareFrame.getEndButton());
        context.setRequestButton(shareFrame.getRequestButton());
        context.setShareButton(shareFrame.getShareButton());
        context.setCloseButton(shareFrame.getCloseButton());



    }

    public void start(String host,int port) {
        boolean net = nettyClient.init(host,port);
        if(net){
            init();
        }else {
            errorTips.setVisible(true);
            errorTips.getErrorMsg().setText("未能连接到服务器");
        }

    }

    public static void send(Message message) {
        try {
            nettyClient.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
