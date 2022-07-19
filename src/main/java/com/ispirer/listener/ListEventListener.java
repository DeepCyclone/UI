package com.ispirer.listener;

public class ListEventListener implements EventListener{
    @Override
    public void update(String eventType) {
        System.out.println("List action:"+eventType);
    }
}
