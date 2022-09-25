package com.poro.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud {
    //cloud image

    private BufferedImage image;
    private int speed;
    private int x, y;

    public Cloud(BufferedImage image, int speed, int x, int y) {
        this.image = image;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }


    public void draw(Graphics g){
        x -= speed;
        g.drawImage(image, x, y, null);
    }

    public boolean isOutFrame(){          // see if the cloud is out of the frame
        if(x < -100)
            return true;
        return false;
    }
}
