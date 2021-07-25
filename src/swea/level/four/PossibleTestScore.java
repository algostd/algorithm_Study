import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {
    private static int[] numbers;
    private static Set<Integer> st;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            //입력
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");
            numbers = new int[N];
            st = new HashSet<Integer>();

            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(s[i]);
                numbers[i] = num;
            }

            Solution sol = new Solution();
            st.add(0);
            for (int i = 0; i < N; i++) {
                int num = numbers[i];
                sol.sumToAll(num);
            }

            answer = st.size();
            System.out.println("#" + test_case + " " + answer);
        }

        br.close();
    }

    private void sumToAll(int num) {
        Set<Integer> tmpSet = new HashSet<Integer>();
        Iterator<Integer> iter = st.iterator();
        while(iter.hasNext()) {
            int cur = iter.next();
            tmpSet.add(cur + num);
        }

        iter = tmpSet.iterator();
        while(iter.hasNext()) {
            st.add(iter.next());
        }
    }
}