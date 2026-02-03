public class MissingNumber {

    public static int findMissing(int[] arr, int n) {
        int xor = 0;

        for (int i = 0; i <= n; i++) xor ^= i;
        for (int num : arr) xor ^= num;

        return xor;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5};
        System.out.println(findMissing(arr, 5));
    }
}
