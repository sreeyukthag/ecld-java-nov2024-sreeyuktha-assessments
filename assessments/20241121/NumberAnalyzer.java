package numberAnalyzer;
import java.util.Scanner;

public class NumberAnalyzer {
    public static void analyzeNumber(int num) {
        String sign;
        if(num>0){
            sign="Positive Number";
        } else if (num<0) {
            sign="Negative Number";
        }
        else{
            sign="Zero";
        }

        String oddEven;
        if (num==0) {
            oddEven="Not odd or even because it is zero";}
        else{
            if (num%2==0){
                oddEven="Even Number";
            }
            else{
                oddEven="Odd Number";
            }
        }


        String multipleOfFive;
        if (num==0) {
            multipleOfFive="Not a multiple of five because it is zero";
        }
        else{
            if(num%5==0){
                multipleOfFive="Multiple of 5";
            }
            else{
                multipleOfFive="Not a Multiple of 5";
            }
        }


        System.out.println("This number is "+sign);
        System.out.println("This number is "+oddEven);
        System.out.println("This number is "+multipleOfFive);


    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<5;i++){
            System.out.println("Enter Number"+i+1+" :");
            int input=scanner.nextInt();
            analyzeNumber(input);
        }
// Your code here
    }
}
