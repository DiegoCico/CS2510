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


//    public void deleteColumn(List<Pixel> pixels) {
//        for(int i =0; i < imageData.height; i++){
//            Pixel iter = imageData.getPixels().get(i);
//            while(iter != null){
//
//                if(pixels.get(i) == iter){
//                    System.out.println("HOWDY");
//                   pixels.get(i).setLeft(pixels.get(i).getRight());
//                   pixels.get(i).setRight(pixels.get(i).getLeft());
//                   System.out.println(iter.getSizeLink());
//                   break;
//                } else {
//                    iter = iter.getRight();
//                }
//            }
//        }
//    }

    public void deleteColumn(List<Pixel> pixels) {
        for(int i = 0; i < imageData.height; i++){
            Pixel target = pixels.get(i);  // The pixel in the current row that needs to be removed
            Pixel iter = imageData.getPixels().get(i);  // Starting pixel of the current row

            if (iter == target) {
                System.out.println("HOWDY");
                // If the first pixel in the row is the target, update the start of the row
                imageData.getPixels().set(i, iter.getRight());
            } else {
                // Traverse the linked list to find the pixel before the target
                while (iter != null && iter.getRight() != target) {
                    iter = iter.getRight();
                }
                if (iter != null && iter.getRight() == target) {
                    // Bypass the target pixel
                    iter.setRight(target.getRight());
                }
            }
        }
    }

    private void undo(){history.pop();}

    public int editCount(){ return history.size(); }


    public static void main(String[] args){
        ImageEdit imageEdit = new ImageEdit(new ImageData());

        imageEdit.imageData.importImage("image/beach.png");



        imageEdit.highlightColumn("blue");

        imageEdit.imageData.exportImage("image/beach2.png");

        System.out.println(imageEdit.editCount());

//        imageEdit.undo();


    }

}
