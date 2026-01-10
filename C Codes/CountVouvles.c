#include <stdio.h>

int main() {
    char str[] = "Interview";
    int count = 0;

    for (int i = 0; str[i]; i++) {
        char c = str[i];
        if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||
            c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
            count++;
    }

    printf("Vowels: %d", count);
    return 0;
}
