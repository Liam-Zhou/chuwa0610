package com.chuwa.learn.PublishSubscribe;

public class Client {
    public static void main(String[] args) {
        SubscribeSubject subject = new SubscribeSubject();

        subject.attach(new User("monkey"));
        subject.attach(new User("pig"));
        subject.attach(new User("wujing"));

        subject.notify("updating");
    }
}
