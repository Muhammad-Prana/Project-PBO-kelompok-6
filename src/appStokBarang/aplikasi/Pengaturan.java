package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

    public class Pengaturan extends JFrame {

        public Pengaturan() {
            setTitle("Manajemen Stok Barang - Pengaturan");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 500);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Warna pink sesuai gambar
            Color pinkDark = new Color(255, 105, 180);
            Color pinkLight = new Color(255, 182, 193);
            Color pinkLighter = new Color(255, 192, 203);
            Color pinkSidebar = new Color(255, 182, 193);
            Color pinkSidebarHighlight = new Color(255, 105, 180);

            // Sidebar panel
            JPanel sidebar = new JPanel();
            sidebar.setBackground(pinkSidebar);
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
            sidebar.setPreferredSize(new Dimension(180, getHeight()));

            // Logo flower icon (emoji bunga sebagai placeholder)
            JLabel logo = new JLabel("\uD83C\uDF3A");
            logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
            logo.setForeground(pinkDark);
            logo.setBorder(new EmptyBorder(20, 20, 20, 20));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidebar.add(logo);

            // Menu items
            String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menuItems) {
                JLabel label = new JLabel(item);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                label.setForeground(Color.BLACK);
                label.setOpaque(true);
                label.setBorder(new EmptyBorder(10, 20, 10, 20));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                if (item.equals("Pengaturan")) {
                    label.setBackground(pinkSidebarHighlight);
                    label.setForeground(Color.WHITE);
                }
                sidebar.add(label);
            }

            add(sidebar, BorderLayout.WEST);

            // Header panel (pink bar with title and search + user icon)
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(pinkDark);
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBorder(new EmptyBorder(10, 20, 10, 20));

            JLabel title = new JLabel("Manajemen Stok Barang");
            title.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
            title.setForeground(Color.WHITE);
            header.add(title, BorderLayout.WEST);

            // Search panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            searchPanel.setOpaque(false);

            JTextField searchField = new JTextField(15);
            searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            searchField.setPreferredSize(new Dimension(150, 30));
            searchPanel.add(searchField);

            JButton searchButton = new JButton("\uD83D\uDD0D"); // icon kaca pembesar
            searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            searchButton.setBackground(pinkLight);
            searchButton.setFocusPainted(false);
            searchPanel.add(searchButton);

            // User icon (circle with user silhouette)
            JLabel userIcon = new JLabel("\uD83D\uDC64"); // emoji user sebagai placeholder
            userIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
            userIcon.setOpaque(true);
            userIcon.setBackground(pinkLight);
            userIcon.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            searchPanel.add(userIcon);

            header.add(searchPanel, BorderLayout.EAST);

            add(header, BorderLayout.NORTH);

            // Main content panel for Pengaturan
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

            // Informasi Pengguna panel
            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.setBackground(pinkLight);
            infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            infoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            // Label "Informasi Pengguna"
            JLabel infoLabel = new JLabel("Informasi Pengguna");
            infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            infoLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
            infoPanel.add(infoLabel, BorderLayout.NORTH);

            // User info content panel
            JPanel userInfoContent = new JPanel();
            userInfoContent.setBackground(new Color(255, 228, 241)); // pink lebih terang
            userInfoContent.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
            userInfoContent.setPreferredSize(new Dimension(600, 80));

            // User icon besar
            JLabel bigUserIcon = new JLabel("\uD83D\uDC64");
            bigUserIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
            bigUserIcon.setOpaque(false);
            userInfoContent.add(bigUserIcon);

            // User text info
            JPanel userTextPanel = new JPanel();
            userTextPanel.setBackground(new Color(255, 228, 241));
            userTextPanel.setLayout(new BoxLayout(userTextPanel, BoxLayout.Y_AXIS));

            JLabel userName = new JLabel("kelompok 6");
            userName.setFont(new Font("Segoe UI", Font.BOLD, 24));
            JLabel userEmail = new JLabel("kelompok6@gmail.com");
            userEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            userTextPanel.add(userName);
            userTextPanel.add(userEmail);

            userInfoContent.add(userTextPanel);

            infoPanel.add(userInfoContent, BorderLayout.CENTER);

            mainPanel.add(infoPanel);

            // Spacer
            mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

            // Logout button panel
            JPanel logoutPanel = new JPanel();
            logoutPanel.setBackground(Color.WHITE);
            logoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton logoutButton = new JButton("Logout");
            logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
            logoutButton.setBackground(pinkLight);
            logoutButton.setForeground(Color.BLACK);
            logoutButton.setFocusPainted(false);
            logoutButton.setPreferredSize(new Dimension(200, 50));

            logoutPanel.add(logoutButton);

            mainPanel.add(logoutPanel);

            add(mainPanel, BorderLayout.CENTER);
        }

        public static void main(String[] args) {
            // Set look and feel ke system default agar lebih bagus
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            SwingUtilities.invokeLater(() -> {
                Pengaturan app = new Pengaturan();
                app.setVisible(true);
            });
        }
    }
//