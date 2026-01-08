#include <stdio.h>

int main() {
    int rows;

    printf("Enter number of rows: ");
    scanf("%d", &rows);

    /* Upper part of butterfly */
    for (int i = 1; i <= rows; i++) {

        // Left stars
        for (int j = 1; j <= i; j++) {
            printf("*");
        }

        // Spaces in between
        for (int space = 1; space <= 2 * (rows - i); space++) {
            printf(" ");
        }

        // Right stars
        for (int j = 1; j <= i; j++) {
            printf("*");
        }

        printf("\n");
    }

    /* Lower part of butterfly */
    for (int i = rows; i >= 1; i--) {

        // Left stars
        for (int j = 1; j <= i; j++) {
            printf("*");
        }

        // Spaces in between
        for (int space = 1; space <= 2 * (rows - i); space++) {
            printf(" ");
        }

        // Right stars
        for (int j = 1; j <= i; j++) {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
