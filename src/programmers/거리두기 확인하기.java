import java.util.*;

public class Solution {
    private static int[] l = {-1,1,0,0};
    private static int[] r = {0,0,-1,1};

    public int[] solution(String[][] places) {
        List<Integer> list = new ArrayList<Integer>();
        for (String[] input : places) {
            //P : 1, 0 : 0, X : -1
            //입력
            int[][] arr = new int[5][5];
            for (int i = 0; i < 5; i++) {
                String str = input[i];
                for (int j = 0; j < 5; j++) {
                    String s = str.substring(j,j+1);
                    if (s.equals("P")) {
                        arr[i][j] = 1;
                    }
                    if (s.equals("X")) {
                        arr[i][j] = -1;
                    }
                }
            }

            //알고리즘
            boolean check = true;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (arr[i][j] == 1) {
                        int[][] visited = new int[5][5];

                        Queue<Node> qu = new LinkedList<Node>();
                        qu.add(new Node(i, j));
                        int tmp = 0;

                        while (!qu.isEmpty() && tmp < 2) {
                            Node newNode = qu.poll();
                            int cx = newNode.x;
                            int cy = newNode.y;

                            visited[i][j] = 1;
                            for (int k = 0; k < 4; k++) {
                                int nx = cx + l[k];
                                int ny = cy + r[k];

                                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && arr[nx][ny] == 0 && visited[nx][ny] == 0) {
                                    qu.add(new Node(nx, ny));
                                }
                                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && arr[nx][ny] == 1 && visited[nx][ny] == 0) {
                                    check = false;
                                    break;
                                }
                            }
                            tmp++;
                        }

                        if (check == false) {
                            break;
                        }
                    }
                }
                if (check == false) {
                    break;
                }
            }

            if (check == false) {
                list.add(0);
            } else {
                list.add(1);
            }
        }

        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}