package com.chuwa.learn.MavenProject.Factory;

public abstract class Coffee {

    public abstract String getName();

    public void addSugar(){
        System.out.println("added sugar");
    }

    public void addMilk(){
        System.out.println("added milk");
    }
}