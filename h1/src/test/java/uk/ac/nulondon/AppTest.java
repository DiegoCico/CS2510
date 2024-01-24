package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class AppTest {
    // Tests for the constructor with dimensions
    @Test
    void constructorWithDimensionsTest1() {
        IntegerGrid grid = new IntegerGrid(3, 4);
        Assertions.assertThat(grid.getRowSize()).isEqualTo(3);
        Assertions.assertThat(grid.getColSize()).isEqualTo(4);
    }

    @Test
    void constructorWithDimensionsTest2() {
        IntegerGrid grid = new IntegerGrid(0, 0);
        Assertions.assertThat(grid.getRowSize()).isEqualTo(0);
        Assertions.assertThat(grid.getColSize()).isEqualTo(0);
    }

    // Tests for the constructor with array
    @Test
    void constructorWithArrayTest1() {
        int[][] initialArray = {{1, 2}, {3, 4}};
        IntegerGrid grid = new IntegerGrid(initialArray);
        Assertions.assertThat(grid.toString()).isEqualTo("1 2 \n3 4 \n");
    }

    @Test
    void constructorWithArrayTest2() {
        int[][] initialArray = new int[0][0];
        IntegerGrid grid = new IntegerGrid(initialArray);
        Assertions.assertThat(grid.toString()).isEqualTo("");
    }

    // Tests for populate method
    @Test
    void populateMethodTest1() {
        IntegerGrid grid = new IntegerGrid(2, 3);
        grid.populate(0);
        Assertions.assertThat(grid.toString()).isEqualTo("0 1 2 \n1 2 3 \n");
    }

    @Test
    void populateMethodTest2() {
        IntegerGrid grid = new IntegerGrid(3, 3);
        grid.populate(5);
        Assertions.assertThat(grid.toString()).isEqualTo("5 6 7 \n6 7 8 \n7 8 9 \n");
    }

    // Tests for deleteRow method
    @Test
    void deleteRowMethodTest1() {
        IntegerGrid grid = new IntegerGrid(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        grid.deleteRow(1);
        Assertions.assertThat(grid.toString()).isEqualTo("1 2 \n5 6 \n");
    }

    @Test
    void deleteRowMethodTest2() {
        IntegerGrid grid = new IntegerGrid(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        grid.deleteRow(0); // Delete first row
        Assertions.assertThat(grid.toString()).isEqualTo("3 4 \n5 6 \n");
    }

    @Test
    void deleteRowMethodTest3() {
        IntegerGrid grid = new IntegerGrid(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        grid.deleteRow(2); // Delete last row
        Assertions.assertThat(grid.toString()).isEqualTo("1 2 \n3 4 \n");
    }

    // Tests for getRowSize method
    @Test
    void getRowSizeMethodTest1() {
        IntegerGrid grid = new IntegerGrid(3, 5);
        Assertions.assertThat(grid.getRowSize()).isEqualTo(3);
    }

    @Test
    void getRowSizeMethodTest2() {
        IntegerGrid grid = new IntegerGrid(new int[][]{{1}, {2}, {3}});
        grid.deleteRow(1);
        Assertions.assertThat(grid.getRowSize()).isEqualTo(2);
    }

    // Tests for getColumnSize method
    @Test
    void getColumnSizeMethodTest1() {
        IntegerGrid grid = new IntegerGrid(4, 4);
        Assertions.assertThat(grid.getColSize()).isEqualTo(4);
    }

    @Test
    void getColumnSizeMethodTest2() {
        IntegerGrid grid = new IntegerGrid(new int[][]{{1, 2}, {3, 4}});
        Assertions.assertThat(grid.getColSize()).isEqualTo(2);
    }
}
