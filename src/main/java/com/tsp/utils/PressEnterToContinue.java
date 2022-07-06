package com.tsp.utils;

public class PressEnterToContinue {
    public static void run() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
