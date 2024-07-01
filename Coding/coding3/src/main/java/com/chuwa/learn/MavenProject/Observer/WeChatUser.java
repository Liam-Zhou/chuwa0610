package com.chuwa.learn.MavenProject.Observer;

public class WeChatUser implements Observer {

    private String username;

    public WeChatUser(String username) {
        this.username = username;
    }

    @Override
    public void update(String message) {
        System.out.println(username + ": " + message);
    }
}