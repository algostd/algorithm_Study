package SWE;
import java.io.*;

public class Ex_9839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // ??€?Έ μΌ??΄?€ κ°μ
        for (int Test_Case = 1; Test_Case <= T; Test_Case++) {

            int n = Integer.parseInt(br.readLine()); // μΌ??΄?€ ?΄ ? ?? κ°μ n
            String str[] = br.readLine().split(" ");
            int arr[] = new int[str.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            // κ²??¬ ??
            int MaxResult = -1;
            for (int i = 0; i < arr.length - 1; i++) {
                int x = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    int y = arr[j];
                    int z = x * y; // z? κ°? ?κ°μ κ³?
                    int num = z % 10;
                    z /= 10;

                    // κ΅¬μ²΄? ?Έ κ²??¬
                    boolean check = true; // check?? 10μ§μ?? ?°?? ?«??Έμ§? κ²??¬
                    while (z > 0) { // zκ°? 1? ?λ¦¬μ ???Όλ©? ?€?΄ ?€μ§?? ??. ?°?Ό?, 1? ?λ¦? ? ?Ό ? check? true,
                                    // MaxResult < x*y? λ§μ‘±
                        if (num - 1 == z % 10) {
                            num--;
                            z /= 10;
                        } else {
                            check = false;
                            break;
                        }
                    }
                    if (check && MaxResult < x * y) { // κ²??¬(10μ§μ ?°?)λ₯? λ§μ‘±?κ³? ? ?¬λ©? κ°±μ 
                        MaxResult = x * y; // z? ?΄λ―? /10λ‘? κ³μ μͺΌκ°μ‘μ
                    }
                }
            }
            bw.write("#" + Test_Case + " " + MaxResult + "\n");
        }
        bw.close();
        br.close();
    }
}
