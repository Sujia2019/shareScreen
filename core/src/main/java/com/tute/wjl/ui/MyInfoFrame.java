/*
 * Created by JFormDesigner on Sat Apr 09 16:13:32 CST 2022
 */

package com.tute.wjl.ui;

import java.awt.event.*;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
public class MyInfoFrame extends JFrame {
    private DataContext dataContext;
    private User thisUser;
    public MyInfoFrame() {
        initComponents();
    }

    public MyInfoFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
        thisUser = dataContext.getUser();
        account.setText(thisUser.getUserAccount());
        userName.setText(thisUser.getUserName());
        userClass.setText(thisUser.getUserClass());
        trueName.setText(thisUser.getTrueName());
        age.setText(""+thisUser.getAge());
        content.setText(thisUser.getContent());
        pwd1.setText(thisUser.getUserPwd());
        pwd2.setText(thisUser.getUserPwd());
        if(!dataContext.isTeacher()){
            isTeacherLabel.setText("学生");
        }
    }

    private void confirmMouseClicked(MouseEvent e) {
        Message message = dataContext.initMessage(Constants.USER);
        message.setToId(Constants.USER_UPDATE);
        thisUser.setUserClass(userClass.getText());
        thisUser.setAge(Integer.parseInt(age.getText()));
        thisUser.setTrueName(trueName.getText());
        thisUser.setUserName(userName.getText());
        thisUser.setContent(content.getText());
        if(!pwd1.getText().equals(pwd2.getText())){
            new ErrorTips("两次密码不一致");
        }else{
            thisUser.setUserPwd(pwd1.getText());
            message.setContent(thisUser);
            ClientApplication.send(message);
            this.dispose();
        }
    }

    private void cancelMouseClicked(MouseEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        accountLabel = new JLabel();
        label1 = new JLabel();
        userNameLabel2 = new JLabel();
        trueNameLabel = new JLabel();
        ageLabel = new JLabel();
        classLabel = new JLabel();
        contentLabel = new JLabel();
        isTeacherLabel = new JLabel();
        account = new JTextField();
        userName = new JTextField();
        trueName = new JTextField();
        age = new JTextField();
        userClass = new JTextField();
        content = new JTextField();
        confirm = new JButton();
        cancel = new JButton();
        contentLabel2 = new JLabel();
        contentLabel3 = new JLabel();
        pwd1 = new JPasswordField();
        pwd2 = new JPasswordField();

        //======== this ========
        setTitle("\u4e2a\u4eba\u4fe1\u606f");
        Container contentPane = getContentPane();

        //---- accountLabel ----
        accountLabel.setText("\u8d26\u53f7");
        accountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- label1 ----
        label1.setText("\u4e2a\u4eba\u4fe1\u606f");
        label1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));

        //---- userNameLabel2 ----
        userNameLabel2.setText("\u6635\u79f0");
        userNameLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- trueNameLabel ----
        trueNameLabel.setText("\u771f\u5b9e\u59d3\u540d");
        trueNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- ageLabel ----
        ageLabel.setText("\u5e74\u9f84");
        ageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- classLabel ----
        classLabel.setText("\u73ed\u7ea7");
        classLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- contentLabel ----
        contentLabel.setText("\u5907\u6ce8");
        contentLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- isTeacherLabel ----
        isTeacherLabel.setText("\u6559\u5e08");
        isTeacherLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        isTeacherLabel.setForeground(Color.blue);

        //---- account ----
        account.setEditable(false);

        //---- confirm ----
        confirm.setText("\u4fdd\u5b58");
        confirm.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmMouseClicked(e);
            }
        });

        //---- cancel ----
        cancel.setText("\u53d6\u6d88");
        cancel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelMouseClicked(e);
            }
        });

        //---- contentLabel2 ----
        contentLabel2.setText("\u5bc6\u7801");
        contentLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        //---- contentLabel3 ----
        contentLabel3.setText("\u518d\u6b21\u8f93\u5165");
        contentLabel3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(userNameLabel2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(isTeacherLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                        .addComponent(accountLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(account, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(ageLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(age, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(classLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(userClass, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(contentLabel3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(trueNameLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(trueName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pwd2, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(contentLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(contentLabel2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                    .addGap(36, 36, 36)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(content, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                        .addComponent(pwd1, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(confirm, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(68, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addComponent(isTeacherLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accountLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(account, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userNameLabel2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(trueNameLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(trueName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ageLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(age, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(classLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contentLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(content, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contentLabel2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pwd1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contentLabel3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pwd2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirm, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JLabel accountLabel;
    private JLabel label1;
    private JLabel userNameLabel2;
    private JLabel trueNameLabel;
    private JLabel ageLabel;
    private JLabel classLabel;
    private JLabel contentLabel;
    private JLabel isTeacherLabel;
    private JTextField account;
    private JTextField userName;
    private JTextField trueName;
    private JTextField age;
    private JTextField userClass;
    private JTextField content;
    private JButton confirm;
    private JButton cancel;
    private JLabel contentLabel2;
    private JLabel contentLabel3;
    private JPasswordField pwd1;
    private JPasswordField pwd2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
