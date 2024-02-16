package uk.ac.nulondon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringFuncsTest {
    @ParameterizedTest
    @CsvSource({
            "'yes',true",
            "'Yes',true",
            "'no',false"
    })
    void startsWithYTest(String word, boolean expected){
       Assertions.assertThat(StringFuncs.startsWithY2(word)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'bingo',B5",
            "'no',N2",
            "'win',W3"
    })
    void bingoWordTest(String word, String expected){
        Assertions.assertThat(StringFuncs.bingoWord2(word)).isEqualTo(expected);
    }
}
