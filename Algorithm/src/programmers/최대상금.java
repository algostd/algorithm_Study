import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD
// 완전 탐색이지만 효율성을 고려한 완전탐색. 미리 최대값을 찾아놓고 그 인덱스를 탐색하여 더 효율성 높은 좋은 풀이가 되었다고 생각
// 그리고 만약 아래 정렬이 되면 남은 바꿔야하는 수를 고려하지 않아도 되어 바로 정답도출하도록 설정

public class 최대상금 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        최대상금 s = new 최대상금();
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int answer = 0;
            String strArr[] = br.readLine().split(" ");

            int[] intArr = new int[strArr[0].length()];
            int length = intArr.length;
            ArrayList<Integer> checkSameNumber = new ArrayList<>();
            boolean sameNumberExist = false;
            int number = Integer.parseInt(strArr[0]);
            int change = Integer.parseInt(strArr[1]);
            for (int i = length - 1; i >= 0; i--) {
                intArr[i] = number % 10;
                number /= 10;
                if (checkSameNumber.contains(intArr[i])) {
                    sameNumberExist = true;
                } else {
                    checkSameNumber.add(intArr[i]);
                }
            }
            intArr = s.getMaxArr(intArr, 0, change, sameNumberExist);
            answer = s.getInt(intArr);
            sb.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(sb);
    }

    public int getInt(int[] intArr) {
        int ox = 1;
        int answer = 0;
        for (int k = intArr.length - 1; k >= 0; k--) {
            answer += intArr[k] * ox;
            ox *= 10;
        }
        return answer;
    }

    public boolean checkDesc(int[] intArr) {
        boolean descCheck = true;
        for (int i = 0; i < intArr.length - 1; i++) {
            if (intArr[i] < intArr[i + 1]) {
                descCheck = false;
            }
        }
        return descCheck;
    }
    public int getMax(int[] intArr, int start){
        int max = -1;
        for (int i = start; i < intArr.length; i++) {
            if (max < intArr[i]) {
                max = intArr[i];
            }
        }
        return max;
    }

    public ArrayList<Integer> findMaxIndices(int[] intArr, int start) {
        ArrayList<Integer> maxIndices = new ArrayList<>();
        int max = -1;
        for (int i = start; i < intArr.length; i++) {
            if (max < intArr[i]) {
                max = intArr[i];
                maxIndices.clear();
                maxIndices.add(i);
            } else if (max == intArr[i]) {
                maxIndices.add(i);
            }
        }
        return maxIndices;
    }

    public int[] getMaxArr(int[] intArr, int start, int change, boolean sameNumberExist) {
        boolean descCheck = checkDesc(intArr);
        // 32888 2
        int[] tempMaxIntArr;
        if (!descCheck) { // 내림차순이 아닌경우
            int max = -1;
            int[] maxIntArr = intArr.clone();
            int i = start;
            // 만약 현재 바꾸려고 하는 인덱스에 이미 최대값이 들어 있는경우
            while(true){
                if(getMax(intArr, start) == intArr[i]){
                    i++;
                } else{
                    break;
                }
            }
            // 최대값을 가지고 있는 index들을 찾고, 그 인덱스들만큼 루프(스왑)
            ArrayList<Integer> maxIndices = findMaxIndices(intArr, start + 1);
            for (Integer maxIndex : maxIndices) {
                int[] tempArr = intArr.clone();
                int temp = tempArr[i];
                tempArr[i] = tempArr[maxIndex];
                tempArr[maxIndex] = temp;
                // 값 확인
                if (change == 1) {
                    int gotInt = getInt(tempArr);
                    if (max < gotInt) {
                        max = gotInt;
                        maxIntArr = tempArr;
                    }
                } else {
                    tempMaxIntArr = getMaxArr(tempArr, start + 1, change - 1, sameNumberExist);
                    int gotInt = getInt(tempMaxIntArr);
                    if (max < gotInt) {
                        max = gotInt;
                        maxIntArr = tempMaxIntArr;
                    }
                }
            }
            return maxIntArr;
        } else { // 내림차순 되어 있으면
            if (change > 0) {
                if (!sameNumberExist) {
                    change %= 2;
                    if (change == 1) {
                        int temp = intArr[intArr.length - 1];
                        intArr[intArr.length - 1] = intArr[intArr.length - 2];
                        intArr[intArr.length - 2] = temp;
                    }
                }
            }
            return intArr;
        }
    }
}
