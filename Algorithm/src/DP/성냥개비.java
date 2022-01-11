import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 성냥개비 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Stick> sticks;
    private static String[][][] minDp;
    private static String[] maxDp;

    static final int MAX_FIGURE = 16;
    static final int MAX_STICK = 101;

    static class Stick {
        int num; // 값
        int count; // 개수

        public Stick(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        // 개수와 숫자 매칭
        initStick();

        // 알고리즘

        // 최솟값 구하기
        // dp[시작0,1][남은 자릿수][남은 성냥개수]
        minDp = new String[2][MAX_FIGURE][MAX_STICK];
        initMinDp(); // 배열 초기화
        getMinDp();

        // 최댓값 구하기
        maxDp = new String[MAX_STICK];
        getMaxDp();

        // 테스트케이스 결과 도출
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int num = Integer.parseInt(br.readLine());
            int figure = (num - 1) / 7 + 1; // 자릿수
            sb.append(minDp[1][figure][num]).append(" ");
            sb.append(maxDp[num]).append("\n");
        }
        System.out.println(sb);
    }

    private static void getMinDp() {
        for (int i = 2; i <= 100; i++) {
            int figure = (i - 1) / 7 + 1; // 자릿수
            minDp[0][figure][i] = getDpValue(0, i, figure, "");
            minDp[1][figure][i] = getDpValue(1, i, figure, "");
        }
    }

    private static void initMinDp() {
        // 최소값 DP 초기화
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < MAX_FIGURE; j++) {
                for (int k = 0; k < MAX_STICK; k++) {
                    minDp[i][j][k] = "";
                }
            }
        }
    }

    private static void getMaxDp() {
        for (int i = 2; i <= 100; i++) {
            int count = i / 2;
            StringBuilder stringBuilder = new StringBuilder();
            if (i % 2 == 0) { // 짝수이면
                for (int j = 0; j < count; j++) {
                    stringBuilder.append(1);
                }
            } else {
                stringBuilder.append(7);
                for (int j = 0; j < count - 1; j++) {
                    stringBuilder.append(1);
                }
            }
            maxDp[i] = stringBuilder.toString();
        }
    }

    // rest 는 남아있는 성냥개비 개수, toggle 은 첫 값이 0으로 시작할지 1로 시작할지 알려주는값
    private static String getDpValue(int toggle, int rest, int figure, String str) {
        if (rest == 0 || figure == 0) {
            return "";
        }
        for (int i = toggle; i < sticks.size(); i++) {
            // 토큰 0일때 dp를 활용
            if (toggle == 0 && !minDp[0][figure][rest].equals("")) {
                return minDp[0][figure][rest];
            } else { // dp 없으면 dfs 로 찾기
                // 숫자 선택
                Stick stick = sticks.get(i);
                // 남은성냥 - 선택한 성냥 < 0 이면 || 뒤에 자릿수가 늘어나게 되면 다시 선택
                if (rest - stick.count < 0 || rest - stick.count > (figure - 1) * 7) {
                    continue;
                }
                // 선택후 다음 자리 탐색
                str += stick.num;
                rest -= stick.count;
                str += getDpValue(0, rest, figure - 1, str);
                break;
            }
        }
        return str;
    }

    private static void initStick() {
        sticks = new ArrayList<>();
        sticks.add(new Stick(1, 2));
        sticks.add(new Stick(7, 3));
        sticks.add(new Stick(4, 4));
        sticks.add(new Stick(2, 5));
        sticks.add(new Stick(0, 6));
        sticks.add(new Stick(6, 6));
        sticks.add(new Stick(8, 7));
        // 3, 9는 사용되지 않음

        Collections.sort(sticks, (a, b) -> {
            return a.num - b.num;
        });
    }
}