import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
    }

    public int[] solution(int[] weights, String[] head2head) {
        PriorityQueue<Player> qu = new PriorityQueue<Player>();

        int n = weights.length;
        for (int i = 0; i < n; i++) {
            String s = head2head[i];
            int cntW = 0;
            int cntL = 0;
            int cntBigWeight = 0;

            for (int j = 0; j < s.length(); j++) {
                String cur = s.substring(j, j+1);
                if (cur.equals("W")) {
                    cntW++;
                    if (weights[i] < weights[j]) {
                        cntBigWeight++;
                    }
                } else if (cur.equals("L")) {
                    cntL++;
                }
            }

            qu.add(new Player(cntW, cntL, cntBigWeight, weights[i], i+1));
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            Player p = qu.poll();
            answer[i] = p.number;
        }

        return answer;
    }
}

class Player implements Comparable<Player> {
    double rate;
    int cntBigWeight;
    int weight;
    int number;

    Player(int cntW, int cntL, int cntBigWeight, int weight, int number) {
        if (cntW == 0) {
            this.rate = 0;
        } else {
            this.rate = (double)cntW / (cntW + cntL);
        }
        this.cntBigWeight = cntBigWeight;
        this.weight = weight;
        this.number = number;
    }

    public int compareTo(Player p) {
        if (this.rate < p.rate) { //승률 내림차순
            return 1;
        } else if (this.rate > p.rate) {
            return -1;
        } else {
            if (this.cntBigWeight < p.cntBigWeight) {
                return 1;
            } else if (this.cntBigWeight > p.cntBigWeight) {
                return -1;
            } else {
                if (this.weight < p.weight) {
                    return 1;
                } else if (this.weight > p.weight) {
                    return -1;
                } else {
                    if (this.number < p.number) {
                        return -1;
                    } else if (this.number > p.number) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}