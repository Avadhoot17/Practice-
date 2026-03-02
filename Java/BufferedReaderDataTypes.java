import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BufferedReaderDataTypes {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(br.readLine());

        System.out.print("Enter your percentage: ");
        double percentage = Double.parseDouble(br.readLine());

        System.out.println("Age: " + age);
        System.out.println("Percentage: " + percentage);
    }
}
