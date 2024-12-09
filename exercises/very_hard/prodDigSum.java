package VeryHard;
import java.util.Scanner;

public class prodDigSum {

        public static int sumDigProd(int totalSum) {
            while (totalSum >= 10) {
                int product = 1;
                while (totalSum > 0) {
                    product *= totalSum % 10;
                    totalSum /= 10;
                }
                totalSum = product;
            }
            return totalSum;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of integers you want to input: ");
            int count = scanner.nextInt();
            int totalSum = 0;
            System.out.println("Enter the integers:");
            for (int i = 0; i < count; i++) {
                totalSum += scanner.nextInt();
            }
            int result = sumDigProd(totalSum);
            System.out.println("The final product is: " + result);
            scanner.close();
        }
    }


