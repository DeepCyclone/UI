package com.ispirer.logic;

public class Counter {
    private static int objects = 0;
    private int currentNumber = 0;
    public Counter(){
        currentNumber = objects++;
    }
    public static int getObjects(){
        return objects;
    }
    public int getCurrentNumber(){
        return currentNumber;
    }
}
