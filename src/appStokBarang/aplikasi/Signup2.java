package appStokBarang.aplikasi;

import javax.swing.*;
import java.awt.*;

    public class Signup2 extends JFrame {

        public Signup2() {
            setTitle("Manajemen Stok Barang - SignUp");
            setSize(600, 450);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // ===== Header =====
            JPanel header = new JPanel(new BorderLayout());
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBackground(new Color(255, 105, 180)); // Pink terang

            JLabel title = new JLabel("  Manajemen Stok Barang");
            title.setFont(new Font("SansSerif", Font.BOLD, 20));
            title.setForeground(Color.BLACK);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            searchPanel.setBackground(new Color(255, 105, 180));
            JTextField searchField = new JTextField("Cari", 10);
            searchField.setBackground(new Color(255, 182, 193));
            JButton searchButton = new JButton("🔍");
            searchButton.setBackground(new Color(255, 182, 193));

            searchPanel.add(searchField);
            searchPanel.add(searchButton);

            header.add(title, BorderLayout.WEST);
            header.add(searchPanel, BorderLayout.EAST);

            // ===== Signup Form =====
            JPanel formPanel = new JPanel();
            formPanel.setBackground(Color.WHITE);
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

            JTextField usernameField = new JTextField();
            usernameField.setMaximumSize(new Dimension(300, 40));
            usernameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            usernameField.setBackground(new Color(255, 182, 193));
            usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            usernameField.setText("Username");

            JTextField emailField = new JTextField();
            emailField.setMaximumSize(new Dimension(300, 40));
            emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            emailField.setBackground(new Color(255, 182, 193));
            emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            emailField.setText("Email");

            JPasswordField passwordField = new JPasswordField();
            passwordField.setMaximumSize(new Dimension(300, 40));
            passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
            passwordField.setBackground(new Color(255, 182, 193));
            passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            passwordField.setEchoChar((char) 0); // Show password as text initially
            passwordField.setText("Password");

            JButton signupButton = new JButton("SignUp");
            signupButton.setMaximumSize(new Dimension(150, 40));
            signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            signupButton.setBackground(new Color(255, 182, 193));
            signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));

            // Spacing dan penambahan ke panel
            formPanel.add(Box.createVerticalStrut(50));
            formPanel.add(usernameField);
            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(emailField);
            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(passwordField);
            formPanel.add(Box.createVerticalStrut(30));
            formPanel.add(signupButton);

            add(header, BorderLayout.NORTH);
            add(formPanel, BorderLayout.CENTER);
            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Signup2::new);
        }
    }
//