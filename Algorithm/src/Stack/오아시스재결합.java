package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 오아시스재결합 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long answer = 0;

    static class Person{
        int height;
        int count;

        public Person(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Stack<Person> monotoneStack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int nowHeight = Integer.parseInt(br.readLine());
            Person person = new Person(nowHeight, 1);

            // pop 하면서 새기
            while (!monotoneStack.isEmpty() && monotoneStack.peek().height <= nowHeight) {
                Person popped = monotoneStack.pop();
                if (popped.height == nowHeight) {
                    person.count += popped.count;
                }
                answer += popped.count;
            }

            // 비어있지않으면 개수만큼 새기
            if (!monotoneStack.empty()) {
                answer++;
            }
            monotoneStack.push(person);
        }
        System.out.println(answer);
    }
}
