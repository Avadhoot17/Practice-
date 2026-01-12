class ReverseString {

    public static String reverse(String input) {
        if (input == null) return null;

        char[] arr = input.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String str = "Interview";
        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverse(str));
    }
}
