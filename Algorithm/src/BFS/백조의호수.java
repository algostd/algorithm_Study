import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백조의호수 {
    /*
    2개의 BFS 진행

    [1] 백조기준에서 탐색
       1. 백조1이 백조2를 찾아간다고 생각
       2. 백조1 기준으로 BFS
        -> 뭘 만나든 모두 방문처리 (백조1의 기준)
          -> 웅덩이('.')인 경우 - 계속 탐색
          -> 빙판('X')인 경우 - 빙판 노드를 다음에 탐색할 nextQueue에 저장 후 멈춤
          -> 백조2('L')인경우 - 정답 처리
    [2] 얼음 녹이기 (waterQueue)
        - 초기: 모든 물은 waterQueue 저장
        - 반복: [1]의 BFS가 끝날 때 마다, 탐색길이 1만큼의 BFS로 물에 닿은 얼음 녹임
             -> 얼음을 녹인경우, 해당 얼음자리의 Pair값을 waterQueue에 다시 넣음
             -> 그리고 answer++;
    */

    private static Queue<Pair> nowQueue;
    private static Queue<Pair> nextQueue;
    private static Queue<Pair> waterQueue;

    static class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int R;
    private static int C;
    private static char[][] map;
    private static boolean[][] visited;
    public static Pair[] swans;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받고 map, Queue 만들기
        String[] strArr = br.readLine().split(" ");
        R = Integer.parseInt(strArr[0]);
        C = Integer.parseInt(strArr[1]);
        map = new char[R][C];
        visited = new boolean[R][C];
        swans = new Pair[2];
        nowQueue = new LinkedList<>();
        waterQueue = new LinkedList<>();

        String str;
        int swanCount = 0;
        for (int row = 0; row < R; row++) {
            str = br.readLine();
            for (int col = 0; col < C; col++) {
                map[row][col] = str.charAt(col);
                if (map[row][col] == 'L') {
                    swans[swanCount++] = new Pair(row, col);
                }
                if (map[row][col] != 'X' ) {
                    waterQueue.add(new Pair(row, col));
                }
            }
        }

        // 큐 두개를 활용한 BFS 알고리즘
        nowQueue.add(swans[0]);
        visited[swans[0].row][swans[0].col] = true;
        int answer = 0;
        outer:
        while (true) {
            // 길이 1만큼 BFS 물이 빙판 녹이기
            nextQueue = new LinkedList<>();
            while (!nowQueue.isEmpty()) {
                Pair pair = nowQueue.poll();
                int row = pair.row;
                int col = pair.col;

                // 1칸 이동
                for (int i = 0; i < 4; i++) {
                    int tr = dr[i] + row;
                    int tc = dc[i] + col;
                    if (tr >= 0 && tc >= 0 && tr < R && tc < C && !visited[tr][tc]) {
                        visited[tr][tc] = true;
                        if (map[tr][tc] == 'X') {
                            nextQueue.add(new Pair(tr, tc));
                        } else if(map[tr][tc] == '.'){
                            nowQueue.add(new Pair(tr, tc));
                        } else{ // 2번째 swan을 찾으면 나가자
                            break outer;
                        }
                    }
                }
            }
            int waterQueueSize = waterQueue.size();
            for (int i = 0; i < waterQueueSize; i++) {
                Pair pair = waterQueue.poll();
                int row = pair.row;
                int col = pair.col;
                for (int j = 0; j < 4; j++) {
                    int tr = dr[j] + row;
                    int tc = dc[j] + col;
                    if (tr >= 0 && tc >= 0 && tr < R && tc < C && map[tr][tc] == 'X') {
                        map[tr][tc] = '.';
                        waterQueue.add(new Pair(tr, tc));
                    }
                }
            }
            nowQueue = nextQueue;
            answer++;
        }
        System.out.println(answer);
    }
}
