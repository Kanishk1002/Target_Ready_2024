package com.target.program;

import java.util.Scanner;

public class NumberToWordsConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number between 1 and 99,99,99,999: ");
        int num = scanner.nextInt();

        String result = inWords(num);
        System.out.println("The number in words: " + result);

        scanner.close();
    }

    public static String inWords(int num) {
        if (num == 0) {
            return "zero";
        }

        String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        // Handling crores
        String result = "";
        if (num >= 10000000) {
            result += inWords(num / 10000000) + " crore ";
            num %= 10000000;
        }

        // Handling lakhs
        if (num >= 100000) {
            result += inWords(num / 100000) + " lakh ";
            num %= 100000;
        }

        // Handling thousands
        if (num >= 1000) {
            result += inWords(num / 1000) + " thousand ";
            num %= 1000;
        }

        // Handling hundreds
        if (num >= 100) {
            result += inWords(num / 100) + " hundred ";
            num %= 100;
        }

        // Handling tens and units
        if (num > 0) {
            if (num < 20) {
                result += units[num];
            } else {
                result += tens[num / 10] + " ";
                num %= 10;
                if (num > 0) {
                    result += units[num];
                }
            }
        }

        return result.trim();
    }
}
