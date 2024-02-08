package uk.ac.nulondon;

/**
 * Represents a two-dimensional grid using linked lists.
 */
public class IntegerGridLL {
    private MySingleLinkList<MySingleLinkList<Integer>> grid;

    /**
     * Constructs an empty IntegerGridLL.
     */
    public IntegerGridLL(){
        grid = new MySingleLinkList<MySingleLinkList<Integer>>();
    }

    /**
     * Constructs a new IntegerGridLL as a copy of an existing grid.
     *
     * @param copy The MySingleLinkList of MySingleLinkList of Integer to be copied.
     */
    public IntegerGridLL(MySingleLinkList<MySingleLinkList<Integer>> copy) {
        this.grid = new MySingleLinkList<>();
        MySingleLinkList<Integer> newRow;
        for(int i = 0; i < copy.length(); i++){
            newRow = new MySingleLinkList<>();
            for(int j = 0; i < copy.getAt(i).length(); i++){
                newRow.add(copy.getAt(i).getAt(j));
            }
            newRow.reverse();
            grid.add(newRow);
        }
    }

    /**
     * Deletes a row from the grid.
     *
     * @param r The row number to be deleted (1-indexed).
     */
    public void deleteRow(int r){
        MySingleLinkList<MySingleLinkList<Integer>> newGrid = new MySingleLinkList<>();
        for(int i = 0; i < grid.length(); i++){
            if(r-1 != i) {
                MySingleLinkList<Integer> newRow = new MySingleLinkList<>();
                for (int j = 0; j < grid.getAt(i).length(); j++) {
                    newRow.add(grid.getAt(i).getAt(j));
                }
                newRow.reverse();
                newGrid.add(newRow);
            }
        }
        grid = newGrid;
    }

    /**
     * Returns the number of rows in the grid.
     *
     * @return The number of rows in the grid.
     */
    public int getRow(){
        return grid.length();
    }

    /**
     * Returns the number of columns in the grid.
     * If the grid is empty, returns 0.
     *
     * @return The number of columns in the grid, or 0 if the grid is empty.
     */
    public int getCol(){
        if(getRow() <= 0) return 0;
        return grid.getAt(0).length();
    }

    /**
     * Returns a string representation of the grid.
     *
     * @return A string representation of the grid.
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < grid.length(); i++){
            for(int j = 0; i < grid.getAt(i).length(); i++){
                s.append(grid.getAt(i).getAt(j));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
