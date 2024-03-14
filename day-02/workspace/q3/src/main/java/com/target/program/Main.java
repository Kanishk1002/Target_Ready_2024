package com.target.program;

public class Main {
    public static void main(String[] args) {

        Shape[] shapes = {
                new Circle(2.0),
                new Rectangle(6.5, 5.8),
                new Rectangle(4.7, 3.0),
                new Square(4.5),
                new Circle(8.0),
                new Square(9.0),
                new Rectangle(6.0, 12.0),
                new Circle(5.6),
                new Square(7.3),
                new Rectangle(6.1, 7.3),
        };

        for(int i = 0; i < shapes.length; i++) {
            System.out.printf("Shape[%d]: Perimeter=%.2f Area=%.2f\n", i + 1, shapes[i].getPerimeter(), shapes[i].getArea());
            System.out.println(shapes[i].toString() + "\n");
        }
    }
}