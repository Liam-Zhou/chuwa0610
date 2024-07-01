package com.chuwa.learn.MavenProject.Builder;

public class MobileBuilder extends Builder{

    @Override
    public void buildFrame() {
        bike.setFrame("Carbon Fiber");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Leather");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}