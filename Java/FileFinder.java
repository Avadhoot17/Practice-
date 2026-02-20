import java.io.File;
import java.util.Scanner;

public class FileFinder {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file name to search (example: test.txt): ");
        String fileName = scanner.nextLine();

        // Change this path if needed
        String startPath = System.getProperty("user.home"); 
        File rootDirectory = new File(startPath);

        System.out.println("\nSearching in: " + startPath);
        System.out.println("Please wait...\n");

        searchFile(rootDirectory, fileName);

        scanner.close();
    }

    public static void searchFile(File directory, String fileName) {

        if (directory == null || !directory.exists()) {
            return;
        }

        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {

            if (file.isDirectory()) {
                searchFile(file, fileName); // Recursive call
            } 
            else {
                if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File Found: " + file.getAbsolutePath());
                }
            }
        }
    }
}
