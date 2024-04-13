package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ImageData {
    // variable to store width
    int width;
    // variable to store height
    int height;
    ArrayList<Pixel> pixels;

    /**
     * @param file will be the filepath to an image that will be converted into a graph
     *             based on a LinkedList of Lists of Integers. It will throw an exception if
     *             the filepath does not exist.
     */
    public void importImage(String file) {
        try {
            System.out.println("Importing " + file);
            BufferedImage image = ImageIO.read(new File(file));

            width = image.getWidth();
            height = image.getHeight();
            pixels = new ArrayList<>();
            Pixel previousPixel = null;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int r = (rgb >> 16) & 0xff;
                    int g = (rgb >> 8) & 0xff;
                    int b = rgb & 0xff;
                    Pixel currentPixel = new Pixel(r, g, b);

                    if (x > 0) {
                        Pixel leftPixel = pixels.get(pixels.size() - 1);
                        previousPixel.setRight(currentPixel);
                        currentPixel.setLeft(leftPixel);
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

    public void iterateEnergy() {
        if(pixels == null) {
            return;
        }
        for (Pixel pix : pixels) {
            Pixel up = pixels.indexOf(pix) > 0 ? pixels.get(pixels.indexOf(pix) - 1) : null;
            Pixel down = pixels.indexOf(pix) < pixels.size() - 1 ? pixels.get(pixels.indexOf(pix) + 1) : null;

            while (pix != null) {
                pix.setEnergy(calcEnergy(up, pix, down));
                up = (up == null) ? null : up.getRight();
                down = (down == null) ? null : down.getRight();
                pix = pix.getRight();
            }

        }

    }

    /**
     * This will calculate the brightness of a pixel by averaging teh RGB values
     * @param pixel given pixel to calculate brightness
     * @return the brightness of that given pixel
     */

    public int calculateBrightness(Pixel pixel, Pixel backup){
        if(pixel == null) {
            int sum = backup.getGreen() + backup.getBlue() +  backup.getRed();
            return sum /3;
        }
        int sum = pixel.getGreen() + pixel.getBlue() +  pixel.getRed();
        return sum /3;
    }

    /**
     * This will calculate energy based on three given pixels
     * @param up this is the pixel above the pixel to be calculated
     * @param middle this is the pixel to be calculated
     * @param down this is the pixel below the pixel to be calculated
     * @return the energy of the middle pixel
     */
    public double calcEnergy(Pixel up, Pixel middle, Pixel down) {
        int horizontal[] = {0, 0};
        int vertical[] = {0, 0};

        if(up == null) {
            up = middle;
        }
        if(down == null) {
            down = middle;
        }

        horizontal[0] = calculateBrightness(up.getLeft(),middle) + calculateBrightness(up,middle) * 2 + calculateBrightness(up.getRight(),middle);
        horizontal[1] = calculateBrightness(down.getLeft(),middle) + calculateBrightness(down,middle) * 2 + calculateBrightness(down.getRight(),middle);

        vertical[0] = calculateBrightness(up.getLeft(),middle) + calculateBrightness(middle.getLeft(),middle) * 2 + calculateBrightness(down.getLeft(),middle);
        vertical[1] = calculateBrightness(up.getRight(),middle) + calculateBrightness(middle.getRight(),middle) * 2 + calculateBrightness(down.getRight(),middle);

        System.out.print(horizontal[0] + " ");
        System.out.println(vertical[0]);
        System.out.print(horizontal[1] + " ");
        System.out.println(vertical[1]);
        System.out.println((double) Math.sqrt(Math.pow((horizontal[1] - horizontal[0]), 2) + Math.pow((vertical[1] - vertical[0]), 2)));
        System.out.println("----------------------------");
        return Math.sqrt(Math.pow((horizontal[1] - horizontal[0]), 2) + Math.pow((vertical[1] - vertical[0]), 2));
    }

    class Pair{
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public String toString() {return ("(%d, %d)".formatted(row, col));}
    }

    public double collectEnergy(Pixel p, int col){
        Pixel iter = p;
        for(int i=0; i<col--; i++){
            if(p.getRight() != null)
                 iter = iter.getRight();
            else
                return 0;
        }

        return  iter == null ? 0 : iter.getEnergy();
    }

    public static <T> List<T> concat(T element, Collection<? extends T> elements) {
        List<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }


        public List<Pair> getSeam() {
        double[] previousValues = new double[width]; // the row above's values
        double[] currentValues = new double[width];  // current row's values
        List<List<Pair>> previousSeams = new ArrayList<>(); // seam values from last iteration
        List<List<Pair>> currentSeams = new ArrayList<>(); // seam values with this row's iteration
        Pair currentPixel = new Pair(0, 0);

        int col = 0;

        // initializing for first row
        while (col < width) {
            previousValues[col] = collectEnergy(pixels.get(0), col);

            // one seam per column
            previousSeams.add(concat(currentPixel, List.of()));
            col++;
            currentPixel = new Pair(currentPixel.row, currentPixel.col + 1);
        }

        // compute values and paths for each row
        for (int row = 1; row < height; row++) {
            col = 0;
            currentPixel = new Pair(row, col);
            while (col < width) {
                double bestSoFar = previousValues[col];
                int ref = col;
                // check both adjacent pixels
                // if left exists and is better, update
                if (col > 0 && previousValues[col - 1] < bestSoFar) {
                    bestSoFar = previousValues[col - 1];
                    ref = col - 1;
                }
                // if right exists and is better, update
                if (col < width - 1 && previousValues[col + 1] < bestSoFar) {
                    bestSoFar = previousValues[col + 1];
                    ref = col + 1;
                }

                // update the value with the current pixel
                currentValues[col] = bestSoFar + collectEnergy(pixels.get(currentPixel.row), currentPixel.col);

                // append this new pixel to existing seams
                currentSeams.add(concat(currentPixel, previousSeams.get(ref)));

                col++;
                // move to neighbor
                currentPixel = new Pair(currentPixel.row, currentPixel.col + 1);
            }

            // update previous values/seams
            // and reset current values/seams
            previousValues = currentValues;
            currentValues = new double[width];
            previousSeams = currentSeams;
            currentSeams = new ArrayList<>();
        }

        // find the seam with the max sum
        double minValue = previousValues[0];
        int minIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (previousValues[i] < minValue) {
                minIndex = i;
                minValue = previousValues[i];
            }
        }
        return previousSeams.get(minIndex);
    }


    public static void main(String[] args) {
        ImageData imageData = new ImageData();

        System.out.println(imageData.width);

        imageData.importImage("image/beach.png");

        System.out.println(imageData.width);




        imageData.iterateEnergy();


        List<Pair> seam = imageData.getSeam();
        for (Pair pair : seam.reversed()) {
            System.out.println(pair);
        }


    }

}