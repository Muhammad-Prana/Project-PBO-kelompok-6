package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class Dashboard extends JFrame {

    private JPanel sidebar;
    private JPanel header;
    private JTextField search;
    private JButton btnTambah;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private boolean sidebarVisible = true;

    // Database connection parameters - adjust as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_stokbarang";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Dashboard() {
        setTitle("Manajemen Stok Barang");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        initSidebar();
        initHeader();
        initContent();

        add(sidebar);
        add(header);
        add(btnTambah);
        add(scrollPane);

        loadDataFromDatabase();
    }

    private void initSidebar() {
        sidebar = new JPanel();
        sidebar.setBackground(new Color(255, 204, 229));
        sidebar.setBounds(0, 0, 220, 600);
        sidebar.setLayout(null);

        JLabel logo = new JLabel("🌸"); // Emoji bunga sebagai logo
        logo.setFont(new Font("Arial", Font.PLAIN, 60));
        logo.setBounds(80, 20, 100, 60);
        sidebar.add(logo);

        String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
        int y = 100;
        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = new JButton(menuItems[i]);
            btn.setBounds(20, y, 180, 40);
            btn.setBackground(i == 0 ? new Color(255, 153, 204) : new Color(255, 204, 229));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            final String menuName = menuItems[i];
            btn.addActionListener(e ->
                    JOptionPane.showMessageDialog(this,
                            "Anda memilih menu: " + menuName,
                            "Menu Dipilih",
                            JOptionPane.INFORMATION_MESSAGE)
            );

            sidebar.add(btn);
            y += 50;
        }
    }

    private void initHeader() {
        header = new JPanel();
        header.setBackground(new Color(255, 105, 180));
        header.setBounds(220, 0, 780, 60);
        header.setLayout(null);

        // Hamburger menu button with HamburgerIcon
        JButton hamburger = new JButton();
        hamburger.setIcon(new HamburgerIcon(30, 24, Color.WHITE));
        hamburger.setBounds(10, 12, 40, 36);
        hamburger.setFocusPainted(false);
        hamburger.setBorderPainted(false);
        hamburger.setContentAreaFilled(false);
        hamburger.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hamburger.addActionListener(e -> toggleSidebar());

        header.add(hamburger);

        JLabel title = new JLabel("Manajemen Stok Buket Bunga");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(60, 10, 400, 40);
        header.add(title);

        search = new JTextField("Cari");
        search.setBounds(420, 15, 200, 30);
        search.setForeground(new Color(130, 130, 130));
        search.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 200), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        // Placeholder behavior
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (search.getText().equals("Cari")) {
                    search.setText("");
                    search.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (search.getText().isEmpty()) {
                    search.setForeground(new Color(130, 130, 130));
                    search.setText("Cari");
                }
            }
        });
        header.add(search);

        JLabel profileIcon = new JLabel("👤"); // Emoji profil sebagai ikon
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 28));
        profileIcon.setBounds(720, 10, 40, 40);
        profileIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileIcon.setToolTipText("Profil Saya");
        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Menuju Profil Saya",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        header.add(profileIcon);
    }

    private void initContent() {
        btnTambah = new JButton("Tambah Barang");
        btnTambah.setBounds(420, 80, 160, 40);
        btnTambah.setBackground(new Color(255, 204, 229));
        btnTambah.setFocusPainted(false);
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTambah.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Fungsi tambah barang belum diimplementasikan.",
                        "Info", JOptionPane.INFORMATION_MESSAGE)
        );

        String[] columnNames = {"No", "Nama Barang", "Satuan", "Harga", "Aksi"};

        // initialize model with column names and zero rows first
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 4; // hanya kolom "Aksi" yang editable
            }
        };

        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(250, 140, 700, 300);
    }

    private void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_barang, nama_barang, satuan, harga FROM tb_barang ORDER BY id_barang ASC")) {

            model.setRowCount(0); // membersihkan data lama

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id_barang"));
                row.add(rs.getString("nama_barang"));
                row.add(rs.getInt("satuan"));
                row.add(String.format("%,d", rs.getInt("harga"))); // format harga dengan ribuan separator
                row.add("Edit"); // Placeholder aksi
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data dari database:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Toggle sidebar visibility and adjust components
    private void toggleSidebar() {
        if (sidebarVisible) {
            // Hide sidebar
            sidebar.setBounds(0, 0, 0, 600);
            header.setBounds(0, 0, 1000, 60);
            search.setBounds(700, 15, 200, 30);
            btnTambah.setBounds(700, 80, 160, 40);
            scrollPane.setBounds(30, 140, 940, 300);
        } else {
            // Show sidebar
            sidebar.setBounds(0, 0, 220, 600);
            header.setBounds(220, 0, 780, 60);
            search.setBounds(420, 15, 200, 30);
            btnTambah.setBounds(420, 80, 160, 40);
            scrollPane.setBounds(250, 140, 700, 300);
        }
        sidebarVisible = !sidebarVisible;
        sidebar.revalidate();
        sidebar.repaint();
        header.revalidate();
        header.repaint();
        btnTambah.revalidate();
        btnTambah.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    // Static inner class for custom hamburger icon with three horizontal lines
    private static class HamburgerIcon implements Icon {
        private final int width;
        private final int height;
        private final Color color;

        public HamburgerIcon(int width, int height, Color color) {
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color);
            g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int barHeight = height / 7;
            int gap = barHeight;

            for (int i = 0; i < 3; i++) {
                int yPos = y + i * (barHeight + gap) + barHeight;
                g2.drawLine(x + 3, yPos, x + width - 3, yPos);
            }

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return width;
        }

        @Override
        public int getIconHeight() {
            return height;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}

