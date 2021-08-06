import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        String boob = br.readLine();

        List<Character> list = new ArrayList<Character>();

        char[] arr = input.toCharArray();
        char[] boo = boob.toCharArray();
        int len = boo.length;
        for (char ch : arr) {
            //한문자씩 차례로 리스트에 담는다.
            list.add(ch);

            //마지막 글자만을 비교한다.
            //마지막 문자가 같을 시 역순으로 비교한다.
            char last = boo[boo.length - 1];
            int size = list.size();
            if (size >= len && ch == last) {

                boolean check = true;
                for (int i = 1; i <= len; i++) {
                    char ch1 = list.get(size - i);
                    char ch2 = boo[boo.length - i];

                    if (ch1 != ch2) {
                        check = false;
                    }
                }
                if (check == true) {
                    for (int i = 1; i <= len; i++) {
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
        if (list.size() == 0) {
            System.out.println("FRULA");
        } else {
            for (char ch : list) {
                bw.write(ch);
            }
            bw.flush();
        }
        bw.close();
    }
}