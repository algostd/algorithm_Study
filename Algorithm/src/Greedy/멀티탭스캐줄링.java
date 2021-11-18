package Algorithm.src.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 멀티탭스캐줄링 {
    private static ArrayList<Integer>[] priority;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int K = Integer.parseInt(strArr[1]);

        strArr = br.readLine().split(" ");
        int[] intArr = new int[K + 1]; // 주어진 전기용품 순서. 1번째 부터 K번째 까지
        priority = new ArrayList[K+1]; // 각 전기용품의 순서들을 담은 인접리스트?
        for (int i = 0; i <= K; i++) {
            priority[i] = new ArrayList<>();
        }
        for (int i = 1; i <= K; i++) { // i는 순서
            intArr[i] = Integer.parseInt(strArr[i-1]);
            priority[intArr[i]].add(i);
        }
        int temp = 0;

        // 콘센트 N개 구멍에 꽂혀 있음
        Set<Integer> nowConcents = new HashSet<>(N);

        // 탐색
        int answer = 0;
        for (int i = 1; i <= K; i++) {
            if(nowConcents.size() != N || nowConcents.contains(intArr[i])){ // 처음 콘센트 자리가 비어 있거나, 같은 전기용품일 때
                nowConcents.add(intArr[i]);
                priority[intArr[i]].remove(0);
                continue;
            }
            // 콘센트하나 비워줘야돼
            int getMax = 0;
            int removingConcent = 0;
            for (Integer nowConcent : nowConcents) {
                if(priority[nowConcent].isEmpty()){
                    removingConcent = nowConcent;
                    break;
                }
                if(getMax < priority[nowConcent].get(0)){
                    getMax = priority[nowConcent].get(0);
                    removingConcent = nowConcent;
                }
            }
            nowConcents.remove(removingConcent);
            nowConcents.add(intArr[i]);
            priority[intArr[i]].remove(0);
            answer++;
        }
        System.out.println(answer);
    }
}
