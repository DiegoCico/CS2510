package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

public class ArrayListFuncsTest {
    @Test
    void testSumEventsNormal() {
        List<int[]> testData = List.of(new int[]{1, 12, 5, 3}, new int[]{2, 15, 8, 7});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.sumEvents()).isEqualTo(10); // 3 + 7
    }

    @Test
    void testSumEventsEmptyList() {
        List<int[]> testData = List.of();
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.sumEvents()).isEqualTo(0); // Sum should be 0 for an empty list
    }

    @Test
    void testSumEventsNegativeNumbers() {
        List<int[]> testData = List.of(new int[]{1, -2, 5, -3}, new int[]{2, 3, -8, -7});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.sumEvents()).isEqualTo(-10); // -3 + -7
    }

    @Test
    void testMaxMonthNormal() {
        List<int[]> testData = List.of(new int[]{1, 12, 5, 10}, new int[]{1, 15, 8, 20}, new int[]{2, 10, 6, 5});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.maxMonth()).isEqualTo(0); // January (index 0) has the highest sum
    }

    @Test
    void testMaxMonthSingleRecord() {
        List<int[]> testData = List.of(new int[]{3, 20, 7, 15});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.maxMonth()).isEqualTo(2); // Only March (index 2) has a record
    }

    @Test
    void testMaxMonthAllMonthsSame() {
        List<int[]> testData = List.of(new int[]{1, 10, 5, 5}, new int[]{2, 10, 6, 5}, new int[]{3, 10, 7, 5});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.maxMonth()).isIn(0, 1, 2); // All months have the same sum, so any could be the max
    }

    @Test
    void testBiggestIndexNormal() {
        ArrayListFuncs funcs = new ArrayListFuncs(null);
        int[] testData = {1, 3, 5, 2};
        Assertions.assertThat(funcs.biggestIndex(testData)).isEqualTo(2); // Index 2 has the highest value (5)
    }

    @Test
    void testBiggestIndexAllSame() {
        ArrayListFuncs funcs = new ArrayListFuncs(null);
        int[] testData = {3, 3, 3, 3};
        Assertions.assertThat(funcs.biggestIndex(testData)).isEqualTo(0); // All elements are same; first index (0) should be returned
    }

    @Test
    void testBiggestIndexEmptyArray() {
        ArrayListFuncs funcs = new ArrayListFuncs(null);
        int[] testData = {};
        Assertions.assertThat(funcs.biggestIndex(testData)).isEqualTo(-1); // Empty array should return -1
    }

    @Test
    void testNightHasMoreTrue() {
        List<int[]> testData = List.of(new int[]{1, 12, 21, 3}, new int[]{2, 15, 22, 7});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.nightHasMore()).isTrue(); // More events at night
    }

    @Test
    void testNightHasMoreFalse() {
        List<int[]> testData = List.of(new int[]{1, 12, 14, 3}, new int[]{2, 15, 16, 7});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.nightHasMore()).isFalse(); // More events during the day
    }

    @Test
    void testNightHasMoreEqual() {
        List<int[]> testData = List.of(new int[]{1, 12, 21, 3}, new int[]{2, 15, 14, 7});
        ArrayListFuncs funcs = new ArrayListFuncs(testData);
        Assertions.assertThat(funcs.nightHasMore()).isFalse(); // Equal number of day and night events; should return false as per the method logic
    }




}
