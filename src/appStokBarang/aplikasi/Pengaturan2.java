package appStokBarang.aplikasi;

import javax.swing.*;
import java.awt.*;

    public class Pengaturan2 extends JFrame {

        public Pengaturan2() {
            setTitle("Manajemen Stok Barang");
            setSize(1000, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // ===== Sidebar =====
            JPanel sidebar = new JPanel();
            sidebar.setPreferredSize(new Dimension(200, getHeight()));
            sidebar.setBackground(new Color(255, 204, 229));
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

            JLabel logo = new JLabel("🌸", JLabel.CENTER);
            logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
            sidebar.add(logo);
            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

            String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menuItems) {
                JButton button = new JButton(item);
                button.setFocusPainted(false);
                button.setMaximumSize(new Dimension(180, 40));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                if (item.equals("Pengaturan")) {
                    button.setBackground(new Color(255, 153, 204)); // aktif
                } else {
                    button.setBackground(new Color(255, 204, 229));
                }
                button.setBorderPainted(false);
                button.setFont(new Font("SansSerif", Font.PLAIN, 16));
                sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
                sidebar.add(button);
            }

            // ===== Header =====
            JPanel header = new JPanel(new BorderLayout());
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBackground(new Color(255, 105, 180));

            JLabel title = new JLabel("  Manajemen Stok Barang");
            title.setFont(new Font("SansSerif", Font.BOLD, 22));
            title.setForeground(Color.BLACK);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            searchPanel.setBackground(new Color(255, 105, 180));
            JTextField searchField = new JTextField("Cari", 15);
            JButton searchButton = new JButton("🔍");
            JLabel profileIcon = new JLabel("👤");

            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(profileIcon);

            header.add(title, BorderLayout.WEST);
            header.add(searchPanel, BorderLayout.EAST);

            // ===== Konten Pengaturan =====
            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBackground(Color.WHITE);
            content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            // Label Informasi Pengguna
            JLabel infoLabel = new JLabel("Informasi Pengguna");
            infoLabel.setOpaque(true);
            infoLabel.setBackground(new Color(255, 192, 203));
            infoLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setPreferredSize(new Dimension(600, 40));
            infoLabel.setMaximumSize(new Dimension(600, 40));

            // Panel Info Pengguna
            JPanel userPanel = new JPanel();
            userPanel.setBackground(new Color(255, 228, 250));
            userPanel.setMaximumSize(new Dimension(600, 100));
            userPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

            JLabel userIcon = new JLabel("👤");
            userIcon.setFont(new Font("SansSerif", Font.PLAIN, 50));

            JPanel userInfo = new JPanel();
            userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
            userInfo.setBackground(new Color(255, 228, 250));

            JLabel userName = new JLabel("kelompok 6");
            userName.setFont(new Font("SansSerif", Font.BOLD, 18));
            JLabel userEmail = new JLabel("kelompok6@gmail.com");
            userEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));

            userInfo.add(userName);
            userInfo.add(userEmail);

            userPanel.add(userIcon);
            userPanel.add(userInfo);

            // Tombol Logout
            JButton logoutButton = new JButton("Logout");
            logoutButton.setMaximumSize(new Dimension(600, 40));
            logoutButton.setBackground(new Color(255, 192, 203));
            logoutButton.setFont(new Font("SansSerif", Font.BOLD, 16));

            // Tambahkan ke konten
            content.add(infoLabel);
            content.add(Box.createRigidArea(new Dimension(0, 10)));
            content.add(userPanel);
            content.add(Box.createRigidArea(new Dimension(0, 30)));
            content.add(logoutButton);

            // ===== Tambahkan ke Frame =====
            add(sidebar, BorderLayout.WEST);
            add(header, BorderLayout.NORTH);
            add(content, BorderLayout.CENTER);

            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Pengaturan2::new);
        }
    }
//