package com.target.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Circle {

    private static final double PI = 3.14;
    private double radius = 1.0;
    private String color = "red";

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return PI*radius*radius;
    }

    @Override
    public String toString() {
        return "Circle[" +
                "radius=" + radius +
                ",color=" + color + "]";
    }
}
