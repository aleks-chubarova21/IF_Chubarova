package org.example;
import java.util.List;


public class CarUtils {
    public static void printInfo(List<Car> autos) {
        for (Car car : autos) {
            if (car.getYear() > 2006) {
                System.out.println(car.getModel() + " (" + car.getYear() + "): Современный авто");
            } else {
                System.out.println(car.getModel() + " (" + car.getYear() + "): Устаревший авто");
            }
        }
    }
}
