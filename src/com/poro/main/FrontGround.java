package com.poro.main;

import com.poro.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrontGround {
    private static final int cloudNum = 2;
    private List<Cloud> cloudList;
    private int speed = 1;

    private BufferedImage[] images;

    private Random random;


    public FrontGround(){                     //initialize
        cloudList = new ArrayList<>();
        images = new BufferedImage[cloudNum];

        for(int i = 0; i < cloudNum; i++){
            images[i] = GameUtil.loadBImage("Img/cloud" + i + ".png");
        }

        random = new Random();
    }

    public void draw(Graphics g){              // draw cloud
        logic();
        for(int i = 0; i < cloudList.size(); i++) {
            cloudList.get(i).draw(g);
        }
    }

    private void logic(){                     //control clouds nums
        if((int)(Math.random()*500) < 5){
            Cloud cloud = new Cloud(images[random.nextInt(cloudNum)], speed, 600, random.nextInt(0, 150));
            cloudList.add(cloud);
        }

        for (int i = 0; i < cloudList.size(); i++) {
            Cloud cloud = cloudList.get(i);
            if(cloud.isOutFrame()) {
                cloudList.remove(i);
                i--;
            }
        }
    }

}
