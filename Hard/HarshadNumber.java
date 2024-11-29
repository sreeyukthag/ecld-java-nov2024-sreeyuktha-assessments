package Hard;

import java.util.Scanner;

public class HarshadNumber {
        public static int calcDigitSum(int n) {
            if (n == 0) {
                return 0;
            }
            return calcDigitSum(n / 10) + n % 10;
        }

        public static boolean isHarshad(int n) {
            if (n == 0) {
                return false;
            }
            int digitSum = calcDigitSum(n);
            return n % digitSum == 0;
        }

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter a number : ");
            int a =sc.nextInt();
            System.out.println(isHarshad(a));
        }
    }

