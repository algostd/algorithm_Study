package programmers;

// https://programmers.co.kr/learn/courses/30/lessons/43165
// dfs문제 난이도는 쉬움

public class 타겟넘버 {
	 private static int answer = 0;
	    public int solution(int[] numbers, int target) {
	        
	        dfs(numbers, 0, 0, target);
	        return answer;
	    }
	    
	    void dfs(int[] numbers, int index, int sum, int target){
	        if(index == numbers.length){
	            if(sum == target){
	                answer++;
	            }
	            return;
	        }
	        int now = numbers[index];
	        
	        // +
	        sum += now;
	        dfs(numbers, index + 1, sum, target);
	        
	        // -
	        sum -= now * 2;
	        dfs(numbers, index + 1, sum, target);
	    }
}
