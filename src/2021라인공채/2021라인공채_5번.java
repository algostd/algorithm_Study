import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Solution {
    private static int n;
    private static List<ArrayList<Integer>> list;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        String[] nicks1 = {"imhero111", "moneyman", "hero111", "imher1111", "hro111", "mmoneyman", "moneymannnn"};
        String[] emails1 = {"superman5@abcd.com", "batman432@korea.co.kr", "superman@abcd.com", "supertman5@abcd.com", "superman@erty.net", "batman42@korea.co.kr", "batman432@usa.com"};

        String[] nicks2 = {"99police", "99poli44"};
        String[] emails2 = {"687ufq687@aaa.xx.yyy", "87ufq687@aaa.xx.yyy"};

        String[] nicks3 = {"99polico", "99policd"};
        String[] emails3 = {"687ufq687@aaa.xx.yyy", "587ufq687@aaa.xx.yyy"};

        Solution s = new Solution();
        int answer = s.solution(nicks3, emails3);

        System.out.println("answer: " + answer);
    }

    public int solution(String[] nicks, String[] emails) {
        n = nicks.length; //전체 사용자의 수

        list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<Integer>());
        }

        //동일한 유저로 판단될 경우 양방향리스트로 표시한다.

        for (int i = 0; i < n; i++) {
            String nickA = nicks[i];
            int nickALen = nickA.length();

            String emailA = emails[i];

            //@ 기점으로 파싱
            int idxA = emailA.indexOf("@");
            String accountA = emailA.substring(0, idxA);
            String serverA = emailA.substring(idxA+1, emailA.length());

            for (int j = i+1; j < n; j++) {
                //i와 j유저 비교
                boolean nick_check = false;

                //닉네임 유사성 판단
                String nickB = nicks[j];
                int nickBLen = nickB.length();

                if (nickALen == nickBLen) {
                    nick_check = deleteTwo_SameLength(nickA, nickB);
                } else if ((nickALen-1 == nickBLen) || (nickALen == nickBLen-1)) {
                    nick_check = deleteOne(nickA, nickB);
                } else if ((nickALen-2 == nickBLen) || (nickALen == nickBLen-2)) {
                    nick_check = deleteTwo(nickA, nickB);
                } else {
                    nick_check = false;
                }

                //이메일 유사성 판단
                boolean email_check = false;

                String emailB = emails[j];

                //@ 기점으로 파싱
                int idxB = emailB.indexOf("@");
                String accountB = emailB.substring(0, idxB);
                String serverB = emailB.substring(idxB+1, emailB.length());

                //1.서버이름이 다를 경우 계정이름이 같으면 ok
                if (!serverA.equals(serverB)) {
                    if (accountA.equals(accountB)) {
                        email_check = true;
                    }
                }

                //2.서버이름이 같을 경우 계정이름에서 총 1개의 문자를 삭제하여 동일하면 ok
                if (serverA.equals(serverB)) {
                    email_check = deleteOne(accountA, accountB);
                }

                //닉네임과 이메일 유사성 최종비교
                if (nick_check == true && email_check == true) {
                    list.get(i).add(j);
                    list.get(j).add(i);
                }

                //test
//				int tmp1 = i + 1;
//				int tmp2 = j + 1;
//				System.out.println("i: " + tmp1 + ", j: " + tmp2 + ", nick_check: " + nick_check + ", email_check: " + email_check);

            }
        }

        int answer = 0;
        visited = new int[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                answer++;
                dfs(i);
            }
        }

        return answer;
    }

    private void dfs(int num) {
        if (visited[num] == 1) {
            return;
        }

        visited[num] = 1;
        for (int node : list.get(num)) {
            dfs(node);
        }
    }

    //nickA, nickB

    //3. A에서 2개의 문자를 삭제하는 경우
    //4. B에서 2개의 문자를 삭제하는 경우
    //5. A에서 1개, B에서 1개의 문자를 삭제하는 경우

    //이메일
    //1.서버이름이 다를 경우 계정이름이 같으면 ok
    //2.서버이름이 같을 경우 계정이름에서 총 1개의 문자를 삭제하여 동일하면 ok

    private boolean deleteOne(String A, String B) {
        boolean check = true;
        int lenA = A.length();
        int lenB = B.length();
        if (!(lenA == lenB-1 || lenA-1 == lenB)) {
            return false;
        }

        //1. A에서 1개의 문자를 삭제하는 경우
        //2. B에서 1개의 문자를 삭제하는 경우

        //A가 더 긴 경우
        if (lenA-1 == lenB) {
            int curA = 0;
            int curB = 0;

            int differ = 0;
            while (curA < lenA && curB < lenB) {
                String A1 = A.substring(curA, curA+1);
                String B1 = B.substring(curB, curB+1);

                if (A1.equals(B1)) {
                    curA++;
                    curB++;
                } else {
                    differ++; //다른 지점의 개수 표시

                    //해당 지점에서 다를 경우
                    //이미 다른 경우가 두번 이상 나왔다면 그즉시 중지한다.
                    if (differ >= 2) {
                        check = false;
                        break;
                    } else {
                        curA++;
                    }
                }
            }
        }

        //B가 더 긴 경우
        if (lenA == lenB-1) {
            int curA = 0;
            int curB = 0;

            int differ = 0;
            while (curA < lenA && curB < lenB) {
                String A1 = A.substring(curA, curA+1);
                String B1 = B.substring(curB, curB+1);

                if (A1.equals(B1)) {
                    curA++;
                    curB++;
                } else {
                    differ++; //다른 지점의 개수 표시

                    //해당 지점에서 다를 경우
                    //이미 다른 경우가 두번 이상 나왔다면 즉시 중지한다.
                    if (differ >= 2) {
                        check = false;
                        break;
                    } else {
                        curB++;
                    }
                }
            }
        }

        return check;
    }

    private boolean deleteTwo(String A, String B) {
        boolean check = true;
        int lenA = A.length();
        int lenB = B.length();
        if (!(lenA == lenB-2 || lenA-2 == lenB)) {
            return false;
        }

        //1. A에서 2개의 문자를 삭제하는 경우
        //2. B에서 2개의 문자를 삭제하는 경우

        //A가 더 긴 경우
        if (lenA-2 == lenB) {
            int curA = 0;
            int curB = 0;

            int differ = 0;
            while (curA < lenA && curB < lenB) {
                String A1 = A.substring(curA, curA+1);
                String B1 = B.substring(curB, curB+1);

                if (A1.equals(B1)) {
                    curA++;
                    curB++;
                } else {
                    differ++; //다른 지점의 개수 표시

                    //해당 지점에서 다를 경우
                    //이미 다른 경우가 세번 이상 나왔다면 즉시 중지한다.
                    if (differ >= 3) {
                        check = false;
                        break;
                    } else {
                        curA++;
                    }
                }
            }
        }

        //B가 더 긴 경우
        if (lenA == lenB-2) {
            int curA = 0;
            int curB = 0;

            int differ = 0;
            while (curA < lenA && curB < lenB) {
                String A1 = A.substring(curA, curA+1);
                String B1 = B.substring(curB, curB+1);

                if (A1.equals(B1)) {
                    curA++;
                    curB++;
                } else {
                    differ++; //다른 지점의 개수 표시

                    //해당 지점에서 다를 경우
                    //이미 다른 경우가 세번 이상 나왔다면 즉시 중지한다.
                    if (differ >= 3) {
                        check = false;
                        break;
                    } else {
                        curB++;
                    }
                }
            }
        }

        return check;
    }

    private boolean deleteTwo_SameLength(String A, String B) {
        boolean check = true;
        int lenA = A.length();
        int lenB = B.length();
        if (lenA != lenB) {
            return false;
        }

        int curA = 0;
        int curB = 0;

        List<Integer> differList = new ArrayList<Integer>();
        int differ = 0;
        while (curA < lenA && curB < lenB) {
            String A1 = A.substring(curA, curA+1);
            String B1 = B.substring(curB, curB+1);

            if (A1.equals(B1)) {
                curA++;
                curB++;
            } else {
                differ++;
                differList.add(curA);

                //만약 다른 경우가 세번이상 나왔다면 그즉시 중지한다.
                if (differ >= 3) {
                    check = false;
                    break;
                } else {
                    curA++;
                    curB++;
                }
            }
        }

        //만약 다른 경우가 두 지점이라면
        //99polie4
        //99poli44

        if (differ == 2) {
            boolean tmp_check = false;

            StringBuilder sbA = new StringBuilder(A);
            StringBuilder sbB = new StringBuilder(B);

            //앞 지점에서 A를 삭제, 뒷 지점에서 B를 삭제
            sbA.deleteCharAt(differList.get(0));
            sbB.deleteCharAt(differList.get(1));

            if (sbA.toString().equals(sbB.toString())) {
                tmp_check = true;
            }

            sbA = new StringBuilder(A);
            sbB = new StringBuilder(B);

            //앞 지점에서 B를 삭제, 뒷 지점에서 A를 삭제
            sbA.deleteCharAt(differList.get(1));
            sbB.deleteCharAt(differList.get(0));

            if (sbA.toString().equals(sbB.toString())) {
                tmp_check = true;
            }

            check = tmp_check;
        }

        return check;
    }
}
