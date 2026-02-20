import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BasicSwingApp {

    public static void main(String[] args) {

        // Create Frame
        JFrame frame = new JFrame("Basic Swing Application");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen

        // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create Components
        JLabel label = new JLabel("Enter Your Name:", SwingConstants.CENTER);
        JTextField textField = new JTextField();
        JButton button = new JButton("Submit");

        // Add Action Listener to Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();

                if(name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter your name!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Hello, " + name + "!",
                            "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add Components to Panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        // Add Panel to Frame
        frame.add(panel);

        // Make Frame Visible
        frame.setVisible(true);
    }
}
