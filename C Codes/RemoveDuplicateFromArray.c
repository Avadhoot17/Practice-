#include <stdio.h>

// Function to remove duplicates
int removeDuplicates(int arr[], int n) {
    int removed = 0;

    for (int i = 0; i < n; i++) {

        for (int j = i + 1; j < n; j++) {

            if (arr[i] == arr[j]) {

                for (int k = j; k < n - 1; k++)
                    arr[k] = arr[k + 1];

                n--;
                j--;
                removed++;
            }
        }
    }

    printf("\nArray after removing duplicates:\n");

    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);

    printf("\nDuplicates removed: %d\n", removed);

    return n;
}

int main() {

    int n;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    int arr[n];

    printf("Enter %d elements:\n", n);

    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    printf("\nOriginal Array:\n");

    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);

    n = removeDuplicates(arr, n);

    return 0;
}
