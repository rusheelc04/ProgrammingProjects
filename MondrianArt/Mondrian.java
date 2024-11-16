// Rusheel Chande
// May 10 2023

// This program funtions to create artwork in a similar style of Mondrian. The class has two main
// methods for creating either basic or complex versions of the artwork, using a divide and paint
// technique to color a 2D array of pixels

import java.awt.*;
import java.util.*;

public class Mondrian {
    private static final Color[] colors = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
    private static final int border = 1;

    // This method paints a basic Mondrian image
    // Pre-conditions: The passed 2D array 'pixels' should not be null.
    // Post-conditions: The 'pixels' array is filled with colors in a Mondrian styled way
    //                  where sections are divided and colored
    public void paintBasicMondrian(Color[][] pixels) {
        divideAndPaint(pixels, 0, 0, pixels.length, pixels[0].length, false);
    }

    // This method paints a complex Mondrian image
    // Pre-conditions: The passed 2D array 'pixels' should not be null.
    // Post-conditions: The 'pixels' array is filled with colors in a complex Mondrian styled way
    //                  where sections are divided and colored
    public void paintComplexMondrian(Color[][] pixels) {
        divideAndPaint(pixels, 0, 0, pixels.length, pixels[0].length, true);
    }

    // This method divides the image into section and colors each section, recursively splitting
    // the image further if it's too large or the image compexity is set to high
    // Pre-conditions: The given 2D array 'pixels' cannot be null and the coordinates and dimentions 
    //                 (x, y, width, height) must fall within the array's boundaries.
    // Post-conditions: The specified section of the 'pixels' array is divided and colored in a 
    //                  Mondrian style
    private void divideAndPaint(Color[][] pixels, int x, int y, int width, 
                                int height, boolean isComplex) {
        if (width >= pixels.length / 4 && height >= pixels[0].length / 4) {
            Random random = new Random();
            int nWidth = random.nextInt(width / 2) + width / 4;
            int nHeight = random.nextInt(height / 2) + height / 4;

            divideAndPaint(pixels, x, y, nWidth, nHeight, isComplex);
            divideAndPaint(pixels, x + nWidth, y, width - nWidth, nHeight, isComplex);
            divideAndPaint(pixels, x, y + nHeight, nWidth, height - nHeight, isComplex);
            divideAndPaint(pixels, x + nWidth, y + nHeight, width - nWidth, height - nHeight, 
                           isComplex);
        } else if (width >= pixels.length / 4) {
            Random random = new Random();
            int nWidth = random.nextInt(width / 2) + width / 4;

            divideAndPaint(pixels, x, y, nWidth, height, isComplex);
            divideAndPaint(pixels, x + nWidth, y, width - nWidth, height, isComplex);
        } else if (height >= pixels[0].length / 4) {
            Random random = new Random();
            int nHeight = random.nextInt(height / 2) + height / 4;

            divideAndPaint(pixels, x, y, width, nHeight, isComplex);
            divideAndPaint(pixels, x, y + nHeight, width, height - nHeight, isComplex);
        } else {
            Color color = chooseColorFromLocation(pixels, x, y, width, height, isComplex);
            for (int i = x + border; i < x + width - border && i < pixels.length; i++) {
                for (int j = y + border; j < y + height - border && j < pixels[0].length; j++) {
                    pixels[i][j] = color;
                }
            }
        }
    }

    // This method chooses a color for a specific location based on whether the image is set to be 
    // complex or not.
    // Pre-conditions: The given 2D array 'pixels' cannot be null and the coordinates and dimentions 
    //                 (x, y, width, height) has to fall within the array's boundaries.
    // Post-conditions: The method returns a Color object chosen based on the Mondrian style and whether
    //                  it's set to complex or not.
    private Color chooseColorFromLocation(Color[][] pixels, int x, int y, int width, int height, 
                                          boolean isComplex) {
        if (isComplex) {
            int red = (int) ((double) x / pixels.length * 255);
            int green = (int) ((double) y / pixels[0].length * 255);
            int blue = 255 - red - green;

            return new Color(red, green, blue);
        } else {
            Random random = new Random();
            return colors[random.nextInt(colors.length)];
        }
    }
}
