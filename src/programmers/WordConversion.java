class Solution {
    static int[] visited;
    static int len;
    static int min = 100;
    static String[] words;
    static String begin, target;

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        len = words.length;
        visited = new int[len];
        Solution.words = words;
        Solution.begin = begin;
        Solution.target = target;

        //words 배열에 target이 없을 경우 0 반환
        boolean check = false;
        for (int i = 0; i < len; i++) {
            if (target.equals(words[i])) {
                check = true;
            }
        }
        if (!check) {
            return answer;
        }

        // dfs 시작
        char[] chArr1 = begin.toCharArray();
        for (int i = 0; i < len; i++) {
            char[] chArr2 = words[i].toCharArray();
            int tmp = 0;
            for (int k = 0; k < chArr2.length; k++) {
                //문자가 다르면 tmp++;
                if (chArr1[k] != chArr2[k]) {
                    tmp++;
                }
            }
            //다른 문자가 단 하나일때
            if (tmp == 1) {
                dfs(i, 1);
                visited[i] = 0; //방문처리 해제
            }
        }

        answer = min;
        return answer;
    }

    static void dfs(int ax, int cnt) {
        //방문처리
        visited[ax] = 1;
        if (words[ax].equals(target)) {
            if (cnt < min) {
                min = cnt;
            }
            return;
        }

        char[] chArr1 = words[ax].toCharArray();
        for (int i = 0; i < len; i++) {
            if (i == ax) {
                continue;
            }

            //방문하지 않았을 경우
            if (visited[i] == 0) {

                char[] chArr2 = words[i].toCharArray();
                int tmp = 0;
                for (int k = 0; k < chArr2.length; k++) {
                    //문자가 다르면 tmp++;
                    if (chArr1[k] != chArr2[k]) {
                        tmp++;
                    }
                }
                //다른 문자가 단 하나일때
                if (tmp == 1) {
                    dfs(i, cnt + 1);
                    visited[i] = 0; //방문처리 해제
                }
            }
        }
        return;
    }
}