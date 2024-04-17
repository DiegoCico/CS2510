package uk.ac.nulondon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageDataTest {

    // BRIGTNESS
    @Test
    public void testCalculateBrightnessWithValidPixel() {
        ImageData imageData = new ImageData();
        Pixel pixel = new Pixel(10, 20, 30);
        int brightness = imageData.calculateBrightness(pixel, null);
        assertThat(brightness).isEqualTo((10 + 20 + 30) / 3);
    }

    @Test
    public void testCalculateBrightnessWithNullPixel() {
        ImageData imageData = new ImageData();
        Pixel backup = new Pixel(10, 20, 30);
        int brightness = imageData.calculateBrightness(null, backup);
        assertThat(brightness).isEqualTo((10 + 20 + 30) / 3);
    }

    // ENERGY
    @Test
    public void testCalcEnergyWithTypicalPixels() {
        ImageData imageData = new ImageData();
        Pixel up = new Pixel(255, 0, 0);
        Pixel middle = new Pixel(0, 255, 0);
        Pixel down = new Pixel(0, 0, 255);
        double energy = imageData.calcEnergy(up, middle, down);
        assertThat(energy).isEqualTo(0);
    }

    // CONCAT
    @Test
    public void testConcatWithNonEmptyCollection() {
        ImageData imageData = new ImageData();
        List<Pixel> additionalPixels = List.of(new Pixel(1, 2, 3), new Pixel(4, 5, 6));
        List<Pixel> result = imageData.concat(new Pixel(7, 8, 9), additionalPixels);
        assertThat(result.get(0)).extracting(Pixel::getRed, Pixel::getGreen, Pixel::getBlue).contains(7, 8, 9);
        assertThat(result.get(1).getRed()).isEqualTo(1);
    }

    @Test
    public void testConcatWithEmptyCollection() {
        ImageData imageData = new ImageData();
        List<Pixel> result = imageData.concat(new Pixel(7, 8, 9), new ArrayList<>());
        assertThat(result.get(0)).extracting(Pixel::getRed, Pixel::getGreen, Pixel::getBlue).contains(7, 8, 9);
    }

    //MAX OR MIN
    @Test
    public void testGetMaxOrMinIndexForMaxValue() {
        ImageData imageData = new ImageData();
        double[] array = {1.0, 3.0, 2.0, 5.0, 4.0};
        int index = imageData.getMaxOrMinIndex(array, true);
        assertThat(index).isEqualTo(3);
    }

    @Test
    public void testGetMaxOrMinIndexForMinValue() {
        ImageData imageData = new ImageData();
        double[] array = {1.0, 3.0, 0.5, 5.0, 4.0};
        int index = imageData.getMaxOrMinIndex(array, false);
        assertThat(index).isEqualTo(2);
    }

    @Test
    public void testGetMaxOrMinIndexWithIdenticalValues() {
        ImageData imageData = new ImageData();
        double[] array = {2.0, 2.0, 2.0, 2.0, 2.0};
        int maxIndex = imageData.getMaxOrMinIndex(array, true);
        int minIndex = imageData.getMaxOrMinIndex(array, false);
        assertThat(maxIndex).isEqualTo(0);
        assertThat(minIndex).isEqualTo(0);
    }


    // SEAM
    @Test
    public void testGetSeamOptimizingForBlue() {
        ImageData imageData = new ImageData();
        imageData.setupPixelDataForSeamTest(imageData, 3, 3, true);
        List<Pixel> seam = imageData.getSeam(true);
        assertThat(seam).isNotEmpty();
        assertThat(seam).hasSize(3);
        assertThat(seam.stream().allMatch(p -> p.getBlue() == 255)).isTrue();
    }

    @Test
    public void testGetSeamOptimizingForEnergy() {
        ImageData imageData = new ImageData();
        imageData.setupPixelDataForSeamTest(imageData, 3, 3, false);
        List<Pixel> seam = imageData.getSeam(false);
        assertThat(seam).isNotEmpty();
        assertThat(seam).hasSize(3);
        assertThat(seam.stream().allMatch(p -> p.getEnergy() == 1.0)).isTrue();
    }



}
