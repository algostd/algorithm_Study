package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class 후보추천하기 {

    private static int postN;
    private static int recommandN;
    private static PriorityQueue<Post> pq;
    private static ArrayList<Integer> postList;
    private static String[] votes;

    static class Post {
        int num;
        int count;
        int time;

        public Post(int num, int count, int time) {
            this.num = num;
            this.count = count;
            this.time = time;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < recommandN; i++) {
            int num = Integer.parseInt(votes[i]);
            // 이미 포스팅 되어 있을 떄
            if (postList.contains(num)) {
                updatePost(num);
                continue;
            }

            Post post = new Post(num, 1, i);
            // 포스팅자리가 없을 때
            if (postList.size() >= postN) {
                Post poll = pq.poll();
                postList.remove(Integer.valueOf(poll.num));
            }
            pq.add(post);
            postList.add(num);
        }

        // 정렬 후 정답 출력
        Collections.sort(postList);
        for (Integer num : postList) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }

    private static void updatePost(int num) {
        Post get = null;
        for (Post post : pq) {
            if (post.num == num) {
                get = post;
            }
        }
        int count = get.count + 1;
        int time = get.time;
        pq.remove(get);

        pq.add(new Post(num, count, time));
    }

    private static void init() throws IOException {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return a.time - b.time;
            }
            return a.count - b.count;
        });
        postList = new ArrayList<>();

        postN = Integer.parseInt(br.readLine());
        recommandN = Integer.parseInt(br.readLine());
        votes = br.readLine().split(" ");
    }
}
