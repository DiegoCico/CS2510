package uk.ac.nulondon;

import jdk.dynalink.Operation;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ImageEdit {
    private Stack<List<Pixel>> history = new Stack<>();
    private ImageData imageData;


    public ImageEdit(ImageData imageData) {
        this.imageData = imageData;
    }

    public void highlightColumn(String operation){
        switch(operation){
            case "blue":
                history.push(highlightBlue());
                break;
            default:
                history.push(highlightRed());
                break;
        }
    }

    private List<Pixel> highlightBlue() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for(Pixel p: imageData.getBlueSeam()){
            p.setPixel(0, 0, 255);
            pixels.add(p);
        }
        return pixels;
    }

    private List<Pixel> highlightRed() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for(Pixel p: imageData.getSeam()){
            p.setPixel(255, 0, 0);
            pixels.add(p);
        }
        return pixels;
    }


    public void deleteColumn(List<Pixel> pixels) {
        for(int i =0; i < imageData.height; i++){
            Pixel iter = imageData.getPixels().get(i);
            while(iter != null){
                if(pixels.get(i) == iter){
                   pixels.get(i).setLeft(pixels.get(i).getRight());
                   pixels.get(i).setRight(pixels.get(i).getLeft());
                   break;
                } else {
                    iter = iter.getRight();
                }
            }
        }
    }

    private void undo(){history.pop();}

    public int editCount(){ return history.size(); }


    public static void main(String[] args){
        ImageEdit imageEdit = new ImageEdit(new ImageData());

        imageEdit.imageData.importImage("image/beach.png");



        imageEdit.highlightColumn("no");

        imageEdit.imageData.exportImage("image/beach2.png");
        imageEdit.deleteColumn(imageEdit.highlightBlue());

        imageEdit.imageData.exportImage("image/beach3.png");



    }

}
