import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TearmProject {
    static int n;
    static List<Integer> list;
    static List<Integer> temp;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int TC = 1;  TC <= T; TC++) {
            //입력
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            list = new ArrayList<Integer>();
            list.add(0); //list[0] 지점을 미리 채워넣는다.
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                list.add(num);
            }

            visited = new int[n + 1];
            int cnt = 0;

            //완전 탐색
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 0) {
                    temp = new ArrayList<Integer>();
                    dfs(i);
                    int lastnum = temp.get(temp.size() - 1);
                    int firstindex = temp.indexOf(lastnum);

                    int lastindex = temp.lastIndexOf(lastnum);
                    if (firstindex != lastindex) {
                        cnt += temp.size() - firstindex - 1;
                    }
                }
            }

            System.out.println(n - cnt);
        }

    }

    public static void dfs(int start) {
        visited[start]++;
        temp.add(start); //방문한 지점을 모두 담는다.

        int next = list.get(start);

        if (visited[start] < 2) {
            dfs(next);
        }
    }
}
