#include <stdio.h>

int main() {
    int i, j, n = 3;

    for (i = 0; i <= n; i++) {
        for (j = 0; j < n - i; j++)
            printf(" ");
        for (j = 0; j <= 2 * i; j++) {
            if (j == 0 || j == 2 * i)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }

    for (i = n - 1; i >= 0; i--) {
        for (j = 0; j < n - i; j++)
            printf(" ");
        for (j = 0; j <= 2 * i; j++) {
            if (j == 0 || j == 2 * i)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }

    return 0;
}
