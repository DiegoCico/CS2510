package uk.ac.nulondon;

import java.io.File;
import java.util.Scanner;

public final class App {
    public static void main(String[] args) {
        String YN;
        Scanner in = new Scanner(System.in);
        System.out.println("What is the source for the file?");
        String src = in.nextLine();
        ImageProcessor ip = new ImageProcessor(new File(src));

        String userInput = "";
        while(!userInput.equals("q")) {
            System.out.println("What would you like to do");
            System.out.println("B for bluest blue");
            System.out.println("R for remove random row");
            System.out.println("U for undo");
            System.out.println("Q for quit");
            userInput = in.next();
            switch (userInput.toLowerCase()) {
                case "b":
                    int col = ip.bluestBlueCol();
                    ip.colorColumnRed(col);
                    ip.exportImage("src/img/process/newImg.png");
                    System.out.println("would you like to remove it (d)");
                    YN = in.next();
                    if (YN.toLowerCase().equals("d")) {
                        ip.removeSpecificCol(col);
                        ip.exportImage("src/img/output/newImg.png");
                    }
                case "r":
                    int random = ip.removeRandomCol();
                    ip.colorColumnRed(random);
                    ip.exportImage("src/img/process/newImg.png");
                    System.out.println("would you like to remove it (d)");
                    YN = in.next();
                    if (YN.toLowerCase().equals("d")) {
                        ip.removeSpecificCol(random);
                        ip.exportImage("src/img/output/newImg.png");
                    }
                case "u":
                    ip.undo();
                    ip.exportImage("src/img/output/newImg.png");
            }
          }
    }
}
