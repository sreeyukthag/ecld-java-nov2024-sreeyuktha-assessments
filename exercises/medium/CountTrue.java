package Medium;

import java.util.stream.IntStream;

public class CountTrue {
    public static int countTrue(boolean[] arr) {
        if(arr.length==0){
            return 0;
        }
        else{
            int count=0;
            for(int i=0;i<arr.length;i++){
                if(arr[i]==true){
                    count+=1;
                }

            }
            return count;
        }
    }
    public static void main(String[]args){
        boolean[] arr={true,false,true,true,true};
        System.out.println(countTrue(arr));
    }

}

