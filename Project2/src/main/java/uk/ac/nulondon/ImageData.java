package uk.ac.nulondon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImageData {
    // variable to store width
    int width;
    // variable to store height
    int height;
    // graph variable
    Graph graph;
    //TODO: CALCULATIONS


    /**
     * @param file will be the filepath to an image that will be converted into a graph
     *             based on a LinkedList of Lists of Integers. It will throw an exception if
     *             the filepath does not exist.
     */
    public void importImage(String file) {
        try{
            BufferedImage image = ImageIO.read(new File(file));

            width = image.getWidth();
            height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    graph.addEdge(rgb);
                }
                graph = new Graph();
            }
        } catch (IOException e) {
            System.out.println("IMAGE NOT FOUND " + e );
        }
    }

    /**
     * @param file this is the filepath where the altered image will be stored at.
     *             This method will convert an altered graph to an image and be
     *             displayed to the user.
     */
    public void exportImage(String file) {
        try{
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            width = image.getWidth();
            height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    image.setRGB(x, y, rgb);
                }
            }

            ImageIO.write(image, "png", new File(file));
            System.out.println("IMAGE EXPORTED SUCCESSFULLY");
        } catch (IOException e) {
            System.out.println("IMAGE NOT FOUND " + e );
        }
    }

    public int br(int[] intArr){
        int sum = 0;
        for(int i = 0; i < intArr.length; i++){
            sum += intArr[i];
        }
        return sum / intArr.length;
    }

    public int getEnergy(int x, int y){
        int horizontal = 0;
        int vertical = 0;
        int br = 0;

        if(x-- < width){
            throw in
        }

        try {
        for(int i = 0; i < 8; i++) {

        }
        } catch (IOException e) {

        }


        return horizontal + vertical;
    }


    /**
     * This class Graph contains how the image will be represented
     * using a LinkedList of List of Integers
     */
    class Graph {
        private LinkedList<List<Integer>> adjLists;

        /**
         * Constructor for graph
         */
        public Graph() {
            adjLists = new LinkedList<>();
        }

        /**
         * @param vertex is the vertex data to be added into the graph
         */
        void addEdge(int vertex) {
            this.adjLists.getLast().addLast(vertex);
        }

        /**
         * @param vertex vertex data to get the data in the graph
         * @param edge   edge data to get the data in the graph
         */
        void getVertex(int vertex, int edge) {
            this.adjLists.get(edge).get(vertex);
        }
    }

}
