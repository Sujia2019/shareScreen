/*
 * Created by JFormDesigner on Sat Apr 02 00:12:50 CST 2022
 */

package com.tute.wjl.ui;

import java.awt.event.*;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.Constants;
import io.netty.util.internal.StringUtil;
import lombok.Data;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
@Data
public class LittleChatPanel extends JFrame {
    private DataContext dataContext;
    public LittleChatPanel(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void sendButtonMouseClicked(MouseEvent e) {
        String content = inputArea.getText();
        if(StringUtil.isNullOrEmpty(content)){
            new ErrorTips("不可发送空信息");
        }else{
            Message message = dataContext.initMessage(Constants.ALL);
            message.setContent(content);
            message.setToId(dataContext.getShareGroupName());
            ClientApplication.send(message);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        scrollPane1 = new JScrollPane();
        chatLogArea = new JTextArea();
        separator1 = new JSeparator();
        scrollPane2 = new JScrollPane();
        inputArea = new JTextArea();
        sendButton = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- chatLogArea ----
            chatLogArea.setEditable(false);
            scrollPane1.setViewportView(chatLogArea);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(inputArea);
        }

        //---- sendButton ----
        sendButton.setText("\u53d1\u9001");
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendButtonMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(separator1)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JScrollPane scrollPane1;
    private JTextArea chatLogArea;
    private JSeparator separator1;
    private JScrollPane scrollPane2;
    private JTextArea inputArea;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
