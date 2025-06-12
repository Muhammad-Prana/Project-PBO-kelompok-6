package appStokBarang.aplikasi;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

    public class SelamatDatang extends JFrame {

        public SelamatDatang() {
            setTitle("Manajemen Stok Barang");
            setSize(600, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Header panel
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(new Color(255, 105, 180)); // Pink color
            headerPanel.setPreferredSize(new Dimension(600, 60));

            JLabel titleLabel = new JLabel("Manajemen Stok Barang");
            titleLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
            titleLabel.setForeground(Color.BLACK);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            headerPanel.add(titleLabel, BorderLayout.WEST);

            // Search bar panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
            searchPanel.setBackground(new Color(255, 105, 180));

            JTextField searchField = new JTextField("Cari", 15);
            searchField.setFont(new Font("Arial", Font.PLAIN, 14));
            searchField.setForeground(Color.GRAY);
            searchPanel.add(searchField);

            // Search icon (using Unicode magnifying glass)
            JLabel searchIcon = new JLabel("\uD83D\uDD0D"); // 🔍
            searchIcon.setFont(new Font("Arial", Font.PLAIN, 18));
            searchPanel.add(searchIcon);

            headerPanel.add(searchPanel, BorderLayout.EAST);

            add(headerPanel, BorderLayout.NORTH);

            // Main content panel
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            // Selamat Datang label with italic font
            JLabel welcomeLabel = new JLabel("Selamat Datang");
            welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 28));
            welcomeLabel.setForeground(Color.BLACK);

            // Sun icon label (load from resource or URL)
            JLabel sunIconLabel = new JLabel();
            try {
                // You can replace this URL with a local file path if needed
                URL sunIconUrl = new URL("https://i.imgur.com/6Y6QZ6M.png"); // Example sun icon URL
                ImageIcon sunIcon = new ImageIcon(sunIconUrl);
                Image scaledSun = sunIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                sunIconLabel.setIcon(new ImageIcon(scaledSun));
            } catch (Exception e) {
                sunIconLabel.setText("☀"); // fallback icon
                sunIconLabel.setFont(new Font("Serif", Font.PLAIN, 28));
            }

            // Panel to hold welcome text and icon horizontally
            JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            welcomePanel.setBackground(Color.WHITE);
            welcomePanel.add(welcomeLabel);
            welcomePanel.add(sunIconLabel);

            mainPanel.add(welcomePanel, gbc);

            // Login button
            gbc.gridy++;
            JButton loginButton = new JButton("Login");
            loginButton.setPreferredSize(new Dimension(200, 50));
            loginButton.setBackground(new Color(255, 182, 193)); // Light pink
            loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
            loginButton.setFocusPainted(false);
            mainPanel.add(loginButton, gbc);

            // "Or" label
            gbc.gridy++;
            JLabel orLabel = new JLabel("Or");
            orLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            mainPanel.add(orLabel, gbc);

            // SignUp button
            gbc.gridy++;
            JButton signUpButton = new JButton("SignUp");
            signUpButton.setPreferredSize(new Dimension(200, 50));
            signUpButton.setBackground(new Color(255, 182, 193)); // Light pink
            signUpButton.setFont(new Font("Arial", Font.PLAIN, 20));
            signUpButton.setFocusPainted(false);
            mainPanel.add(signUpButton, gbc);

            add(mainPanel, BorderLayout.CENTER);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                SelamatDatang welcomePage = new SelamatDatang();
                welcomePage.setVisible(true);
            });
        }
    }
//