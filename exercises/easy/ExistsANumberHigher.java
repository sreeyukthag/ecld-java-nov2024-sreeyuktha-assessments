public class ExistsANumberHigher {
    public static boolean existsHigher(int[] arr, int n) {
        if (arr.length == 0) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= n) {
                return true;
            }
        }

        return false;
    }
}
