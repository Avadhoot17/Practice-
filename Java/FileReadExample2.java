import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExample2 {
fef
    public static void main(String[] args) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

            bw.write("Hello Java");
            bw.newLine();
            bw.write("BufferedWriter Example");

            bw.close();

            System.out.println("File created successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
