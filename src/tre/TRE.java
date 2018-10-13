/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tre;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cole
 */
public class TRE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int howManyCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(howManyCores);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(new task(i));
        }

        threadPool.shutdown();
    }

    static class task implements Runnable {

        Random rand = new Random();
        int loopCount;
        int time;

        public task(int loopCount) {
            this.loopCount = loopCount;
            time = rand.nextInt(500);
        }

        public void run() {

            try {
                System.out.println(Thread.currentThread().getName() + " Loop count: " + loopCount + " is sleeping for " + time);
                Thread.sleep(time);                
                System.out.println(Thread.currentThread().getName() + " Loop count: " + loopCount + " is awake");
            } catch (InterruptedException ex) {
                Logger.getLogger(TRE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
