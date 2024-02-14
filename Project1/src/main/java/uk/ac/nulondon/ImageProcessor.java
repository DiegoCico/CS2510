package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageProcessor {
    private ArrayList<ArrayList<Pixel>> grid;
    private BufferedImage image;

    public ImageProcessor(){
        grid = new ArrayList<>();
    }

    public ImageProcessor(File file){
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
                    int blue = cRGB.getGreen();
                    row.add(new Pixel(red, green, blue));
                }
                grid.add(row);
            }


        } catch (IOException e) {
            // TODO: FIX THIS
            System.out.println(e);
        }
    }

//    TODO: BIGGEST BLUE
//    public int bluestBlueCol(){
//        for(int i = 0; i < grid.size(); i++){
//            for(int j = 0; j < grid.get(0).size(); j++){
//
//            }
//        }
//        return 0;
//    }


}
