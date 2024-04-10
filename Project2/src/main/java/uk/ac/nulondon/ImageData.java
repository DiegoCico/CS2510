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

    public boolean outOfBounds(Pixel pixel){
        return pixel.getLeft() == null || pixel.getRight() == null;
    }

    public void iterateEnergy(){

        for (int i = 0; i < pixels.size(); i++) {
            Pixel up = i == 0? null : pixels.get(i-1);
            Pixel down = i == pixels.size()-1? null : pixels.get(i+1);
            Pixel pix = pixels.get(i);

            Pixel iter = pixels.get(i);

            while (iter.getRight() != null) {
                if(up == null){
                    pix = pix.getRight();
                    down = down.getRight();
                    pix.setEnergy(calcEnergy(up,iter,down));
                } else if(down == null){
                    pix = pix.getRight();
                    up = up.getRight();
                    pix.setEnergy(calcEnergy(up,iter,down));
                } else {
                    up = up.getRight();
                    pix = pix.getRight();
                    down = down.getRight();
                    pix.setEnergy(calcEnergy(up, iter, down));
                }
            }
        }
    }

    /**
     * This will calculate the brightness of a pixel by averaging teh RGB values
     * @param pixel given pixel to calculate brightness
     * @return the brightness of that given pixel
     */

    public int calculateBrightness(Pixel pixel){
        int sum = pixel.getBlue() + pixel.getGreen() + pixel.getRed();
        return sum /3;
    }

    /**
     * This will calculate energy based on three given pixels
     * @param up this is the pixel above the pixel to be calculated
     * @param middle this is the pixel to be calculated
     * @param down this is the pixel below the pixel to be calculated
     * @return the energy of the middle pixel
     */
    public float calcEnergy(Pixel up, Pixel middle, Pixel down) {
        int horizontal[] = {0, 0};
        int vertical[] = {0, 0};

        if(up == null) {
            up = middle;
        }
        if(down == null) {
            down = middle;
        }

        horizontal[0] = calculateBrightness(up.getLeft()) + calculateBrightness(up) * 2 + calculateBrightness(up.getRight());
        horizontal[1] = calculateBrightness(down.getLeft()) + calculateBrightness(down) * 2 + calculateBrightness(down.getRight());

        vertical[0] = calculateBrightness(up.getLeft()) + calculateBrightness(middle.getLeft()) * 2 + calculateBrightness(down.getLeft());
        vertical[1] = calculateBrightness(up.getRight()) + calculateBrightness(middle.getRight()) * 2 + calculateBrightness(down.getRight());


        return (float) Math.sqrt(Math.pow((horizontal[1] - horizontal[0]), 2 + Math.pow((vertical[1] - vertical[0]), 2)));
    }





}