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

    public ShareFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    public void setScreenIcon(ImageIcon icon){
        screen.setIcon(icon);
    }

    private void startShareScreen(MouseEvent e) {
        if(dataContext.getUser().isTeacher()){
            new Thread(new ShareScreen()).start();
        }
        // TODO add your code here
    }

    private void sendMessage(String type,String msg,String toId) {
        // TODO add your code here
        Message message = dataContext.initMessage(type);
        message.setContent(msg);
        message.setToId(dataContext.getShareGroupName());
        message.setFromName(dataContext.getUser().getTrueName());
        ClientApplication.send(message);

    }

    private void sendToOneMouseClicked(MouseEvent e) {
        // TODO add your code here
        String content = inputArea.getText();
        String[] array = content.split("\\(");
        String res = array[1].substring(0,array[1].length()-1);
        // 发送私聊
        sendMessage(Constants.PRIVATE,content,res);
    }

    private void sendToAllMouseClicked(MouseEvent e) {
        // TODO add your code here
        String content = inputArea.getText();
        if(StringUtil.isNullOrEmpty(content)){
            ErrorTips error = new ErrorTips();
            error.getErrorMsg().setText("请不要发送空信息");
            error.setVisible(true);
        }else{
            // 发送全部信息
            sendMessage(Constants.ALL,content,dataContext.getShareGroupName());
        }

    }

    private void requestButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String toUser = userList.getSelectedValue();
        if(!StringUtil.isNullOrEmpty(toUser)){
            Message message = dataContext.initMessage(Constants.SHARE);
            message.setToId(toUser);
            ClientApplication.send(message);
        }else{
            new ErrorTips("未指定学生");
        }
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
        }
    }

    private void initComponents(){
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        system = new JFrame();
        chatPanel = new JPanel();
        userNames = new JPanel();
        scrollPane3 = new JScrollPane();
        chatLog = new JList();
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
                chatPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER
                , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
                .BOLD ,12 ), java. awt. Color. red) ,chatPanel. getBorder( )) ); chatPanel. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r"
                .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //======== userNames ========
                {
                    userNames.setBackground(new Color(255, 204, 255));

                    //======== scrollPane3 ========
                    {
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
                                "student(student)"
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

                //---- endButton ----
                endButton.setText("\u7ed3\u675f\u8bfe\u7a0b");

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
    private JList chatLog;
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
