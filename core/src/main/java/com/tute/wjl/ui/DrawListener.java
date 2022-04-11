package com.tute.wjl.ui;

import com.tute.wjl.ClientApplication;
import com.tute.wjl.context.DataContext;
import com.tute.wjl.entity.DrawOption;
import com.tute.wjl.entity.Message;
import com.tute.wjl.utils.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static com.tute.wjl.ui.DrawPanel.ox;
import static com.tute.wjl.ui.DrawPanel.oy;
import static com.tute.wjl.ui.DrawPanel.flag;

public class DrawListener implements MouseMotionListener, ActionListener {
    private DataContext dataContext;

    DrawListener(DataContext dataContext){
        this.dataContext = dataContext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "黑色":
                flag = 0;
                break;
            case "红色":
                flag = 1;
                break;
            case "蓝色":
                flag = 2;
                break;
            case "橡皮擦":
                flag = 3;
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        Container c = (Container) e.getSource();
//        Graphics g = c.getGraphics();
        Message message = dataContext.initMessage(Constants.DRAW);
        message.setToId(dataContext.getShareGroupName());
        DrawOption drawOption = new DrawOption();
        drawOption.setOx(ox);
        drawOption.setOy(oy);
        drawOption.setX(e.getX());
        drawOption.setY(e.getY());
        if (ox >= 0) {
            if(flag==0){
                drawOption.setColor(Color.black);
//                g.setColor(Color.black);
//                g.drawLine(ox, oy, e.getX(), e.getY());
            }
            else if(flag == 1) {
                drawOption.setColor(Color.red);
//                g.setColor(Color.red);
//                g.drawLine(ox, oy, e.getX(), e.getY());
            }
            else if(flag==2){
                drawOption.setColor(Color.blue);
//                g.setColor(Color.blue);
//                g.drawLine(ox, oy, e.getX(), e.getY());
            }
//            else{
//                  g.clearRect(ox,oy,10,10);
//            }
        }
        message.setContent(drawOption);
        ClientApplication.send(message);
        ox = e.getX();
        oy = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ox = -1;
        oy = -1;
    }
}
