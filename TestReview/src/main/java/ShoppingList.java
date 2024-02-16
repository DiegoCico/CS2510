import java.util.ArrayList;
import java.util.List;

public class ShoppingList<T> {
    private ArrayList<T> item;

    public ShoppingList() {}

    public ShoppingList(ArrayList<T> l){
        setItem(l);
    }

    public ArrayList getItem(){ return item; }

    public void setItem(ArrayList<T> l){
        for(int i = 0; i<l.size();i++){
            item.add(l.get(i));
        }
    }
}
