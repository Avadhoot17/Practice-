#include <stdio.h>

int main() {
    int n = 4;
    int arr[10][10];
    int top = 0, bottom = n - 1, left = 0, right = n - 1;
    int num = 1, i;

    while (top <= bottom && left <= right) {
        for (i = left; i <= right; i++)
            arr[top][i] = num++;
        top++;

        for (i = top; i <= bottom; i++)
            arr[i][right] = num++;
        right--;

        for (i = right; i >= left; i--)
            arr[bottom][i] = num++;
        bottom--;

        for (i = bottom; i >= top; i--)
            arr[i][left] = num++;
        left++;
    }

    for (i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)
            printf("%3d ", arr[i][j]);
        printf("\n");
    }

    return 0;
}
