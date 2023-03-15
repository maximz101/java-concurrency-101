package com.novencia.jconcurrency.misc;

/**
 * @author max
 */
public class C_FieldVisibility {
    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        var sharedInstance = new C_FieldVisibility();

        // writer thread
        var t1 = new Thread(() -> {
            while (true) {
                sharedInstance.flag = !sharedInstance.flag;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t1.start();

        //reader thread
        var t2 = new Thread(() -> {
            while (true) {
                if (sharedInstance.flag) {
                    System.out.println("reader thread : flag=" + sharedInstance.flag);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        t2.start();

        t1.join();
        t2.join();
    }

}
