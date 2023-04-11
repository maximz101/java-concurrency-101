package com.novencia.jconcurrency.ch02.synctools;

import java.util.concurrent.Semaphore;

/**
 * @author max
 */
public class SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        var t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 1 acquired the semaphore.");
                Thread.sleep(5000);
                semaphore.release();
                System.out.println("Thread 1 released the semaphore.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var t2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread 2 acquired the semaphore.");
                Thread.sleep(5000);
                semaphore.release();
                System.out.println("Thread 2 released the semaphore.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
