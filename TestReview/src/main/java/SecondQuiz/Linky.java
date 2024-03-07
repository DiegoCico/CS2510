package SecondQuiz;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.LinkedList;

public class Linky {
    public static void main(String[] args){
        LinkedList<String> l = new LinkedList<String>();

        l.add("1+2");
        l.add("test");
        l.add("diego");


        System.out.println(l);

        Iterator it = l.iterator();
        int c = 0;
        while(it.next() != "diego"){
            System.out.println("found diego " + c) ;
            c++;
        }
    }
}
