import java.util.Stack;

class Solution
{
    public static int solution(String s)
    {
        int answer = -1;

        Stack<Character> st = new Stack<Character>();
        char[] chArr = s.toCharArray();
        for (int i = 0; i < chArr.length; i++) {
            if (chArr.length - i  < st.size()) {
                answer = 0;
                return answer;
            }

            char ch = chArr[i];
            if (st.empty()) {
                st.push(ch);
            } else {
                if (ch == st.peek()) {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }
        }

        if (st.empty()) {
            answer = 1;
            return answer;
        }
        if (!st.empty()) {
            answer = 0;
            return answer;
        }

        return answer;
    }
}