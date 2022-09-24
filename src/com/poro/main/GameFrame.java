package com.poro.main;

import static com.poro.util.Constant.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
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
    }
}
