package ru.nsu.fit.oop.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class of the substring search.
 */
public class SubstringSearch {
    /**
     * Finds the indices of the occurrence of a substring needle in reader.
     *
     * @param reader Reader where we want to search occurrence
     * @param substring String we want to find
     * @return array of indices of the occurrences. Count from 0
     * @throws IOException if some problems with a reading of the reader
     */
    public static List<Integer> zfunction(Reader reader, String substring) throws IOException {
        Reader rdr = new BufferedReader(reader);
        char[] string = substring.toCharArray();
        int len = substring.length();
        int[] suffshift = zsuffshift(len, string);
        int j;
        int bound = 0;
        int i = 0;

        char[] cbuf = new char[len];
        ArrayList<Integer> ret = new ArrayList<>();

        while (true) {
            rdr.mark(2 * len);
            if (rdr.read(cbuf, 0, len) != len) {
                break;
            }
            for (j = len - 1; j >= bound && string[j] == cbuf[j]; ) {
                j--;
            }
            if (j < bound) {
                ret.add(i);
                bound = len - suffshift[0];
                j = -1;
            } else {
                bound = 0;
            }
            rdr.reset();
            if (rdr.skip(suffshift[j + 1]) != suffshift[j + 1]) {
                break;
            }
            i += suffshift[j + 1];
        }
        return ret;
    }

    /**
     * Algorithm for calculating the suffix table.
     *
     * @param len Len of the string
     * @param string String we want to find suffix table
     * @return array of suffix table. Count from arr[0] - mean all substr in str
     * arr[len] - empty suffix
     */
    private static int[] zsuffshift(int len, char[] string) {
        int[] suffshift = new int[len + 1];
        Arrays.fill(suffshift, len);
        int[] z = new int[len];
        int left = 0;
        int right = 0;
        for (int j = 1; j < len; j++) {
            if (j <= right) {
                z[j] = Math.min(right - j + 1, z[j - left]);
            }
            while (j + z[j] < len && string[len - 1 - z[j]] == string[len - 1 - (j + z[j])]) {
                ++z[j];
            }
            if (j + z[j] - 1 > right) {
                left = j;
                right = j + z[j] - 1;
            }
        }
        for (int j = len - 1; j > 0; j--) {
            suffshift[len - z[j]] = j;
        }
        for (int j = 1; j <= len - 1; j++) {
            if (j + z[j] == len) {
                for (int r = 0; r <= j; r++) {
                    if (suffshift[r] == len) {
                        suffshift[r] = j;
                    }
                }
            }
        }
        return suffshift;
    }
}
