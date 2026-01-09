#include <stdio.h>

int main() {
    int n = 5, i, j, coef;

    for (i = 0; i < n; i++) {
        for (j = 1; j <= n - i; j++)
            printf(" ");

        coef = 1;
        for (j = 0; j <= i; j++) {
            printf("%d ", coef);
            coef = coef * (i - j) / (j + 1);
        }
        printf("\n");
    }
    return 0;
}
