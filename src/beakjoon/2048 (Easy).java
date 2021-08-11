import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int N, max;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        max = Integer.MIN_VALUE;
        Main m = new Main();
        m.move(arr, 'U', 1);
        m.move(arr, 'D', 1);
        m.move(arr, 'L', 1);
        m.move(arr, 'R', 1);

        System.out.println(max);
    }

    private void move(int[][] arr, char direction, int cnt) {
        if (cnt > 5) {
            return;
        }

        if (direction == 'U') {
            int[][] new_arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<Integer>();
                Queue<Integer> qu = new LinkedList<Integer>();
                for (int j = 0; j < N; j++) {
                    int num = arr[j][i];
                    if (num != 0) {
                        qu.add(num);
                    }
                }

                while(!qu.isEmpty()) {
                    int num = qu.poll();
                    //마지막 수일때
                    if (qu.isEmpty()) {
                        list.add(num);
                        break;
                    }

                    int num2 = qu.peek();
                    if (num == num2) {
                        list.add(num*2);
                        qu.poll();
                    } else {
                        //같지않다면 그냥 그대로 넣는다.
                        list.add(num);
                    }
                }

                for (int j = 0; j < list.size(); j++) {
                    int num = list.get(j);
                    new_arr[j][i] = num;
                    max = Math.max(num, max);
                }
            }

            move(new_arr, 'U', cnt + 1);
            move(new_arr, 'D', cnt + 1);
            move(new_arr, 'L', cnt + 1);
            move(new_arr, 'R', cnt + 1);
        } else if (direction == 'D') {
            int[][] new_arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<Integer>();
                Queue<Integer> qu = new LinkedList<Integer>();
                for (int j = N-1; j >= 0; j--) {
                    int num = arr[j][i];
                    if (num != 0) {
                        qu.add(num);
                    }
                }

                while(!qu.isEmpty()) {
                    int num = qu.poll();
                    //마지막 수일때
                    if (qu.isEmpty()) {
                        list.add(num);
                        break;
                    }

                    int num2 = qu.peek();
                    if (num == num2) {
                        list.add(num*2);
                        qu.poll();
                    } else {
                        //같지않다면 그냥 그대로 넣는다.
                        list.add(num);
                    }
                }

                int j = N-1;
                for (int num : list) {
                    new_arr[j][i] = num;
                    max = Math.max(num, max);
                    j--;
                }
            }

            move(new_arr, 'U', cnt + 1);
            move(new_arr, 'D', cnt + 1);
            move(new_arr, 'L', cnt + 1);
            move(new_arr, 'R', cnt + 1);
        } else if (direction == 'L') {
            int[][] new_arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<Integer>();
                Queue<Integer> qu = new LinkedList<Integer>();
                for (int j = 0; j < N; j++) {
                    int num = arr[i][j];
                    if (num != 0) {
                        qu.add(num);
                    }
                }

                while(!qu.isEmpty()) {
                    int num = qu.poll();
                    //마지막 수일때
                    if (qu.isEmpty()) {
                        list.add(num);
                        break;
                    }

                    int num2 = qu.peek();
                    if (num == num2) {
                        list.add(num*2);
                        qu.poll();
                    } else {
                        //같지않다면 그냥 그대로 넣는다.
                        list.add(num);
                    }
                }

                for (int j = 0; j < list.size(); j++) {
                    int num = list.get(j);
                    new_arr[i][j] = num;
                    max = Math.max(num, max);
                }
            }

            move(new_arr, 'U', cnt + 1);
            move(new_arr, 'D', cnt + 1);
            move(new_arr, 'L', cnt + 1);
            move(new_arr, 'R', cnt + 1);
        } else if (direction == 'R') {
            int[][] new_arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                List<Integer> list = new ArrayList<Integer>();
                Queue<Integer> qu = new LinkedList<Integer>();
                for (int j = N-1; j >= 0; j--) {
                    int num = arr[i][j];
                    if (num != 0) {
                        qu.add(num);
                    }
                }

                while(!qu.isEmpty()) {
                    int num = qu.poll();
                    //마지막 수일때
                    if (qu.isEmpty()) {
                        list.add(num);
                        break;
                    }

                    int num2 = qu.peek();
                    if (num == num2) {
                        list.add(num*2);
                        qu.poll();
                    } else {
                        //같지않다면 그냥 그대로 넣는다.
                        list.add(num);
                    }
                }

                int j = N-1;
                for (int num : list) {
                    new_arr[i][j] = num;
                    max = Math.max(num, max);
                    j--;
                }
            }

            move(new_arr, 'U', cnt + 1);
            move(new_arr, 'D', cnt + 1);
            move(new_arr, 'L', cnt + 1);
            move(new_arr, 'R', cnt + 1);
        }
    }

}