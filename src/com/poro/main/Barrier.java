package com.poro.main;

import com.poro.util.Constant;
import com.poro.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {

    private Rectangle barrierRec;

    private boolean moving = true;

    private int barrierSpeed = 3; // barrier moving speed
    private static BufferedImage[] images;

    private boolean barrierStatus;        // is barrier outside the screen

    static {
        final int count = 3;
        images = new BufferedImage[count];

        for (int i = 0; i < count; i++) {                         //initialize image
            images[i] = GameUtil.loadBImage(Constant.barrImg[i]);
        }
    }

    private int x,y;                   //position
    private int w,h;                   //width and height

    private int type;
    public static final int typeUp = 0;         //type of the barrier
    public static final int typeBot = 1;
    public static final int typeHover = 2;
    public static final int typeMoving = 3;

    public static final int barrierW = images[0].getWidth();
    public static final int barrierH = images[0].getHeight();
    public static final int barrierTopW = images[1].getWidth();
    public static final int barrierTopH = images[1].getHeight();


    public Barrier(int x, int y, int h, int type) {
        this.x = x;
        this.y = y;
        this.w = barrierW;
        this.h = h;
        this.type = type;
    }
    public Barrier() {
        barrierRec = new Rectangle();
    }

    public void draw(Graphics g){           //draw barrier
        switch (type){
            case typeUp:
                drawTopB(g);
                break;

            case typeBot:
                drawBotB(g);
                break;

            case typeHover:
                drawMidB(g);
                break;

            case typeMoving:
                drawMovB(g);
                break;
        }
    }

    public void drawTopB(Graphics g){       //draw barrier from top
        int count = (h - barrierTopH)/barrierH+1;     //number of barrier need in the middle

        for(int i = 0; i < count; i++){
            g.drawImage(images[0], x, y+i*barrierH, null);     //draw middle part
        }

        int y = h - barrierTopH;
        g.drawImage(images[2], x-(barrierTopW-barrierW)/2, y, null);
        x -= barrierSpeed;
        if(x < -50)
            barrierStatus = false;

        barrierRect(g);
    }


    private void drawBotB(Graphics g){            //draw barrier from bot
        int count = h/barrierH+1;                   //number of barrier need in the middle

        for (int i = 0; i < count; i++) {
            g.drawImage(images[0], x, Constant.frameH-i*barrierH, null);     //draw middle part
        }

        int y = Constant.frameH-h;
        g.drawImage(images[1], x-(barrierTopW-barrierW)/2, y, null);
        x -= barrierSpeed;
        if(x < -50)
            barrierStatus = false;

        barrierRect(g);
    }

    private void drawMidB(Graphics g){                          // middle barrier
        int count = (h-barrierTopH)/barrierH;                   //number of barrier need in the middle

        g.drawImage(images[1],x, y,null);

        for (int i = 0; i < count; i++) {
            g.drawImage(images[0], x, y+barrierTopH+i*barrierH, null);     //draw top part
        }
        barrierRect(g);

        int yb = y+h-barrierTopH;
        g.drawImage(images[2], x, yb, null);
        x -= barrierSpeed;
        if(x < -50)
            barrierStatus = false;

    }

    private void drawMovB(Graphics g){                    //moving barrier
        int count = (h-barrierTopH)/barrierH;                   //number of barrier need in the middle

        g.drawImage(images[1],x, y,null);

        for (int i = 0; i < count; i++) {
            g.drawImage(images[0], x, y+barrierTopH+i*barrierH, null);     //draw top part
        }
        barrierRect(g);

        int yb = y+h-barrierTopH;
        g.drawImage(images[2], x, yb, null);
        x -= barrierSpeed;
        if(x < -50)
            barrierStatus = false;

        if(moving){
            y += 5;
            if(y >= 250)
                moving = false;
        }
        else if(!moving){
            y -= 5;
            if(y <= 100)
                moving = true;
        }

    }

    public void barrierRect(Graphics g){      //draw the rect for barrier
        int x1 = this.x;
        int y1 = this.y;
        int w1 = images[0].getWidth();

        //g.setColor(Color.BLUE);
        //g.drawRect(x1, y1, w1, h);
        setBarrierRec(x1, y1, w1, h);
    }

    public void setBarrierRec(int x, int y, int w, int h){         //collision data for the barrier
        barrierRec.x = x;
        barrierRec.y = y;
        barrierRec.width = w;
        barrierRec.height = h;
    }



    public boolean isInFrame(){    //when to draw next barrier
        return 600-x > 150;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isBarrierStatus() {
        return barrierStatus;
    }

    public void setBarrierStatus(boolean barrierStatus) {
        this.barrierStatus = barrierStatus;
    }

    public Rectangle getBarrierRec() {
        return barrierRec;
    }
}
