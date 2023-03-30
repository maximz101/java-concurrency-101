package com.novencia.jconcurrency.ch01.singleton;

public class B_LazySingleton {
    static B_LazySingleton instance;

    private B_LazySingleton() {
    }

    public static synchronized B_LazySingleton getInstance() {
        // Ex01 B todo implement your code here
        throw new UnsupportedOperationException();
    }
}
