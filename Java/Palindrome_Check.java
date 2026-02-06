public class PalindromeCheck {
public static boolean isPalindrome(String s) {
s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();


int left = 0, right = s.length() - 1;


while (left < right) {
if (s.charAt(left++) != s.charAt(right--)) return false;
}


return true;
}


public static void main(String[] args) {
System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
}
}
