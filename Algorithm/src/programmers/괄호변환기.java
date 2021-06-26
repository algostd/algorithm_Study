package programmers;

public class 괄호변환기 {
	public static void main(String[] args) {
		String str = "[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]";
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
