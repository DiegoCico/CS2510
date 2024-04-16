package uk.ac.nulondon;


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
        for(Pixel p: imageData.getSeam(true)){
            p.setPixel(0, 0, 255);
            pixels.add(p);
        }
        return pixels;
    }

    private List<Pixel> highlightRed() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for(Pixel p: imageData.getSeam(false)){
            p.setPixel(255, 0, 0);
            pixels.add(p);
        }
        return pixels;
    }


    public void deleteColumn(List<Pixel> pixelsToRemove) {
        for (Pixel pixel : pixelsToRemove) {
                if (pixel.getLeft() != null) {
                    pixel.getLeft().setRight(pixel.getRight());
                }
                if (pixel.getRight() != null) {
                    pixel.getRight().setLeft(pixel.getLeft());
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

        imageEdit.deleteColumn(imageEdit.highlightRed());
        imageEdit.imageData.exportImage("image/beach3.png");

        System.out.println(imageEdit.editCount());

        imageEdit.undo();


    }

}
