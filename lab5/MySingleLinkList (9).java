package uk.ac.nulondon;

import java.util.Stack;

public class MySingleLinkList<T> {

    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;
        private Node(T elem) {
            data = elem;
            next = null;
        }
    }

    public MySingleLinkList() {
        head = null;
    }

    // Add an item to the front of the list
    public void add(T data) {
        // Make a new Node with data
        Node<T> temp = new Node<>(data);

        if (head == null) {
            head = temp;
        }
        // List is not empty
        else {
            temp.next = head;
            head = temp;
        }
    }

    // Get the head node a list
    public Node<T> getHead() {
        return head;
    }

    /**
     * Searches the list for a match and returns where the given element is in the list
     * @param element the data we're looking for
     * @return the index of the given element, -1 if empty
     */
    public int indexOf(T element) {
        int i = -1;

        // If the list is empty, return
        if (head == null) {
            return i;
        }

        boolean found = false;
        Node<T> iter = head;

        // Iterate until we find the element
        while (iter != null && !found) {
            if (iter.data.equals(element)) {
                found = true;
            }
            i++;
            iter = iter.next;
        }

        return i;
    }

    /**
     * Interleave the given list with this list
     * @param givenList the list we want to interleave
     */
    public MySingleLinkList<T> interleave(Node<T> givenList) {
        MySingleLinkList<T> result = new MySingleLinkList<>();

        Node<T> iter1 = head;
        Node<T> iter2 = givenList;

        // While loop will continue as long as either list still has elements to add
        while (iter1 != null || iter2 != null) {
            if (iter1 != null) {
                result.add(iter1.data);
                iter1 = iter1.next;
            }

            if (iter2 != null) {
                result.add(iter2.data);
                iter2 = iter2.next;
            }
        }
        return result;
    }

    /**
     * Function that shifts this list by one to the left,
     * making the head the tail and second-to-first the head
     */
    public void shiftByOne() {

        // Empty or single element don't need shifting
        if (head == null || head.next == null) {
            return;
        }

        // Keep track of the new head and last node
        Node<T> newHead = head.next;
        Node<T> lastNode = head;

        // Get lastNode to the end of the list
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        // Head becomes the last element
        lastNode.next = head;
        head.next = null;

        // Update the head
        head = newHead;
    }

    /**
     * Determines if this list is a palindrome
     * @return true if this list is a palindrome, false if not
     */
    public boolean palindrome() {

        // Empty and one-element are palindromes
        if (head == null || head.next == null) {
            return true;
        }

        Stack<T> myStack = new Stack<>();

        // Keep track of two iterators, one that's going twice as fast
        Node<T> slow = head;
        Node<T> fast = head;

        // Add the first half of the list to the stack
        while (fast != null && fast.next != null) {
            myStack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        // Handle odd case
        if (fast != null) {
            slow = slow.next;
        }

        // Perform the check
        while (slow != null) {
            if (!(slow.data == myStack.pop())) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }

    /**
     * Convert the linked list to a string with space-separated values
     *
     * @return a string with every value in the list
     */
    @Override
    public String toString() {
        // If empty we return empty string
        if(head == null) {
            return "";
        }

        StringBuilder val = new StringBuilder();

        // Iterate but stop at last node
        Node<T> iter = head;
        while(iter.next != null) {
            val.append(iter.data.toString());
            val.append(" ");
            iter = iter.next;
        }

        // Copy value from tail and add newline
        val.append(iter.data.toString());
        return val.toString();
    }

    public static void main(String[] args) {

    }
}