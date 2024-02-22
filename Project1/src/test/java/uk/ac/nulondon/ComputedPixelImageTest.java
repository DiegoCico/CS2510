package uk.ac.nulondon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ComputedPixelImageTest {

    @Test
    public void testDefaultConstructor() {
        ComputedPixelImage image = new ComputedPixelImage();
        assertEquals(0, image.getX());
        assertEquals(0, image.getY());
    }

    @Test
    public void testParameterizedConstructor() {
        int width = 5;
        int height = 5;
        ComputedPixelImage image = new ComputedPixelImage(width, height);

        assertEquals(width, image.getX());
        assertEquals(height, image.getY());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel pixel = getPixel(image, x, y);
                assertEquals(255, pixel.getRed());
                assertEquals(255, pixel.getGreen());
                assertEquals(255, pixel.getBlue());
            }
        }
    }

    @Test
    public void testSetPixelWithinBounds() {
        int width = 10;
        int height = 10;
        ComputedPixelImage image = new ComputedPixelImage(width, height);
        Pixel newPixel = new Pixel(255, 0, 0);

        image.setPixel(5, 5, newPixel);
        Pixel resultPixel = getPixel(image, 5, 5);

        assertEquals(255, resultPixel.getRed());
        assertEquals(0, resultPixel.getGreen());
        assertEquals(0, resultPixel.getBlue());
    }

    @Test
    public void testSetPixelOutsideBounds() {
        ComputedPixelImage image = new ComputedPixelImage(10, 10);
        Pixel newPixel = new Pixel(255, 0, 0);

        image.setPixel(10, 10, newPixel);

        Pixel resultPixel = getPixel(image, 9, 9);
        assertEquals(255, resultPixel.getRed());
        assertEquals(255, resultPixel.getGreen());
        assertEquals(255, resultPixel.getBlue());
    }

    @Test
    public void testSetPixelsWithinBounds() {
        ComputedPixelImage image = new ComputedPixelImage(10, 10);
        Pixel newPixel = new Pixel(0, 255, 0);
        image.setPixels(2, 2, 3, 3, newPixel);

        for (int x = 2; x < 5; x++) {
            for (int y = 2; y < 5; y++) {
                Pixel resultPixel = getPixel(image, x, y);
                assertEquals(0, resultPixel.getRed());
                assertEquals(255, resultPixel.getGreen());
                assertEquals(0, resultPixel.getBlue());
            }
        }
    }

    @Test
    public void testSetPixelsOutsideBounds() {
        ComputedPixelImage image = new ComputedPixelImage(5, 5);
        Pixel newPixel = new Pixel(0, 0, 255);
        image.setPixels(3, 3, 5, 5, newPixel);

        for (int x = 3; x < 5; x++) {
            for (int y = 3; y < 5; y++) {
                Pixel resultPixel = getPixel(image, x, y);
                assertEquals(0, resultPixel.getRed());
                assertEquals(0, resultPixel.getGreen());
                assertEquals(255, resultPixel.getBlue());
            }
        }

        Pixel unchangedPixel = getPixel(image, 0, 0);
        assertNotEquals(newPixel, unchangedPixel);
    }

    private Pixel getPixel(ComputedPixelImage image, int x, int y) {
        return image.getGrid().get(x).get(y);
    }

}
