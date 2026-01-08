#include <stdio.h>

// Function to check even or odd
void checkEvenOdd(int num) {
    if (num % 2 == 0) {
        printf("%d is Even\n", num);
    } else {
        printf("%d is Odd\n", num);
    }
}

int main() {

    int n;

    // Taking input from user
    printf("Enter a number: ");
    scanf("%d", &n);

    // Calling function
    checkEvenOdd(n);

    // Print numbers from 1 to n
    printf("\nNumbers from 1 to %d:\n", n);
    for (int i = 1; i <= n; i++) {
        printf("%d ", i);
    }

    printf("\n");

    return 0;
}
