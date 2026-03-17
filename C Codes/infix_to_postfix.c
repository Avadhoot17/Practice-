#include <stdio.h>
#include <ctype.h>

char stack[100];
int top = -1;

void push(char x) {
    stack[++top] = x;
}

char pop() {
    return stack[top--];
}

int precedence(char x) {
    if (x == '+' || x == '-') return 1;
    if (x == '*' || x == '/') return 2;
    return 0;
}

int main() {
    char exp[] = "a+b*c";
    char result[100];
    int k = 0;

    for (int i = 0; exp[i]; i++) {
        if (isalnum(exp[i])) {
            result[k++] = exp[i];
        } else {
            while (top != -1 && precedence(stack[top]) >= precedence(exp[i])) {
                result[k++] = pop();
            }
            push(exp[i]);
        }
    }

    while (top != -1) {
        result[k++] = pop();
    }

    result[k] = '\0';
    printf("Postfix: %s\n", result);
    return 0;
}
