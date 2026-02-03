public class CycleInArray {

    public static boolean hasCycle(int[] arr) {
        int slow = 0, fast = 0;

        while (true) {
            slow = arr[slow];
            fast = arr[arr[fast]];

            if (slow == fast) return true;
            if (slow >= arr.length || fast >= arr.length) return false;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 2};
        System.out.println(hasCycle(arr));
    }
}
