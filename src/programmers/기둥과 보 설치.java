import java.util.*;

class Solution {
    private static int N;
    private static int[][] G, B;

    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        G = new int[n+1][n+1];
        B = new int[n+1][n+1];

        for (int[] input : build_frame) {
            int x = input[0];
            int y = input[1];
            int a = input[2];
            int b = input[3];

            //1,0,0,1
            //설치할 경우
            if (b == 1) {
                if (install_check(x, y, a) == true) {
                    //기둥일 경우
                    if (a == 0) {
                        G[x][y] = 1;
                    }
                    //보일 경우
                    if (a == 1) {
                        B[x][y] = 1;
                    }
                }
            }

            //삭제할 경우
            if (b == 0) {
                delete_check(x, y, a);
            }
        }

        List<Build> list = new ArrayList<Build>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (G[i][j] == 1) {
                    list.add(new Build(i,j,0));
                }
                if (B[i][j] == 1) {
                    list.add(new Build(i,j,1));
                }
            }
        }

        int[][] answer = new int[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            Build build = list.get(i);
            answer[i][0] = build.getX();
            answer[i][1] = build.getY();
            answer[i][2] = build.getType();
        }

        return answer;
    }

    private boolean install_check(int x, int y, int a) {
        boolean result = false;

        //기둥 설치가 가능한지 확인하는 경우
        if (a == 0) {
            //기둥이 바닥 위에 있거나
            if (y == 0) {
                result = true;
            } else if ((x-1 >= 0 && B[x-1][y] == 1) || (B[x][y] == 1)) {
                //보의 한쪽 끝 부분 위에 있거나
                result = true;
            } else if (y-1 >= 0 && G[x][y-1] == 1) {
                //다른 기둥 위에 있을 때
                result = true;
            }
        }

        //보 설치가 가능한지 확인하는 경우
        if (a == 1) {
            //한쪽 끝 부분이 기둥 위에 있거나
            if (y-1 >= 0 && G[x][y-1] == 1) {
                result = true;
            } else if (x+1 <= N && y-1 >= 0 && G[x+1][y-1] == 1) {
                result = true;
            } else if ((x-1 >= 0 && B[x-1][y] == 1) && (x+1 <= N && B[x+1][y] == 1)) {
                //양쪽 끝 부분이 다른 보와 동시에 연결될 때
                result = true;
            }
        }

        return result;
    }

    private void delete_check(int x, int y, int a) {
        //기둥을 삭제할 경우
        if (a == 0) {
            //일단 삭제해본다.
            G[x][y] = 0;

            //삭제 후 영향이 가는 지점들을 체크해본다.
            boolean check = true;

            //x-1, y+1 지점 : 보가 있는지 확인
            //만약 보가 있다면
            if (x-1 >= 0 && y+1 <= N && B[x-1][y+1] == 1) {
                check = check && install_check(x-1, y+1, 1);
            }

            //x, y+1 지점 : 기둥, 보가 있는지 확인
            if (y+1 <= N && G[x][y+1] == 1) {
                //만약 기둥이 있다면
                check = check && install_check(x, y+1, 0);
            }
            if (y+1 <= N && B[x][y+1] == 1) {
                //만약 보가 있다면
                check = check && install_check(x, y+1, 1);
            }

            //다른 지점에 영향이 간다면 원복
            if (check == false) {
                G[x][y] = 1;
            }
        }

        //보를 삭제할 경우
        if (a == 1) {
            //일단 삭제해본다.
            B[x][y] = 0;

            //삭제 후 영향이 가는 지점들을 체크해본다.
            boolean check = true;

            //x-1, y 지점 : 보가 있는지 확인
            //만약 보가 있다면
            if (x-1 >= 0 && B[x-1][y] == 1) {
                check = check && install_check(x-1, y, 1);
            }

            //x, y 지점 : 기둥이 있는지 확인
            //만약 기둥이 있다면
            if (G[x][y] == 1) {
                check = check && install_check(x, y, 0);
            }

            //x+1, y 지점 : 기둥, 보가 있는지 확인
            if (x+1 <= N && G[x+1][y] == 1) {
                //만약 기둥이 있다면
                check = check && install_check(x+1, y, 0);
            }
            if (x+1 <= N && B[x+1][y] == 1) {
                //만약 보가 있다면
                check = check && install_check(x+1, y, 1);
            }

            //다른 지점에 영향이 간다면 원복
            if (check == false) {
                B[x][y] = 1;
            }
        }
    }
}

class Build {
    private int x;
    private int y;
    private int type;

    Build(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }
}
