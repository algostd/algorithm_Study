package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("C:/Users/gjdud/eclipse-workspace/algorithm/res/input.txt"));
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(br.readLine());
    
        for (int test_case = 1; test_case <= T; test_case++) {
            
            int n = Integer.parseInt(br.readLine());
            int[] array = new int[n];
            
            //N개의 양의 정수를 배열에 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }
            
            //조건에 충족하는 지 모든 경우의 수 검사
            List<Integer> answerList = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                int num = array[i];
                for (int j = i + 1; j < n; j++) {
                    int next = array[j];
                    int product = num * next;
                    
                    int check = 0;
                    int temp = product;
                    List<Integer> list = new ArrayList<Integer>();
                    while (temp > 0) {
                        int remain = temp % 10;
                        list.add(remain);
                        temp = temp / 10;
                    }
                    Collections.reverse(list);
                    
                    for (int k = 1; k < list.size(); k++) {
                        int pre = list.get(k - 1);
                        int current = list.get(k);
                        
                        if (current != pre + 1) {
                           check = 1; 
                        }
                    }
                    
                    if (check == 0) {
                        answerList.add(product);
                    }
                }
            }
            
            Collections.sort(answerList);
            Collections.reverse(answerList);
            
            //출력
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(test_case);
            sb.append(" ");

            if (answerList.size() != 0) {
                sb.append(answerList.get(0));
            } else if (answerList.size() == 0) {
                sb.append(-1);
            }
//            sb.append(System.getProperty("line.separator"));
            System.out.println(sb.toString());
        }
        
    }
}