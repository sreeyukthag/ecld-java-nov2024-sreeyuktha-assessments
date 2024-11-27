package Medium;

import java.util.Scanner;

public class WarOfNumbers {
        public static int warOfNumbers(int[]numbers){
            int oddCount=0;
            int evenCount=0;
            for(int num:numbers){
                if(num%2!=0){
                    oddCount=oddCount+num;

                }
                else{
                    evenCount=evenCount+num;

                }
            }
            if(oddCount>=evenCount){
                return oddCount-evenCount;
            }
            else{
                return evenCount-oddCount;
            }


        }
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter length of Array :");
            int n=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                System.out.println("Enter Element "+i+1+" :");
                arr[i]=sc.nextInt();
            }

            System.out.println("The final answer is : "+warOfNumbers(arr));


        }
    }

