package org.example;

public class Toyota extends Car{

    private boolean isHybrid;

    public Toyota(String model, String type, String color, int hp, int year, boolean isAutomatic, boolean isHybrid) {
        super("Toyota" + model, type, color, hp, year, isAutomatic);
        this.isHybrid = isHybrid;
    }

    @Override
    public void printlnEngineInfo() {
        System.out.println("Тип двигателя: Бензин");
        boolean isAllGrip = false;
        System.out.println("Полный привод: " + (isAllGrip ? "AllGrip" : "Нет"));
    }

}

