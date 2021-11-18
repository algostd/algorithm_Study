package Algorithm.src.DFS;

public class 프로그래머스_위클리챌린지5주차_모음사전 {
    static String findWord;
    boolean findCheck;
    char[] chars, alpha;
    static int answer;
    public int solution(String word) {
        findWord = word;
        chars = new char[5];
        alpha = new char[]{'A', 'E', 'I', 'O', 'U'};
        findCheck = false;
        getSubset(0);
        return answer;
    }

    private void getSubset(int start) {
        for (int i = 0; i < 5; i++) {
            if(findCheck){
                return;
            }
            chars[start] = alpha[i];
            answer++;
            if(String.valueOf(chars).substring(0,start + 1).equals(findWord)){ // 찾았으면
                findCheck = true;
                return;
            }
            if(start != 4){
                getSubset(start + 1);
                chars[start + 1] = '\0';
            }
        }
    }

}
