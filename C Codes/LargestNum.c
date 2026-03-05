#include <stdio.h>

int findMax(int arr[], int n) {
    int max = arr[0];

    for (int i = 1; i < n; i++) {
        if (arr[i] > max)
            max = arr[i];
    }

    return max;
}

int findMin(int arr[], int n) {
    int min = arr[0];

    for (int i = 1; i < n; i++) {
        if (arr[i] < min)
            min = arr[i];
    }

    return min;
}

int sumArray(int arr[], int n) {
    int sum = 0;

    for (int i = 0; i < n; i++)
        sum += arr[i];

    return sum;
}

int main() {

    int n;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    int arr[n];

    printf("Enter %d elements:\n", n);

    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    int max = findMax(arr, n);
    int min = findMin(arr, n);
    int sum = sumArray(arr, n);
    float avg = (float)sum / n;

    printf("\nArray Analysis\n");
    printf("-----------------\n");
    printf("Maximum : %d\n", max);
    printf("Minimum : %d\n", min);
    printf("Sum     : %d\n", sum);
    printf("Average : %.2f\n", avg);

    return 0;
}
