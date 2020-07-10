package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Diamond {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("/res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {

			//입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken()); //max - min <= k이하인 것들의 묶음
			
			List<Integer> inputList = new ArrayList<Integer>();
			
			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(br.readLine());
				inputList.add(num);
			}
		
			//inputList를 오름차순으로 정렬
			Collections.sort(inputList);
			
			//알고리즘
			int answer = 1;
			for (int i = 0; i < n; i++) {
			    int cnt = 1;
			    int min = inputList.get(i);
			    for (int j = i + 1; j < n; j++) {
			        int max = inputList.get(j);
			        
			        if (max - min <= k) {
			            cnt++;
			            if (cnt > answer) {
                            answer = cnt;
                        }
			        } else {
			            if (cnt > answer) {
			                answer = cnt;
			            }
			            break;
			        }
			    }
			}
			
			//출력
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write("#" + test_case + " " + answer + "\n");
			bw.flush();
		}
	}
	

}
