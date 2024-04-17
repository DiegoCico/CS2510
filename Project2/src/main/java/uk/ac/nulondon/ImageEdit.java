package uk.ac.nulondon;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class ImageEdit {
    // This will store the deleted seam
    private Stack<List<Pixel>> history = new Stack<>();
    //Instance of ImageData class
    public ImageData imageData;

    /**
     * This is a constructor for ImageEdit using ImageData
     */
    public ImageEdit() {
        imageData = new ImageData();
    }

    /**
     * This is an abstracted method to highlight a column
     * @param operation will provide a string to determine what seam will be highlighted
     * @return this will return the list of pixels that are highlighted
     */
    public List<Pixel> highlightColumn(String operation){
        switch(operation){
            case "b":
                return highlightBlue();
            default:
                return highlightRed();
        }
    }

    /**
     * This is a helper function to highlight the greatest-blue seam
     * @return this returns the list of pixels to be deleted
     */
    private List<Pixel> highlightBlue() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        ArrayList<Pixel> oldPixels = new ArrayList<>();
        for(Pixel p: imageData.getSeam(true)){
            oldPixels.add(new Pixel(p));
            p.setPixel(0, 0, 255);
            pixels.add(p);
        }
        history.push(oldPixels);
        return pixels;
    }

    /**
     * This is a helper function to highlight the lowest-energy seam
     * @return this returns the list of pixels to be deleted
     */
    private List<Pixel> highlightRed() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        ArrayList<Pixel> oldPixels = new ArrayList<>();
        for(Pixel p: imageData.getSeam(false)){
            oldPixels.add(new Pixel(p));
            p.setPixel(255, 0, 0);
            pixels.add(p);
        }
        history.push(oldPixels);
        return pixels;
    }

    /**
     * This method will save and delete the list of pixels chosen from highlightColumn
     * It will remove the references between the pixels in seam to be deleted
     * @param pixelsToRemove this is the list of pixels chosen from highlightColumn
     */
    public void deleteColumn(List<Pixel> pixelsToRemove) {
        try {
            history.push(pixelsToRemove);
            int count = pixelsToRemove.size() - 1;
            for (Pixel pixel : pixelsToRemove) {
                if (pixel.getLeft() != null && pixel.getRight() != null) {
                    pixel.getLeft().setRight(pixel.getRight());
                    pixel.getRight().setLeft(pixel.getLeft());
                }
                if (pixel.getLeft() == null) {
                    imageData.getPixels().set(count, pixel.getRight());
                    pixel.getRight().setLeft(null);
                } else if (pixel.getRight() == null) {
                    pixel.getLeft().setRight(null);
                }
                count--;
            }
        }
        //TODO: ASK ABOUT EXCEPTION
        catch (Exception e) {
           System.out.println("No more deletes left");
        }
    }

    /**
     * This will add references back with the seam deleted and undo an edit
     */
    public void undo(){
        try {
            history.pop();
            List<Pixel> add = history.pop();
            int count = add.size() - 1;
            for (Pixel p : add) {
                if (p.getLeft() != null) {
                    p.getLeft().setRight(p);
                } else {
                    imageData.getPixels().set(count, p);
                }

                if (p.getRight() != null) {
                    p.getRight().setLeft(p);
                } else {
                    p.getLeft().setRight(p);
                }

                count--;
            }
        }
        catch (EmptyStackException e) {
            System.out.println("Stack is empty");
        }
    }

    /**
     * This will return how many edits have been made
     * @return the number of edits
     */
    public int editCount(){ return history.size(); }
}
