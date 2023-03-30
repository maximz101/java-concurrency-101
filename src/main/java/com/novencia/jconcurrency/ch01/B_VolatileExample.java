package com.novencia.jconcurrency.ch01;

import java.util.Scanner;

public class B_VolatileExample {
    // try changing this variable to volatile
    private static boolean running = true;

    public static void main(String[] args) {
        var t = new Thread(() -> {
            // thread may cache "running" variable
            // unless we mark it as volatile => hence it will always be read from main memory
            while (running) {
                System.out.println("running " + running);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();

        // wait for an input
        new Scanner(System.in).nextLine();

        running = false;

        new Scanner(System.in).nextLine();
    }
}
