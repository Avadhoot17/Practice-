#include <stdio.h>

// Function to print diamond
void printDiamond(int n, int type) {
    int i, j;

    // Upper part
    for (i = 0; i <= n; i++) {

        for (j = 0; j < n - i; j++)
            printf(" ");

        for (j = 0; j <= 2 * i; j++) {

            if (type == 1) // Hollow
            {
                if (j == 0 || j == 2 * i)
                    printf("*");
                else
                    printf(" ");
            }
            else // Solid
                printf("*");
        }

        printf("\n");
    }

    // Lower part
    for (i = n - 1; i >= 0; i--) {

        for (j = 0; j < n - i; j++)
            printf(" ");

        for (j = 0; j <= 2 * i; j++) {

            if (type == 1)
            {
                if (j == 0 || j == 2 * i)
                    printf("*");
                else
                    printf(" ");
            }
            else
                printf("*");
        }

        printf("\n");
    }
}

int main() {

    int size, choice;

    printf("Diamond Pattern Generator\n");
    printf("-------------------------\n");

    printf("Enter diamond size: ");
    scanf("%d", &size);

    printf("\nChoose type\n");
    printf("1. Hollow Diamond\n");
    printf("2. Solid Diamond\n");
    printf("Enter choice: ");
    scanf("%d", &choice);

    printDiamond(size, choice);

    return 0;
}
