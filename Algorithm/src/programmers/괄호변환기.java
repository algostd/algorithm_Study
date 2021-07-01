package programmers;

public class 괄호변환기 {
	public static void main(String[] args) {
		String str = "[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]";
		String newStr = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '[') {
				newStr += "{";
			} else if (str.charAt(i) == ']') {
				newStr += "}";
			} else {
				newStr += str.charAt(i);
			}
		}
		System.out.println(newStr);
	}
}
