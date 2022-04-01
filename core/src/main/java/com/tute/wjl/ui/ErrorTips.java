/*
 * Created by JFormDesigner on Thu Mar 31 15:31:50 CST 2022
 */

package com.tute.wjl.ui;

import lombok.Data;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author sujia
 */
@Data
public class ErrorTips extends JFrame {
    public ErrorTips() {
        initComponents();
    }

    public ErrorTips(String errorMessage){
        initComponents();
        errorMsg.setText(errorMessage);
        setVisible(true);
    }

    private void click(MouseEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        scrollPane1 = new JScrollPane();
        errorMsg = new JTextArea();
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- errorMsg ----
            errorMsg.setEnabled(false);
            errorMsg.setCaretColor(Color.white);
            errorMsg.setBorder(null);
            errorMsg.setSelectedTextColor(Color.white);
            errorMsg.setText("hello");
            errorMsg.setEditable(false);
            scrollPane1.setViewportView(errorMsg);
        }

        //---- label1 ----
        label1.setText("\u51fa\u73b0\u9519\u8bef\uff01\u9519\u8bef\u4fe1\u606f\uff1a");

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(53, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 149, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                    .addGap(148, 148, 148))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(button1, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addGap(17, 17, 17))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JScrollPane scrollPane1;
    private JTextArea errorMsg;
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
