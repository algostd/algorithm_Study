import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Flatten {

    // https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int test_Case = 1; test_Case <= 10; test_Case++) {
            int N = Integer.parseInt(br.readLine());

            String[] strArr = br.readLine().split(" ");
            int length = strArr.length;
            int[] boxes = new int[length];

            for (int i =
                 0; i < length; i++) {
                boxes[i] = Integer.parseInt(strArr[i]);
            }
            Arrays.sort(boxes);
            // 알고리즘
            int min = boxes[0];
            int max = boxes[length-1];
            int maxIndex = -1;
            int minIndex = -1;
            int i = 0;
            while(N > 0 && max - min > 1){

                // 최대값 최솟값 구하기
                max = 0;
                min = 1001;
                for (i = 0; i < length; i++) {
                    if(max < boxes[i]){
                        max = boxes[i];
                    }
                    if(min > boxes[i]){
                        min = boxes[i];
                    }
                }

                // 높이가 가장높은 박스 찾기
                i = length - 1;
                while(boxes[i] < max && i > 0){
                    i--;
                }
                maxIndex = i;

                // 높이가 가장낮은 박스 찾기
                i = 0;
                while(boxes[i] > min && i < length - 1){
                    i++;
                }
                minIndex = i;

                // 박스 이동
                boxes[minIndex]++;
                boxes[maxIndex]--;
                N--;
            }

            // 최대값 최솟값 구하기
            max = 0;
            min = 1001;
            for (i = 0; i < length; i++) {
                if(max < boxes[i]){
                    max = boxes[i];
                }
                if(min > boxes[i]){
                    min = boxes[i];
                }
            }
            int answer = max - min;
            sb.append("#" + test_Case + " " + answer + "\n");
        }
        System.out.println(sb);
    }
}
