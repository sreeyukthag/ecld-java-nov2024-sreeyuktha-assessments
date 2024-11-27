package Medium;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOfMultiples {
    public static int[] arrayMultiple(int num,int length){
        int[] arr=new int[length];
        for(int i=0;i<length;i++){
            arr[i]=num*(i+1);
        }
        return arr;
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Number : ");
        int num=s.nextInt();

        System.out.println("Enter Length of Array : ");
        int length=s.nextInt();

        int[] result = arrayMultiple(num, length);
        System.out.println(Arrays.toString(result));


    }


}
