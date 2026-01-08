#include <stdio.h>

int main() {
    int rows;

    printf("Enter number of rows: ");
    scanf("%d", &rows);

    for (int i = 1; i <= rows; i++) {

        // Print leading spaces
        for (int space = 1; space <= rows - i; space++) {
            printf(" ");
        }

        // Print stars and hollow space
        for (int star = 1; star <= (2 * i - 1); star++) {

            // First row, last row, or boundary stars
            if (i == rows || star == 1 || star == (2 * i - 1)) {
                printf("*");
            } else {
                printf(" ");
            }
        }

        printf("\n");
    }

    return 0;
}
