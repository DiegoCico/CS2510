package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class ImageProcessor {
    private ArrayList<ArrayList<Pixel>> grid;
    private ArrayList<ArrayList<ArrayList<Pixel>>> copy;
    private BufferedImage image;

    public ImageProcessor(){
        grid = new ArrayList<>();
        copy = new ArrayList<>();
        save();
    }

    public ImageProcessor(File file){
        grid = new ArrayList<>();
        copy = new ArrayList<>();
        try{
            image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < height; y++) {
                ArrayList<Pixel> row = new ArrayList<>();
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    Color cRGB = new Color(rgb);
                    int red = cRGB.getRed();
                    int green = cRGB.getGreen();
                    int blue = cRGB.getBlue();
                    row.add(new Pixel(red, green, blue));
                }
                grid.add(row);
            }
            save();
        } catch (IOException e) {
            System.out.println("IMAGE NOT FOUND " + e );
        }
    }


    public int bluestBlueCol() {
        int col = -1;
        int highestBlue = 0;
        int highestCol = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                    highestBlue += grid.get(j).get(i).getBlue();
                }
                if(highestBlue > highestCol) {
                    highestCol = highestBlue;
                    col = i;
                }
                highestBlue = 0;
            }
        return col;
        }

    public void colorColumnRed(int col) {
        if (col == -1) {
            return;
        }
        for (int i = 0; i < grid.size(); i++) {
            grid.get(col).get(i).setPixel(255,0,0);
        }
    }


    public int removeRandomCol(){
        return (int) (Math.random()*grid.size());
    }

    public void removeSpecificCol(int col){
        save();
        grid.remove(col);
    }

    public void save(){ copy.add(grid); }

    public void undo(){
        grid = copy.getLast();
        copy.removeLast();
    }

    public void printTable(){
        for (int i = 0; i < grid.size(); i++){
            for (int j = 0; j < grid.get(0).size(); j++){
                System.out.println(grid.get(i).get(j).toString() + "    ");
            }
        }
    }

    public ArrayList<ArrayList<Pixel>> getGrid(){
        return grid;
    }

    public void exportImage(String outputPath) {
        BufferedImage newImage = new BufferedImage(grid.size(), grid.get(0).size(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < newImage.getHeight(); y++) {
            for (int x = 0; x < newImage.getWidth(); x++) {
                int red = grid.get(x).get(y).getRed();
                int green = grid.get(x).get(y).getGreen();
                int blue = grid.get(x).get(y).getBlue();
                int rgb = (255 << 24) | (red << 16) | (green << 8) | blue;

                newImage.setRGB(x, y, rgb);
            }
        }
        File outputFile = new File(outputPath);

        try {
            ImageIO.write(newImage, "png", outputFile);
        } catch (IOException e) {
            System.out.println("Error writing image file: " + e);
        }
    }

}
