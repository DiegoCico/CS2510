package uk.ac.nulondon;

import java.util.List;
import java.util.Stack;

public class ImageEdit {
    private Stack<ImageData> history = new Stack<>();
    private ImageData imageData;

    public int mostBlueCol(){
        int[] BLUE_COLOR = {0,0,255};

        int index = 0;
        int blueSoFar = 0;

        for(int i = 0; i < imageData.height; i++){
            for(int j = 0; j < imageData.getPixelRow(i).getSizeLink(); j++){
                int blueSum = imageData.sumColBlue(j);
                if(blueSum > blueSoFar){
                    blueSoFar = blueSum;
                    index = i;
                }
            }
        }
        return index;
    }

    private int lowestEnergyCol(){
        int[] RED_COLOR = {255,0,0};

        int index = 0;
        List<Pixel> seam = imageData.getSeam();
        for(int i = 0; i < imageData.getPixels().size(); i++){
            Pixel pixel = imageData.getPixels().get(i);
            for(int j = 0; j < imageData.getPixelRow(i).getSizeLink(); j++){
                if(pixel == seam.get(i)){
                    pixel.setPixel(RED_COLOR[0], RED_COLOR[1], RED_COLOR[2]);
                }
                pixel = pixel.getRight();
            }
        }
        return index;

    }


//    private int lowestEnergyCol(){
//        int[] RED_COLOR = {255,0,0};
//
//        int index = 0;
//        List<Pixel> seam = imageData.getSeam();
//        for(int i = 0; i < imageData.getPixels().size(); i++){
//            Pixel pixel = imageData.getPixels().get(i);
//            for(int j = 0; j < imageData.getPixelRow(i).getSizeLink(); j++){
//                if(pixel == seam.get(i)){
//                    pixel.setPixel(RED_COLOR[0], RED_COLOR[1], RED_COLOR[2]);
//                }
//                pixel = pixel.getRight();
//            }
//        }
//        return index;
//
//    }


}
