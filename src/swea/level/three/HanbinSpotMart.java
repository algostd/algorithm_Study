package swea.level.three;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 한빈이와 Spot Mart
public class HanbinSpotMart {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T, N, M;
        List<Integer> ansArr;
        int[] arr;
        
        T = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= T; i++) {
            String[] strArr = br.readLine().split(" ");
            N = Integer.parseInt(strArr[0]); //과자 봉지 개수
            M = Integer.parseInt(strArr[1]); // 최대 무게 합
            arr = new int[N];
            String[] strArr2 = br.readLine().split(" "); 
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(strArr2[j]); // 과자 무게 배열에 저장
            }
            
            ansArr = find(N, M, arr); 
            
            Collections.sort(ansArr, Collections.reverseOrder()); //몇개가 들어간지 모르기 때문에 reverseOrder
            if(ansArr.size() == 0) {
                bw.write("#" + i + " " + -1 +"\n");
            } else {
                bw.write("#" + i + " " + ansArr.get(0) +"\n");
            }
            
            
        }
        
        br.close();
        bw.close();
        

    }
    
    static List<Integer> find(int N, int M, int[] arr) {
        List<Integer> ansArr = new ArrayList<Integer>();
        for (int j = N - 1; j >= 0; j--) {
            if (arr[j] < M) {
                for (int z = j - 1; z >= 0; z--) {
                    if (arr[j] + arr[z] <= M) {
                        ansArr.add(arr[j] + arr[z]);
                    }
                }
            }
         }
        
        return ansArr;
    }

}
