package edu.school21.reflection.models.car;

import java.util.StringJoiner;

public class Car {
    private String manufacturer;
    private String model;
    private int year;

    public Car() {
        manufacturer = "unknown";
        model = "unknown";
        year = 1900;
    }

    public Car(String manufacturer, String model, int year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }

    public int gettingOlder(int value) {
        year += value;
        return year;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class . getSimpleName() + "[", "]")
                .add("manufacturer='" + manufacturer + "'")
                .add("model='" + model + "'")
                .add("year=" + year)
                .toString();
    }
}
