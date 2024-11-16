// Rusheel Chande
// February 2 2023

// This program was pre-written and averages an array of images

import java.awt.*;

public class FaceAveraging {
    public static void main(String[] args) {
        // Load images
        Picture face1 = new Picture("alex.jpg");
        Picture face2 = new Picture("alexander.jpg");
        Picture face3 = new Picture("alfred.jpg");
        Picture face4 = new Picture("ambroz.jpg");
        Picture face5 = new Picture("arnold.jpg");
        Picture face6 = new Picture("zelmira.jpg");
        Picture face7 = new Picture("zita.jpg");
        Picture face8 = new Picture("zlata.jpg");
        Picture face9 = new Picture("zlatica.jpg");
        Picture face10 = new Picture("zora.jpg");

        // Create overall data set
        Picture[] allFaces = new Picture[] {face1, face2, face3, face4, face5,
                                            face6, face7, face8, face9, face10};

        // TODO: Create at least three new data set arrays here
        Picture[] assumedMaleFaces = new Picture[] {face1, face2, face3, face4, 
                                                    face5};
        Picture[] assumbedFemaleFaces = new Picture[] {face6, face7, face8, face9,
                                                       face10};
        Picture[] blueEyes = new Picture[] {face1, face2, face3, face6, face10};

        // TODO: Edit call of faceAverage below to take your desired data set
        Picture averageFace = faceAverage(allFaces);
        Picture averageMaleFace = faceAverage(assumedMaleFaces);
        Picture averageFemaleFace = faceAverage(assumbedFemaleFaces);
        Picture averageBlueEyes = faceAverage(blueEyes);

        averageFace.save("averageFace.jpg");
        averageMaleFace.save("averageMaleFace.jpg");
        averageFemaleFace.save("averageFemaleFace.jpg");
        averageBlueEyes.save("averageBlueEyes.jpg");
    }

    // Averages multiple faces.
    // Takes an array of Pictures as a parameter. This array contains
    // the faces to average. This array must contain at least 1 Picture
    // or an IllegalArgumentException will be thrown.
    // Returns a Picture of the average face. 
    public static Picture faceAverage(Picture[] faces) {
        if (faces.length < 1) {
            throw new IllegalArgumentException("faces.length must be at least 1");
        }

        Picture firstFace = faces[0];

        Picture avgFace = new Picture(firstFace);
        Color[][] average = avgFace.getPixels();
        
        for (int faceNumber = 1; faceNumber < faces.length; faceNumber++) {
            Picture face = faces[faceNumber];
            Color[][] pixels = face.getPixels();

            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    Color newColor = pixels[i][j];
                    int newRed = newColor.getRed();
                    int newGreen = newColor.getGreen();
                    int newBlue = newColor.getBlue();

                    Color avgColor = average[i][j];
                    int avgRed = avgColor.getRed() * faceNumber;
                    int avgGreen = avgColor.getGreen() * faceNumber;
                    int avgBlue = avgColor.getBlue() * faceNumber;

                    Color newAvg = new Color((newRed + avgRed) / (faceNumber + 1),
                                            (newGreen + avgGreen) / (faceNumber + 1),
                                            (newBlue + avgBlue) / (faceNumber + 1));
                    average[i][j] = newAvg;
                }
                
            }
        }
        
        avgFace.setPixels(average);
        return avgFace;
    }
}
