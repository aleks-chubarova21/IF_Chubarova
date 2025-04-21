package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> cars = new ArrayList<>();

        cars.add(new Suzuki (" Swift", "Coupe", "Black", 150, 2004, false, false));
        cars.add(new Toyota (" Camry", "Sedan", "White", 140, 2004, false, true));
        cars.add(new BMW (" X5", "Hatchback", "Red", 150, 2005, false, false, 30));
        cars.add(new Audi (" A6", "Coupe", "Grey", 160, 2011, true, false));
        cars.add(new Mazda (" RX", "Sport", "Green", 170, 2002, true, false));
        cars.add(new Toyota (" RAV", "Sport", "Green", 170, 2011, true, false));
        cars.add(new Mazda ("Mazda RX2", "Sport", "Green", 170, 2002, true, false));
        cars.add(new BMW (" X6", "Sport", "Pink", 170, 2020, true, false, 60));
        cars.add(new Mazda (" RX", "Sport", "Green", 170, 2007, true, false));
        cars.add(new Audi (" A5", "Sport", "Green", 170, 2002, true, false));

        System.out.println("Полная информация:");

        System.out.println("________________");
        for (Car car : cars){
            car.printlnFullInfo();
            System.out.println("___________________");
        }
        System.out.println("________________");

        System.out.println("Изменение цвета");
        cars.get(0).changeColor("Yellow");cars.get(1).changeColor("Yellow");
        cars.get(2).changeColor("Yellow");cars.get(3).changeColor("Yellow");
        cars.get(4).changeColor("Yellow");cars.get(5).changeColor("Yellow");
        cars.get(6).changeColor("Green");cars.get(7).changeColor("Green");
        cars.get(8).changeColor("Green");cars.get(9).changeColor("Green");
        cars.get(9).changeColor("Green");cars.get(9).changeColor("Green");

        System.out.println("________________");

        for (Car car : cars) {
            car.changeColor("Green", "Red");
        }

        System.out.println("________________");

        for (Car car : cars) {
            System.out.println(car.getModel() + " цвет: " + car.getColor());
        }

        System.out.println("________________");

        System.out.println("Проверка года выпуска");
        CarUtils.printInfo(cars);

    }

}
