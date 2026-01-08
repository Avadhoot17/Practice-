#include <stdio.h>

// Structure definition
struct Student {
    int id;
    float marks;
};

void displayStudent(struct Student *s) {
    // Access structure members using pointer
    printf("\nStudent Details:\n");
    printf("ID    : %d\n", s->id);
    printf("Marks : %.2f\n", s->marks);
}

int main() {

    struct Student s1;          // Structure variable
    struct Student *ptr;        // Pointer to structure

    // Assign pointer to structure address
    ptr = &s1;

    // Taking input using pointer
    printf("Enter Student ID: ");
    scanf("%d", &ptr->id);

    printf("Enter Marks: ");
    scanf("%f", &ptr->marks);

    // Function call (passing pointer)
    displayStudent(ptr);

    return 0;
}
