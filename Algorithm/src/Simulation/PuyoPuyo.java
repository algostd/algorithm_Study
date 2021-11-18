package Algorithm.src.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PuyoPuyo {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count = 0;

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[12][6];
        for (int row = 0; row < 12; row++) {
            String str = br.readLine();
            for (int col = 0; col < 6; col++) {
                map[row][col] = str.charAt(col);
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        boolean check = true;
        int answer = 0;
        while (check) {
            check = false;
            boolean[][] visited = new boolean[12][6];
            for (int row = 0; row < 12; row++) {
                for (int col = 0; col < 6; col++) {
                    if (map[row][col] != '.' && !visited[row][col]) {
                        count = 1;
                        arrayList.clear();
                        queue.add(new Pair(row, col));
                        arrayList.add(new Pair(row, col));
                        visited[row][col] = true;
                        while (!queue.isEmpty()) {
                            Pair pair = queue.poll();
                            for (int i = 0; i < 4; i++) {
                                int tr = dr[i] + pair.row;
                                int tc = dc[i] + pair.col;
                                if (tr >= 0 && tc >= 0 && tr < 12 && tc < 6
                                        && !visited[tr][tc] && map[pair.row][pair.col] == map[tr][tc]) {
                                    count++;
                                    visited[tr][tc] = true;
                                    queue.add(new Pair(tr, tc));
                                    arrayList.add(new Pair(tr, tc));
                                }
                            }
                        }
                        if (count >= 4) { // 폭발해야되는 것으로 인식시켜주기
                            for (Pair pair : arrayList) {
                                map[pair.row][pair.col] = '0';
                            }
                            check = true;
                        }
                    }
                }
            }
            if(!check){
                break;
            }
            // 당겨주기
            for (int i = 0; i < 6; i++) {
                LinkedList<Character> linkedList = new LinkedList<>();
                for (int j = 11; j >= 0; j--) {
                    linkedList.add(map[j][i]);
                }
                for (int j = 0; j < 12; j++) {
                    if(linkedList.get(j) == '0'){
                        linkedList.remove(j--);
                        linkedList.add('.');
                    }
                }
                int k = 0;
                for (int j = 11; j >= 0; j--) {
                    map[k++][i] = linkedList.get(j);
                }
            }
            answer++;
        }
        System.out.println(answer);
    }
}
