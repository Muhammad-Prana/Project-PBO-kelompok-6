package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

    public class Login2 extends JFrame {

        public Login2() {
            setTitle("Manajemen Stok Barang");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Center window

            // Warna tema
            Color pinkMain = new Color(0xFF69B4);
            Color pinkLight = new Color(0xFFB6C1);
            Color white = Color.WHITE;

            // Header panel
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(pinkMain);
            headerPanel.setPreferredSize(new Dimension(500, 60));

            JLabel titleLabel = new JLabel("Manajemen Stok Barang");
            titleLabel.setForeground(white);
            titleLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
            titleLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
            headerPanel.add(titleLabel, BorderLayout.WEST);

            // Search bar panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            searchPanel.setBackground(pinkMain);

            JTextField searchField = new JTextField(15);
            searchField.setFont(new Font("Arial", Font.PLAIN, 14));
            searchField.setPreferredSize(new Dimension(150, 30));
            searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            searchPanel.add(searchField);

            // Icon kaca pembesar (gunakan Unicode magnifying glass)
            JLabel searchIcon = new JLabel("\uD83D\uDD0D"); // 🔍
            searchIcon.setFont(new Font("Arial", Font.PLAIN, 18));
            searchIcon.setForeground(white);
            searchPanel.add(searchIcon);

            headerPanel.add(searchPanel, BorderLayout.EAST);

            add(headerPanel, BorderLayout.NORTH);

            // Main panel untuk form login
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(white);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

            // Username field
            JTextField usernameField = new JTextField();
            usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
            usernameField.setForeground(Color.GRAY);
            usernameField.setText("Username");
            usernameField.setBackground(pinkLight);
            usernameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Placeholder effect for username
            usernameField.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (usernameField.getText().equals("Username")) {
                        usernameField.setText("");
                        usernameField.setForeground(Color.BLACK);
                    }
                }
                public void focusLost(FocusEvent e) {
                    if (usernameField.getText().isEmpty()) {
                        usernameField.setForeground(Color.GRAY);
                        usernameField.setText("Username");
                    }
                }
            });

            mainPanel.add(usernameField);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            // Password field
            JPasswordField passwordField = new JPasswordField();
            passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
            passwordField.setForeground(Color.GRAY);
            passwordField.setEchoChar((char)0); // Show placeholder text
            passwordField.setText("Password");
            passwordField.setBackground(pinkLight);
            passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Placeholder effect for password
            passwordField.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    String passText = new String(passwordField.getPassword());
                    if (passText.equals("Password")) {
                        passwordField.setText("");
                        passwordField.setEchoChar('\u2022'); // Bullet char
                        passwordField.setForeground(Color.BLACK);
                    }
                }
                public void focusLost(FocusEvent e) {
                    String passText = new String(passwordField.getPassword());
                    if (passText.isEmpty()) {
                        passwordField.setEchoChar((char)0);
                        passwordField.setForeground(Color.GRAY);
                        passwordField.setText("Password");
                    }
                }
            });

            mainPanel.add(passwordField);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            // Login button
            JButton loginButton = new JButton("Login");
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginButton.setBackground(pinkMain);
            loginButton.setForeground(white);
            loginButton.setFont(new Font("Arial Black", Font.BOLD, 18));
            loginButton.setFocusPainted(false);
            loginButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
            loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Action listener untuk tombol login (contoh)
            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("Username") || username.isEmpty() ||
                        password.equals("Password") || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Mohon isi Username dan Password dengan benar.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Login berhasil! Username: " + username);
                    // Di sini bisa ditambahkan logika login sebenarnya
                }
            });

            mainPanel.add(loginButton);

            add(mainPanel, BorderLayout.CENTER);
        }

        public static void main(String[] args) {
            // Set look and feel ke system default agar lebih rapi
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            SwingUtilities.invokeLater(() -> {
                Login2 loginUI = new Login2();
                loginUI.setVisible(true);
            });
        }
    }
//