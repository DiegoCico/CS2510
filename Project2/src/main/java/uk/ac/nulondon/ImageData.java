package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImageData {
    // variable to store width
    int width;
    // variable to store height
    int height;
    ArrayList<Pixel> pixels;


    //TODO: CALCULATIONS

    /**
     * @param file will be the filepath to an image that will be converted into a graph
     *             based on a LinkedList of Lists of Integers. It will throw an exception if
     *             the filepath does not exist.
     */
    public void importImage(String file) {
        try {
            BufferedImage image = ImageIO.read(new File(file));

            width = image.getWidth();
            height = image.getHeight();
            Pixel previousPixel = null;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int r = (rgb >> 16) & 0xff;
                    int g = (rgb >> 8) & 0xff;
                    int b = rgb & 0xff;
                    Pixel currentPixel = new Pixel(r, g, b);
                    pixels.add(currentPixel);

                    if (x > 0) {
                        previousPixel.setRight(currentPixel);
                        currentPixel.setLeft(previousPixel);
                    }
                    previousPixel = currentPixel;
                    pixels.add(currentPixel);
                }

                previousPixel = null;
            }

        } catch (IOException e) {
            System.out.println("IMAGE NOT FOUND " + e);
        }
    }


    /**
     * @param file this is the filepath where the altered image will be stored at.
     *             This method will convert an altered graph to an image and be
     *             displayed to the user.
     */
    public void exportImage(String file) {
        try{
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            width = image.getWidth();
            height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    image.setRGB(x, y, rgb);
                }
            }

            ImageIO.write(image, "png", new File(file));
            System.out.println("IMAGE EXPORTED SUCCESSFULLY");
        } catch (IOException e) {
            System.out.println("IMAGE NOT FOUND " + e );
        }
    }

    public int calculateBrightness(Pixel pixel){
        int sum = pixel.getBlue() + pixel.getGreen() + pixel.getRed();
        return sum /3;
    }

    public double getEnergy(int x, int y) {
        int index = x * y;
        int horizontal[] = {0, 0};
        int vertical[] = {0, 0};


        horizontal[0] = calculateBrightness(pixels.get(index - 4)) + calculateBrightness(pixels.get(index - 3)) * 2 + calculateBrightness(pixels.get(index - 2));
        horizontal[1] = calculateBrightness(pixels.get(index + 4)) + calculateBrightness(pixels.get(index + 3)) * 2 + calculateBrightness(pixels.get(index + 2));

        vertical[0] = calculateBrightness(pixels.get(index - 4)) + calculateBrightness(pixels.get(index - 1)) * 2 + calculateBrightness(pixels.get(index + 2));
        vertical[1] = calculateBrightness(pixels.get(index + 4)) + calculateBrightness(pixels.get(index + 1)) * 2 + calculateBrightness(pixels.get(index - 2));


        return Math.sqrt(Math.pow((horizontal[1] - horizontal[0]), 2 + Math.pow((vertical[1] - vertical[0]), 2)));
    }

}