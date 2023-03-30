package com.novencia.jconcurrency.ch01.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class E_EnumSingletonTest {


    @RepeatedTest(1000)
    public void should_have_single_instance() {
        var s = Collections.synchronizedSet(new HashSet<E_EnumSingleton>());
        Runnable runnable = () -> {
            E_EnumSingleton instance = E_EnumSingleton.getInstance();
            s.add(instance);
        };

        List<Thread> listThread = createThreads(32, runnable);
        listThread.forEach(Thread::start);
        listThread.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Assertions.assertEquals(1, s.size());
    }

    private List<Thread> createThreads(int i, Runnable runnable) {
        return IntStream.range(0, i).mapToObj(unused -> new Thread(runnable)).collect(Collectors.toList());
    }
}