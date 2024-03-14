package com.target.programs;

import lombok.Data;

import java.io.*;
import java.util.*;

@Data
public class Text_File_Analyzer {
    private static int longestLineIndex;
    private static int shortestLineIndex;

    public static void main(String[] args) {
        String filePath = com.target.utils.Keyboard_Util.getString("Enter the filename: ");
        String[] fileLines = readData(filePath);

        System.out.println("Longest Line:");
        String longestLine = findLongestLine(fileLines);
        System.out.printf("Line %d: %s\n\n", longestLineIndex + 1, longestLine);

        System.out.println("Shortest Line:");
        String shortestLine = findShortestLine(fileLines);
        System.out.printf("Line %d: %s\n\n", shortestLineIndex + 1, shortestLine);

        int[] wordCount = countWords(fileLines);

        System.out.println("Word count for each line:");
        for(int i = 0; i < wordCount.length; i++) {
            System.out.printf("Line %d: %d words\n", i + 1, wordCount[i]);
        }

        sortWordCount(wordCount);

        System.out.println("\nSorted word count:");
        for (int count : wordCount) {
            System.out.printf("%d words\n", count);
        }
    }

    private static String[] readData(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.toArray(new String[0]);
    }


    public static String findLongestLine(String[] lines) {
        longestLineIndex = 0;
        int count = lines[0].length();
        for(int i = 1; i < lines.length; i++) {
            if(count < lines[i].length()) {
                count = lines[i].length();
                longestLineIndex = i;
            }
        }
        return lines[longestLineIndex];
    }


    public static String findShortestLine(String[] lines) {
        shortestLineIndex = 0;
        int count = lines[0].length();
        for(int i = 1; i < lines.length; i++) {
            if(count > lines[i].length()) {
                count = lines[i].length();
                shortestLineIndex = i;
            }
        }
        return lines[shortestLineIndex];
    }

    private static int getWordCount(String line) {
        String[] words = line.split(" ");
        return words.length;
    }


    public static int[] countWords(String[] lines) {
        int[] wordCount = new int[lines.length];
        for(int i = 0; i < lines.length; i++) {
            wordCount[i] = getWordCount(lines[i]);
        }
        return wordCount;
    }

    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }


    public static void sortWordCount(int[] wordCounts) {
        Arrays.sort(wordCounts);
        reverseArray(wordCounts);
    }

}
