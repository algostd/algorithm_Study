import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] array = new int[rows][columns];
        int[][] copy = new int[rows][columns];

        //행렬생성
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = i*columns + (j+1);
                copy[i][j] = i*columns + (j+1);
            }
        }

        List<Integer> answerList = new ArrayList<Integer>();

        //회전
        for (int i = 0; i < queries.length; i++) { // 0 1 2
            int num1 = queries[i][0] - 1; // 1
            int num2 = queries[i][1] - 1; // 1
            int num3 = queries[i][2] - 1; // 4
            int num4 = queries[i][3] - 1; // 3

            int startLow = num1;
            int lastLow = num3;
            int startCol = num2;
            int lastCol = num4;

            List<Integer> tempList = new ArrayList<Integer>();
            for (int ax = num1; ax <= num3; ax++) { // 1, 2, 3, 4
                for (int ay = num2; ay <= num4; ay++) { // 1, 2, 3
                    if (ax == startLow) {
                        if (ay == lastCol) {
                            array[ax + 1][ay] = copy[ax][ay];
                            tempList.add(copy[ax][ay]);
                        }
                        if (ay != lastCol) {
                            array[ax][ay + 1] = copy[ax][ay];
                            tempList.add(copy[ax][ay]);
                        }
                    } else if (ax == lastLow) {
                        if (ay == startCol) {
                            array[ax - 1][ay] = copy[ax][ay];
                            tempList.add(copy[ax][ay]);
                        }
                        if (ay != startCol) {
                            array[ax][ay - 1] = copy[ax][ay];
                            tempList.add(copy[ax][ay]);
                        }
                    } else if (ay == lastCol) {
                        array[ax + 1][ay] = copy[ax][ay];
                        tempList.add(copy[ax][ay]);
                    } else if (ay == startCol) {
                        array[ax - 1][ay] = copy[ax][ay];
                        tempList.add(copy[ax][ay]);
                    }
                }



            }

            // copy 배열 복사
            for (int copyx = 0; copyx < rows; copyx++) {
                for (int copyy = 0; copyy < columns; copyy++) {
                    copy[copyx][copyy] = array[copyx][copyy];
                }
            }

            Collections.sort(tempList);
            answerList.add(tempList.get(0));
        }



        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}