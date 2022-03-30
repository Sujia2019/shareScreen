package com.tute.wjl.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PicSend implements Runnable{
    private BufferedImage image;
    private Socket socket;
    private Robot robot;
//    private Message msg;
    private ObjectOutputStream oos;

    PicSend(Socket s){
        try {
            robot=new Robot();
            socket = s;
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rec = new Rectangle(size);
        try {
            while (!socket.isClosed()) {

                oos = new ObjectOutputStream(socket.getOutputStream());
                image = robot.createScreenCapture(rec);
                String filePath = "/home/xxx/桌面/pic/picture.jpg";
                FileOutputStream fops = new FileOutputStream(filePath);
                ImageIO.write(image, "jpg", fops);
                fops.flush();
                fops.close();
//                msg = new Message(filePath);
//                oos.writeObject(msg);
//                oos.flush();
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
