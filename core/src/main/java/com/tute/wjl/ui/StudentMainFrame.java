/*
 * Created by JFormDesigner on Fri Apr 01 11:52:55 CST 2022
 */

package com.tute.wjl.ui;

import com.mysql.jdbc.StringUtils;
import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.Course;
import com.tute.wjl.entity.Message;
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
public class StudentMainFrame extends JFrame {
    private DataContext dataContext;
    public StudentMainFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void showMyInfo(MouseEvent e) {
        new MyInfoFrame(dataContext).setVisible(true);
    }

    private void joinCourseMouseClicked(MouseEvent e) {
        Message message = dataContext.initMessage(Constants.ADD);
        // toId改为选中的课程-班级
        message.setToId(resList.getSelectedValue());
        ClientApplication.send(message);

    }

    private void searchButtonMouseClicked(MouseEvent e) {
        String searchContent = searchField.getText();
        if(StringUtils.isNullOrEmpty(searchContent)){
            new ErrorTips("搜索内容不可为空");
        }else{
            Message message = dataContext.initMessage(Constants.COURSE);
            message.setContent(searchContent);
            message.setToId(Constants.COURSE_SEARCH);
            ClientApplication.send(message);
        }
    }

    private void myCourseMouseClicked(MouseEvent e) {
        Message message = dataContext.initMessage(Constants.COURSE);
        message.setContent(dataContext.getUser().getUserClass());
        message.setToId(Constants.COURSE_CLASS);
        ClientApplication.send(message);
    }

    private void resListMouseClicked(MouseEvent e) {
        int index = resList.getSelectedIndex();
        Object obj = dataContext.getData().get(index);
        if(obj instanceof Course){
            courseClass.setText(((Course) obj).getCourseClass());
            courseContent.setText(((Course) obj).getCourseContent());
            courseHours.setText(""+((Course) obj).getCourseHours());
            courseName.setText(((Course) obj).getCourseName());
            courseTeacher.setText(((Course) obj).getCourseTeacher());
            courseTime.setText(((Course) obj).getCourseTime());
        }
    }

    private void exitMouseClicked(MouseEvent e) {
        new LoginFrame(dataContext).setVisible(true);
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        myCourse = new JButton();
        separator1 = new JSeparator();
        joinCourse = new JButton();
        myInfo = new JButton();
        exit = new JButton();
        panel1 = new JPanel();
        searchField = new JTextField();
        searchButton = new JButton();
        resScoll = new JScrollPane();
        resList = new JList<>();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        courseName = new JTextField();
        courseTeacher = new JTextField();
        courseClass = new JTextField();
        courseTime = new JTextField();
        courseHours = new JTextField();
        scrollPane1 = new JScrollPane();
        courseContent = new JTextArea();

        //======== this ========
        setVisible(true);
        setTitle("\u5728\u7ebf\u6559\u5b66\u7cfb\u7edf(\u5b66\u751f\u7aef)");
        Container contentPane = getContentPane();

        //---- myCourse ----
        myCourse.setText("\u6211\u7684\u8bfe\u7a0b");
        myCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myCourseMouseClicked(e);
            }
        });

        //---- joinCourse ----
        joinCourse.setText("\u52a0\u5165\u8bfe\u7a0b");
        joinCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                joinCourseMouseClicked(e);
            }
        });

        //---- myInfo ----
        myInfo.setText("\u6211\u7684\u4fe1\u606f");
        myInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showMyInfo(e);
            }
        });

        //---- exit ----
        exit.setText("\u9000\u51fa\u767b\u9646");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exitMouseClicked(e);
            }
        });

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );

            //---- searchButton ----
            searchButton.setText("\u67e5\u8be2");
            searchButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    searchButtonMouseClicked(e);
                }
            });

            //======== resScoll ========
            {

                //---- resList ----
                resList.setModel(new AbstractListModel<String>() {
                    String[] values = {
                        "\u8bfe\u7a0b\u540d-\u73ed\u7ea7"
                    };
                    @Override
                    public int getSize() { return values.length; }
                    @Override
                    public String getElementAt(int i) { return values[i]; }
                });
                resList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        resListMouseClicked(e);
                    }
                });
                resScoll.setViewportView(resList);
            }

            //---- label1 ----
            label1.setText("\u8bfe\u7a0b\u540d\u79f0");

            //---- label2 ----
            label2.setText("\u6388\u8bfe\u6559\u5e08");

            //---- label3 ----
            label3.setText("\u6388\u8bfe\u73ed\u7ea7");

            //---- label4 ----
            label4.setText("\u6388\u8bfe\u65f6\u95f4");

            //---- label5 ----
            label5.setText("\u8bfe\u65f6");

            //---- label6 ----
            label6.setText("\u8bfe\u7a0b\u4ecb\u7ecd");

            //---- courseName ----
            courseName.setEnabled(false);

            //---- courseTeacher ----
            courseTeacher.setEnabled(false);

            //---- courseClass ----
            courseClass.setEnabled(false);

            //---- courseTime ----
            courseTime.setEnabled(false);

            //---- courseHours ----
            courseHours.setEnabled(false);

            //======== scrollPane1 ========
            {
                scrollPane1.setEnabled(false);

                //---- courseContent ----
                courseContent.setEnabled(false);
                scrollPane1.setViewportView(courseContent);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                            .addComponent(resScoll, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label5)
                            .addComponent(label6))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(courseName)
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(courseTime, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(courseTeacher, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(courseClass, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(courseHours, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseTeacher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(scrollPane1)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resScoll, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                    .addComponent(joinCourse, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(myCourse, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                .addComponent(exit, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                            .addComponent(myInfo, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                        .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(myCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(joinCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addGap(97, 97, 97)
                            .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(myInfo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(exit, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(61, 61, 61))
        );
        setSize(635, 395);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JButton myCourse;
    private JSeparator separator1;
    private JButton joinCourse;
    private JButton myInfo;
    private JButton exit;
    private JPanel panel1;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane resScoll;
    private JList<String> resList;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField courseName;
    private JTextField courseTeacher;
    private JTextField courseClass;
    private JTextField courseTime;
    private JTextField courseHours;
    private JScrollPane scrollPane1;
    private JTextArea courseContent;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
