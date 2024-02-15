package uk.ac.nulondon;

import java.util.Scanner;

public final class App {
    private App() {
    }

    public static boolean startsWithY(String s) {
        return s.toUpperCase().startsWith("Y");
    }

    public static String bingoWord(String s){
        return s.toUpperCase().charAt(0) + " " + s.length();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Write a word");
        String s  = in.next();
        System.out.println("Does your word start with Y: " + startsWithY(s));
        System.out.println(bingoWord(s));
    }
}
