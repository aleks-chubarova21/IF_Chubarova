package org.example;

public class BMW extends Car{
    private final int batteryCapacity;
    private boolean isHybrid;

    public BMW(String model, String type, String color, int hp, int year, boolean isAutomatic, boolean isHybrid, int batteryCapacity) {
        super(model, type, color, hp, year, isAutomatic);
        this.isHybrid = isHybrid;
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void printlnEngineInfo() {
        System.out.println("Тип двигателя: Электрический");
        System.out.println("Емкость батареи: " + batteryCapacity);
    }


}
