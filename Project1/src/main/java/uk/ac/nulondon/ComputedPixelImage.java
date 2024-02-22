package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The ComputedPixelImage class represents an image as a grid of Pixel objects.
 * It allows for the creation and manipulation of a pixel-based image and can export the image to a PNG file.
 */
public class ComputedPixelImage {
    private ArrayList<ArrayList<Pixel>> grid;
    private int x;
    private int y;

    /**
     * Default constructor. Initializes an empty grid of pixels.
     */
    public ComputedPixelImage() {
        grid = new ArrayList<ArrayList<Pixel>>();
    }

    /**
     * Constructs a ComputedPixelImage of specified width and height.
     *
     * @param xI the width of the image in pixels
     * @param yI the height of the image in pixels
     */
    public ComputedPixelImage(int xI, int yI) {
        x = xI;
        y = yI;
        grid = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            ArrayList<Pixel> newRow = new ArrayList<Pixel>();
            for (int j = 0; j < y; j++) {
                newRow.add(new Pixel());
            }
            grid.add(newRow);
        }
    }

    /**
     * Sets the pixel at the specified (xI, yI) location to the given Pixel object.
     *
     * @param xI    the x-coordinate of the pixel to set
     * @param yI    the y-coordinate of the pixel to set
     * @param pixel the Pixel object to set at the specified location
     */
    public void setPixel(int xI, int yI, Pixel pixel) {
        if (xI >= 0 && xI < grid.size() && yI >= 0 && yI < grid.get(0).size()) {
            grid.get(xI).set(yI, pixel);
        }
    }

    /**
     * Sets a block of pixels starting at (xI, yI) to the specified width and height to the given Pixel object.
     *
     * @param xI     the starting x-coordinate
     * @param yI     the starting y-coordinate
     * @param height the height of the block of pixels to set
     * @param width  the width of the block of pixels to set
     * @param pixel  the Pixel object to use for setting the block of pixels
     */
    public void setPixels(int xI, int yI, int height, int width, Pixel pixel) {
        for (int i = xI; i < xI + width && i < grid.size(); i++) {
            for (int j = yI; j < yI + height && j < grid.get(0).size(); j++) {
                grid.get(i).set(j, pixel);
            }
        }
    }

    /**
     * Exports the current state of the image to a PNG file.
     *
     * @param filename the name of the file to save the image to
     * @throws IOException if an error occurs during writing the file
     */
    public void exportToPNG(String filename) throws IOException {
        BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Pixel p = grid.get(i).get(j);
                int color = (255 << 24) | (p.getRed() << 16) | (p.getGreen() << 8) | p.getBlue();
                image.setRGB(i, j, color);
            }
        }

        File outputFile = new File(filename);
        ImageIO.write(image, "png", outputFile);
    }

    /**
     * Returns the width of the image.
     *
     * @return the width of the image
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the height of the image.
     *
     * @return the height of the image
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the grid of pixels representing the image.
     *
     * @return the grid of Pixel objects
     */
    public ArrayList<ArrayList<Pixel>> getGrid() {
        return grid;
    }
}
