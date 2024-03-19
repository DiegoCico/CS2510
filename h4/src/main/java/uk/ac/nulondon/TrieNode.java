package uk.ac.nulondon;

/**
 * Represents a single node in a Trie structure.
 * Each node contains an array of children nodes and a character.
 */
public class TrieNode {
    private final TrieNode[] children = new TrieNode[26];
    private Character character;

    /**
     * Constructs a TrieNode with all children initialized to null.
     */
    public TrieNode() {
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }

    /**
     * Constructs a TrieNode with a specified character.
     *
     * @param character The character this node represents.
     */
    public TrieNode(Character character){
        this();
        this.character = character;
    }

    /**
     * Sets the specified child node at the given index.
     *
     * @param index    The index where the child node is to be set.
     * @param trieNode The TrieNode to set at the specified index.
     */
    public void setChildren(int index, TrieNode trieNode){
        children[index] = trieNode;
    }

    /**
     * Retrieves all children of this node.
     *
     * @return An array of TrieNode representing this node's children.
     */
    public TrieNode[] getChildren() {
        return children;
    }

    /**
     * Checks if this node is the end of a word.
     *
     * @return {@code true} if this node has no children, otherwise {@code false}.
     */
    public boolean isEndWord(){
        return children.length == 0;
    }

    /**
     * Retrieves the character this node represents.
     *
     * @return The character this node represents.
     */
    public Character getCharacter() {
        return character;
    }
}
