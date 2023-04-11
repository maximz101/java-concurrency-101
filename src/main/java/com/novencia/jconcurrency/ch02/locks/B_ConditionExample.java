package com.novencia.jconcurrency.ch02.locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class B_ConditionExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printHello() throws InterruptedException {
        lock.lock();
        try {
            System.out.print("Hello, ");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printWorld() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
            System.out.println("World!");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        B_ConditionExample example = new B_ConditionExample();
        Thread thread1 = new Thread(() -> {
            try {
                example.printHello();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                example.printWorld();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread1.start();

        new Scanner(System.in).nextLine();
    }
}
