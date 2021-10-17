import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            list.add(num);
        }

        //내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());

        int max = 0;
        for (int i = 1; i <= N; i++) {
            int num = list.get(i-1);
            int curWeight = i * num;
            if (curWeight > max) {
                max = curWeight;
            }
        }

        System.out.println(max);
    }

}
