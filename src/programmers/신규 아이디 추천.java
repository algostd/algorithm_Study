import java.util.regex.Pattern;

class Solution {
    public String solution(String new_id) {
        String answer = "";

        //1. 대문자를 소문자로 치환.
        new_id = new_id.toLowerCase();

        //2. 소문자, 숫자, -, _, . 만 허용
        String new_id_check = "";
        for (int i = 0; i < new_id.length(); i++) {
            String s = new_id.substring(i, i+1);

            boolean regex = false;
            String pattern1 = "^[a-z]*$"; //영문자
            String pattern2 = "^[0-9]*$"; //숫자
            regex = Pattern.matches(pattern1, s);
            if (regex == true) {
                new_id_check += s;
                continue;
            }
            regex = Pattern.matches(pattern2, s);
            if (regex == true) {
                new_id_check += s;
                continue;
            }
            if (s.equals("-") || s.equals("_") || s.equals(".")) {
                new_id_check += s;
            }
        }
        new_id = new_id_check;

        //3. 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환
        new_id_check = "";
        for (int i = 0; i < new_id.length(); i++) {
            int left = i;
            int right = i+1;
            String s = new_id.substring(left, left+1);
            new_id_check += s;
            if (!s.equals(".")) {
                continue;
            }

            boolean check = false;
            while(right < new_id.length()) {
                String right_str = new_id.substring(right, right+1);
                if (!right_str.equals(".")) {
                    break;
                } else {
                    check = true;
                    right++;
                }
            }
            if (check == true) {
                i = right - 1;
            }
        }
        new_id = new_id_check;

        //4. 처음과 끝의 마침표 제거
        new_id = first_last_check(new_id);

        //5. 빈문자열이라면 "a" 대입
        if (new_id.length() == 0) {
            new_id = "a";
        }

        //6. 15까지만
        new_id_check = "";
        if (new_id.length() >= 16) {
            for (int i = 0; i < 15; i++) {
                String s = new_id.substring(i,i+1);
                new_id_check += s;
            }
            new_id = new_id_check;
        }
        new_id = first_last_check(new_id);

        //7.
        if(new_id.length() <= 2) {
            String s = new_id.substring(new_id.length()-1,new_id.length());
            while (new_id.length() <= 2) {
                new_id += s;
            }
        }

        return new_id;
    }

    private String first_last_check(String s) {
        //s.length == 0
        if (s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            if (s.substring(0, 1).equals(".")) {
                return "";
            } else {
                return s;
            }
        }
        if (s.length() > 1) {
            String first = s.substring(0, 1);
            String last = s.substring(s.length()-1, s.length());

            String new_s = "";
            if (!first.equals(".")) {
                new_s += first;
            }
            for (int i = 1; i < s.length()-1; i++) {
                new_s += s.substring(i,i+1);
            }
            if (!last.equals(".")) {
                new_s += last;
            }
            s = new_s;
        }
        return s;
    }
}