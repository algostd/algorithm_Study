package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.System.in;

// https://www.acmicpc.net/problem/1202 (골드2)
public class 보석도둑 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int N, K;
    static PriorityQueue<Integer> bags = new PriorityQueue<>();
    static ArrayList<Jewel> jewels = new ArrayList<>();

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        getInfo();

        long answer = 0;
        PriorityQueue<Integer> maxValue = new PriorityQueue<>(Collections.reverseOrder());

        int jewelIndex = 0;
        while (!bags.isEmpty()) {
            int weight = bags.poll();

            while(jewelIndex < N && jewels.get(jewelIndex).weight <= weight){
                maxValue.add(jewels.get(jewelIndex++).value);
            }

            if (!maxValue.isEmpty()) {
                answer += maxValue.poll();
            }
        }

        System.out.println(answer);
    }

    private static void getInfo() throws IOException {
        String[] str;
        str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            int weight = Integer.parseInt(str[0]);
            int value = Integer.parseInt(str[1]);
            jewels.add(new Jewel(weight, value));
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        // 보석들은 무게 순으로 정렬
        jewels.sort((a, b) -> {
            if (a.weight == b.weight) {
                return b.value - a.value;
            }
            return a.weight - b.weight;
        });
    }
}
