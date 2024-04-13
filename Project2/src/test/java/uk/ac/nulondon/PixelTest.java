package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PixelTest {

    // Tests for the default constructor
    @Test
    void defaultConstructorShouldCreateBlackPixel() {
        Pixel pixel = new Pixel();
        assertThat(pixel.getRed()).isEqualTo(255);
        assertThat(pixel.getGreen()).isEqualTo(255);
        assertThat(pixel.getBlue()).isEqualTo(255);
    }

    // Tests for the parameterized constructor
    @Test
    void parameterizedConstructorShouldSetCorrectValues() {
        Pixel pixel = new Pixel(255, 128, 64);
        assertThat(pixel.getRed()).isEqualTo(255);
        assertThat(pixel.getGreen()).isEqualTo(128);
        assertThat(pixel.getBlue()).isEqualTo(64);
    }
    // Tests for the parameterized constructor
    @Test
    void parameterizedConstructorShouldNotSetIncorrectValues() {
        Pixel pixel = new Pixel(255, 128, 64);
        assertThat(pixel.getRed()).isNotEqualTo(0);
        assertThat(pixel.getGreen()).isNotEqualTo(0);
        assertThat(pixel.getBlue()).isNotEqualTo(0);
    }

    // Tests for setPixel method
    @Test
    void setPixelShouldChangeValuesCorrectly() {
        Pixel pixel = new Pixel();
        pixel.setPixel(10, 20, 30);
        assertThat(pixel.getRed()).isEqualTo(10);
        assertThat(pixel.getGreen()).isEqualTo(20);
        assertThat(pixel.getBlue()).isEqualTo(30);
    }
    // Tests for setPixel method
    @Test
    void setPixelShouldNotRetainOldValues() {
        Pixel pixel = new Pixel(100, 100, 100);
        pixel.setPixel(10, 20, 30);
        assertThat(pixel.getRed()).isNotEqualTo(100);
        assertThat(pixel.getGreen()).isNotEqualTo(100);
        assertThat(pixel.getBlue()).isNotEqualTo(100);
    }

    // Tests for toSting
    @Test
    void toStringShouldNotReturnIncorrectFormat() {
        Pixel pixel = new Pixel(70, 80, 90);
        assertThat(pixel.toString()).isNotEqualTo("70, 80, 90, 100");
    }

    //Tests for setEnergy
    @Test
    void setEnergy(){
        Pixel pixel = new Pixel();
        pixel.setEnergy(100);
        assertThat(pixel.getEnergy()).isEqualTo(100);
        pixel.setEnergy(170);
        assertThat(pixel.getEnergy()).isEqualTo(170);
    }

    // Tests for getEnergy
    @Test
    void getEnergy(){
        Pixel pixel = new Pixel();
        pixel.setEnergy(100);
        assertThat(pixel.getEnergy()).isEqualTo(100);
        pixel.setEnergy(200);
        assertThat(pixel.getEnergy()).isEqualTo(200);
    }

    // This tests setPixel
    @Test
    void setPixel(){
        Pixel pixel = new Pixel();
        pixel.setPixel(10, 20, 30);
        assertThat(pixel.getRed()).isEqualTo(10);
        assertThat(pixel.getGreen()).isEqualTo(20);
        assertThat(pixel.getBlue()).isEqualTo(30);

    }

    // Tests setLeft
    @Test
    void setLeft(){
        Pixel pixel = new Pixel();
        assertThat(pixel.getLeft()).isEqualTo(null);

        Pixel pixel2 = new Pixel();
        pixel.setPixel(10, 20, 30);
        pixel2.setPixel(20,90,70);
        pixel.setLeft(pixel2);
        assertThat(pixel.getLeft()).isEqualTo(pixel2);

    }

    // Tests setRight
    @Test
    void setRight(){
        Pixel pixel = new Pixel();
        pixel.setPixel(10, 20, 30);
        assertThat(pixel.getRight()).isEqualTo(null);

        Pixel pixel2 = new Pixel();
        pixel.setPixel(10, 20, 30);
        pixel2.setPixel(20,90,70);
        pixel.setRight(pixel2);
        assertThat(pixel.getRight()).isEqualTo(pixel2);

    }


}
