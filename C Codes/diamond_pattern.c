#include <stdio.h>

int main() {
    int rows;

    printf("Enter number of rows: ");
    scanf("%d", &rows);

    /* Upper half of diamond */
    for (int i = 1; i <= rows; i++) {

        // Print spaces
        for (int space = 1; space <= rows - i; space++) {
            printf(" ");
        }

        // Print stars
        for (int star = 1; star <= (2 * i - 1); star++) {
            printf("*");
        }

        printf("\n");
    }

    /* Lower half of diamond */
    for (int i = rows - 1; i >= 1; i--) {

        // Print spaces
        for (int space = 1; space <= rows - i; space++) {
            printf(" ");
        }

        // Print stars
        for (int star = 1; star <= (2 * i - 1); star++) {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
