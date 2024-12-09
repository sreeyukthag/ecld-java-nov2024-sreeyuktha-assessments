
import java.util.Scanner;


public class Main {
    public static int solutions(int a, int b, int c) {
        int D=b*b-4*a*c;
        if(D>0)
            return 2;
        if(D==0)
            return 1;
        else
            return 0;
    }


    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);

        System.out.println("Enter A: ");
        int a=s.nextInt();

        System.out.println("Enter B: ");
        int b=s.nextInt();

        System.out.println("Enter C: ");
        int c=s.nextInt();

       System.out.println("Number of solutions of this quadratic equation is : "+solutions(a,b,c));
    }
    }
