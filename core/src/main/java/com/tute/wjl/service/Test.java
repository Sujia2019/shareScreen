package com.tute.wjl.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        // 截屏
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rec = new Rectangle(size);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(rec);
        for(int i=0;i<10;i++){
            String filePath = "/Users/sujia/Desktop/picture"+i+".jpg";
            // 创建文件io流
            FileOutputStream fops = new FileOutputStream(filePath);
            // 保存文件
            ImageIO.write(image, "jpg", fops);
        }

    }
}
