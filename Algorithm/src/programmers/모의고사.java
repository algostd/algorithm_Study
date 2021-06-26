package programmers;

import java.util.ArrayList;

// https://programmers.co.kr/learn/courses/30/lessons/42840
// 주기에 따른 % 문제 

public class 모의고사 {
	public int[] solution(int[] answers) {
        ArrayList<Integer> tempAnswer = new ArrayList<Integer>();
        
        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
            
        for(int i = 0; i < answers.length; i++){
            int p1 = i % 5;
            int p2 = i % 8;
            int p3 = i % 10;
            
            count1 += checkSame(answers[i], person1[p1]);
            count2 += checkSame(answers[i], person2[p2]);
            count3 += checkSame(answers[i], person3[p3]);
            
        }
        
        int max = Math.max(Math.max(count1, count2), count3);
        if(max == count1){
        	tempAnswer.add(1);
        } 
        if(max == count2){
        	tempAnswer.add(2);
        }
        if(max == count3){
        	tempAnswer.add(3);
        }
        
        int[] answer = new int[tempAnswer.size()];
        for(int i = 0; i < tempAnswer.size(); i++){
            answer[i] = tempAnswer.get(i);
        }

        return answer;
    }
    
    public int checkSame(int a, int b){
        if(a == b){
            return 1;
        }
        return 0;
    }
    
	public static void main(String[] args) {
		모의고사 s = new 모의고사();
		int[] answers = {1,3,2,4,2};
		int[] answer = s.solution(answers);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);	
		}

	}
}
