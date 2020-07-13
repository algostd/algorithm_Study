package swea.level.four;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Diamond {
    
    static int MAX = 10000;
    static int[] diamond = new int[MAX + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T, N, K;
        int ans, right, cnt;
        String[] strArr;
        
        
        T = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= T; i++) {
            strArr = br.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            K = Integer.parseInt(strArr[1]);
            ans = 0;
            
            Arrays.fill(diamond, 0);
            for (int j = 0; j < N; j++) { //다이아몬드 크기 배열에 저장
                diamond[Integer.parseInt(br.readLine())]++;
            }
            
            for (int left = 0; left <= MAX - K; left++) {
                right = left + K;
                cnt = 0;
                for (int j = left; j <= right; j++) {
                    cnt += diamond[j];
                }
                
                ans = ans < cnt ? cnt : ans;
            }
            
        
            
            bw.write("#" + i + " " + ans + "\n");
            
        }
        
        br.close();
        bw.flush();
        bw.close();

    }

}
