package com.novencia.jconcurrency.ch02.threadlocal;

import java.util.Scanner;

public class ThreadLocalExample {
    private static final ThreadLocal<Integer> threadLocalVar = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int value = threadLocalVar.get();
            threadLocalVar.set(value + 1);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocalVar.get());
        };

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        new Scanner(System.in).nextLine();
    }
}
