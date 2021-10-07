import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class 괄호제거 {
    private static HashSet<String> answer;
    private static ArrayList<int[]> pairs;
    private static int count;
    private static boolean[] visited;
    private static char[] charArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s = br.readLine();
        pairs = new ArrayList<>();
        answer = new HashSet<>();
        visited = new boolean[s.length()];
        Arrays.fill(visited, true);

        // 찾아 괄호 쌍의 인덱스를
        charArr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (charArr[i] == '(') {
                stack.add(i);
            } else if (charArr[i] == ')') {
                pairs.add(new int[]{stack.pop(), i});
            }
        }
        count = pairs.size();

        removeGaul(0);

        answer.remove(s);
        List<String> answerList = new ArrayList(answer);
        Collections.sort(answerList);
        StringBuilder sb = new StringBuilder();
        for (String s1 : answerList) {
            sb.append(s1).append("\n");
        }
        System.out.println(sb);
    }

    private static void removeGaul(int now) { // 부분집합
        if (now == count) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(charArr[i]);
                }
            }
            answer.add(sb.toString());
            return;
        }

        // 부분집합
        // 괄호를 제거하지 않고 재귀
        int[] pair = pairs.get(now);
        visited[pair[0]] = true;
        visited[pair[1]] = true;
        removeGaul(now + 1);

        // 괄호를 제거하고 재귀
        visited[pair[0]] = false;
        visited[pair[1]] = false;
        removeGaul(now + 1);
    }
}
