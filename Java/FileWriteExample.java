import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample {

    public static void main(String[] args) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

            bw.write("Hello Java");
            bw.newLine();
            bw.write("This is BufferedWriter example");

            bw.close();

            System.out.println("File written successfully.");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
