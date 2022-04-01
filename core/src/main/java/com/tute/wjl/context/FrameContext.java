package com.tute.wjl.context;

import com.tute.wjl.ui.ErrorTips;
import lombok.Data;

import javax.swing.*;

// 用于上下文传输数据 保存的临时对象 应是全局只创建一个
@Data
public class FrameContext {
    private JLabel label;
    private ErrorTips error;
    private JFrame shareFrame;
    private JFrame loginFrame;
    private JList userList;
    private JFrame teacherFrame;
    private JFrame studentFrame;
    private JButton shareButton;
    private JButton requestButton;
    private JButton endButton;
    private JButton closeButton;
    private JFrame boardController;
    private JTextArea bigChatArea;
    private JTextArea littleChatArea;
}
