import java.util.Random;
import java.util.Scanner;

public class CaptionGenerator {

    // Caption building blocks
    static String[] starters = {
        "Some moments",
        "This phase",
        "Life lately",
        "Today felt",
        "Right now",
        "These days"
    };

    static String[] happy = {
        "feels light",
        "makes me smile",
        "is full of warmth",
        "hits differently",
        "feels peaceful"
    };

    static String[] calm = {
        "moves slowly",
        "feels quiet",
        "brings peace",
        "needs no words",
        "just flows"
    };

    static String[] motivational = {
        "is teaching me growth",
        "is building discipline",
        "is shaping strength",
        "is worth the effort",
        "is pushing me forward"
    };

    static String[] endings = {
        "and I like it.",
        "without any rush.",
        "in its own way.",
        "for a reason.",
        "and it shows."
    };

    static Random random = new Random();

    // Utility method to pick random element
    static String pick(String[] array) {
        return array[random.nextInt(array.length)];
    }

    // Caption generator logic
    static String generateCaption(String mood, int length) {
        String caption = pick(starters) + " ";

        switch (mood.toLowerCase()) {
            case "happy":
                caption += pick(happy);
                break;
            case "calm":
                caption += pick(calm);
                break;
            case "motivational":
                caption += pick(motivational);
                break;
            default:
                caption += pick(calm);
        }

        if (length > 1) {
            caption += " " + pick(endings);
        }

        return caption;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Caption Generator ===");
        System.out.println("Choose mood: Happy / Calm / Motivational");
        String mood = sc.nextLine();

        System.out.println("Choose length: 1 = Short, 2 = Medium");
        int length = sc.nextInt();

        System.out.println("\nGenerated Captions:\n");

        for (int i = 1; i <= 5; i++) {
            System.out.println(i + ". " + generateCaption(mood, length));
        }

        sc.close();
    }
}
