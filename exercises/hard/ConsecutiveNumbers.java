package Hard;
import java.util.Arrays;
import java.util.Scanner;

public class ConsecutiveNumbers {
        public static boolean cons(int[] arr) {
            Arrays.sort(arr);
            for (int i=0;i<arr.length-1;i++){
                if (arr[i]+1!=arr[i+1]) return false;
            }
            return true;
        }
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter Length of Number : ");
            int a=sc.nextInt();

            int[] arr=new int[a];

            for(int i=0;i<a;i++){
                System.out.println("Enter "+(i+1)+" element : ");
                arr[i]= sc.nextInt();;
            }

            System.out.println(cons(arr));
        }
    }

