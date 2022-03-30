/*
 * Created by JFormDesigner on Tue Mar 29 21:38:42 CST 2022
 */

package com.tute.wjl.ui;

import com.tute.wjl.net.NettyClient;
import lombok.Data;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * @author unknown
 */
@Data
public class Client extends JFrame {
    public Client() throws IOException {
        initComponents();
    }

    public void setScreenIcon(ImageIcon icon){
        screen.setIcon(icon);
    }

    private void startShareScreen(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        system = new JFrame();
        chatLog = new JPanel();
        userNames = new JPanel();
        panel4 = new JPanel();
        input = new JPanel();
        scrollPane1 = new JScrollPane();
        inputArea = new JTextArea();
        sendButton = new JButton();
        controller = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        screen = new JLabel();

        //======== system ========
        {
            system.setTitle("\u5728\u7ebf\u6559\u5b66\u7cfb\u7edf");
            system.setBackground(Color.white);
            system.setVisible(true);
            Container systemContentPane = system.getContentPane();

            //======== chatLog ========
            {
                chatLog.setBackground(Color.darkGray);
                chatLog.setForeground(new Color(102, 102, 102));
                chatLog.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing
                .border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),
                java.awt.Color.red),chatLog. getBorder()));chatLog. addPropertyChangeListener(new java.beans.PropertyChangeListener()
                {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))
                throw new RuntimeException();}});

                //======== userNames ========
                {
                    userNames.setBackground(new Color(255, 204, 255));

                    GroupLayout userNamesLayout = new GroupLayout(userNames);
                    userNames.setLayout(userNamesLayout);
                    userNamesLayout.setHorizontalGroup(
                        userNamesLayout.createParallelGroup()
                            .addGap(0, 120, Short.MAX_VALUE)
                    );
                    userNamesLayout.setVerticalGroup(
                        userNamesLayout.createParallelGroup()
                            .addGap(0, 540, Short.MAX_VALUE)
                    );
                }

                //======== panel4 ========
                {
                    panel4.setBackground(new Color(204, 255, 255));

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGap(0, 540, Short.MAX_VALUE)
                    );
                }

                GroupLayout chatLogLayout = new GroupLayout(chatLog);
                chatLog.setLayout(chatLogLayout);
                chatLogLayout.setHorizontalGroup(
                    chatLogLayout.createParallelGroup()
                        .addGroup(chatLogLayout.createSequentialGroup()
                            .addComponent(userNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                chatLogLayout.setVerticalGroup(
                    chatLogLayout.createParallelGroup()
                        .addComponent(userNames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }

            //======== input ========
            {
                input.setBackground(Color.darkGray);
                input.setToolTipText("\u8f93\u5165");

                //======== scrollPane1 ========
                {

                    //---- inputArea ----
                    inputArea.setBackground(Color.white);
                    inputArea.setForeground(new Color(51, 51, 51));
                    scrollPane1.setViewportView(inputArea);
                }

                //---- sendButton ----
                sendButton.setText("\u53d1\u9001");

                GroupLayout inputLayout = new GroupLayout(input);
                input.setLayout(inputLayout);
                inputLayout.setHorizontalGroup(
                    inputLayout.createParallelGroup()
                        .addGroup(inputLayout.createSequentialGroup()
                            .addComponent(scrollPane1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                );
                inputLayout.setVerticalGroup(
                    inputLayout.createParallelGroup()
                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                );
            }

            //======== controller ========
            {
                controller.setBackground(Color.darkGray);

                //---- button2 ----
                button2.setText("\u5171\u4eab\u5c4f\u5e55");
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        startShareScreen(e);
                    }
                });

                //---- button3 ----
                button3.setText("\u7ed3\u675f\u5171\u4eab");

                //---- button5 ----
                button5.setText("\u9000\u51fa");

                //---- button6 ----
                button6.setText("\u7533\u8bf7\u5171\u4eab");

                GroupLayout controllerLayout = new GroupLayout(controller);
                controller.setLayout(controllerLayout);
                controllerLayout.setHorizontalGroup(
                    controllerLayout.createParallelGroup()
                        .addGroup(controllerLayout.createSequentialGroup()
                            .addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button3)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
                controllerLayout.setVerticalGroup(
                    controllerLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }

            //---- screen ----
            screen.setText("\u4f60\u597d");
            screen.setIcon(null);

            GroupLayout systemContentPaneLayout = new GroupLayout(systemContentPane);
            systemContentPane.setLayout(systemContentPaneLayout);
            systemContentPaneLayout.setHorizontalGroup(
                systemContentPaneLayout.createParallelGroup()
                    .addGroup(systemContentPaneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(screen, GroupLayout.PREFERRED_SIZE, 983, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(systemContentPaneLayout.createParallelGroup()
                            .addComponent(chatLog, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(controller, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(input, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
            );
            systemContentPaneLayout.setVerticalGroup(
                systemContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, systemContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(systemContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(screen, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                            .addGroup(systemContentPaneLayout.createSequentialGroup()
                                .addComponent(chatLog, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
    private JPanel chatLog;
    private JPanel userNames;
    private JPanel panel4;
    private JPanel input;
    private JScrollPane scrollPane1;
    private JTextArea inputArea;
    private JButton sendButton;
    private JPanel controller;
    private JButton button2;
    private JButton button3;
    private JButton button5;
    private JButton button6;
    private JLabel screen;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) throws Exception {
        // 打开图形化界面
        Client client = new Client();
        // 创建网络连接
        NettyClient nettyClient = new NettyClient(client);
        nettyClient.init("localhost",8888);

    }
}
