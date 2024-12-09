package Hard;
import java.util.Arrays;
import java.util.Scanner;

public class Streches {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = sc.nextInt();
        int[] numbers = new int[size];
        
        System.out.println("Now, enter the values for the array: ");
        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
        }
        
        int gap = findLargestGap(numbers);
        System.out.println("The largest gap between the numbers is: " + gap);
    }

    public static int findLargestGap(int[] numbers) {
        Arrays.sort(numbers);
        int maxGap = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            int currentGap = numbers[i + 1] - numbers[i];
            if (currentGap > maxGap) {
                maxGap = currentGap;
            }
        }

        return maxGap;
    }
}
