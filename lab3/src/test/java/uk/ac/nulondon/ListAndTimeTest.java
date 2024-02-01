package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class ListAndTimeTest {

    @Test
    void getTimeTablesTest(){
        Time t = new Time(1,2,3);
        Assertions.assertThat(t.getHour()).isEqualTo(1);
        Assertions.assertThat(t.getSecond()).isEqualTo(2);
        Assertions.assertThat(t.getMinute()).isEqualTo(3);
    }

    @Test
    void sameTimeTest(){
        Time t = new Time(1,2,3);
        Time t2 = new Time(1,2,3);
        Time t3 = new Time(4,5,6);
        Assertions.assertThat(t.sameTime(t2)).isEqualTo(true);
        Assertions.assertThat(t2.sameTime(t3)).isEqualTo(false);
    }

    @Test
    void myListTest(){
        int[] a = {1,2,3};
        MyList ml = new MyList(a);
        int[] b = {};
        MyList ml2 = new MyList(b);
        Assertions.assertThat(ml.toString()).isEqualTo("1, 2, 3");
        Assertions.assertThat(ml2.toString()).isEqualTo("");
    }
}
