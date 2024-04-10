package uk.ac.nulondon;

/**
 * The Pixel class represents a pixel with red, green, and blue color values.
 */
public class Pixel {
    // Class fields
    private int r;
    private int g;
    private int b;
    private Pixel left = null;
    private Pixel right;

    /**
     * Default constructor that creates a black pixel (all values set to 0).
     */
    public Pixel(){
        r = 255;
        g = 255;
        b = 255;
    }

    /**
     * Constructor that creates a pixel with specified red, green, and blue values.
     *
     * @param red the red component of the pixel
     * @param green the green component of the pixel
     * @param blue the blue component of the pixel
     */
    public Pixel(int red, int green, int blue){
        setPixel(red, green, blue);
    }

    /**
     * Sets the pixel's color values.
     *
     * @param red the red component to set
     * @param green the green component to set
     * @param blue the blue component to set
     */
    public void setPixel(int red, int green, int blue){
        r = red;
        g = green;
        b = blue;
    }
    // Individual setters and getters for each color component
    public void setRed(int red){ r = red; }
    public void setGreen(int green) { g = green; }
    public void setBlue(int blue) { b = blue; }
    public void setLeft(Pixel left){ this.left = left; }
    public void setRight(Pixel right){ this.right = right; }
    public int getRed(){ return r; }
    public int getGreen(){ return g; }
    public int getBlue(){ return b; }
    public Pixel getLeft(){ return left; }
    public Pixel getRight(){ return right; }
}
