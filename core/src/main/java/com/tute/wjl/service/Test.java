package com.tute.wjl.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class Test {
    public static void main(String[] args) throws Exception {

        String string = "student(11)";
        String[] res = string.split("\\(");
        String r = res[1].substring(0,res[1].length()-1);
        System.out.println(r);
    }
}
