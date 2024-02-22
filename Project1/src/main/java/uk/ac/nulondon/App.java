package uk.ac.nulondon;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The App class serves as the entry point for the image processing application.
 * It interacts with the user through the command line, allowing them to perform
 * various operations on images such as finding and coloring the bluest column,
 * removing random rows, and undoing changes. The application supports processing
 * of both existing image files and newly created pixel-based images.
 */
public final class App {
    /**
     * The main method starts the application. It handles user input and
     * directs the flow of the image processing operations.
     * <p>
     * The user is first prompted to enter a source file path for an existing image
     * or "N/A" to create a new pixel-based image. Based on the input, the user
     * can then choose various operations:
     * <ul>
     *     <li>For an existing image, options include finding the bluest column,
     *     removing a random row, undoing the last operation, and exporting the
     *     modified image.</li>
     *     <li>For a new pixel-based image, options include setting individual pixels,
     *     setting multiple pixels, and exporting the image.</li>
     * </ul>
     * The program continues to run until the user decides to quit.
     * </p>
     *
     * @param args Command line arguments (not used in this application).
     * @throws IOException If an I/O error occurs when reading the image file or exporting images.
     */
    public static void main(String[] args) throws IOException {

        // Get the source file from the user
        Scanner in = new Scanner(System.in);
        System.out.println("What is the source for the file? || type N/A if none");
        String src = in.nextLine();
        ImageProcessor ip = new ImageProcessor(new File(src));

        if (!src.equals("N/A")) {
            String userInput;
            while (true) {
                // Display options to the user
                System.out.println("What would you like to do?");
                System.out.println("B for bluest blue");
                System.out.println("R for remove random row");
                System.out.println("U for undo");
                // Process user input
                userInput = in.nextLine();
                if (userInput.equalsIgnoreCase("q")) {
                    break;
                }

                switch (userInput.toLowerCase()) {
                    case "b":
                        int col = ip.bluestBlueCol();
                        ip.colorColumnRed(col);
                        ip.exportImage("src/img/process/newImg.png");
                        System.out.println("Would you like to remove it? (d for yes)");
                        if (in.nextLine().toLowerCase().equals("d")) {
                            ip.removeSpecificCol(col);
                            ip.exportImage("src/img/output/newImg.png");
                        }
                        break;
                    case "r":
                        int random = ip.removeRandomCol();
                        ip.colorColumnRed(random);
                        ip.exportImage("src/img/process/newImg.png");
                        System.out.println("Would you like to remove it? (d for yes)");
                        if (in.nextLine().toLowerCase().equals("d")) {
                            ip.removeSpecificCol(random);
                            ip.exportImage("src/img/output/newImg.png");
                        }
                        break;
                    case "u":
                        ip.undo();
                        ip.exportImage("src/img/output/newImg.png");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Width size in pixel");
            int x = in.nextInt();
            System.out.println("Height size in pixel");
            int y = in.nextInt();

            ComputedPixelImage cp = new ComputedPixelImage(x,y);
            String userInput;
            while (true){
                System.out.println("What would you like to do?");
                System.out.println("A for set pixel");
                System.out.println("B for set pixels");
                System.out.println("C for export");
                userInput = in.next();
                switch (userInput.toLowerCase()){
                    case "a":
                        System.out.println("x");
                        int xI = in.nextInt();
                        System.out.println("y");
                        int yI = in.nextInt();
                        System.out.println("RGB");
                        String rgb = in.next();
                        Pixel pixel = new Pixel(rgb);
                        cp.setPixel(xI,yI,pixel);
                        break;
                    case "b":
                        System.out.println("x");
                        int iX = in.nextInt();
                        System.out.println("y");
                        int iY = in.nextInt();
                        System.out.println("Width size in pixel");
                        int width = in.nextInt();
                        System.out.println("Height size in pixel");
                        int heigth = in.nextInt();
                        System.out.println("RGB");
                        String rgb2 = in.next();
                        Pixel pixel2 = new Pixel(rgb2);
                        cp.setPixels(iX,iY,width,heigth,pixel2);
                        break;
                    case "c":
                        cp.exportToPNG("src/img/computedPixelFile/newImg.png");
                        return;
                    default:
                        System.out.println("Enter Valid Option");
                }
            }
        }
    }
}
