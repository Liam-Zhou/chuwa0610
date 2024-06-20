package com.chuwa.learn.Builder;

public class MobileBuilder extends  Builder{
    @Override
    public void buildFrame() {
        bike.setFrame("Carb");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Lether");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
