import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 학년1 {
    private static int N;
    private static int[] intArr;
    private static long[] countArr;
    private static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] strArr = br.readLine().split(" ");
        intArr = new int[N];
        countArr = new long[21];
        for (int i = 0; i < N; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        countArr[intArr[0]] = 1;
        for (int i = 1; i < N - 1; i++) { // 8 3 2 4 8 7 2 4 0 8 8
            long[] countClone = new long[21];
            for (int j = 0; j < 21; j++) { // countArr의 인덱스 뒤짐
                if(countArr[j] > 0){ // 꺼내서 +- 한 것에 count++
                    if(j - intArr[i] >= 0){
                        countClone[j - intArr[i]] += countArr[j];
                    }
                    if(j + intArr[i] <= 20){
                        countClone[j + intArr[i]] += countArr[j];
                    }
                }
            }
            countArr = countClone;
        }

        answer = countArr[intArr[N-1]];
        System.out.println("answer = " + answer);
    }



}
