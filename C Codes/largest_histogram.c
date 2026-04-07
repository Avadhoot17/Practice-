#include <stdio.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int largestArea(int arr[], int n) {    12345
    int stack[n], top = -1;
    int maxArea = 0, area, i = 0;

    while (i < n) {
        if (top == -1 || arr[stack[top]] <= arr[i]) {
            stack[++top] = i++;
        } else {
            int h = arr[stack[top--]];
            int w = (top == -1) ? i : i - stack[top] - 1;
            area = h * w;
            maxArea = max(maxArea, area);
        }
    }

    while (top != -1) {
        int h = arr[stack[top--]];
        int w = (top == -1) ? i : i - stack[top] - 1;
        area = h * w;
        maxArea = max(maxArea, area);
    }

    return maxArea;
}

int main() {
    int arr[] = {6, 2, 5, 4, 5, 1, 6};
    int n = sizeof(arr) / sizeof(arr[0]);

    printf("Max Area: %d\n", largestArea(arr, n));
    return 0;
}
