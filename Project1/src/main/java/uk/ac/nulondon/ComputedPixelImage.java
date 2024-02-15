package uk.ac.nulondon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComputedPixelImage {
    private ArrayList<ArrayList<Pixel>> grid;
    private int x;
    private int y;

    public ComputedPixelImage(){
        grid = new ArrayList<ArrayList<Pixel>>();
    }

    public ComputedPixelImage(int xI, int yI){
        x = xI;
        y = yI;
        grid = new ArrayList<>();
        for (int i = 0; i<x;i ++){
            ArrayList<Pixel> newRow = new ArrayList<Pixel>();
            for (int j=0; j<y; j++){
                newRow.add(new Pixel());
            }
            grid.add(newRow);
        }
    }

    public void setPixel(int xI, int yI, Pixel pixel){
        if (xI >= 0 && xI < grid.size() && yI >= 0 && yI < grid.get(0).size()) {
            grid.get(xI).set(yI, pixel);
        }
    }

    public void setPixels(int xI, int yI, int height, int width, Pixel pixel){
        for(int i = xI; i < xI + width && i < grid.size(); i++){
            for(int j = yI; j < yI + height && j < grid.get(0).size(); j++){
                grid.get(i).set(j, pixel);
            }
        }
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public ArrayList<ArrayList<Pixel>> getGrid(){ return grid; }


}
