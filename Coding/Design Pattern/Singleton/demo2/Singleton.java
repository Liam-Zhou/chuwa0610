package com.chuwa.learn.Singleton.demo2;

public class Singleton {
    private Singleton() {}
    private static Singleton instance;
    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
