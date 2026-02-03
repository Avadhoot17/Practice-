import java.util.Random;
import java.util.Scanner;

public class TwoFactorAuth {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";
    private static int generatedOTP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Username & Password
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!authenticateUser(username, password)) {
            System.out.println("❌ Invalid username or password");
            return;
        }

        // Step 2: Generate OTP
        generatedOTP = generateOTP();
        sendOTP(generatedOTP);

        // Step 3: Verify OTP
        System.out.print("Enter OTP: ");
        int enteredOTP = sc.nextInt();

        if (verifyOTP(enteredOTP)) {
            System.out.println("✅ Login successful (2FA verified)");
        } else {
            System.out.println("❌ Invalid OTP. Access denied");
        }

        sc.close();
    }

    private static boolean authenticateUser(String user, String pass) {
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }

    private static int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // 6-digit OTP
    }

    private static void sendOTP(int otp) {
        // In real apps: SMS / Email / Auth App
        System.out.println("📩 OTP sent: " + otp);
    }

    private static boolean verifyOTP(int userOTP) {
        return generatedOTP == userOTP;
    }
}
