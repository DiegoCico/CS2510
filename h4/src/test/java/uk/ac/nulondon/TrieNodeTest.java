package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieNodeTest {
    @Test
    public void testNodeInitialization() {
        TrieNode node = new TrieNode('A');
        assertNotNull(node.getChildren(), "Children array should be initialized.");
        assertEquals('A', node.getCharacter(), "Character should be set correctly.");
    }

    @Test
    public void testSetAndGetChildren() {
        TrieNode parent = new TrieNode();
        TrieNode child = new TrieNode('B');
        parent.setChildren(1, child); // 'B' - 'A' = 1
        assertEquals(child, parent.getChildren()[1], "Child should be set correctly.");
    }


}
