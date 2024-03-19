package uk.ac.nulondon;

/**
 * A Trie data structure implementation for efficient storage and retrieval
 * of strings. This Trie implementation is specifically designed for uppercase
 * alphabetic characters.
 */
public class Trie {
    private TrieNode root;

    /**
     * Constructs an empty Trie with a root node.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word The word to insert into the Trie. It should consist of
     *             uppercase alphabetic characters only.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'A';
            if (current.getChildren()[index] == null) {
                TrieNode temp = new TrieNode(ch);
                current.setChildren(index, temp);
                current = temp;
            } else {
                current = current.getChildren()[index];
            }
        }
    }

    /**
     * Searches for a word in the Trie.
     *
     * @param word The word to search for in the Trie.
     * @return {@code true} if the word is found in the Trie, otherwise {@code false}.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'A';
            if (current.getChildren()[index] == null) {
                return false;
            }
            current = current.getChildren()[index];
        }
        return true;
    }

    /**
     * The main method to demonstrate the usage of the Trie class.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("CAB");
        trie.insert("CAT");
        trie.insert("CABIN");
        trie.insert("CART");
        trie.insert("CATCH");
        trie.insert("COB");
        trie.insert("CORE");
        trie.insert("COME");

        System.out.println(trie.search("CAB"));
        System.out.println(trie.search("CATCH"));
        System.out.println(trie.search("CAST"));
    }

}
