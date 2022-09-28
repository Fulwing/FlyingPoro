package com.poro.main;

import static com.poro.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.security.Key;

public class GameFrame extends Frame {
    private BackGround backGround; // instantiate background

    private Poro poro; //instantiate Poro

    private FrontGround frontGround;  //instantiate FrontGround

    private BarrierLayer barrierLayer; //instantiate BarrierLayer


    //saved picture's pic
    private BufferedImage bufferedImage = new BufferedImage(frameW, frameH, BufferedImage.TYPE_4BYTE_ABGR);

    public GameFrame(){
        setVisible(true);
        setSize(frameW, frameH);          //initial values
        setTitle(frameT);
        setLocation(frameX, frameY);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);                         // exit window
            }
        });

        initGameImg();  //Initiate game

        new run().start();


        //catch keyboard
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minus(e);
            }
        });
    }

    public void initGameImg(){                      //method for initialize game
        backGround = new BackGround();
        poro = new Poro();
        frontGround = new FrontGround();
        barrierLayer = new BarrierLayer();
    }

    class run extends Thread{
        @Override
        public void run() {
            while(true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Graphics g) {       // all picture are drawn from here
        if(poro.isPoroAlive) {               //only draw when poro is alive
            Graphics graphics = bufferedImage.getGraphics();           //double buffer

            backGround.draw(graphics);
            frontGround.draw(graphics);
            poro.draw(graphics);
            barrierLayer.draw(graphics, poro);

            g.drawImage(bufferedImage, 0, 0, null);
        }
        else {
            String gameStatus = "Game over";
            g.setColor(new Color(255, 153, 51, 255));
            g.setFont(new Font("Arial", 1, 60));
            g.drawString(gameStatus, 200, 250);

            String reset = "Press space to restart game";
            g.setColor(new Color(255, 102, 102, 255));
            g.setFont(new Font("Arial", 1, 20));
            g.drawString(reset, 200, 300);
        }
    }


    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:          //press button
                poro.fly(1);
                break;

            case KeyEvent.VK_SPACE:
                if(poro.isPoroAlive == false)       //restart game with space is game end
                    restart();
                break;

        }
    }


    public void minus(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:          //let go button
                poro.fly(5);
                break;
        }
    }

    public void restart(){
        barrierLayer.restartG();             //restart the game
        poro.restartPoro();
    }


}
