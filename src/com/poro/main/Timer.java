package com.poro.main;

import java.util.SplittableRandom;

public class Timer {
    private long startT;
    private long endT;

    private long timeDiff;

    public Timer(){

    }

    public void startTimer(){
        startT = System.currentTimeMillis();
    }

    public long timeDiff(){
        endT = System.currentTimeMillis();
        return timeDiff = (endT - startT)/1000;
    }
}
