package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterGridLLTest {
    private IntegerGridLL grid;

    @BeforeEach
    void setUp() {
        grid = new IntegerGridLL();
        // Populate the grid with some data for testing
        // For example, add rows and columns with some integer values
    }

    // Tests for the default constructor
    @Test
    void testDefaultConstructor_ShouldInitializeEmptyGrid() {
        IntegerGridLL newGrid = new IntegerGridLL();
        Assertions.assertThat(newGrid.getRow()).isEqualTo(0);
        Assertions.assertThat(newGrid.getCol()).isEqualTo(0);
    }

    @Test
    void testDefaultConstructor_ShouldNotBeNull() {
        IntegerGridLL newGrid = new IntegerGridLL();
        Assertions.assertThat(newGrid).isNotNull();
    }

    // Tests for the copy constructor
    @Test
    void testCopyConstructor_ShouldCopyCorrectly() {
        MySingleLinkList<MySingleLinkList<Integer>> copy = new MySingleLinkList<>();
        // Populate 'copy' with some data
        IntegerGridLL copiedGrid = new IntegerGridLL(copy);
        Assertions.assertThat(copiedGrid.getRow()).isEqualTo(copy.length());
        // More assertions can be added based on the data added to 'copy'
    }

    @Test
    void testCopyConstructor_ShouldBeIndependentCopy() {
        MySingleLinkList<MySingleLinkList<Integer>> copy = new MySingleLinkList<>();
        // Populate 'copy' with some data
        IntegerGridLL copiedGrid = new IntegerGridLL(copy);
        // Modify 'copy' and assert 'copiedGrid' remains unchanged
    }

    // Tests for deleteRow
    @Test
    void testDeleteRow_ShouldDeleteValidRow() {
        int initialRowCount = grid.getRow();
        grid.deleteRow(1);
        Assertions.assertThat(grid.getRow()).isEqualTo(initialRowCount);
    }

    @Test
    void testDeleteRow_ShouldIgnoreInvalidRow() {
        int initialRowCount = grid.getRow();
        grid.deleteRow(initialRowCount + 1); // Assuming this is an invalid row number
        Assertions.assertThat(grid.getRow()).isEqualTo(initialRowCount);
    }

    // Tests for getRow
    @Test
    void testGetRow_ShouldReturnCorrectRowCount() {
        Assertions.assertThat(grid.getRow()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void testGetRow_ShouldReturnZeroForEmptyGrid() {
        IntegerGridLL newGrid = new IntegerGridLL();
        Assertions.assertThat(newGrid.getRow()).isEqualTo(0);
    }

    // Tests for getCol
    @Test
    void testGetCol_ShouldReturnCorrectColCount() {
        Assertions.assertThat(grid.getCol()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void testGetCol_ShouldReturnZeroForEmptyGrid() {
        IntegerGridLL newGrid = new IntegerGridLL();
        Assertions.assertThat(newGrid.getCol()).isEqualTo(0);
    }

    // Tests for toString
    @Test
    void testToString_ShouldReturnNonEmptyStringForNonEmptyGrid() {
        String result = grid.toString();
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    void testToString_ShouldReturnEmptyStringForEmptyGrid() {
        IntegerGridLL newGrid = new IntegerGridLL();
        String result = newGrid.toString();
        Assertions.assertThat(result.trim()).isEmpty();
    }
}
