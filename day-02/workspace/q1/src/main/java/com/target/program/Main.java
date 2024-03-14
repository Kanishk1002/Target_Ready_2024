package com.target.program;

public class Main {
    public static void main(String[] args) {

        Circle[] circles = {
                new Cylinder(12.34),
                new Cylinder(12.34, 10.0),
                new Cylinder(12.34, 10.0, "blue")
        };

        for(int i = 0; i < circles.length; i++) {
            System.out.printf("Area of Cylinder %d is: %.2f and Volume is %.2f\n", (i + 1), circles[i].getArea(), ((Cylinder)circles[i]).getVolume());
        }
    }
}