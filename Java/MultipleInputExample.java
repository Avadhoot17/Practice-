import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MultipleInputExample {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter two numbers separated by space: ");
        String[] values = br.readLine().split(" ");

        int num1 = Integer.parseInt(values[0]);
        int num2 = Integer.parseInt(values[1]);

        System.out.println("Sum = " + (num1 + num2));
    }
}
