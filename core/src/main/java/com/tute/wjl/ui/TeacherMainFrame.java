/*
 * Created by JFormDesigner on Fri Apr 01 14:47:46 CST 2022
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
public class TeacherMainFrame extends JFrame {
    private DataContext dataContext;
    public TeacherMainFrame(DataContext dataContext) {
        this.dataContext = dataContext;
        initComponents();
    }

    private void showMyInfo(MouseEvent e) {
        new MyInfoFrame(dataContext).setVisible(true);
    }

    private void startCourseMouseClicked(MouseEvent e) {
        // 开始上课
        Message message = dataContext.initMessage(Constants.CREATE);
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
        message.setContent(dataContext.getUser().getUserAccount());
        message.setToId(Constants.COURSE_TEACHER);
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

    private void deleteCourseMouseClicked(MouseEvent e) {
        int index = resList.getSelectedIndex();
        Object obj = dataContext.getData().get(index);
        if(obj instanceof Course){
            int id = ((Course) obj).getId();
            Message message = dataContext.initMessage(Constants.COURSE);
            message.setContent(id);
            message.setToId(Constants.COURSE_DELETE);
            ClientApplication.send(message);
        }
    }

    private void addNewCourseMouseClicked(MouseEvent e) {
        Course course = new Course();
        initCourse(course);
        Message message = dataContext.initMessage(Constants.COURSE);
        message.setContent(course);
        message.setToId(Constants.COURSE_NEW);
        ClientApplication.send(message);
    }

    private void updateCourseMouseClicked(MouseEvent e) {
        int index = resList.getSelectedIndex();
        Course course = (Course) dataContext.getData().get(index);
        initCourse(course);
        Message message = dataContext.initMessage(Constants.COURSE);
        message.setContent(course);
        message.setToId(Constants.COURSE_UPDATE);
        ClientApplication.send(message);
    }

    private void initCourse(Course course) {
        course.setCourseName(courseName.getText());
        course.setCourseTime(courseTime.getText());
        course.setCourseContent(courseContent.getText());
        course.setCourseHours(Integer.parseInt(courseHours.getText()));
        course.setCourseTeacher(dataContext.getUser().getTrueName());
        course.setCourseClass(courseClass.getText());
    }

    private void updateInfoMouseClicked(MouseEvent e) {
        new LoginFrame(dataContext).setVisible(true);
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - sujia
        myCourse = new JButton();
        separator1 = new JSeparator();
        startCourse = new JButton();
        addNewCourse = new JButton();
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
        updateCourse = new JButton();
        deleteCourse = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u5728\u7ebf\u6559\u5b66\u7cfb\u7edf(\u6559\u5e08\u7aef)");
        Container contentPane = getContentPane();

        //---- myCourse ----
        myCourse.setText("\u6211\u7684\u8bfe\u7a0b");
        myCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myCourseMouseClicked(e);
            }
        });

        //---- startCourse ----
        startCourse.setText("\u5f00\u59cb\u4e0a\u8bfe");
        startCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startCourseMouseClicked(e);
            }
        });

        //---- addNewCourse ----
        addNewCourse.setText("\u6dfb\u52a0\u8bfe\u7a0b");
        addNewCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNewCourseMouseClicked(e);
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
                updateInfoMouseClicked(e);
            }
        });

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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
                resList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

            //======== scrollPane1 ========
            {
                scrollPane1.setEnabled(false);
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
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(courseTime, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(courseTeacher, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(courseClass, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(courseHours, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(courseName, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
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
                                .addComponent(resScoll, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //---- updateCourse ----
        updateCourse.setText("\u7f16\u8f91\u8bfe\u7a0b");
        updateCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateCourseMouseClicked(e);
            }
        });

        //---- deleteCourse ----
        deleteCourse.setText("\u5220\u9664\u8bfe\u7a0b");
        deleteCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCourseMouseClicked(e);
            }
        });

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
                                .addComponent(myCourse, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addComponent(startCourse, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                            .addComponent(addNewCourse, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                        .addComponent(updateCourse, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteCourse, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exit, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(myInfo, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(myCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addNewCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(updateCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteCourse, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(myInfo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(exit, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(61, 61, 61))
        );
        setSize(645, 415);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public JButton getMyCourse() {
        return myCourse;
    }

    public void setMyCourse(JButton myCourse) {
        this.myCourse = myCourse;
    }

    public JButton getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(JButton startCourse) {
        this.startCourse = startCourse;
    }

    public JButton getAddNewCourse() {
        return addNewCourse;
    }

    public void setAddNewCourse(JButton addNewCourse) {
        this.addNewCourse = addNewCourse;
    }

    public JButton getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(JButton myInfo) {
        this.myInfo = myInfo;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JList<String> getResList() {
        return resList;
    }

    public void setResList(JList<String> resList) {
        this.resList = resList;
    }

    public JTextField getCourseName() {
        return courseName;
    }

    public void setCourseName(JTextField courseName) {
        this.courseName = courseName;
    }

    public JTextField getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(JTextField courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public JTextField getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(JTextField courseClass) {
        this.courseClass = courseClass;
    }

    public JTextField getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(JTextField courseTime) {
        this.courseTime = courseTime;
    }

    public JTextField getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(JTextField courseHours) {
        this.courseHours = courseHours;
    }

    public JTextArea getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(JTextArea courseContent) {
        this.courseContent = courseContent;
    }

    public JButton getUpdateCourse() {
        return updateCourse;
    }

    public void setUpdateCourse(JButton updateCourse) {
        this.updateCourse = updateCourse;
    }

    public JButton getDeleteCourse() {
        return deleteCourse;
    }

    public void setDeleteCourse(JButton deleteCourse) {
        this.deleteCourse = deleteCourse;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - sujia
    private JButton myCourse;
    private JSeparator separator1;
    private JButton startCourse;
    private JButton addNewCourse;
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
    private JButton updateCourse;
    private JButton deleteCourse;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
