import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String[] records1 = {
                "2020-02-02 uid1 pid1",   "2020-02-26 uid1 pid1",
                "2020-02-26 uid2 pid1",   "2020-02-27 uid3 pid2",
                "2020-02-28 uid4 pid2",   "2020-02-29 uid3 pid3",
                "2020-03-01 uid4 pid3",   "2020-03-03 uid1 pid1",
                "2020-03-04 uid2 pid1",   "2020-03-05 uid3 pid2",
                "2020-03-05 uid3 pid3",   "2020-03-05 uid3 pid3",
                "2020-03-06 uid1 pid4"
        };
        int k1 = 10;
        String date1= "2020-03-05";

        String[] records2 = {"2020-02-02 uid141 pid141", "2020-02-03 uid141 pid32", "2020-02-04 uid32 pid32", "2020-02-05 uid32 pid141"};
        int k2 = 10;
        String date2= "2020-02-05";

        String[] records3 = {"2020-01-01 uid1000 pid5000"};
        int k3 =10;
        String date3= "2020-01-11";

        Solution s = new Solution();
        String[] answer = s.solution(records2, k2, date2);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public String[] solution(String[] records, int k, String date) {
        String[] answer = {};

        //총구매횟수
        //재구매율 => 기간내 상품을 재구매한 고객수, 기간내 상품을 한번 이상 구매한 고객수
        //상품아이디 : Product 객체
        Map<Integer, Product> pMap = new HashMap<Integer, Product>();

        //기준 기간을 구한다.
        int endDay = changeDateToDay(date); //일수로 변환한다.
        int startDay = endDay - k + 1;

        //records형식 "2020-02-02 uid1004 pid10004"
        //기준기간: startDate ~ endDate
        for (String record : records) {
            String[] input = record.split(" ");
            int buyDay = changeDateToDay(input[0]);
            int uidNum = Integer.parseInt(input[1].substring(3));
            int pidNum = Integer.parseInt(input[2].substring(3));

            //상품이 처음 구매된 것이라면
            if (!pMap.containsKey(pidNum)) {
                pMap.put(pidNum, new Product(pidNum));
            }

            //구매날짜가 범위 내인 경우
            if (buyDay >= startDay && buyDay <= endDay) {
                pMap.get(pidNum).reBuy(uidNum);
            }
            pMap.get(pidNum).totalPlus(); //총구매횟수 +1;
        }


        //우선순위큐를 만들어 조건에 맞춰 정렬한다.
        PriorityQueue<Product> qu = new PriorityQueue<Product>();
        for (int num : pMap.keySet()) {
            qu.add(pMap.get(num));
        }

        //빈 배열일 경우
        if (qu.peek().getUserCnt() == 0) {
            answer = new String[1];
            answer[0] = "no result";
            return answer;
        }

        //빈 배열이 아닐 경우
        List<String> tmpList = new ArrayList<String>();
        while(!qu.isEmpty()) {
            Product p = qu.poll();
            if (p.getUserCnt() != 0) {
                tmpList.add("pid" + p.getPidNum());
            }
        }

        answer = new String[tmpList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = tmpList.get(i);
        }

        return answer;
    }

    private int changeDateToDay(String date) {
        String[] date_arr = date.split("-");
        String date_str = date_arr[0] + date_arr[1] + date_arr[2];
        int mmdd = Integer.parseInt(date_str) - 20200000; //mmdd
        int result = mmdd/100*30 + mmdd%100; //일수로 변환한다.
        return result;
    }

}

class Product implements Comparable<Product>{
    private int pidNum;
    private int total;
    private int reUserCnt;
    private int userCnt;
    private Map<Integer, Integer> userMap;
    //재구매율 reUserCnt / userCnt

    Product(int pidNum) {
        this.pidNum = pidNum;
        this.total = 0;
        this.reUserCnt = 0;
        this.userCnt = 0;
        userMap = new HashMap<Integer, Integer>(); // uid : 구매횟수
    }

    public void totalPlus() {
        this.total++;
    }

    //기간내에 구매했을 경우
    public void reBuy(int uidNum) {
        //uidNum 유저가 해당 상품을 구매한다.
        //해당 유저가 첫 구매일 경우
        if (!userMap.containsKey(uidNum)) {
            userMap.put(uidNum, 1);
            this.userCnt++;
        } else if (userMap.get(uidNum) == 1) {
            //해당 유저가 두번째 구매일 경우
            userMap.put(uidNum, 2);
            this.reUserCnt++;
        } else if (userMap.get(uidNum) > 1) {
            //해당 유저가 세번째 이상 구매일 경우
            userMap.put(uidNum, userMap.get(uidNum) + 1);
        }
    }

    public int compareTo(Product p) {
        double reBuyRate1 = 0;
        double reBuyRate2 = 0;
        if (this.userCnt != 0) {
            reBuyRate1 = (double) this.reUserCnt / this.userCnt;
        }
        if (p.getUserCnt() != 0) {
            reBuyRate2 = (double) p.getReUserCnt() / p.getUserCnt();
        }

        if (reBuyRate1 == reBuyRate2) {
            if (this.total == p.getTotal()) {
                return this.pidNum - p.getPidNum();
            }
            return p.getTotal() - this.total;
        }

        if (reBuyRate1 < reBuyRate2) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getPidNum() {
        return pidNum;
    }

    public int getTotal() {
        return total;
    }

    public int getReUserCnt() {
        return reUserCnt;
    }

    public int getUserCnt() {
        return userCnt;
    }
}
