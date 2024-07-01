package com.chuwa.learn.MavenProject.Observer;

public class Client {
    public static void main(String[] args) {

        SubscriptionSubject subject = new SubscriptionSubject();

        subject.attach(new WeChatUser("user1"));
        subject.attach(new WeChatUser("user2"));
        subject.attach(new WeChatUser("user3"));

        subject.notify("There's an update!");
    }
}