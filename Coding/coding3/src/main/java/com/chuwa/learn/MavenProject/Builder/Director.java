package com.chuwa.learn.MavenProject.Builder;

public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Bike constructor(){
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
