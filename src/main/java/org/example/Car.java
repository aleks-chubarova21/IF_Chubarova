package org.example;

public abstract class Car {
    protected String model;
    protected String type;
    protected String color;
    protected int hp;
    protected int year;
    protected boolean isAutomatic;

    public Car(String model, String type, String color, int hp, int year, boolean isAutomatic) {
        this.model = model;
        this.type = type;
        this.color = color;
        this.hp = hp;
        this.year = year;
        this.isAutomatic = isAutomatic;
    }

    public void printlnFullInfo(){
        System.out.println("Модель: "  + model);
        System.out.println("Тип : "  + type);
        System.out.println("Цвет: "  + color);
        System.out.println("Мощность: "  + hp);
        System.out.println("Год выпуска: "  + year);
        System.out.println("Коробка передач: "  + (isAutomatic ? "Автомат" : "Механика"));
        printlnEngineInfo();

    }


    public void changeColor(String newColor) {
        System.out.println("Изменение цвета " + model + " с " + color + " на " + newColor);
        this.color = newColor;

    }
    public void changeColor(String oldColor, String newColor) {
        if (this.color.equalsIgnoreCase(oldColor)) {
            System.out.println("Меняем цвет " + this.model + " с " + oldColor + " на " + newColor);
            this.color = newColor;
        }
    }

    public void checkYear(){
        if (year > 2006){
            System.out.println(model + " (" + year + "): Современный авто");
        }
        else {
            System.out.println(model + " (" + year + "): Устаревший авто");
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public abstract void printlnEngineInfo();

}