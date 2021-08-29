
public class Solution {
    private static int[][] arr;
    private static int[] visited, visited1, d, d1;
    private static int N;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        arr = new int[n][n]; // i => j 로 이동할 때 요금
        N = n;

        //arr 배열 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    arr[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < fares.length; i++) {
            int node_one = fares[i][0];
            int node_two = fares[i][1];
            int fare = fares[i][2];

            arr[node_one - 1][node_two - 1] = fare;
            arr[node_two - 1][node_one - 1] = fare;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == -1) { //방문하지 않았다면
                    arr[i][j] = n*100000 + 1; // 최대값을 넣는다.
                }
            }
        }

        visited = new int[n];
        d = new int[n];

        //다익스트라
        dijkstra(s-1); //시작점 s-1
        int INF = n*100000 + 1;
        int min = n*100000 + 1;
        for (int i = 0; i < n; i++) { // i는 합승을 끝내는 지점
            visited1 = new int[n];
            d1 = new int[n];
            dijkstra1(i); //합승을 끝내는 지점인 i에서 새로운 다익스트라를 만든다.

            int sum = d[i] + d1[a-1] + d1[b-1];
            if (sum < min && sum > 0) {
                min = sum; //최소값을 찾는다.
            }
        }

        return min;
    }

    //가장 최소 거리를 가지는 정점을 반환합니다.
    private int getSmallIndex() {
        int min = N*100000 + 1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (d[i] < min && d[i] > 0 && visited[i] == 0) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    //가장 최소 거리를 가지는 정점을 반환합니다.
    private int getSmallIndex1() {
        int min = N*100000 + 1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (d1[i] < min && d1[i] > 0 && visited1[i] == 0) {
                min = d1[i];
                index = i;
            }
        }
        return index;
    }

    //다익스트라를 수행하는 함수입니다.
    private void dijkstra(int start) {
        for (int i = 0; i < N; i++) {
            d[i] = arr[start][i]; //거리
        }
        visited[start] = 1; // 방문처리
        for (int i = 0; i < N - 1; i++) {
            int current = getSmallIndex(); // 경유지점
            visited[current] = 1;
            for (int j = 0; j < N; j++) {
                if (visited[j] == 0) {
                    if (d[current] + arr[current][j] < d[j]) {
                        d[j] = d[current] + arr[current][j];
                    }
                }
            }
        }
    }

    //다익스트라를 수행하는 함수입니다.
    private void dijkstra1(int start) {
        for (int i = 0; i < N; i++) {
            d1[i] = arr[start][i];
        }
        visited1[start] = 1;
        for (int i = 0; i < N - 1; i++) {
            int current = getSmallIndex1();
            visited1[current] = 1;
            for (int j = 0; j < N; j++) {
                if (visited1[j] == 0) {
                    if (d1[current] + arr[current][j] < d1[j]) {
                        d1[j] = d1[current] + arr[current][j];
                    }
                }
            }
        }
    }
}
