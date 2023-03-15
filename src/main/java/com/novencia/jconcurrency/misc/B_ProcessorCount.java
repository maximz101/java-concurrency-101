package com.novencia.jconcurrency.misc;

/**
 * @author max
 * run with -XX:ActiveProcessorCount=1
 */
public class B_ProcessorCount {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
