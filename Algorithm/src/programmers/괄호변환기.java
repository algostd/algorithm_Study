package programmers;

public class °ýÈ£º¯È¯±â {
	public static void main(String[] args) {
		String str = "[[\"100\",\"ryan\",\"music\",\"2\"],[\"200\",\"apeach\",\"math\",\"2\"],[\"300\",\"tube\",\"computer\",\"3\"],[\"400\",\"con\",\"computer\",\"4\"],[\"500\",\"muzi\",\"music\",\"3\"],[\"600\",\"apeach\",\"music\",\"2\"]]";
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
