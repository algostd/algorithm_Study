package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HanbinSpotMart {
    
    public static void main(String[] args) throws Exception {
        
        //System.setIn(new FileInputStream("/res/input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {

            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            List<Integer> inputList = new ArrayList<Integer>();
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (num <= m) { //무게 제한 m 이하의 과자만 inputList에 저장
                    inputList.add(num);
                }
            }
            
            //알고리즘
            //먼저 가능한 두 쌍의 합을 모두 더해 리스트에 저장한다.
            List<Integer> sumList = new ArrayList<Integer>();
            int size = inputList.size();
            for (int i = 0; i < size; i++) {
                int num1 = inputList.get(i);
                for (int j = i + 1; j < size; j++) {
                    int num2 = inputList.get(j);
                    sumList.add(num1 + num2);
                }
            }
            Collections.sort(sumList); //오름차순으로 정렬
            
            int answer = -1;
            if (sumList.size() > 0) {
                boolean check = false;
                for (int i = sumList.size() - 1; i >= 0; i--) {
                    int num = sumList.get(i);
                    if (num <= m) {
                        answer = num;
                        check = true;
                        break;
                    }
                }
                
                if (check == false) {
                    answer = -1;
                }
            } else if (sumList.size() == 0) {
                answer = -1;
            }
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write("#");
            bw.write(String.valueOf(test_case));
            bw.write(" ");
            bw.write(String.valueOf(answer));
            bw.write("\n");
            bw.flush();
        }
    }
}