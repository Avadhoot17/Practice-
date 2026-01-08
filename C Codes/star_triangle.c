#include <stdio.h>

int main() {
    int rows;

    printf("Enter number of rows: ");
    scanf("%d", &rows);

    for (int i = 1; i <= rows; i++) {

        // Print spaces
        for (int space = 1; space <= rows - i; space++) {
            printf(" ");
        }

        // Print stars
        for (int star = 1; star <= i; star++) {
            printf("* ");
        }

        printf("\n");
    }

    return 0;
}
