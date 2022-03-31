package com.tute.wjl.service;

import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.GzipUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PictureService {
    private BufferedImage image;
    private Robot robot;
    private Dimension size;
    private Rectangle rec;
    private FileOutputStream fops;
    private FileInputStream fins;
    private String url = "/Users/sujia/Desktop/picture.jpg";

    private static PictureService instance;
    private PictureService() throws Exception {
        robot=new Robot();
        size = Toolkit.getDefaultToolkit().getScreenSize();
        rec = new Rectangle(size);
        // 文件输出流存储位置
        fops = new FileOutputStream(url);
        // 文件输入流存储位置
        fins = new FileInputStream(url);
    }

    // 双检锁单例模型
    public static PictureService getInstance() throws Exception {
        if(instance==null) {//先判断是否为null 后上锁进行初始化
            synchronized (PictureService.class) {
                if (instance == null)//将对象上锁之后再次判断 是否有别的线程初始化了
                    instance = new PictureService();
            }
        }
        return instance;
    }

    // 截图并保存文件
    public void shotCutAndSavePicture() throws Exception {
        // 截屏
        image = robot.createScreenCapture(rec);
        // 保存文件
        ImageIO.write(image, "jpg", fops);
    }

    // 截图并发送文件
    public Message sendPicByte(Message message) throws Exception {
        shotCutAndSavePicture();
        byte[] picByte = new byte[fins.available()];
        System.out.println("截屏信息："+fins.available());
        int len = fins.read(picByte);
        // 压缩
        byte[] send = GzipUtils.gzip(picByte);
        System.out.println("压缩大小："+send.length);
        message.setFileContent(send);
        // 告知文件长度
        message.setLength(send.length);
        return message;
    }

    // 获取字节流转换为图片，设置到屏幕上
    public void setImage(byte[] data, JLabel jLabel){
        jLabel.setIcon(new ImageIcon(data));
    }

    // 关闭流
    public void close() throws IOException {
        fins.close();
        fops.close();
    }

    private class PicRecive implements Runnable{

        @Override
        public void run() {

        }
    }

}
