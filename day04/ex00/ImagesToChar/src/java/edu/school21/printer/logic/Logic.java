package edu.school21.printer.logic;

import edu.school21.printer.app.Program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logic {
    private int x;
    public static int index = 10;

    public static int[][] get2DArray(String path, char white, char black) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(path));
        int[][] D2Array = new int[image.getHeight()][image.getWidth()];

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int color = image.getRGB(x, y);
                if (color == Color.BLACK.getRGB()) {
                    D2Array[x][y] = black;
                } else if (color == Color.WHITE.getRGB()) {
                    D2Array[x][y] = white;
                }
            }
        }
        return D2Array;
    }
}
