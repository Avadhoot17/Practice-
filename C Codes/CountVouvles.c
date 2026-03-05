#include <stdio.h>
#include <ctype.h>

void analyzeString(char str[]) {
    int vowels = 0, consonants = 0, digits = 0, spaces = 0;

    for (int i = 0; str[i] != '\0'; i++) {
        char c = str[i];

        if (isdigit(c)) {
            digits++;
        }
        else if (isspace(c)) {
            spaces++;
        }
        else if (isalpha(c)) {
            c = tolower(c);

            if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
                vowels++;
            else
                consonants++;
        }
    }

    printf("\nAnalysis Result\n");
    printf("----------------------\n");
    printf("Vowels     : %d\n", vowels);
    printf("Consonants : %d\n", consonants);
    printf("Digits     : %d\n", digits);
    printf("Spaces     : %d\n", spaces);
}

int main() {
    char str[200];

    printf("Enter a string: ");
    fgets(str, sizeof(str), stdin);

    analyzeString(str);

    return 0;
}
