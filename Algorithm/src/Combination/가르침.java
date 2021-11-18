package Algorithm.src.Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


// https://www.acmicpc.net/problem/1062
// 조합(탐색)

public class 가르침 {
    static ArrayList<Character> antic = new ArrayList<Character>();

    public static void main(String[] args) throws IOException {
        가르침 s = new 가르침();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");
        int N = Integer.parseInt(strArr[0]);
        int K = Integer.parseInt(strArr[1]);

        strArr = new String[N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            strArr[i] = str.substring(4, str.length() - 4);
        }

        antic.add('a');
        antic.add('n');
        antic.add('t');
        antic.add('i');
        antic.add('c');
        int answer = 0;

        // 단어가 어떤 알파벳으로 이루어 졌나 검사
        ArrayList<Set<Character>> alphabets = new ArrayList<>();
        HashSet<Character> allAlphabetsSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < strArr[i].length(); j++) {
                char ch = strArr[i].charAt(j);
                if (!antic.contains(ch)) { // antic이 아닌 거만 담음
                    set.add(ch);
                    allAlphabetsSet.add(ch);
                }
            }
            alphabets.add(set);
        }

        if (K < 5) {
            answer = 0;
        } else if (K == 5) {
            int count = 0;
            for (Set<Character> alphabet : alphabets) {
                if (alphabet.size() == 0) {
                    count++;
                }
            }
            answer = count;
        } else { // K > 5
            int C = K - 5; // C크기 만큼의 알파벳 조합을 만들어야함
            ArrayList<Character> charArr = new ArrayList<>();
            if (allAlphabetsSet.size() < C){
                answer = alphabets.size();
            } else{
                answer = s.getCombination(allAlphabetsSet, alphabets, C, charArr);
            }
        }
        System.out.println(answer);
    }

    // 이 Set안에서 K-5만큼의 알파벳을 선택(글로벌Set.length C K-5 =>C) 조합

    public int getCombination(HashSet<Character> allAlphabetsSet, ArrayList<Set<Character>> alphabets, int C, ArrayList<Character> charArr) {
        if (charArr.size() == C) {
            int count = 0;
            for (Set<Character> alphabet : alphabets) {
                if (charArr.containsAll(alphabet)) {
                    count++;
                }
            }
            return count;
        }

        int answer = -1;
        HashSet<Character> copy = (HashSet<Character>) allAlphabetsSet.clone();
        for (Character character : allAlphabetsSet) {
            charArr.add(character);
            copy.remove(character);
            if(copy.isEmpty()){

            }
            int tempAnswer = getCombination(copy, alphabets, C, charArr);
            if (answer < tempAnswer) {
                answer = tempAnswer;
            }
            charArr.remove(character);
        }
        return answer;
    }
}
