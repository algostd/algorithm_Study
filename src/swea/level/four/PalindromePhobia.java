package swea.level.four;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 8934. 팰린드롬 공포증
public class PalindromePhobia {
    
    static int[] abcNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T;
        String S, ans;
        
        
        
        T = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= T; i++) {
            abcNum = new int[3];
            S = br.readLine();
            abcCheckAndSort(S);
            ans = palindromCheck(S);
            
            
            bw.write("#" + i + " " + ans + "\n");

        }
        
        br.close();
        bw.flush();
        bw.close();

    }
    
    //문자열에 있는 abc개수 체크 후 오름차순 정렬
    static void abcCheckAndSort (String S) {
        char[] arrChar = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            if (arrChar[i] == 'a') {
                abcNum[0]++;
            } else if (arrChar[i] == 'b') {
                abcNum[1]++;
            } else {
                abcNum[2]++;
            }
        }
        
        Arrays.sort(abcNum);
        
    }
    
    static String palindromCheck(String S) {
        String ans = "NO";
        if (S.length() % 3 == 2) {
            if (abcNum[2] == abcNum[1] && abcNum[1] == abcNum[0] + 1) {
                ans = "YES";
            }
        } else if (S.length() % 3 == 1) {
            if (abcNum[2] == abcNum[1] + 1 && abcNum[1] == abcNum[0]) {
                ans = "YES";
            }
        } else if (S.length() % 3 == 0) {
            if (abcNum[0] == abcNum[1] && abcNum[1] == abcNum[2]) {
                ans = "YES";
            }
        }
        
        return ans;
    }

}
