#include <stdio.h>

int main() {
    int i, j;

    for (i = 0; i < 3; i++) {
        for (j = 0; j < 7; j++) {
            if ((i == 0 && (j == 1 || j == 5)) ||
                (i == 1 && (j == 0 || j == 2 || j == 4 || j == 6)) ||
                i == 2)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }

    for (i = 5; i >= 0; i--) {
        for (j = 0; j < 6 - i; j++)
            printf(" ");
        for (j = 0; j < 2 * i + 1; j++)
            printf("*");
        printf("\n");
    }

    return 0;
}
