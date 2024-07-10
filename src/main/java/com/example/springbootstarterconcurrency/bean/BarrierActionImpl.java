package com.example.springbootstarterconcurrency.bean;

public class BarrierActionImpl implements BarrierAction {

    @Override
    public void action() {
        System.out.printf("Барьер сработал.");
    }

}
