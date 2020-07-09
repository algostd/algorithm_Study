package Algorithm.MondaySinchon.SWEA9229;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

    static ArrayList<Integer> arrayList;

    static int array[];
    static int TC, N, M, sum;

    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;
    static StringTokenizer stringTokenizer;

    public static void main(String[] args) throws IOException {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        TC = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < TC; i++) {
            sum = 0;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            array = new int[N];
            arrayList = new ArrayList<>();


            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                array[j] = Integer.parseInt(stringTokenizer.nextToken());
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == k) {
                        continue;
                    } else {
                        sum = array[j] + array[k];
                        if (sum <= M) {
                            arrayList.add(sum);
                        }
                    }
                }

            }
            Collections.sort(arrayList, Collections.reverseOrder());
            //Collections.reverse(arrayList);
            if (arrayList.size() == 0) {
                bufferedWriter.write("#" + (i + 1) + " " + (-1) + "\n");
            } else {
                bufferedWriter.write("#" + (i + 1) + " " + arrayList.get(0) + "\n");
            }
        }
        bufferedWriter.flush();


    }

}

