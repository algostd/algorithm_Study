package Algorithm.Spring.SWEA8934;

import java.io.*;
import java.util.Arrays;

public class Solution {

    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;

    static int TC;
    static int[] array;
    static String s;


    public static void main(String[] args) throws IOException {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        TC = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < TC; i++) {
            array = new int[3];
            s = bufferedReader.readLine();
            for (int j = 0; j < s.length(); j++) {
                array[s.charAt(j) - 'a']++;
            }

            Arrays.sort(array);

            if ((array[0] == array[1] && array[1] == array[2])
                    || (array[0] == array[1] && array[2] - 1 == array[1])
                    || (array[1] == array[2] && array[0] + 1 == array[1])) {
                bufferedWriter.write("#" + (i + 1) + " YES" + "\n");
            } else {
                bufferedWriter.write("#" + (i  + 1) + " NO" + "\n");
            }
        }
        bufferedWriter.flush();
    }

}
