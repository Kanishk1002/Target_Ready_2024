package com.targetindia.utils;

import java.util.Scanner;

public class KeyboardUtil {

    public static int getInt(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextInt();
    }
    public static String getString(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine().trim();
    }
}
