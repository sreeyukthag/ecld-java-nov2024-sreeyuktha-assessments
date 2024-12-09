package Hard;

import java.util.Scanner;

public class PronicNumber {
        public static boolean isHeteromecic(int n) {
            for(int i=1;i*(i+1)<=n;i++){
                if(i*(i+1)==n) {
                    return true;
                }
            }
            return false;
        }
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter number : ");
            int a=sc.nextInt();
            System.out.println("Entered number is "+isHeteromecic(a));
        }
    }


