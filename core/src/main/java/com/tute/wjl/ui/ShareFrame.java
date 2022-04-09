/*
 * Created by JFormDesigner on Tue Mar 29 21:38:42 CST 2022
 */

package com.tute.wjl.ui;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.service.PictureService;
import com.tute.wjl.utils.Constants;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
@Data
public class ShareFrame extends JFrame {
    private DataContext dataContext;
    private BoardController boardController;

    public ShareFrame(DataContext dataContext,BoardController boardController) {
        this.dataContext = dataContext;
        this.boardController = boardController;
        initComponents();
    }

    public void setScreenIcon(ImageIcon icon){
        screen.setIcon(icon);
    }

    private void startShareScreen(MouseEvent e) {
        if(dataContext.getUser().isTeacher()||DataContext.isReceive){
            DataContext.needStop = false;
            boardController.setVisible(true);
            system.setVisible(false);
            new Thread(new ShareScreen()).start();
        }
    }

    private void sendMessage(String type,String msg,String toId) {
        Message message = dataContext.initMessage(type);
        message.setContent(msg);
        message.setToId(toId);
        ClientApplication.send(message);

    }

    private void sendToOneMouseClicked(MouseEvent e) {
        String toUser = userList.getSelectedValue();
        String content = inputArea.getText();
        String[] array = toUser.split("\\(");
        String res = array[1].substring(0,array[1].length()-1);
        // 发送私聊
        if(StringUtil.isNullOrEmpty(content)){
            new ErrorTips("请不要发送空信息").setVisible(true);
        }else{
            // 发送组内信息
            sendMessage(Constants.PRIVATE,content,res);
        }
    }

    private void sendToAllMouseClicked(MouseEvent e) {
        // TODO add your code here
        String content = inputArea.getText();
        if(StringUtil.isNullOrEmpty(content)){
            new ErrorTips("请不要发送空信息").setVisible(true);
        }else{
            // 发送组内信息
            sendMessage(Constants.ALL,content,dataContext.getShareGroupName());
        }

    }

    private void requestButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String toUser = userList.getSelectedValue();
        String[] array = toUser.split("\\(");
        String res = array[1].substring(0,array[1].length()-1);
        if(!StringUtil.isNullOrEmpty(toUser)){
            Message message = dataContext.initMessage(Constants.SHARE);
            message.setToId(res);
            ClientApplication.send(message);
        }else{
            new ErrorTips("未指定学生");
        }
    }

    private void closeButtonMouseClicked(MouseEvent e) {
        DataContext.needStop = true;
        DataContext.isReceive = false;
        screen.setText("您已停止共享屏幕");
    }

    private void endButtonMouseClicked(MouseEvent e) {
        if(dataContext.getUser().isTeacher()){
            sendMessage(Constants.END,"结束课程",dataContext.getShareGroupName());
        }else{
            sendMessage(Constants.QUIT,"退出课程",dataContext.getShareGroupName());
        }
        system.dispose();
    }

    class ShareScreen implements Runnable{
        @SneakyThrows
        @Override
        public void run() {
            // 不需要停止
            while (!DataContext.needStop){
                Message message = dataContext.initMessage(Constants.PICTURE);
                // 获取到的课程-班级
                message.setToId(dataContext.getShareGroupName());
                PictureService.getInstance().sendPicByte(message);
                ClientApplication.send(message);
                // 30帧  33
                // 60帧  17
                // 10帧  10
                Thread.sleep(33);
            }
            system.setVisible(true);
        }
    }

    private void initComponents(){
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        system = new JFrame();
        chatPanel = new JPanel();
        userNames = new JPanel();
        scrollPane3 = new JScrollPane();
        chatLog = new JTextArea();
        chatLogs = new JPanel();
        scrollPane4 = new JScrollPane();
        userList = new JList<>();
        input = new JPanel();
        scrollPane1 = new JScrollPane();
        inputArea = new JTextArea();
        sendToAll = new JButton();
        sendToOne = new JButton();
        controller = new JPanel();
        shareButton = new JButton();
        closeButton = new JButton();
        endButton = new JButton();
        requestButton = new JButton();
        screen = new JLabel();

        //======== system ========
        {
            system.setTitle("\u5728\u7ebf\u6559\u5b66\u7cfb\u7edf");
            system.setBackground(Color.white);
            system.setVisible(true);
            Container systemContentPane = system.getContentPane();

            //======== chatPanel ========
            {
                chatPanel.setBackground(Color.white);
                chatPanel.setForeground(new Color(102, 102, 102));
                chatPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
                .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
                Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
                ),chatPanel. getBorder()));chatPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
                public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
                )))throw new RuntimeException();}});

                //======== userNames ========
                {
                    userNames.setBackground(new Color(255, 204, 255));

                    //======== scrollPane3 ========
                    {

                        //---- chatLog ----
                        chatLog.setEditable(false);
                        scrollPane3.setViewportView(chatLog);
                    }

                    GroupLayout userNamesLayout = new GroupLayout(userNames);
                    userNames.setLayout(userNamesLayout);
                    userNamesLayout.setHorizontalGroup(
                        userNamesLayout.createParallelGroup()
                            .addGroup(userNamesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    userNamesLayout.setVerticalGroup(
                        userNamesLayout.createParallelGroup()
                            .addGroup(userNamesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane3)
                                .addContainerGap())
                    );
                }

                //======== chatLogs ========
                {
                    chatLogs.setBackground(new Color(204, 255, 255));

                    //======== scrollPane4 ========
                    {

                        //---- userList ----
                        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        userList.setModel(new AbstractListModel<String>() {
                            String[] values = {
                                "name(id)"
                            };
                            @Override
                            public int getSize() { return values.length; }
                            @Override
                            public String getElementAt(int i) { return values[i]; }
                        });
                        scrollPane4.setViewportView(userList);
                    }

                    GroupLayout chatLogsLayout = new GroupLayout(chatLogs);
                    chatLogsLayout.setHonorsVisibility(false);
                    chatLogs.setLayout(chatLogsLayout);
                    chatLogsLayout.setHorizontalGroup(
                        chatLogsLayout.createParallelGroup()
                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    );
                    chatLogsLayout.setVerticalGroup(
                        chatLogsLayout.createParallelGroup()
                            .addGroup(chatLogsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }

                GroupLayout chatPanelLayout = new GroupLayout(chatPanel);
                chatPanel.setLayout(chatPanelLayout);
                chatPanelLayout.setHorizontalGroup(
                    chatPanelLayout.createParallelGroup()
                        .addGroup(chatPanelLayout.createSequentialGroup()
                            .addComponent(userNames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chatLogs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
                chatPanelLayout.setVerticalGroup(
                    chatPanelLayout.createParallelGroup()
                        .addComponent(chatLogs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userNames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }

            //======== input ========
            {
                input.setBackground(Color.white);
                input.setToolTipText("\u8f93\u5165");

                //======== scrollPane1 ========
                {

                    //---- inputArea ----
                    inputArea.setBackground(Color.white);
                    inputArea.setForeground(new Color(51, 51, 51));
                    scrollPane1.setViewportView(inputArea);
                }

                //---- sendToAll ----
                sendToAll.setText("\u6240\u6709\u4eba");
                sendToAll.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        sendToAllMouseClicked(e);
                    }
                });

                //---- sendToOne ----
                sendToOne.setText("\u79c1\u804a");
                sendToOne.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        sendToOneMouseClicked(e);
                    }
                });

                GroupLayout inputLayout = new GroupLayout(input);
                input.setLayout(inputLayout);
                inputLayout.setHorizontalGroup(
                    inputLayout.createParallelGroup()
                        .addGroup(inputLayout.createSequentialGroup()
                            .addComponent(scrollPane1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(inputLayout.createParallelGroup()
                                .addComponent(sendToOne, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                .addComponent(sendToAll, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                );
                inputLayout.setVerticalGroup(
                    inputLayout.createParallelGroup()
                        .addGroup(inputLayout.createSequentialGroup()
                            .addComponent(sendToAll, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendToOne, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                );
            }

            //======== controller ========
            {
                controller.setBackground(Color.white);

                //---- shareButton ----
                shareButton.setText("\u5171\u4eab\u5c4f\u5e55");
                shareButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        startShareScreen(e);
                    }
                });

                //---- closeButton ----
                closeButton.setText("\u7ed3\u675f\u5171\u4eab");
                closeButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        closeButtonMouseClicked(e);
                    }
                });

                //---- endButton ----
                endButton.setText("\u7ed3\u675f\u8bfe\u7a0b");
                endButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        endButtonMouseClicked(e);
                    }
                });

                //---- requestButton ----
                requestButton.setText("\u8bf7\u6c42\u5171\u4eab");
                requestButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        requestButtonMouseClicked(e);
                    }
                });

                GroupLayout controllerLayout = new GroupLayout(controller);
                controller.setLayout(controllerLayout);
                controllerLayout.setHorizontalGroup(
                    controllerLayout.createParallelGroup()
                        .addGroup(controllerLayout.createSequentialGroup()
                            .addComponent(shareButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(closeButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(requestButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(endButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
                controllerLayout.setVerticalGroup(
                    controllerLayout.createParallelGroup()
                        .addComponent(closeButton, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addComponent(endButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(requestButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shareButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                );
            }

            //---- screen ----
            screen.setText("                \u6682\u672a\u5171\u4eab\u5c4f\u5e55");
            screen.setIcon(null);
            screen.setBackground(Color.white);
            screen.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

            GroupLayout systemContentPaneLayout = new GroupLayout(systemContentPane);
            systemContentPane.setLayout(systemContentPaneLayout);
            systemContentPaneLayout.setHorizontalGroup(
                systemContentPaneLayout.createParallelGroup()
                    .addGroup(systemContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(screen, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(systemContentPaneLayout.createParallelGroup()
                            .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(controller, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(input, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
            );
            systemContentPaneLayout.setVerticalGroup(
                systemContentPaneLayout.createParallelGroup()
                    .addGroup(systemContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(systemContentPaneLayout.createParallelGroup()
                            .addComponent(screen, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                            .addGroup(systemContentPaneLayout.createSequentialGroup()
                                .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(controller, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            system.pack();
            system.setLocationRelativeTo(system.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JFrame system;
    private JPanel chatPanel;
    private JPanel userNames;
    private JScrollPane scrollPane3;
    private JTextArea chatLog;
    private JPanel chatLogs;
    private JScrollPane scrollPane4;
    private JList<String> userList;
    private JPanel input;
    private JScrollPane scrollPane1;
    private JTextArea inputArea;
    private JButton sendToAll;
    private JButton sendToOne;
    private JPanel controller;
    private JButton shareButton;
    private JButton closeButton;
    private JButton endButton;
    private JButton requestButton;
    private JLabel screen;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) throws Exception {
    }
}
