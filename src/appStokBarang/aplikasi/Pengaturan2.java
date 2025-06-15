package appStokBarang.aplikasi;

import appStokBarang.aplikasi.Login; // pastikan ini sesuai dengan package Login.java

import javax.swing.*;
import java.awt.*;

public class Pengaturan2 extends JFrame {

    public Pengaturan2() {
        setTitle("Manajemen Stok Barang - Pengaturan");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(255, 204, 229));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("🌸", JLabel.CENTER);
        logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(20));

        String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFocusPainted(false);
            button.setMaximumSize(new Dimension(180, 40));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("SansSerif", Font.BOLD, 14));
            button.setBackground(item.equals("Pengaturan") ? new Color(255, 153, 204) : new Color(255, 204, 229));
            button.setBorderPainted(false);
            button.addActionListener(ev -> navigateTo(item));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(button);
        }

        // Header
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

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel pageTitle = new JLabel("Pengaturan", JLabel.CENTER);
        pageTitle.setOpaque(true);
        pageTitle.setBackground(new Color(255, 204, 229));
        pageTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        pageTitle.setPreferredSize(new Dimension(100, 50));

        JPanel contentArea = new JPanel();
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.setBackground(Color.WHITE);
        contentArea.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel infoLabel = new JLabel("Informasi Pengguna");
        infoLabel.setOpaque(true);
        infoLabel.setBackground(new Color(255, 192, 203));
        infoLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setPreferredSize(new Dimension(600, 40));
        infoLabel.setMaximumSize(new Dimension(600, 40));

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

        JButton logoutButton = new JButton("Logout");
        logoutButton.setMaximumSize(new Dimension(600, 40));
        logoutButton.setBackground(new Color(255, 192, 203));
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        logoutButton.addActionListener(e -> logout());

        contentArea.add(infoLabel);
        contentArea.add(Box.createRigidArea(new Dimension(0, 10)));
        contentArea.add(userPanel);
        contentArea.add(Box.createRigidArea(new Dimension(0, 30)));
        contentArea.add(logoutButton);

        contentPanel.add(pageTitle, BorderLayout.NORTH);
        contentPanel.add(contentArea, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void navigateTo(String menuName) {
        switch (menuName) {
            case "Dashboard": new Dashboard().setVisible(true); break;
            case "Stok Barang": new StokBarang().setVisible(true); break;
            case "Barang Masuk": new BarangMasuk().setVisible(true); break;
            case "Barang Keluar": new BarangKeluar().setVisible(true); break;
            case "Pengaturan": new Pengaturan2().setVisible(true); break;
        }
        dispose();
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
//            JOptionPane.showMessageDialog(null, "Berhasil logout.");
//            new Login().setVisible(true); // pastikan Login terletak di package dan file yang benar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pengaturan2::new);
    }
}
