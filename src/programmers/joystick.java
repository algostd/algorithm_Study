class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        char[] chArr = name.toCharArray();
        //visited[] 생성
        int[] visited = new int[len];
        for (int i = 0; i < len; i++) {
            visited[i] = 0;
            if (chArr[i] == 'A') {
                visited[i] = 1; // 'A'일경우 방문할필요가 없기에 방문처리.
            }
        }

        //시작점 0
        int cur = 0;
        while (true) {
            visited[cur] = 1;
            char ch = chArr[cur];

            // 커서에 있는 문자 바꾸기
            int num = (int)ch - 65; //A = 0

            if (num <= 13) {
                answer += num;
                visited[cur] = 1;
            }
            if (num >= 14) {
                num -= 26;
                num = Math.abs(num);
                answer += num;
                visited[cur] = 1;
            }

            //모든 문자를 방문했는지 체크
            int check = 0;
            for (int i = 0; i < len; i++) {
                if (visited[i] == 0) { //방문한 적이 없는 지점이 아직 남아있다면 check = 1 처리
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                break;
            }

            //다음 방향 결정
            int right = 0;
            int left = 0;

            //cur, cur1, cur2는 인덱스를 의미한다.
            int cur1 = cur; //오른쪽
            while (true) {
                right++;
                cur1++;
                if (cur1 >= len) {
                    cur1 = 0;
                }

                if (visited[cur1] == 0) {
                    break;
                }
            }

            int cur2 = cur; //왼쪽
            while (true) {
                left++;
                cur2--;
                if (cur2 < 0) {
                    cur2 = len - 1;
                }

                if (visited[cur2] == 0) {
                    break;
                }
            }

            if (right <= left) {
                cur = cur1;
                answer += right;
            }
            if (right > left) {
                cur = cur2;
                answer += left;
            }
        }

        return answer;
    }
}