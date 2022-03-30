package com.tute.wjl;

import javax.swing.*;
import java.io.ObjectOutputStream;

public class ClientFrame extends JFrame{
    private JFrame jFrame = new JFrame();
    private ImageIcon icon;
    private ObjectOutputStream ous ;

    ClientFrame() {
        jFrame.setSize(1300, 700);
        jFrame.setLocation(100, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        // 这里是不需要启线程去监听的，直接传过来反序列化流就可以，
        // awt里的监听会自动监听到控制机，然后将监听事件
        // 序列化输入进去 在被控制那边写循环等待事件即可
        // 问题一直卡在这里了，逻辑没有搞清楚
        // this.ous = os;
    }

    public static void main(String[] args) {

    }
}
