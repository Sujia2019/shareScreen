/*
 * Created by JFormDesigner on Fri Apr 01 18:16:25 CST 2022
 */

package com.tute.wjl.ui;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.Constants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
public class RequestFrame extends JFrame {
    private DataContext dataContext;
    public RequestFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void receiveMouseClicked(MouseEvent e) {
        // TODO add your code here
        Message message = dataContext.initMessage(Constants.SHARE_RECEIVE);
        message.setToId(dataContext.getTeacherId());
        ClientApplication.send(message);

        try {
            // 三秒后开始共享屏幕
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void refuseMouseClicked(MouseEvent e) {
        // TODO add your code here
        Message message = dataContext.initMessage(Constants.SHARE_REFUSE);
        message.setToId(dataContext.getTeacherId());
        ClientApplication.send(message);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        label1 = new JLabel();
        label2 = new JLabel();
        receive = new JButton();
        refuse = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u6536\u5230\u8001\u5e08\u53d1\u9001\u7684\u5171\u4eab\u5c4f\u5e55\u8bf7\u6c42");
        label1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        //---- label2 ----
        label2.setText("\u662f\u5426\u540c\u610f\u5171\u4eab\uff1f");
        label2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        //---- receive ----
        receive.setText("\u63a5\u53d7");
        receive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                receiveMouseClicked(e);
            }
        });

        //---- refuse ----
        refuse.setText("\u62d2\u7edd");
        refuse.setBackground(new Color(255, 153, 102));
        refuse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refuseMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(125, 125, 125)
                            .addComponent(label2))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(receive, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(refuse, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label1))))
                    .addContainerGap(73, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(label2)
                    .addGap(45, 45, 45)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(refuse, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(receive, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(42, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JLabel label1;
    private JLabel label2;
    private JButton receive;
    private JButton refuse;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
