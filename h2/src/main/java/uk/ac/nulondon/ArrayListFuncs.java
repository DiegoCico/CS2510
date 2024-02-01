package uk.ac.nulondon;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArrayListFuncs {
    private final List<int[]> records;

    // Constructor to initialize the ArrayListFuncs with a list of integer arrays.
    public ArrayListFuncs(List<int[]> records) {
        this.records = records;
    }

    // Sums the last element of each array in the records list, which represents an event count.
    public int sumEvents() {
        int sum = 0;
        for (int[] a : records) {
            sum += a[a.length - 1];
        }
        return sum;
    }

    // Determines the month with the highest sum of events.
    public int maxMonth() {
        int[] months = new int[12];

        for (int[] a : records) {
            months[a[0] - 1] += a[a.length - 1];
        }

        return biggestIndex(months);
    }

    // Finds the index of the largest element in an array, used to determine the month with the most events.
    public int biggestIndex(int[] a) {
        int max = 0;
        int index = -1;
        for (int i = 0; i < a.length; i++)
            if (max < a[i]) {
                max = a[i];
                index = i;
            }

        return index;
    }

    // Determines whether there are more events at night (before 6 AM or after 7 PM) than during the day.
    public boolean nightHasMore() {
        int nights = 0;
        int days = 0;
        for (int[] a : records) {
            if (a[2] < 6 || a[2] >= 19) {
                nights++;
            } else {
                days++;
            }
        }
        return nights > days;
    }

    // Reads data from a CSV file and converts it into a list of integer arrays.
    public static List<int[]> readFile(String filePath) {
        try (FileReader in = new FileReader(filePath);
             CSVParser parser = CSVFormat.DEFAULT.parse(in)) {
            return parser.stream().skip(1)
                    .map(CSVRecord::values)
                    .map(r -> Arrays.stream(r).mapToInt(Integer::parseInt).toArray())
                    .toList();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // Main method to execute the functionalities of ArrayListFuncs class.
    public static void main(String[] args) {
        List<int[]> records = readFile("/Users/diegocicotoste/Documents/CS2510/h2/src/main/resources/Report2014.csv");
        ArrayListFuncs arrayListFuncs = new ArrayListFuncs(records);

        int totalEvents = arrayListFuncs.sumEvents();
        int monthWithMostOccurrences = arrayListFuncs.maxMonth() + 1; // Adding 1 as months are 0-indexed
        boolean moreNightEvents = arrayListFuncs.nightHasMore();

        System.out.printf("Total number of events: %d\n", totalEvents);
        System.out.printf("Month with the most occurrences: %d\n", monthWithMostOccurrences);
        System.out.printf("Night has more events: %b\n", moreNightEvents);
    }
}
