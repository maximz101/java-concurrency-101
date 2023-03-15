package com.novencia.jconcurrency.singleton;

public class MySingletonDoubleChecked {
    private static MySingletonDoubleChecked instance;

    private MySingletonDoubleChecked() {
    }

    public static MySingletonDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null) {
                    instance = new MySingletonDoubleChecked();
                }
            }
        }
        return instance;
    }
}
