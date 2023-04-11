package com.novencia.jconcurrency.ch02.locks;

import java.util.concurrent.locks.StampedLock;

/**
 * @author max
 */
public class C_StampedLockExample {
    private double x, y;
    private final StampedLock lock = new StampedLock();

    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) {
        C_StampedLockExample example = new C_StampedLockExample();
        example.move(3, 4);

        Thread writerThread = new Thread(() -> example.move(5, 6));

        Thread readerThread = new Thread(() -> {
            double distance = example.distanceFromOrigin();
            System.out.println("Distance from origin: " + distance);
        });

        writerThread.start();
        readerThread.start();
    }
}
