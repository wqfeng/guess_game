package com.udacity;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void main(String[] args) throws IOException {
        Scanner s = null;
        String[] puzzles = new String[4];
        int index = 0;
        
        // read puzzles from file to an array
        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(GuessGame.class.getResourceAsStream("/main/resources/puzzle.txt"))));

            while (s.hasNext()) {
                puzzles[index] = s.next();
                index += 1;
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }

        // select an random word to guess
        Scanner sIn = new Scanner(System.in);
        Random generator = new Random();
        int i = generator.nextInt(puzzles.length);
        String word = puzzles[i];
        StringBuilder q = new StringBuilder(word.replaceAll("\\w", "_"));
        System.out.println("What's the animal? " + formatPuzzle(q.toString()));
        while (!q.toString().equalsIgnoreCase(word)) {
            String guess = sIn.next();
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) == guess.toLowerCase().charAt(0)) {
                    q.setCharAt(j, guess.charAt(0));
                }

            }
            if (q.toString().indexOf(guess) > -1){
                System.out.println("Good job! The puzzle now is: " + formatPuzzle(q.toString()));
            }
            else {
                System.out.printf("Take another guess: " + formatPuzzle(q.toString()));
            }

        }
        sIn.close();
        System.out.println("You Win! The animal is " + word);
    }

    private static String formatPuzzle(String q) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q.length(); i++) {

            sb.append(q.charAt(i));
            sb.append(' ');
        }
        return sb.toString();
    }
}