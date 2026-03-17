#include <stdio.h>

int stack[100], minStack[100];
int top = -1, minTop = -1;

void push(int x) {
    stack[++top] = x;

    if (minTop == -1 || x <= minStack[minTop]) {
        minStack[++minTop] = x;
    }
}

void pop() {
    if (stack[top] == minStack[minTop]) {
        minTop--;
    }
    top--;
}

int getMin() {
    return minStack[minTop];
}

int main() {
    push(5); push(2); push(8); push(1);

    printf("Minimum: %d\n", getMin());
    pop();
    printf("Minimum after pop: %d\n", getMin());

    return 0;
}
