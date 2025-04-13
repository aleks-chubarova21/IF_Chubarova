package org.example;

public class Mazda extends Car{
    private boolean isHybrid;

    public Mazda(String model, String type, String color, int hp, int year, boolean isAutomatic, boolean isHybrid) {
        super(model, type, color, hp, year, isAutomatic);
        this.isHybrid = isHybrid;
    }

    @Override
    public void printlnEngineInfo() {
        System.out.println("Тип двигателя: " + (isHybrid ? "Гибрид" : "Бензин"));
    }


}