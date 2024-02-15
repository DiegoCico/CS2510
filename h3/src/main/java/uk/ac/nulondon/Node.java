package uk.ac.nulondon;
public class Node<T> {
    T data;
    Node<T> next;
    public Node(T elem) {
        data = elem;
        next = null;
    }
}
