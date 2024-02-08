package uk.ac.nulondon;

public class IntegerGridLL {
    private MySingleLinkList<MySingleLinkList<Integer>> grid;

    public IntegerGridLL(){
        grid = new MySingleLinkList<MySingleLinkList<Integer>>();
    }

    public IntegerGridLL(MySingleLinkList<MySingleLinkList<Integer>> copy) {
        this.grid = new MySingleLinkList<>();
        MySingleLinkList<Integer> newRow = new MySingleLinkList<>();
        for(int i = 0; i < copy.length(); i++){
            for(int j = 0; i< copy.getAt(i).length(); i++){
                MySingleLinkList<Integer> thisCol = copy.getAt(i);
                newRow.add(thisCol.getAt(j));
            }
            newRow.reverse();
            grid.add(newRow);
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        return s.toString();
    }

}
