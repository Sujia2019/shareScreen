package com.tute.wjl.ui;

import com.tute.wjl.context.DataContext;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author sujia
 */
public class DrawPanel extends JFrame {
    public static int ox, oy;
    public static int flag;
    private DataContext dataContext;
//    public DrawPanel(){
//        initComponents();
//        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//    }
    public DrawPanel(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        black = new JButton();
        red = new JButton();
        blue = new JButton();
        clear = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        red.addActionListener(new DrawListener(dataContext));
        blue.addActionListener(new DrawListener(dataContext));
        black.addActionListener(new DrawListener(dataContext));
        clear.addActionListener(new DrawListener(dataContext));
        contentPane.addMouseMotionListener(new DrawListener(dataContext));
        //---- black ----
        black.setText("\u9ed1\u8272");

        //---- red ----
        red.setText("\u7ea2\u8272");

        //---- blue ----
        blue.setText("\u84dd\u8272");

        //---- clear ----
        clear.setText("\u6a61\u76ae\u64e6");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(black, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(red, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(blue, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(clear, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(284, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(black, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(red, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(blue, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(clear, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(380, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JButton black;
    private JButton red;
    private JButton blue;
    private JButton clear;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
