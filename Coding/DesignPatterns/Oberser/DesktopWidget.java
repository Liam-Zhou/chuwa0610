package com.chuwa.exercise.DesignPatterns.Oberser;

/**
 * @Version
 * @Create 06/20/2024 - 15:20
 * @Author xiao
 * @Description TODO
 */

class DesktopWidget implements Observer {
 @Override
 public void update(float temperature) {
  System.out.println("Desktop Widget: Current temperature is " + temperature + "°C");
 }
}