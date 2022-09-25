package com.poro.main;

import java.util.ArrayList;
import java.util.List;

public class BarrierPool {
    private static List<Barrier> pool = new ArrayList<>();     // control all the barrier
    public static final int barrierIniC = 16;               //initial barrier
    public static final int barrierFinC = 20;              //max barrier it could have

    static {
        for (int i = 0; i < barrierIniC; i++) {         //initialize barrier
            pool.add(new Barrier());
        }
    }


    public static Barrier getPool(){
        int size = pool.size();

        if(size > 0)                                //only get the barrier if there has one
            return pool.remove(size-1);
        else
            return new Barrier();                   // if no barrier left create a new barrier to give
    }

    public static void setPool(Barrier barrier){       //return the barrier to the pool
        if(pool.size() < barrierFinC) {
            pool.add(barrier);
        }
    }
}
