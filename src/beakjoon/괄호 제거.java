import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int max;
    private static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        char[] chArr = input.toCharArray();
        int len = chArr.length;
        int[] arr = new int[len];

        int cnt = 0;
        max = 0;
        for (int i = 0; i < len; i++) {
            if (chArr[i] == '(') {
                cnt++;
                max = Math.max(cnt, max);
            }
        }

        List<Node> sList = new ArrayList<Node>();
        for (int i = 0; i < len; i++) {
            if (chArr[i] == '(') {
                sList.add(new Node(i, "("));
            }
            if (chArr[i] == ')') {
                sList.add(new Node(i, ")"));
            }
        }


        int tmpCnt = 0;
        while (!sList.isEmpty()) {
            tmpCnt++;

            int idx1 = -1;
            int idx2 = -1;
            int tmp1 = -1;
            int tmp2 = -1;
            for (int i = 0; i < sList.size(); i++) {
                Node newNode = sList.get(i);
                if (newNode.s.equals("(")) {
                    idx1 = newNode.idx;
                    tmp1 = i;
                } else if (idx1 != -1 && newNode.s.equals(")")) {
                    idx2 = newNode.idx;
                    tmp2 = i;
                    break;
                }
            }
            arr[idx1] = tmpCnt;
            arr[idx2] = tmpCnt;

            sList.remove(tmp2);
            sList.remove(tmp1);
        }

        //모든 부분집합 구하기
        int[] num = new int[max];
        int tmp = 1;
        for (int i = 0; i < max; i++) {
            num[i] = tmp;
            tmp++;
        }

        Main m = new Main();
        list = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[max];
        m.powerSet(num, visited, max, 0);

        //괄호쌍의 개수 max
        Set<String> set = new HashSet<String>();
        for (int i = 1; i <= max; i++) {
            for (ArrayList<Integer> curList : list) {
                //curList에 속하지 않을 경우만 담는다.
                String s = "";
                for (int j = 0; j < len; j++) {
                    if (curList.contains(arr[j])) {
                        continue;
                    }
                    s += chArr[j];
                }
                set.add(s);
            }
        }

        PriorityQueue<String> qu = new PriorityQueue<String>();
        for (String s : set) {
            if (s.equals(input)) {
                continue;
            }
            qu.add(s);
        }

        int size = qu.size();
        for (int i = 0; i < size; i++) {
            String s = qu.poll();
            System.out.println(s);
        }
    }

    //1부터 max까지의 가능한 모든 조합
    private void powerSet(int[] arr, boolean[] visited, int n, int idx) {
        if(idx == n) {
            ArrayList<Integer> tmpList = new ArrayList<Integer>();
            for (int i = 0; i < max; i++) {
                if (visited[i] == true) {
                    tmpList.add(arr[i]);
                }
            }
            list.add(tmpList);
            return;
        }

        visited[idx] = false;
        powerSet(arr, visited, n, idx + 1);

        visited[idx] = true;
        powerSet(arr, visited, n, idx + 1);
    }

}

class Node {
    int idx;
    String s;

    Node (int idx, String s) {
        this.idx = idx;
        this.s = s;
    }
}