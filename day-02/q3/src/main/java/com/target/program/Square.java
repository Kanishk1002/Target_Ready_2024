package com.target.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Square extends Rectangle {

    private double side = 1.0;

    public Square(double side, String color, boolean filled) {
        super(color, filled);
        this.side = side;
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        this.side = width;
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
        this.side = length;
    }

    @Override
    public String toString() {
        return "A Square with side=" + side + ", which is subclass of " + super.toString();
    }
}
