/*
 * Created by JFormDesigner on Fri Apr 01 21:01:37 CST 2022
 */

package com.tute.wjl.ui;

import java.awt.event.*;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.Constants;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
public class BoardController extends JFrame {
    private DataContext dataContext;
    private LittleChatPanel littleChatPanel;
    public BoardController(DataContext dataContext,LittleChatPanel littleChatPanel) {
        this.dataContext = dataContext;
        this.littleChatPanel = littleChatPanel;
        initComponents();
    }

    private void closeShareMouseClicked(MouseEvent e) {
        DataContext.needStop = true;
        dispose();
    }

    private void showChatMouseClicked(MouseEvent e) {
        littleChatPanel.setVisible(true);
    }

    private void drawPanelMouseClicked(MouseEvent e) {
        Message message = dataContext.initMessage(Constants.DRAW_OPEN);
        message.setToId(dataContext.getShareGroupName());
        ClientApplication.send(message);
//        new DrawPanel(dataContext).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        showChat = new JButton();
        closeShare = new JButton();
        drawPanel = new JButton();

        //======== this ========
        setTitle("\u63a7\u5236\u677f");
        Container contentPane = getContentPane();

        //---- showChat ----
        showChat.setText("\u663e\u793a\u804a\u5929");
        showChat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChatMouseClicked(e);
            }
        });

        //---- closeShare ----
        closeShare.setText("\u7ed3\u675f\u5171\u4eab");
        closeShare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeShareMouseClicked(e);
            }
        });

        //---- drawPanel ----
        drawPanel.setText("\u5171\u4eab\u753b\u677f");
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawPanelMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(showChat, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(closeShare, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(drawPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(5, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(showChat, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeShare, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(drawPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JButton showChat;
    private JButton closeShare;
    private JButton drawPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
