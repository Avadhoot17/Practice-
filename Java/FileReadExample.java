import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class FileReadExample {

    public static void main(String[] args) {

        String fileName = "data.txt";
        File file = new File(fileName);

        // Check if file exists
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        // try-with-resources automatically closes the reader
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            System.out.println("----- File Content -----\n");

            while ((line = br.readLine()) != null) {

                lineCount++;

                System.out.println(lineCount + ": " + line);

                charCount += line.length();

                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            System.out.println("\n----- File Statistics -----");
            System.out.println("Total Lines       : " + lineCount);
            System.out.println("Total Words       : " + wordCount);
            System.out.println("Total Characters  : " + charCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
