#include <stdio.h>

int q1[100], q2[100];
int front1 = 0, rear1 = -1;
int front2 = 0, rear2 = -1;

void enqueue(int q[], int *rear, int x) {
    q[++(*rear)] = x;
}

int dequeue(int q[], int *front) {
    return q[(*front)++];
}

void push(int x) {
    enqueue(q2, &rear2, x);

    while (front1 <= rear1) {
        enqueue(q2, &rear2, dequeue(q1, &front1));
    }

    // swap q1 and q2
    for (int i = front2; i <= rear2; i++) {
        q1[i] = q2[i];
    }

    front1 = front2;
    rear1 = rear2;

    front2 = 0;
    rear2 = -1;
}

int pop() {
    return dequeue(q1, &front1);
}

int main() {
    push(10);
    push(20);
    push(30);

    printf("Popped: %d\n", pop());
    printf("Popped: %d\n", pop());

    return 0;
}
