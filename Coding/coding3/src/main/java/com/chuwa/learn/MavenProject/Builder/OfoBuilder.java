package com.chuwa.learn.MavenProject.Builder;

public class OfoBuilder extends Builder {
    @Override
    public void buildFrame() {
        bike.setFrame("aluminum");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("rubber");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}