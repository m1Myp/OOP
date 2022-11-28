package ru.nsu.fit.oop.lab2;

import java.io.*;
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
 * @param needle String we want to find
 * @return array of indices of the occurrences. Count from 0
 * @throws IOException if some problems with a reading of the reader
 */
    public static List<Integer> zFunction(Reader reader, String needle) throws IOException {
        var rdr = new BufferedReader(reader);

        var m = needle.length();
        var s = needle.toCharArray();
        var suffshift = new int[m + 1];
        Arrays.fill(suffshift, m);
        var z = new int[m];
        int maxZidx = 0;
        int maxZ = 0;
        for (int j = 1; j < m; j++) {
            if (j <= maxZ) z[j] = Math.min(maxZ - j + 1, z[j - maxZidx]);
            while (j + z[j] < m && s[m - 1 - z[j]] == s[m - 1 - (j + z[j])]) z[j]++;
            if (j + z[j] - 1 > maxZ) {
                maxZidx = j;
                maxZ = j + z[j] - 1;
            }
        }
        for (int j = m - 1; j > 0; j--) suffshift[m - z[j]] = j;
        for (int j = 1, r = 0; j <= m - 1; j++) {
            if (j + z[j] == m) {
                for (; r <= j; r++) {
                    if (suffshift[r] == m) suffshift[r] = j;
                }
            }
        }

        int j, bound = 0;
        int i = 0;

        var cbuf = new char[m];
        var ret = new ArrayList<Integer>();

        while (true) {
            rdr.mark(2 * m);
            if (rdr.read(cbuf, 0, m) != m) break;
            for (j = m - 1; j >= bound && s[j] == cbuf[j]; ) j--;
            if (j < bound) {
                ret.add(i);
                bound = m - suffshift[0];
                j = -1;
            } else {
                bound = 0;
            }
            rdr.reset();
            if (rdr.skip(suffshift[j + 1]) != suffshift[j + 1]) break;
            i += suffshift[j + 1];
        }

        return ret;
    }
}
