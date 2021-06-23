// https://programmers.co.kr/learn/courses/30/lessons/77484?language=java
// 로또의 최고 순위와 최저 순위
package programmers;

class lotto {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int zeroCount = 0;
        int equalCount = 0;
        
        for(int i = 0; i < 6; i++){ // lottos
            if(lottos[i] == 0){
                zeroCount++;
                continue;
            }
            for(int j = 0; j < 6; j++){ // win_nums
                if(lottos[i] == win_nums[j]){
                    equalCount++;
                }
            }   
        }
        answer[0] = equalCount + zeroCount;
        answer[1] = equalCount;
        for(int i = 0; i < 2; i++){
            if(answer[i] <= 1){
                answer[i] = 6;
                continue;
            }
            answer[i] = 7 - answer[i];
        }
        
        return answer;
    }
}