import java.util.Arrays;

public class AdvancedCycleDetector {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 2, 2};

        System.out.println("Array: " + Arrays.toString(arr));

        Result result = detectCycle(arr);

        if (result.hasCycle) {
            System.out.println("Cycle Detected ✅");
            System.out.println("Cycle Start Index: " + result.startIndex);
            System.out.println("Cycle Length: " + result.cycleLength);
        } else {
            System.out.println("No Cycle ❌");
        }
    }

    static class Result {
        boolean hasCycle;
        int startIndex;
        int cycleLength;

        Result(boolean hasCycle, int startIndex, int cycleLength) {
            this.hasCycle = hasCycle;
            this.startIndex = startIndex;
            this.cycleLength = cycleLength;
        }
    }

    public static Result detectCycle(int[] arr) {

        int slow = 0;
        int fast = 0;

        // Step 1: Detect cycle safely
        while (true) {

            if (!isValid(arr, slow) || !isValid(arr, fast) 
                    || !isValid(arr, arr[fast])) {
                return new Result(false, -1, 0);
            }

            slow = arr[slow];
            fast = arr[arr[fast]];

            if (slow == fast)
                break;
        }

        // Step 2: Find cycle start
        slow = 0;
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }

        int startIndex = slow;

        // Step 3: Find cycle length
        int cycleLength = 1;
        fast = arr[slow];
        while (slow != fast) {
            fast = arr[fast];
            cycleLength++;
        }

        return new Result(true, startIndex, cycleLength);
    }

    private static boolean isValid(int[] arr, int index) {
        return index >= 0 && index < arr.length;
    }
}
