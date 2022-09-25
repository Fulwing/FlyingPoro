package com.poro.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GameUtil {
    public static BufferedImage loadBImage(String path){
        try {
            return ImageIO.read(new FileInputStream(path));        //load image
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
