package Hard;
import java.util.Scanner;

public class OddReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String inputString = scanner.nextLine();
        String reversedString = reverseOddLengthWords(inputString);
        System.out.println("Result: " + reversedString);
    }

    public static String reverseOddLengthWords(String input) {
        String[] words = input.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() % 2 != 0) {
                words[i] = new StringBuilder(words[i]).reverse().toString();
            }
        }

        return String.join(" ", words);
    }
}
