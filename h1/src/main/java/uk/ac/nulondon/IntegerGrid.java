package uk.ac.nulondon;

public class IntegerGrid {
    private int[][] grid;

    // Constructs a grid with given row and column sizes
    public IntegerGrid(int row, int col) {
        grid = new int[row][col];
    }

    // Constructs a grid by deep copying an existing 2D array
    public IntegerGrid(int[][] inputGrid) {
        if (inputGrid != null && inputGrid.length > 0 && inputGrid[0].length > 0) {
            grid = new int[inputGrid.length][inputGrid[0].length];
            for (int i = 0; i < inputGrid.length; i++) {
                for (int j = 0; j < inputGrid[0].length; j++) {
                    grid[i][j] = inputGrid[i][j];
                }
            }
        } else {
            grid = new int[0][0];
        }
    }

    // Populates the grid with each cell as the sum of its indices plus a given number
    public void populate(int s){
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                grid[i][j] = i+j+s;
            }
        }
    }

    // Returns a string representation of the grid
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                s += grid[i][j] + " ";
            }
            s+="\n";
        }
        return s;
    }

    // Deletes a specified row from the grid
    public void deleteRow(int r){
        int[][] newGrid = new int[getRowSize() - 1][getColSize()];
        for (int i = 0, newRow = 0; i < getRowSize(); i++) {
            if (i != r) {
                for (int j = 0; j < getColSize(); j++) {
                    newGrid[newRow][j] = grid[i][j];
                }
                newRow++;
            }
        }
        grid = newGrid;
    }


    // Returns the number of rows in the grid
    public int getRowSize(){ return  grid.length; }
    // Returns the number of columns in the grid
    public int getColSize() {
        if(getRowSize() == 0)
            return 0;
        return grid[0].length;
    }

}

