package com.novencia.jconcurrency.misc;

import java.util.Scanner;

/**
 * @author max
 * Check states in visual VM
 */
public class D_ThreadStates_Waiting {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                //Thread.sleep(10000); // "Wait" in visualVm
                // uncomment below line to see the difference
                //LockSupport.park(); // "Park" in VisualVm
                System.out.println("hello world");
                while (true) {
                    // todo
                    Thread.yield();
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
        t1.setName("t1");

        t1.start();
        Thread.sleep(60000);
        // interrupt
        t1.interrupt();
        new Scanner(System.in).next();
    }
}
