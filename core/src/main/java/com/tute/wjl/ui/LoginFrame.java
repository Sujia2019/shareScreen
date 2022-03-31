/*
 * Created by JFormDesigner on Thu Mar 31 12:49:21 CST 2022
 */

package com.tute.wjl.ui;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Message;
import com.tute.wjl.entity.User;
import com.tute.wjl.utils.Constants;
import lombok.Data;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
@Data
public class LoginFrame extends JFrame {
    private boolean isTeacher = false;
    private DataContext dataContext;
    public LoginFrame(){
        initComponents();
    }
    public LoginFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void doLogin(MouseEvent e) {
        User user = new User();
        user.setUserAccount(this.accountField.getText());
        user.setUserPwd(this.pwdField.getText());
        Message message = new Message();
        message.setFromId(user.getUserAccount());
        message.setMessageType(Constants.USER);
        message.setToId(Constants.LOGIN);
        if(this.radioTeacher.isSelected()){
            user.setTeacher(true);
        }
        message.setContent(user);
        try {
            ClientApplication.send(message);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // TODO add your code here
    }

    private void doRegister(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        label1 = new JLabel();
        label2 = new JLabel();
        accountField = new JTextField();
        pwdField = new JTextField();
        loginButton = new JButton();
        button2 = new JButton();
        label3 = new JLabel();
        textField3 = new JTextField();
        radioStudent = new JRadioButton();
        radioTeacher = new JRadioButton();

        //======== this ========
        setBackground(new Color(204, 255, 255));
        setTitle("\u5728\u7ebf\u6559\u5b66\u7cfb\u7edf");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u8d26\u53f7");
        label1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        label2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        //---- loginButton ----
        loginButton.setText("\u767b\u9646");
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doLogin(e);
            }
        });

        //---- button2 ----
        button2.setText("\u6ce8\u518c");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doRegister(e);
            }
        });

        //---- label3 ----
        label3.setText("\u518d\u6b21\u8f93\u5165");
        label3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        //---- radioStudent ----
        radioStudent.setText("\u5b66\u751f");
        radioStudent.setSelected(true);

        //---- radioTeacher ----
        radioTeacher.setText("\u6559\u5e08");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(accountField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(radioStudent))
                                            .addGap(77, 77, 77)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(radioTeacher)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(132, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(accountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(pwdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radioStudent)
                        .addComponent(radioTeacher))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioStudent);
        buttonGroup1.add(radioTeacher);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JLabel label1;
    private JLabel label2;
    private JTextField accountField;
    private JTextField pwdField;
    private JButton loginButton;
    private JButton button2;
    private JLabel label3;
    private JTextField textField3;
    private JRadioButton radioStudent;
    private JRadioButton radioTeacher;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
