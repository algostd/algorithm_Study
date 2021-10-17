// https://programmers.co.kr/learn/courses/30/lessons/87391#qna
public class 월코챌3_공이동시뮬레이션 {
    /*
    1. 쿼리를 반대방향으로 반대순서로 만든다.
    2. 변경된 쿼리를 도착지점(x,y)을 출발점으로 생각하여 돌려보고 '기준점'을 찾는다.
    3. 기준점 기준에서 제대로된 쿼리를 다시 실행했을때
       => 만약 기준점에서 쿼리를 다시 실행했는데도 도착자리에 오지 못했다면, 그것은 해당 자리에 도착하는 것이 불가능한 경우이다. 이 경우 답이 0이다.
       => 그 외에는 답이 존재한다.
          답 COUNT 방법
          - 정답으로 갈 수 있는 칸을 세기 위해 up,down,left,right를 사용할 건데, 기준점으로 초기화 시켜준다.
          - 만약 위,아래,왼쪽,오른쪽 벽에 부딪히는 경우 기준점부터 부딪힌 벽까지의 칸들은 모두 쿼리를 실행했을 때 도착지에 도착할 수 있는 칸으로 정답을 세는데 포함되어야 한다.
            즉, 벽에 부딪히면
                위쪽벽은 up을 0으로, 아래쪽 벽은 down을 n-1로, 왼쪽벽은 left를 0으로, 오른쪽벽은 right를 m-1로 갱신시켜준다.
          - 아무데도 부딪히지 않는다면 정답은 1개이다.
    */
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        // 기준점
        int newRow = x;
        int newCol = y;
        // 쿼리를 거꾸로, 기준점을 구한다.
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int dx = query[1]; // 몇 칸
            // 이동
            if (query[0] == 0) { // 왼쪽 -> 오른쪽
                newCol += dx;
                newCol = Math.min(m - 1, newCol);
            } else if (query[0] == 1) { // 오른쪽 -> 왼쪽
                newCol -= dx;
                newCol = Math.max(0, newCol);
            } else if (query[0] == 2) { // 위쪽 -> 아래쪽
                newRow += dx;
                newRow = Math.min(n - 1, newRow);
            } else { // 아래쪽 -> 위쪽
                newRow -= dx;
                newRow = Math.max(0, newRow);
            }
            System.out.println(newRow + " " + newCol);
        }

        // 기준점을 출발해서 제대로된 쿼리를 다시 실행
        int left = newCol;
        int right = newCol;
        int up = newRow;
        int down = newRow;
        for (int[] query : queries) { // 쿼리 실행
            int dx = query[1]; // 몇 칸
            // 이동
            if (query[0] == 0) { // 왼쪽
                newCol -= dx;
                newCol = Math.max(0, newCol);
                if (newCol == 0) {
                    left = 0;
                }
            } else if (query[0] == 1) { // 오른쪽
                newCol += dx;
                newCol = Math.min(m - 1, newCol);
                if (newCol == m-1) {
                    right = m - 1;
                }
            } else if (query[0] == 2) { // 위쪽
                newRow -= dx;
                newRow = Math.max(0, newRow);
                if (newRow == 0) {
                    up = 0;
                }
            } else { // 아래쪽
                newRow += dx;
                newRow = Math.min(n - 1, newRow);
                if (newRow == n-1) {
                    down = n-1;
                }
            }
        }
        if (newRow == x && newCol == y) {
            answer = (long) (right - left + 1) * (down - up + 1);
        } else{
            answer = 0;
        }
        return answer;
    }

    public static void main(String[] args) {
        월코챌3_공이동시뮬레이션 s = new 월코챌3_공이동시뮬레이션();
        long solution = s.solution(2, 5, 0, 1, new int[][]{{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}});
        System.out.println(solution);
    }
}
