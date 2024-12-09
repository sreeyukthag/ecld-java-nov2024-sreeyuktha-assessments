package Hard;

import java.util.Scanner;

public class LcmOfTwo {

    public static int gcd(int n1, int n2) {
        while (n2 != 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1;
    }

    public static int lcm(int n1, int n2) {
        return (n1 * n2) / gcd(n1, n2);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter First Number : ");
        int n1 = s.nextInt();

        System.out.println("Enter Second Number : ");
        int n2 = s.nextInt();

        System.out.println("LCM of " + n1 + " and " + n2 + " is " + lcm(n1, n2));
    }
}
