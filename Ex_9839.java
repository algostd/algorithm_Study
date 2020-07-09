package SWE;
import java.io.*;

public class Ex_9839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // ?…Œ?Š¤?Š¸ ì¼??´?Š¤ ê°œìˆ˜
        for (int Test_Case = 1; Test_Case <= T; Test_Case++) {

            int n = Integer.parseInt(br.readLine()); // ì¼??´?Š¤ ?‚´ ? •?ˆ˜?˜ ê°œìˆ˜ n
            String str[] = br.readLine().split(" ");
            int arr[] = new int[str.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            // ê²??‚¬ ?‹œ?‘
            int MaxResult = -1;
            for (int i = 0; i < arr.length - 1; i++) {
                int x = arr[i];
                for (int j = i + 1; j < arr.length; j++) {
                    int y = arr[j];
                    int z = x * y; // z?Š” ê°? ?‘ê°œì˜ ê³?
                    int num = z % 10;
                    z /= 10;

                    // êµ¬ì²´? ?¸ ê²??‚¬
                    boolean check = true; // check?? 10ì§„ìˆ˜?—?„œ ?—°?†?œ ?ˆ«??¸ì§? ê²??‚¬
                    while (z > 0) { // zê°? 1?˜ ?ë¦¬ìˆ˜ ???œ¼ë©? ?“¤?–´ ?˜¤ì§??„ ?•Š?Œ. ?”°?¼?„œ, 1?˜ ?ë¦? ?ˆ˜ ?¼ ?• check?„ true,
                                    // MaxResult < x*y?„ ë§Œì¡±
                        if (num - 1 == z % 10) {
                            num--;
                            z /= 10;
                        } else {
                            check = false;
                            break;
                        }
                    }
                    if (check && MaxResult < x * y) { // ê²??‚¬(10ì§„ìˆ˜ ?—°?†)ë¥? ë§Œì¡±?•˜ê³? ?” ?¬ë©? ê°±ì‹ 
                        MaxResult = x * y; // z?Š” ?´ë¯? /10ë¡? ê³„ì† ìª¼ê°œì¡ŒìŒ
                    }
                }
            }
            bw.write("#" + Test_Case + " " + MaxResult + "\n");
        }
        bw.close();
        br.close();
    }
}
