package com.chuwa.learn.PublishSubscribe;
import java.util.*;
public class SubscribeSubject implements Subject{

    private List<Observer> list = new ArrayList<>();
    @Override
    public void attach(Observer observer) {
        list.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : list) {
            observer.update(message);
        }
    }
}
