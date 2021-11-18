package Algorithm.src.ETC;

/* https://programmers.co.kr/learn/courses/30/lessons/60057
 * ���ڿ� ���� 
ababcdcdababcdcd

abcabcabcabcdededededede
2abcabc2dedede <- 6����
4abcdededededede <- 3����
abcabcabcabc6de <- 2����

1. ���� ����n (1 ~ s.length��)
2. ó������ n���� ������ ��� ���ڿ��� ����� ���ӵǴ��� Ȯ�� 
 - �ȵǾ� ������(ó�� ������ ���ڿ��� ���� ũ���� ���ڿ��� �ٸ���)
   ó�� ������ ���ڿ��� �ٽ� ���� <- �������ڿ��� ����
 - �Ǿ������� 
   ����ũ���� ���ڿ� Ȯ�� dupCount ++
   
 * */
public class ���ڿ����� {
	
	    public int solution(String s) {
	        int answer = s.length();
	        for(int n = 1; n < s.length(); n++){
	        	String tempString = "";
	        	// n���� ����
	        	int index = 0;
	            
	            String standardString = s.substring(index, index+n);
	            int dupCount = 1;
	            while(index < s.length()) {
	            	// ���� string ���ϱ�
	            	if(index + 2*n > s.length()) {
	            		if(dupCount > 1) {
		        			tempString += dupCount;
		        		} 
	            		tempString += standardString;
	            		tempString += s.substring(index+n, s.length());
	            		// ������
//	            		tempAnswer += (s.length() - (index + n));
//		        		tempAnswer += standardString.length();
	            		break;
	            	}
	            	index += n;
		        	String nextString = s.substring(index, index+n);
		        	
		        	if(standardString.equals(nextString)) { // ���ӵǸ� (������)
		        		dupCount++;
		        	} else { // ���ӵ��� �ʰ� (�ٸ���)
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
		���ڿ����� s = new ���ڿ�����();
		System.out.println(s.solution("aaaaaaaaaa"));
		
	}
}
