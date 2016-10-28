/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class TimeSleep extends Thread {

    private long intervall;
    private int time = 0;

    public TimeSleep(long intervall) {
        this.intervall = intervall * 1000;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(intervall);
            time=1;

        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
        System.out.println("Done");
    }
    public boolean done(){
        if(time == 1)
            return true;
        else
            return false;
    }

}
