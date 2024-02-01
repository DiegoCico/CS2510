package uk.ac.nulondon;

import java.util.ArrayList;

public class MyList {
    int[] a;

    public MyList(int[] l){
        a = new int[l.length];
        for(int i = 0; i<l.length;i++)
            a[i] = l[i];
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i<a.length; i++){
            s += a[i] + ", ";
        }
        if(a.length != 0)
            return s.substring(0,s.length()-2);
        else
            return s;
    }

}
