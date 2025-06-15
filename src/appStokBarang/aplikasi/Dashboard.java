package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Dashboard extends JFrame {

    private DefaultTableModel model;
    private JTextField searchField;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_stokbarang";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Dashboard() {
        setTitle("Manajemen Stok Barang - Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(255, 204, 229));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("\uD83C\uDF38", JLabel.CENTER);
        logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logo);

        String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFocusPainted(false);
            button.setMaximumSize(new Dimension(180, 40));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setBackground(item.equals("Dashboard") ? new Color(255, 153, 204) : new Color(255, 204, 229));
            button.setBorderPainted(false);
            final String menuName = item;
            button.addActionListener(ev -> navigateTo(menuName));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(button);
        }

        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBackground(new Color(255, 105, 180));

        JLabel title = new JLabel("  Manajemen Stok Barang");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        searchField = new JTextField("Cari", 15);
        JButton searchBtn = new JButton("\uD83D\uDD0D");
        JLabel profileIcon = new JLabel("\uD83D\uDC64");

        // Placeholder behavior
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Cari")) {
                    searchField.setText("");
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Cari");
                }
            }
        });

        // Tombol cari ditekan
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            searchData(keyword);
        });

        // Ketik otomatis pencarian
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                searchData(searchField.getText().trim());
            }

            public void removeUpdate(DocumentEvent e) {
                searchData(searchField.getText().trim());
            }

            public void changedUpdate(DocumentEvent e) {
                searchData(searchField.getText().trim());
            }
        });

        searchPanel.setBackground(new Color(255, 105, 180));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(profileIcon);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel tableTitle = new JLabel("Dashboard", JLabel.CENTER);
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));

        String[] columnNames = {"ID", "Nama Barang", "Satuan", "Stok", "Status", "Keterangan"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(221, 160, 221));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        tableContainer.add(tableTitle, BorderLayout.NORTH);
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        tableContainer.setBackground(Color.WHITE);

        contentPanel.add(tableContainer, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        loadDataFromDatabase();
        setVisible(true);
    }

    private void navigateTo(String menuName) {
        switch (menuName) {
            case "Dashboard":
                break;
            case "Stok Barang":
                new StokBarang().setVisible(true);
                dispose();
                break;
            case "Barang Masuk":
                new BarangMasuk().setVisible(true);
                dispose();
                break;
            case "Barang Keluar":
                new BarangKeluar().setVisible(true);
                dispose();
                break;
            case "Pengaturan":
                new Pengaturan2().setVisible(true);
                dispose();
                break;
        }
    }

    private void loadDataFromDatabase() {
        model.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_barang, nama_barang, satuan, stok, keterangan FROM tb_barang")) {

            while (rs.next()) {
                int stok = rs.getInt("stok");
                String status;
                if (stok == 0) status = "❌ Kosong";
                else if (stok <= 5) status = "⚠ Rendah";
                else status = "✅ Aman";

                Object[] row = {
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        stok,
                        status,
                        rs.getString("keterangan")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data dari database:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchData(String keyword) {
        model.setRowCount(0);
        String sql = "SELECT id_barang, nama_barang, satuan, stok, keterangan FROM tb_barang " +
                "WHERE nama_barang LIKE ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int stok = rs.getInt("stok");
                String status;
                if (stok == 0) status = "❌ Kosong";
                else if (stok <= 5) status = "⚠ Rendah";
                else status = "✅ Aman";

                Object[] row = {
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        stok,
                        status,
                        rs.getString("keterangan")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal melakukan pencarian:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}