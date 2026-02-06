import java.util.*;


public class MoveZeroes {
public static void moveZeroes(int[] nums) {
int index = 0;


for (int n : nums) {
if (n != 0) nums[index++] = n;
}


while (index < nums.length) nums[index++] = 0;
}


public static void main(String[] args) {
int[] arr = {0, 1, 0, 3, 12};
moveZeroes(arr);
System.out.println(Arrays.toString(arr));
}
}
