package com.ispirer.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private static final AtomicInteger objects = new AtomicInteger(0);
    private volatile int currentNumber = 0;
    public Counter(){
        currentNumber = objects.getAndIncrement();
    }
    public static int getObjects(){
        return objects.get();
    }
    public int getCurrentNumber(){
        return currentNumber;
    }
}
