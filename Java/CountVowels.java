import java.util.*;

class VowelConsonantCount {

    public static void countCharacters(String input) {

        if (input == null || input.isEmpty()) {
            System.out.println("Invalid input");
            return;
        }

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int spaces = 0;
        int special = 0;

        Map<Character, Integer> vowelFreq = new HashMap<>();

        vowelFreq.put('a',0);
        vowelFreq.put('e',0);
        vowelFreq.put('i',0);
        vowelFreq.put('o',0);
        vowelFreq.put('u',0);

        for(char ch : input.toCharArray()) {

            if(Character.isLetter(ch)) {

                char lower = Character.toLowerCase(ch);

                if(vowelFreq.containsKey(lower)) {
                    vowels++;
                    vowelFreq.put(lower, vowelFreq.get(lower) + 1);
                }
                else {
                    consonants++;
                }
            }

            else if(Character.isDigit(ch)) {
                digits++;
            }

            else if(Character.isWhitespace(ch)) {
                spaces++;
            }

            else {
                special++;
            }
        }

        printResults(input, vowels, consonants, digits, spaces, special, vowelFreq);
    }


    public static void printResults(String text, int v, int c, int d, int sp, int sc, Map<Character,Integer> vf) {

        System.out.println("Input Text: " + text);
        System.out.println("\n---- Character Statistics ----");

        System.out.println("Vowels      : " + v);
        System.out.println("Consonants  : " + c);
        System.out.println("Digits      : " + d);
        System.out.println("Spaces      : " + sp);
        System.out.println("Special Char: " + sc);

        System.out.println("\nVowel Frequency:");
        for(char ch : vf.keySet()) {
            System.out.println(ch + " : " + vf.get(ch));
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        countCharacters(text);

        sc.close();
    }
}
