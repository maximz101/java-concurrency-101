package com.novencia.jconcurrency.misc;

import java.lang.runtime.ObjectMethods;
import java.util.concurrent.locks.LockSupport;

public class D_ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        var obj = new Object();
        Thread t1 = new Thread(() -> {
            try {
                // Thread.sleep(5000);
                //LockSupport.park();
                synchronized (obj) {
                    obj.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.setName("t1");
        t1.start();

        Thread.sleep(5000);
        synchronized (obj){
            obj.notifyAll();
        }
        t1.join();
        Thread.sleep(500000);
    }
}
