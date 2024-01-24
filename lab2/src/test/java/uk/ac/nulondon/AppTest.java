package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class AppTest {
    @Test
    void startsWithYTest(){
        Assertions.assertThat(startsWithY("no")).isEqualTo(false);
        Assertions.assertThat(startsWithY("Yes")).isEqualTo(true);
        Assertions.assertThat(startsWithY("maybe")).isEqualTo(false);
    }

    @Test
    void bingoWordTest(){
        Assertions.assertThat(bingoWord("Bingo")).isEqualTo("B 5");
        Assertions.assertThat(bingoWord("no")).isEqualTo("N 2");
        Assertions.assertThat(bingoWord("win")).isEqualTo("W 3");
    }
}
