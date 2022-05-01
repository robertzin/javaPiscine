package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Program [charW] [charB] [pathToFile]");
            System.exit(1);
        }

        try {
            char white = args[0].charAt(0);
            char black = args[1].charAt(0);
            String path = args[2];
            int[][] D2Array = Logic.get2DArray(path, white, black);
            for (int x = 0; x < D2Array.length; x++) {
                for (int y = 0; y < D2Array[0].length; y++) {
                    System.out.print((char)D2Array[y][x]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
