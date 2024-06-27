package chuwa.com.behavior;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}