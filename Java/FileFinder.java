import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileFinder {

    static int filesScanned = 0;
    static int filesFound = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Advanced File Finder ===");

        System.out.print("Enter file name or keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        String startPath = System.getProperty("user.home");

        System.out.println("\nSearching inside: " + startPath);
        System.out.println("Searching...\n");

        long startTime = System.currentTimeMillis();

        searchFile(new File(startPath), keyword);

        long endTime = System.currentTimeMillis();

        System.out.println("\n------ SEARCH COMPLETE ------");
        System.out.println("Files scanned : " + filesScanned);
        System.out.println("Files found   : " + filesFound);
        System.out.println("Time taken    : " + (endTime - startTime) + " ms");

        scanner.close();
    }

    public static void searchFile(File directory, String keyword) {

        if (directory == null || !directory.exists())
            return;

        File[] files = directory.listFiles();

        if (files == null)
            return;

        for (File file : files) {

            if (file.isDirectory()) {
                searchFile(file, keyword);
            }

            else {

                filesScanned++;

                if (file.getName().toLowerCase().contains(keyword)) {

                    filesFound++;

                    printFileDetails(file);
                }
            }
        }
    }

    public static void printFileDetails(File file) {

        long sizeKB = file.length() / 1024;

        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        System.out.println("File Found");
        System.out.println("Name  : " + file.getName());
        System.out.println("Path  : " + file.getAbsolutePath());
        System.out.println("Size  : " + sizeKB + " KB");
        System.out.println("Date  : " + sdf.format(date));
        System.out.println("----------------------------------");
    }
}
