package com.poro.main;

import com.poro.util.Constant;
import com.poro.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class BackGround {
    private BufferedImage bkimg;     //game pictures
    private BufferedImage backG;

    public BackGround(){
        bkimg = GameUtil.loadBImage(Constant.birdBackGImage);
        backG = GameUtil.loadBImage(Constant.backGroundImage);                    //image path
    }


    public void draw(Graphics g){                //draw pictures
        //fill background color
        g.drawImage(backG, 0, 0, null);
        g.setColor(Color.BLACK );


        //get wid and hig
        int h = bkimg.getHeight();
        int w = bkimg.getWidth();

        int count = Constant.frameW/w+1;  // loop time
        for(int i = 0; i < count; i++){
            g.drawImage(bkimg, w*i, Constant.frameH-h, null);
        }
    }
}
