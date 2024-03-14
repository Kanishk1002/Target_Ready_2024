package com.target.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String address;

    @Override
    public String toString() {
        return "Person[" +
                "name=" + name +
                ",address=" + address + "]";
    }
}
