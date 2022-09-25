package com.poro.main;

import static com.poro.util.Constant.*;
import com.poro.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Poro {

    public boolean isPoroAlive = true; // true == poro is still alive     false == poro is dead :/

    private Rectangle poroRec;             //poro size using for detect collision

    private int acceleration;
    private BufferedImage[] images;
    public static final int PoroICount = 3;    // get poro image


    //Poro status
    private int state;  // 0 == flat flying   1 == flying up   2 == flying down
    public static final int stateNormal = 0;
    public static final int stateUp = 1;
    public static final int stateDown = 2;

    //poro position
    private int x = 200, y = 200;


    //poro movement
    private boolean up = false, down = false;
    private int speed = 4;                       // speed of poro



    public Poro(){                                     //initialize
        images = new BufferedImage[PoroICount];
        for(int i = 0; i < PoroICount; i++){
            images[i] =GameUtil.loadBImage(poroImg[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        poroRec = new Rectangle(w, h);
    }


    //draw Poro
    public void draw(Graphics g){
        flyLogic();
        g.drawImage(images[state], x, y, null );
        //g.drawRect(x, y, poroRec.width, (int)poroRec.getHeight());
        poroRec.x = this.x;
        poroRec.y = this.y;
    }

    //control Poro
    public void flyLogic(){
        if(up) {
            acceleration--;
            y += acceleration;
            y -= speed;
            if(acceleration < -10 )
                acceleration = -10;
            if(y < 20) {
                y = 20;
                acceleration = 0;
            }
        }
        if(!up) {
            acceleration++;
            y += acceleration;
            if(acceleration > 10)
                acceleration = 10;
            if(y > 475) {
                y = 475;
                acceleration = 0;
            }
        }

    }

    public void fly(int fly){
        switch (fly){
            case 1:
                state = 1;
                up = true;
                break;

            case 5:
                state = 2;
                up = false;
                break;
        }
    }

    public Rectangle getPoroRec() {
        return poroRec;
    }


    public void restartPoro(){
        isPoroAlive = true;
        x = 200;
        y = 200;
    }
}
