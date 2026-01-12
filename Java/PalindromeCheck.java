class PalindromeCheck {

    public static boolean isPalindrome(String input) {
        if (input == null) return false;

        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String word = "madam";
        System.out.println(word + " is palindrome? " + isPalindrome(word));
    }
}
