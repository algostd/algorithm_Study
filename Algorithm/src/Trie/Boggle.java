package Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;

// https://www.acmicpc.net/problem/9202 (플레5) (TRIE)
public class Boggle {
    private static Node rootNode;
    private static boolean[][] visited;
    private static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    private static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    private static int sum = 0;
    private static String answerWord = "";
    private static int wordCount = 0;
    private static final int MATRIX_SIZE = 4;

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static StringBuilder sb = new StringBuilder();
    static StringBuilder answerSb = new StringBuilder();

    static char[][] map;
    static int N;

    static class Node {
        boolean isEnd = false;
        boolean isHit = false;
        Node[] children = new Node[26];

        boolean hasChild(char c) {
            return children[c - 'A'] != null;
        }

        Node getChild(char c) {
            return children[c - 'A'];
        }

        void clearHit() {
            isHit = false;
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    children[i].clearHit();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        getInfo();

        // 문제풀이
        br.readLine(); // 한줄 띄고
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            createMap();
            findAnswer();
            br.readLine();
        }
        System.out.println(answerSb);

    }

    private static void findAnswer() {
        for (int row = 0; row < MATRIX_SIZE; row++) {
            for (int col = 0; col < MATRIX_SIZE; col++) {
                if (rootNode.hasChild(map[row][col])) {
                    visited = new boolean[4][4];
                    sb = new StringBuilder();
                    search(row, col, rootNode.getChild(map[row][col]));
                }
            }
        }
        answerPrintAndInit();
    }

    private static void answerPrintAndInit() {
        answerSb.append(sum)
                .append(" ")
                .append(answerWord)
                .append(" ")
                .append(wordCount)
                .append("\n");
        answerWord = "";
        wordCount = 0;
        sum = 0;
        rootNode.clearHit();
    }

    private static void search(int row, int col, Node node) {
        // 체크인
        visited[row][col] = true;
        sb.append(map[row][col]);

        // 노드가 만난적 없는 단어를 만남 ! 정답체크
        if (node.isEnd && !node.isHit) {
            node.isHit = true;
            sum += score[sb.length()];
            if (compare(answerWord, sb.toString()) > 0) {
                answerWord = sb.toString();
            }
            wordCount++;
        }

        // 순회
        for (int i = 0; i < 8; i++) {
            int tr = row + dr[i];
            int tc = col + dc[i];
            if (tr >= 0 && tc >= 0 && tr < MATRIX_SIZE && tc < MATRIX_SIZE
                    && !visited[tr][tc] && node.hasChild(map[tr][tc])) {
                search(tr, tc, node.getChild(map[tr][tc]));
            }
        }

        // 체크아웃
        visited[row][col] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private static int compare(String answerWord, String candidate) {
        if (candidate.length() == answerWord.length()) {
            return answerWord.compareTo(candidate);
        }
        return Integer.compare(candidate.length(), answerWord.length());
    }

    private static void createMap() throws IOException {
        map = new char[MATRIX_SIZE][MATRIX_SIZE];
        for (int row = 0; row < MATRIX_SIZE; row++) {
            String str = br.readLine();
            for (int col = 0; col < MATRIX_SIZE; col++) {
                map[row][col] = str.charAt(col);
            }
        }

    }

    private static void getInfo() throws IOException {
        N = parseInt(br.readLine());
        String str;
        rootNode = new Node();
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            // TRIE 만들기
            putWordInTrie(str);
        }
    }

    private static void putWordInTrie(String str) {
        Node node = rootNode;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Node nextNode;
            if (node.hasChild(c)) {
                nextNode = node.getChild(c);
            } else {
                nextNode = new Node();
                node.children[c - 'A'] = nextNode;
            }

            // 단어의 끝은 End 처리
            if (i == str.length() - 1) {
                nextNode.isEnd = true;
            }

            node = nextNode;
        }
    }

}