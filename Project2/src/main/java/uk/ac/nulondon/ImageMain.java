package uk.ac.nulondon;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ImageMain {
        /*
        User Interface
            Your program should take the following key inputs:
            “b” show the bluest seam in the image (highlight blue)
            “e” show the seam with the lowest energy in the image (highlight red)
            “d” delete the seam from the image and show the resulting image
            “u” undo by reinserting the previously deleted seam back into the image
            “q” quit the program and export the final image once more
            For example, if the user enters B-D-E-D-U-Q, the program will display the blue highlight, delete the seam, display the lowest energy highlight, delete the seam, restore the most recently deleted seam, and export the final image.
            If there is no seam highlighted, “d” should not delete anything and should prompt the user for an edit selection.
            The user should be able to remove and restore any number of seams. You can experiment and go further by using a GUI to accept these commands, but this is not required.

            Displaying Images
            So that you can verify and see the changes made, the program will export the altered images to a temporary file after each change that is made to the image. These should be named tempIMG_x.png where x is a counter.
            We require you to export changes at every step in this program.
            If the user chooses blue (“b”), then the new image with the bluest seam highlighted in blue will be exported.
            If the user choses lowest energy (“e”), the new image with the lowest energy seam highlighted in red will be exported.
            If the user chooses to delete (“d”), the new image with the seam removed will be exported
            After undoing (“u”), the image with the most recent seam removed (not the highlighted seam) is reinserted and the resulting image is exported
            When the user quits (“q”) the program, we should export the final image once more. This will be the same image as your most recent edit, just with a different name i.e. newImg.png
         */


    private static ImageEdit imageHandler;
    private static int editCount = 0;
    private static String choice = "";
    private static Scanner in;

    /**
     * Print the UI menu options to the user
     */
    private static void printMenu() {
        System.out.println("Please enter a command");
        System.out.println("b - Remove the bluest column");
        System.out.println("r - Remove a random column");
        System.out.println("u - Undo previous edit");
        System.out.println("q - Quit");
    }

    /**
     * Perform an operation based on what the user selected
     */
    private static void handleChoice() {
        String option;
        switch (choice.toLowerCase()) {
            case "b":
                // highlight and export intermediate image
                List<Pixel> blueCol = imageHandler.highlightColumn(choice.toLowerCase());
                imageHandler.imageData.exportImage("tempIMG_0" + editCount + ".png");
                editCount++;

                // ask for confirmation and try to execute
                System.out.println("Remove the bluest column. Continue? (Y/N)");
                option = getUserInput();
                if (option.toUpperCase().equals("Y")) { imageHandler.deleteColumn(blueCol);}
                else imageHandler.undo();
                break;
            case "r":
                // highlight and export intermediate image
                List<Pixel> redCol = imageHandler.highlightColumn("");
                imageHandler.imageData.exportImage("tempIMG_0" + editCount + ".png");
                editCount++;

                // ask for confirmation and try to execute
                System.out.println("Remove a random column. Continue? (Y/N)");
                option = getUserInput();
                if (option.toUpperCase().equals("Y")) { imageHandler.deleteColumn(redCol);}
                else imageHandler.undo();
                break;
            case "u":
                System.out.println("Undo. Continue? (Y/N)");
                option = getUserInput();
                if (option.toUpperCase().equals("Y")) { imageHandler.undo();}
                break;
            case "q":
                System.out.println("Thanks for playing.");
                break;
            default:
                System.out.println("That is not a valid option.");
                break;
        }
    }

    /**
     * Get the user's input. Either a menu selection or confirmation value.
     *
     * @return the user's input
     */
    private static String getUserInput() {
        String keyValue = "";

        // get the user's input
        try {
            keyValue = in.next().toLowerCase();
        } catch (InputMismatchException e) {
            // if user enters anything except a menu option
            System.out.println("Input should be one of the menu options");
        }

        return keyValue;
    }


    public static void main(String[] args) {
        boolean shouldQuit = false;

        in = new Scanner(System.in);

        System.out.println("Welcome! Enter file path");
        String filePath = getUserInput();
        imageHandler = new ImageEdit();

        // import the file
        try {
            imageHandler.imageData.importImage(filePath);
        } catch (Exception e) {
            System.out.println("Failed to import image");
            System.exit(0);
        }

        // display the menu after every edit
        while (!shouldQuit) {
            printMenu();

            // get and handle user input
            choice = getUserInput();
            handleChoice();

            if (choice.equals("q")) {
                shouldQuit = true;
            }
        }

        // After the user exits, export the final image
        imageHandler.imageData.exportImage("newImg.png");
        in.close();

    }

}

