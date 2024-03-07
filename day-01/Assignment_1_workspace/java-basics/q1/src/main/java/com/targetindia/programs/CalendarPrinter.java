package com.targetindia.programs;

import java.util.Scanner;

public class CalendarPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        printCalendar(month, year);
        scanner.close();
    }

    public static void printCalendar(int month, int year) {
        if (month < 1 || month > 12 || year < 0) {
            System.out.println("Sorry, the input is invalid. Please enter a valid month (1-12) and year.");
            return;
        }

        int daysInMonth = getDaysInMonth(month, year);
        int startingDayOfWeek = getStartingDayOfWeek(month, year);

        // Printing the calendar header
        System.out.println("Su Mo Tu We Th Fr Sa");

        // Printing leading spaces
        for (int i = 0; i < startingDayOfWeek; i++) {
            System.out.print("   ");
        }

        // Printing days
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%2d ", day);
            if ((day + startingDayOfWeek) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int getDaysInMonth(int month, int year) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return daysInMonth[month - 1];
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getStartingDayOfWeek(int month, int year) {
        int day = 1;
        int[] monthCodes = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5}; // Codes for January to December
        int centuryCode = (year / 100) % 4 * 2; // Code for 2000 to 2099
        int yearCode = year % 100;
        int leapYearAdjustment = (year % 4 == 0 && month < 3) ? -1 : 0; // Leap year adjustment
        int dayOfWeek = (day + monthCodes[month - 1] + yearCode + (yearCode / 4) + centuryCode + leapYearAdjustment) % 7;
        return (dayOfWeek + 6) % 7; // Adjusting for Java's day of week representation (0 for Sunday)
    }
}
