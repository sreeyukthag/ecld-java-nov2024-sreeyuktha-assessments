import hard;
import java.util.Scanner;

public class CarryDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int n1 = sc.nextInt();
        System.out.println("Enter the second number: ");
        int n2 = sc.nextInt();
        
        int carryCount = calculateCarryDigits(n1, n2);
        System.out.println("The number of carry operations: " + carryCount);
    }

    public static int calculateCarryDigits(int n1, int n2) {
        int firstDigit, secondDigit, carry = 0, carryCount = 0;
        
        while (n1 > 0 || n2 > 0 || carry > 0) {
            firstDigit = n1 % 10;
            secondDigit = n2 % 10;
            int sum = firstDigit + secondDigit + carry;
            
            if (sum >= 10) {
                carry = 1;
                carryCount++;
            } else {
                carry = 0;
            }
            
            n1 /= 10;
            n2 /= 10;
        }
        
        return carryCount;
    }
}
