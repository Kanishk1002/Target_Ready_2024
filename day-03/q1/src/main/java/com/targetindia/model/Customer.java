package com.targetindia.model;

import com.targetindia.exceptions.*;
import com.targetindia.utils.EmailValidator;
import com.targetindia.utils.KeyboardUtil;

public class Customer {

    public static int readId(String message) throws InvalidIdException {
        int id = KeyboardUtil.getInt(message);

        if(id < 0) {
            throw new InvalidIdException("Id must be >= 0.");
        }

        return id;
    }

    public static String readName(String message) throws InvalidNameException {
        String name = KeyboardUtil.getString(message);

        if(name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");
        }

        if(name.length() < 3 || name.length() > 25) {
            throw new InvalidNameException("Length of name must be between 3 and 25 characters.");
        }

        return name;
    }

    public static String readCity(String message) throws InvalidCityException {
        String city = KeyboardUtil.getString(message);

        if(city.isEmpty()) {
            throw new InvalidCityException("City cannot be blank.");
        }

        if(city.length() < 3 || city.length() > 25) {
            throw new InvalidCityException("Length of city must be between 3 and 25 characters.");
        }

        return city;
    }

    public static String readEmail(String message) throws InvalidEmailException {
        String email = KeyboardUtil.getString(message);

        if(email.isEmpty()) {
            throw new InvalidEmailException("Email cannot be blank.");
        }

        if(!EmailValidator.isValidEmail(email)) {
            throw new InvalidEmailException("Entered email is not a valid email id.");
        }

        return email;
    }

    public static String readNumber(String message) throws InvalidNumberException {
        String number = KeyboardUtil.getString(message);

        if(number.length() != 10) {
            throw new InvalidNumberException("Length of number must be 10 characters.");
        }

        return number;
    }
}
