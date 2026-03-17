#include <stdio.h>
#include <string.h>

char stack[100];
int top = -1;

void push(char x) {
    stack[++top] = x;
}

void pop() {
    if (top != -1) top--;
}

int isBalanced(char exp[]) {
    for (int i = 0; i < strlen(exp); i++) {
        if (exp[i] == '(') push(exp[i]);
        else if (exp[i] == ')') {
            if (top == -1) return 0;
            pop();
        }
    }
    return top == -1;
}

int main() {
    char exp[] = "(())()";
    if (isBalanced(exp))
        printf("Balanced\n");
    else
        printf("Not Balanced\n");
    return 0;
}
