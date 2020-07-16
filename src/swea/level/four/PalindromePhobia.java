package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PalindromePhobia {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("/res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {

		    //입력
			String str = br.readLine();
			
			//알고리즘
			char[] arr = str.toCharArray();
            
			Arrays.sort(arr); // 한글자씩 정렬. 3번째 TC의 경우 ccbabababac => aaaabbbbccc

            int size = arr.length;
			
            //서로 다른 문자가 몇개씩 있는지 파악한다.
			int cnt = 0;
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) { 
			    cnt++;
			    if (i == size - 1) {
			        list.add(cnt); //4 4 3
			        break;
			    }
			    
			    char ch1 = arr[i];
			    char ch2 = arr[i + 1];
			    if (ch1 != ch2) {
			        list.add(cnt);
			        cnt = 0;
			    }
			}
			Collections.sort(list, Collections.reverseOrder()); //내림차순으로 정렬
			
			//int 배열에 저장한다.
			//하나하나의 문자가 무엇인지는 중요하지 않고 각각 몇개씩 있는지만 중요하다. 
			//=> 숫자로 표현해도 상관없음 (ex. aaaabbbbccc라면 433)
			int[] int_arr = new int[100002];
			for (int i = 0; i < list.size(); i++) {
			    int_arr[i] = list.get(i);
			}
			
			List<Integer> answerList = new ArrayList<Integer>();
			
			boolean answer = true;
			if (list.size() > 1) {
			    answerList.add(0);
	            int_arr[0] -= 1;
	            answerList.add(1);
	            int_arr[1] -= 1;
	            
	            for (int i = 2; i < size; i++) {
	                int preNum1 = answerList.get(i - 1); //바로 앞의 수
	                int preNum2 = answerList.get(i - 2); //두번째 앞의 수
	                
	                boolean check = false;
	                for (int j = 0; j < list.size(); j++) {
	                    if (j != preNum1 && j != preNum2 && int_arr[j] > 0) { // 바로 앞의 수와 두번째 앞의 수와 중복되는지 검사한다.
	                        answerList.add(j);
	                        int_arr[j] -= 1;
	                        check = true;
	                        break;
	                    }
	                }
	                if (check != true) {
	                    answer = false;
	                    break;
	                }
	            }    
			}
			else if (list.size() == 1) {
			    if (list.get(0) > 1) {
			        answer = false;
			    }
			}
			
			//출력
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            if (answer == true) {
                bw.write("#" + test_case + " " + "YES" + "\n");
            } else if (answer == false) {
                bw.write("#" + test_case + " " + "NO" + "\n");
            }
            bw.flush();
			
		}
	}
	

}
