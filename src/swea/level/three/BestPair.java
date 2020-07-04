package swea.level.three;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//√÷∞Ì¿« Ω÷ 9839
public class BestPair {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int N;
        String[] arr;
        int [] arrNum, muti;
        
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            arr = br.readLine().split(" ");
            arrNum = new int[arr.length];
            for (int j = 0; j < N; j++) {
                arrNum[j] = Integer.parseInt(arr[j]);
            }
            
            int maxMulti = -1;
            for (int j = 0; j < arrNum.length - 1; j++) {
                int x = arrNum[j];
                for (int z = j + 1; z < arrNum.length; z++) {
                    int y = arrNum[z];
                    int t = x * y;
                    int num = t % 10;
                    t = t / 10;
                    boolean check = true;
                    
                    while (t > 0) {
                        if (num - 1 == t % 10) {
                            num--;
                        } else {
                            check = false;
                            break;
                        }
                        t = t / 10;
                    }
                    if (check && maxMulti < x * y) {
                        maxMulti = x * y;
                    }
                }
            }
            bw.write("#" + i + " " + maxMulti + "\n");
        }
        br.close();
        bw.close();
    }

}
