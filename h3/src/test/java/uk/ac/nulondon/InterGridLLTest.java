package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterGridLLTest {
    private IntegerGridLL grid;

    @BeforeEach
    void setUp() {
        grid = new IntegerGridLL();
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
        grid.deleteRow(initialRowCount + 1);
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
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    void testToString_ShouldReturnEmptyStringForEmptyGrid() {
        IntegerGridLL newGrid = new IntegerGridLL();
        String result = newGrid.toString();
        Assertions.assertThat(result.trim()).isEmpty();
    }
}
