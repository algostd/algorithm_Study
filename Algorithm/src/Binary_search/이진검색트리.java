package Binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5639 (실버1)
public class 이진검색트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    private static Node rootNode;

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        rootNode = new Node(Integer.parseInt(br.readLine()));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null || str.equals("") || str.length() <= 0) {
                break;
            }
            insert(Integer.parseInt(str));
        }
        // 후위 순위
        postTravel(rootNode);
        System.out.println(sb);
    }

    private static void postTravel(Node node) {
        if (node == null) {
            return;
        }
        postTravel(node.left);
        postTravel(node.right);
        sb.append(node.key).append("\n");
    }

    static public void insert(int key) {
        Node newNode = new Node(key);
        Node node = rootNode;
        while (true) {
            if (node.key < key && node.right == null) {
                node.setRight(newNode);
                break;
            } else if (node.key < key && node.right != null) {
                node = node.right;
            } else if (node.key > key && node.left == null) {
                node.setLeft(newNode);
                break;
            } else if (node.key > key && node.left != null) {
                node = node.left;
            }
        }
    }
}
