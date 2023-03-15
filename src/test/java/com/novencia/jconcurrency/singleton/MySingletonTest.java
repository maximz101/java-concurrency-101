package com.novencia.jconcurrency.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MySingletonTest {

    @BeforeEach
    void dod() throws InterruptedException {
        MySingleton.instance = null;
        Thread.sleep(1);
    }

    @RepeatedTest(1000)
    public void should_have_single_instance() throws InterruptedException {
        Set<MySingleton> s = Collections.synchronizedSet(new HashSet<>());
        Runnable runnable = () -> {
            MySingleton instance = MySingleton.getInstance();
            s.add(instance);
        };

        List<Thread> listThread = createThreads(32, runnable);
        listThread.forEach(Thread::start);
        listThread.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Assertions.assertEquals(1, s.size());
    }

    private List<Thread> createThreads(int i, Runnable runnable) {
        return IntStream.range(0, i).mapToObj(unused -> new Thread(runnable)).collect(Collectors.toList());
    }
}