import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, max, divide_start;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input);

        // N = 4라면 max = 10,000
        // 이를 재귀함수의 기저조건으로 활용한다.
        max = 1;
        for (int i = 0; i < N; i++) {
            max *= 10;
        }
        divide_start = max / 10;
        list = new ArrayList<Integer>();

        Main main = new Main();
        main.recur(2*divide_start, divide_start);

        Collections.sort(list);

        for (int num : list) {
            System.out.println(num);
        }
    }

    private void recur(int number, int divide) {
        if (number >= max) {
            return;
        }

        int checkNum = number / divide;
        if (divide == 1) {
            //소수인지 판단
            boolean check = true;
            int sqrt = (int) Math.sqrt(checkNum);
            for (int i = 2; i <= sqrt; i++) {
                if (checkNum % i == 0) {
                    check = false;
                    break;
                }
            }
            // 만약 소수가 아니라면
            if (check == false) {
                recur(number + divide, divide_start);
            } else {
                //만약 소수라면
                list.add(number);
                recur(number + 1, divide_start);
            }

        } else {
            //소수인지 판단
            boolean check = true;
            int sqrt = (int) Math.sqrt(checkNum);
            for (int i = 2; i <= sqrt; i++) {
                if (checkNum % i == 0) {
                    check = false;
                    break;
                }
            }
            // 만약 소수가 아니라면
            if (check == false) {
                recur(number + divide, divide_start);
            } else {
                //만약 소수라면
                recur(number, divide / 10);
            }
        }
    }
}