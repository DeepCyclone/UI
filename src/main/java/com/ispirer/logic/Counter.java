package com.ispirer.logic;

public class Counter {
    private static int objects = 0;
        public Counter(){
            objects++;
        }
        public int count(){
            return objects;
        }

}
