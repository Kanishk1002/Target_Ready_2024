package com.target.utils;

import java.util.Scanner;

public class Keyboard_Util {

    public static String getString(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine().trim();
    }
}
