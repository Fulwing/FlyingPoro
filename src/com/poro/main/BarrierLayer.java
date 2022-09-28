package com.poro.main;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarrierLayer {

    private Timer timer;

    private int highestT;

    private Random random = new Random();
    private List<Barrier> barriers;

    public BarrierLayer(){
        barriers = new ArrayList<>();
        timer = new Timer();
    }

    public void draw(Graphics g, Poro poro){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if(barrier.isBarrierStatus())
                barrier.draw(g);
            else {
                Barrier remove = barriers.remove(i);
                BarrierPool.setPool(remove);
                i--;
            }

        }
        poroCollision(poro);
        logic(g);
    }

    public void logic(Graphics g){
        if (barriers.size() == 0){
            ranD();
            timer.startTimer();
            insert(600, 0, numTop, 0);
            insert(600, 500-numBot, numBot, 1);
        }
        else {
            long differT = timer.timeDiff();
            g.setFont(new Font("Arial", 1, 20));
            g.setColor(new Color(255, 102, 102, 255));
            g.drawString("Timer: " + differT + " s", 30, 50);           // how much time you insist in game

            highestT = getHTime();
            if(differT <= highestT){
                g.drawString("Longest survival: " + highestT + " s", 200, 50);
            }
            else {
                setHTime(String.valueOf(differT));
                g.drawString("Longest survival: " + getHTime() + " s", 200, 50);
            }
            Barrier last = barriers.get(barriers.size() - 1);
            if(last.isInFrame()){
                ranD();
                if(num < 50){
                    insert(600, 32, 440,2);           //middle barrier
                }
                else if(num > 450){
                    insert(600, 125, 200, 3);     //moving barrier
                }
                else {
                    insert(600, 0, numTop, 0);
                    insert(600, 500 - numBot, numBot, 1);     //stable barrier
                }
            }
        }
    }

    File file = new File("Highest time.txt");          //record highest rank

    public int getHTime(){                                                   //get the time form file
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int num = 0;
        try {
            num = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return num;
    }

    public void setHTime(String s){                    //set the highest rank
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int x, int y, int num, int type){
        Barrier top = BarrierPool.getPool();
        top.setX(x);
        top.setY(y);
        top.setH(num);
        top.setType(type);
        top.setBarrierStatus(true);
        barriers.add(top);
    }



    private int numTop;      //random top height
    private int numBot;       //random bot height

    private int num;           //

    public void ranD(){
        numTop = random.nextInt(400)+100;
        numBot = random.nextInt(400)+100;
        num = random.nextInt(500);

        if(numTop + numBot > 450){
            ranD();
        }
    }

    public boolean poroCollision(Poro poro){           // test to see if poro collide with barrier
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if(barrier.getBarrierRec().intersects(poro.getPoroRec()))
                poro.isPoroAlive = false;
        }
        return false;
    }


    public void restartG(){       // empty all the barriers
        barriers.clear();
    }


}
