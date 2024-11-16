// Rusheel Chande
// February 2 2023

// This program manipulates a given image in various ways such
// as creating a filter that increases the blue value, inverts the Color
// of the pixels, creates a filter that increaess blue given dimensions,
// and mirrors the image to the right.

import java.util.*;
import java.awt.*;

public class ImageManipulation {
    public static void main(String[] args) {
        Picture pic = new Picture("suzzallo.jpg");
        pic.save("suzzaloBefore.jpg");
        
        Color[][] pixels = pic.getPixels();

        // Task 1: Increasing Blue
        increaseBlue(pixels);

        // Task 2: Invert the color of the pixels
        invertPixels(pixels);

        // Task 3: Increasing Blue within a specified dimensions
        // Enter dimensions here
        int top = 50;
        int bottom = 150;
        int leftStart = 1;
        int rightEnd = 50;
        // method call
        increaseSpecifiedBlue(pixels, top, leftStart, bottom, rightEnd);

        // Task 4: Mirror half of the image to the right
        mirrorRight(pixels);

        // Set Pixels and display Image
        pic.setPixels(pixels);
        pic.save("suzzaloAfter.jpg");
    }

    // This method takes pixels a Color[][] as a parameter that resembles an image
    // and increases the blue color value at each pixel by 80 and caps it at 255
    public static void increaseBlue(Color[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                Color newColor = new Color(red, green, Math.min(blue + 80, 255));
                pixels[i][j] = newColor;
            }
        }
    }

    // This method takes pixels a Color[][] as a parameter that resembles an image
    // and inverts the color of each pixel
    public static void invertPixels(Color[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                Color newColor = new Color(Math.max(255 - red, 0), Math.min(255 - green, 0), 
                                                    Math.min(255 - blue, 0));
                pixels[i][j] = newColor;
            }
        }
    }

    // This method takes pixels a Color[][] as a parameter that resembles an image
    // and int's of top, bottom, leftStart, and rightEnd for dimentions. It then increases
    // the blue color value of the image in that specified dimention
    public static void increaseSpecifiedBlue(Color[][] pixels, int top, int leftStart, 
                                             int bottom, int rightEnd) {
        for (int i = top; i < bottom; i++) {
            for (int j = leftStart; j < rightEnd; j++) {
                Color originalColor = pixels[i][j];
                int red = originalColor.getRed();
                int green = originalColor.getGreen();
                int blue = originalColor.getBlue();

                Color newColor = new Color(red, green, Math.min(blue + 80, 255));
                pixels[i][j] = newColor;
            }
        }
    }

    // This method takes the Color[][] which resembles and image and mirrors 
    // that image on the left to the right.
    public static void mirrorRight(Color[][] image) {
        int width = image[0].length;
        int height = image.length;
        for (int y = 0; y < height; y++) {
            for (int x = width / 2; x < width; x++) {
                image[y][width - x - 1] = image[y][x];
            }
        }
    }
}