#include <stdio.h>

// Function to check palindrome vh
int isPalindrome(int num) {
    int temp = num, rev = 0;

    while (temp != 0) {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }

    return (rev == num);
}

int reverseNumber(int num) {
    int rev = 0;

    while (num != 0) {
        rev = rev * 10 + num % 10;
        num /= 10;
    }

    return rev;
}

int main() {
    int num, choice;

    do {
        printf("\nEnter a number: ");
        scanf("%d", &num);

        if (num < 0) {
            printf("Negative numbers are not considered palindrome.\n");
        } else {
            int reversed = reverseNumber(num);

            printf("Original Number : %d\n", num);
            printf("Reversed Number : %d\n", reversed);

            if (isPalindrome(num))
                printf("Result : It is a Palindrome\n");
            else
                printf("Result : Not a Palindrome\n");
        }

        printf("\nCheck another number? (1 = Yes, 0 = No): ");
        scanf("%d", &choice);

    } while (choice == 1);

    printf("\nProgram Ended.\n");

    return 0;
}
