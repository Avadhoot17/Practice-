#include <stdio.h>

void printHeart() {         12345678
    int i, j;

    // Upper part of heart
    for (i = 0; i <= 3; i++) {

        for (j = 0; j <= 6; j++) {

            if ((i == 0 && (j == 1 || j == 2 || j == 4 || j == 5)) ||
                (i == 1 && (j == 0 || j == 3 || j == 6)) ||
                (i > 1))
                printf("* ");
            else
                printf("  ");
        }
        printf("\n");
    }

    // Lower inverted pyramid part
    for (i = 3; i >= 0; i--) {

        for (j = 0; j < 3 - i; j++)
            printf("  ");

        for (j = 0; j < 2 * i + 1; j++)
            printf("* ");

        printf("\n");
    }
}

int main() {

    printf("\nHeart Star Pattern\n\n");

    printHeart();

    return 0;
}
