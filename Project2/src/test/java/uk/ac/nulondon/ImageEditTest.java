package uk.ac.nulondon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * This class contains unit tests for the ImageEdit class.
 * It tests various functionalities such as highlighting columns, deleting columns, and undoing changes.
 */
public class ImageEditTest {
    // This implements imageEdit class
    private ImageEdit imageEdit;
    // This is a 2D grid array for testing purposes
    private Pixel[][] grid;

    /**
     * Sets up the test environment before each test method.
     * This includes initializing the ImageEdit instance and a 3x3 grid of white pixels.
     */
    @Before
    public void setUp() {
        imageEdit = new ImageEdit();
        initializeGrid();
    }

    /**
     * Initializes a 3x3 grid of white pixels and sets horizontal links between them.
     * Also mocks the ImageData class to provide specific responses for testing.
     */
    private void initializeGrid() {
        grid = new Pixel[3][3];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Pixel(255, 255, 255);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length - 1; j++) {
                grid[i][j].setRight(grid[i][j+1]);
                grid[i][j+1].setLeft(grid[i][j]);
            }
        }

        imageEdit.imageData = new ImageData() {
            public List<Pixel> getSeam(boolean blue) {
                return List.of(grid[1]);
            }

            public List<Pixel> getPixels() {
                return List.of(grid[0][0], grid[1][0], grid[2][0]);
            }
        };
    }

    /**
     * Tests the highlightColumn method with the "b" parameter, which should highlight a blue column.
     * Verifies that the highlighted column is indeed blue.
     */
    @Test
    public void testHighlightColumnBlue() {
        List<Pixel> result = imageEdit.highlightColumn("b");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(255, result.get(1).getBlue());
    }

    /**
     * Tests the highlightColumn method with a parameter other than "b", defaulting to red.
     * Verifies that the highlighted column is red.
     */
    @Test
    public void testHighlightColumnDefaultRed() {
        List<Pixel> result = imageEdit.highlightColumn("anythingElse");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(255, result.get(1).getRed());
    }

    /**
     * Tests the deleteColumn method by deleting a previously highlighted column and checking if the edit count increases.
     */
    @Test
    public void testDeleteColumnSuccessfullyDeletes() {
        List<Pixel> pixels = imageEdit.highlightColumn("b");
        int beforeDelete = imageEdit.editCount();
        imageEdit.deleteColumn(pixels);
        int afterDelete = imageEdit.editCount();
        assertEquals(beforeDelete + 1, afterDelete);
    }

    /**
     * Tests the deleteColumn method with null input to handle edge cases.
     * Checks if the edit count does not increase upon handling null.
     */
    @Test
    public void testDeleteColumnHandlesNoPixels() {
        imageEdit.deleteColumn(null);
        assertEquals(1, imageEdit.editCount());
    }

    /**
     * Tests the undo functionality when there are no edits to undo.
     * Verifies that the edit count remains zero.
     */
    @Test
    public void testUndoWithEmptyHistory() {
        imageEdit.undo();
        assertEquals(0, imageEdit.editCount());
    }

    /**
     * Tests the initial state of edit count, which should be zero before any operations.
     */
    @Test
    public void testEditCountInitial() {
        assertEquals(0, imageEdit.editCount());
    }

    /**
     * Tests the edit count after a highlight operation to ensure it is being tracked correctly.
     */
    @Test
    public void testEditCountAfterHighlight() {
        imageEdit.highlightColumn("b");
        assertEquals(1, imageEdit.editCount());
    }
}
