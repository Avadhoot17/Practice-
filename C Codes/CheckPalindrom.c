#include <stdio.h>

int main() {
    int n = 121, temp = n, rev = 0;

    while (n > 0) {
        rev = rev * 10 + n % 10;
        n /= 10;
    }

    if (temp == rev)
        printf("Palindrome");
    else
        printf("Not Palindrome");

    return 0;
}
