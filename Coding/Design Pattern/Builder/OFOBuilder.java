package com.chuwa.learn.Builder;

public class OFOBuilder extends Builder{

    @Override
    public void buildFrame() {
        bike.setFrame("ofo");
    }

    @Override
    public void buildSeat() {
        bike.setSeat("Nilon");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
