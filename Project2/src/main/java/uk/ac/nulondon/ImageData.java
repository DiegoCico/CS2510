package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ImageData {
    // variable to store width
    int width;
    // variable to store height
    int height;
    ArrayList<Pixel> pixels = new ArrayList<>();

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

                    if (x == 0) {
                        pixels.add(currentPixel);
                    } else {
                        previousPixel.setRight(currentPixel);
                        currentPixel.setLeft(previousPixel);
                    }
                    previousPixel = currentPixel;
                }
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
        if (pixels == null || pixels.isEmpty()) {
            System.out.println("No pixel data available to export.");
            return;
        }
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < height; y++) {
                Pixel currentPixel = pixels.get(y);
                for (int x = 0; currentPixel != null; x++) {
                    int rgb = (currentPixel.getRed() << 16) | (currentPixel.getGreen() << 8) | currentPixel.getBlue();
                    image.setRGB(x, y, rgb);
                    currentPixel = currentPixel.getRight();
                }
            }
            ImageIO.write(image, "png", new File(file));
            System.out.println("IMAGE EXPORTED SUCCESSFULLY");
        } catch (IOException e) {
            System.out.println("ERROR EXPORTING IMAGE: " + e);
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

    public static <T> List<T> concat(T element, Collection<? extends T> elements) {
        List<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }

//    public List<Integer> getSeam() {
//        double[] previousValues = new double[pixels.size()];
//        double[] currentValues = new double[pixels.size()];
//        List<List<Pixel>> previousSeams = new ArrayList<>();
//        List<List<Pixel>> currentSeams = new ArrayList<>();
//        Pixel pix = pixels.getFirst();
//
//        for (int index = 0; pix != null; index++) {
//            previousValues[index] = pix.getEnergy();
//            List<Pixel> initialSeam = new ArrayList<>();
//            initialSeam.add(pix);
//            previousSeams.add(initialSeam);
//            pix = pix.getRight();
//        }
//
//        // Process the rest of the rows
//        for (int i = 1; i < pixels.size(); i++) {
//            pix = pixels.get(i);
//            //currentSeams.clear();
//            for (int j = 0; pix != null; pix = pix.getRight(), j++) {
//                double bestSoFar = previousValues[j];
//                int bestIndex = j;
//                if (j > 0 && previousValues[j - 1] < bestSoFar) {
//                    bestSoFar = previousValues[j - 1];
//                    bestIndex = j - 1;
//                }
//                if (j < pixels.size() - 1 && previousValues[j + 1] < bestSoFar) {
//                    bestSoFar = previousValues[j + 1];
//                    bestIndex = j + 1;
//                }
//
//                currentValues[j] = bestSoFar + pix.getEnergy();
////                System.out.println(previousSeams);
//                System.out.println(previousValues);
//                List<Pixel> newSeam = new ArrayList<>(previousSeams.get(bestIndex));
//                newSeam.add(pix);
//                currentSeams.add(newSeam);
//            }
////            previousValues = Arrays.copyOf(currentValues, currentValues.length);
////            previousSeams = new ArrayList<>(currentSeams);
//            previousValues = currentValues;
//            previousSeams = currentSeams;
//            currentValues = new double[pixels.size()];
//            currentSeams = new ArrayList<>();
//        }
//
//        double minEnergy = Double.MAX_VALUE;
//        List<Pixel> minSeam = null;
//        for (int j = 0; j < previousSeams.size(); j++) {
//            if (previousValues[j] < minEnergy) {
//                minEnergy = previousValues[j];
//                minSeam = previousSeams.get(j);
//            }
//        }
//
//        List<Integer> seamIndices = new ArrayList<>();
//        for (Pixel p : minSeam) {
//            seamIndices.add(pixels.indexOf(p));
//        }
//        return seamIndices;
//    }


    public List<Pixel> getSeam() {
        int width = 8;
        if (pixels.isEmpty()) return new ArrayList<>();

        double[] previousValues = new double[width]; // the row above's values
        double[] currentValues = new double[width];  // current row's values
        List<List<Pixel>> previousSeams = new ArrayList<>(); // seam values from last iteration
        List<List<Pixel>> currentSeams = new ArrayList<>(); // seam values with this row's iteration
        int col = 0;
        Pixel p = pixels.getFirst();
        Pixel currentPixel;

        // initializing for first row
        while (col < width) {
            previousValues[col] = p.getEnergy();
            previousSeams.add(concat(p, List.of()));
            p = p.getRight();
            col++;
        }

        // compute values and paths for each row
        for (int row = 1; row < width; row++) {
            currentPixel = pixels.get(row);
            int index = 0;

            while (currentPixel != null) {
                double bestSoFar = previousValues[index];
                int ref = index;
                if (index > 0 && previousValues[index - 1] < bestSoFar) {
                    bestSoFar = previousValues[index - 1];
                    ref = index - 1;
                }
                if (index < pixels.size() - 1  && previousValues[index + 1] < bestSoFar) {
                    bestSoFar = previousValues[index + 1];
                    ref = index + 1;
                }


                currentValues[index] = bestSoFar + currentPixel.getEnergy();
                currentSeams.add(concat(currentPixel, previousSeams.get(ref)));
                index++;
                currentPixel = currentPixel.getRight();

            }

            previousValues = currentValues;
            previousSeams = currentSeams;
            currentValues = new double[pixels.size()];
            currentSeams = new ArrayList<>();
        }

        return previousSeams.get(getMinIndex(previousValues));
    }


    // Helper method to get the index of the minimum value in an array
    private int getMinIndex(double[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }


    public static void main(String[] args) {
        ImageData imageData = new ImageData();

        System.out.println(imageData.width);

        imageData.importImage("image/beach.png");

        System.out.println(imageData.width);

        imageData.exportImage("image/beach2.png");

        imageData.iterateEnergy();

        List<Pixel> seam = imageData.getSeam();
        for (Pixel pair : seam.reversed()) {
            System.out.println(pair);
        }


    }

}