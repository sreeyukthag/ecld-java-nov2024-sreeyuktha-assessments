public class GetWordCount {
	public static int countWords(String s) {
		String[] arr=s.trim().split(" ");
		int count=arr.length;
		return count;
	}
}
