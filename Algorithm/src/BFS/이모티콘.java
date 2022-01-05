import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
   낮은 cost(시간)로 먼저 BFS 탐색하는 알고리즘
   증식 방법->
     - 자기보다 1 적은 이모티콘으로 탐색 -> min[현재 - 1] + 1
     - 자기 * j 배수의 이모티콘으로 탐색 -> min[현재 * j] + j
   */

public class 이모티콘 {

    final static int PRICEMAX = 1000;
    final static int INDEXMAX = 1010;

    private static int[] min;
    private static int N;

    static class Emoticon {
        int index;
        int price;

        public Emoticon(int index, int price) {
            this.index = index;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 최소 걸리는 시간을 다루는 배열(인덱스: i개의 이모티콘, 값: i개의 이모티콘을 만드는데 걸리는 시간의 최솟값)
        min = new int[INDEXMAX];
        for (int i = 1; i < INDEXMAX; i++) { // 1을 복사에서 i번씩 복붙할 수 있음으로 인데그 값으로 초기화
            min[i] = i;
        }

        // BFS를 탐색할 PriorityQueue 생성
        PriorityQueue<Emoticon> pq = new PriorityQueue<Emoticon>((a, b) -> {
            return a.price - b.price;
        });
        pq.add(new Emoticon(2, 2));
        pq.add(new Emoticon(3, 3));

        // BFS
        while (!pq.isEmpty()) {
            Emoticon emoticon = pq.poll();
            int index = emoticon.index;
            int price = emoticon.price;
            if (index == N) {
                break;
            }
            if (index <= 0 || index >= INDEXMAX - 1 || price > min[index]) {
                continue;
            }

            // 이모티콘 - 1 한 값에 접근
            if (min[index - 1] >= price + 1) {
                min[index - 1] = price + 1;
                pq.add(new Emoticon(index - 1, min[index - 1]));
            }

            // 이모티콘 * j (배수) 값에 접근
            for (int j = 2; j < INDEXMAX; j++) {
                int multipleIndex = index * j; // 배수
                if (multipleIndex >= INDEXMAX) {
                    break;
                }
                if (min[multipleIndex] >= price + j) {
                    min[multipleIndex] = price + j;
                    pq.add(new Emoticon(multipleIndex, min[multipleIndex]));
                }
            }
        }

        System.out.println(min[N]);
    }

}
