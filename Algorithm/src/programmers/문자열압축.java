package programmers;

/* https://programmers.co.kr/learn/courses/30/lessons/60057
 * 문자열 압축 
ababcdcdababcdcd

abcabcabcabcdededededede
2abcabc2dedede <- 6단위
4abcdededededede <- 3단위
abcabcabcabc6de <- 2단위

1. 단위 설정n (1 ~ s.length개)
2. 처음부터 n개의 종류가 담긴 문자열을 만들고 연속되는지 확인 
 - 안되어 있으면(처음 설정한 문자열과 다음 크기의 문자열이 다르면)
   처음 설정한 문자열을 다시 세팅 <- 다음문자열을 대입
 - 되어있으면 
   다음크기의 문자열 확인 dupCount ++
   
 * */
public class 문자열압축 {
	
	    public int solution(String s) {
	        int answer = s.length();
	        for(int n = 1; n < s.length(); n++){
	        	String tempString = "";
	        	// n개의 단위
	        	int index = 0;
	            
	            String standardString = s.substring(index, index+n);
	            int dupCount = 1;
	            while(index < s.length()) {
	            	// 다음 string 구하기
	            	if(index + 2*n > s.length()) {
	            		if(dupCount > 1) {
		        			tempString += dupCount;
		        		} 
	            		tempString += standardString;
	            		tempString += s.substring(index+n, s.length());
	            		// 문제점
//	            		tempAnswer += (s.length() - (index + n));
//		        		tempAnswer += standardString.length();
	            		break;
	            	}
	            	index += n;
		        	String nextString = s.substring(index, index+n);
		        	
		        	if(standardString.equals(nextString)) { // 연속되면 (같으면)
		        		dupCount++;
		        	} else { // 연속되지 않고 (다르면)
		        		if(dupCount > 1) {
		        			tempString += dupCount;
		        		}
		        		tempString += standardString;
		        		standardString = nextString;
		        		dupCount = 1;
		        	}
		        	
	            }
	            if(answer > tempString.length()) {
	            	answer = tempString.length();
	            }
	        	
	        }
	        
	        return answer;
	    }
	
	public static void main(String[] args) {
		문자열압축 s = new 문자열압축();
		System.out.println(s.solution("aaaaaaaaaa"));
		
	}
}
