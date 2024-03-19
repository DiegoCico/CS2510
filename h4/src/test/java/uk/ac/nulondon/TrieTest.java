package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrieTest {
    @Test
    public void testInsertAndSearch() {
        Trie trie = new Trie();
        trie.insert("CAB");
        trie.insert("CAT");
        trie.insert("CABIN");
        trie.insert("CART");
        trie.insert("CATCH");
        trie.insert("COB");
        trie.insert("CORE");
        trie.insert("COME");

        // Test for words that are inserted
        Assertions.assertThat(trie.search("CAB")).isTrue();
        Assertions.assertThat(trie.search("CAT")).isTrue();
        Assertions.assertThat(trie.search("CABIN")).isTrue();
        Assertions.assertThat(trie.search("CART")).isTrue();
        Assertions.assertThat(trie.search("CATCH")).isTrue();
        Assertions.assertThat(trie.search("COB")).isTrue();
        Assertions.assertThat(trie.search("CORE")).isTrue();
        Assertions.assertThat(trie.search("COME")).isTrue();

        // Test for a word that is not inserted
        Assertions.assertThat(trie.search("CAST")).isFalse();
    }

    @Test
    public void testSearchForNonExistingWord() {
        Trie trie = new Trie();
        trie.insert("HELLO");

        Assertions.assertThat(trie.search("WORLD")).isFalse();
    }

}
