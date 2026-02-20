import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BasicSwingApp {

    public static void main(String[] args) {

        // Run GUI on Event Dispatch Thread (Best Practice)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createUI();
            }
        });
    }

    private static void createUI() {

        // Create Frame
        JFrame frame = new JFrame("Basic Swing GUI Application");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center screen

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Swing Application", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton button = new JButton("Submit");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(60, 179, 113));
        button.setForeground(Color.WHITE);

        inputPanel.add(textField);
        inputPanel.add(button);

        // Add Action Listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = textField.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter your name!",
                            "Input Required",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Hello, " + name + " 👋",
                            "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add Components
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}
