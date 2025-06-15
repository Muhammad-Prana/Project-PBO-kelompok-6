package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class StokBarang extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton btnEdit, btnExport;

    // Koneksi database
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_stokbarang";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    public StokBarang() {
        setTitle("Manajemen Stok Barang - Stok Barang");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            button.setBackground(item.equals("Stok Barang") ? new Color(255, 153, 204) : new Color(255, 204, 229));
            button.setBorderPainted(false);
            final String menuName = item;
            button.addActionListener(e -> navigateTo(menuName));
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
        searchField = new JTextField("", 15);
        JButton searchBtn = new JButton("\uD83D\uDD0D");
        JLabel profileIcon = new JLabel("\uD83D\uDC64");

        searchPanel.setBackground(new Color(255, 105, 180));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(profileIcon);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel tableTitle = new JLabel("Stok Barang", JLabel.CENTER);
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));

        String[] columnNames = {"ID", "Nama Barang", "Satuan", "Stok", "Status", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEdit = new JButton("Edit Barang");
        btnExport = new JButton("Export CSV");
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnExport);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        tableContainer.add(tableTitle, BorderLayout.NORTH);
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        tableContainer.add(buttonPanel, BorderLayout.SOUTH);
        tableContainer.setBackground(Color.WHITE);

        contentPanel.add(tableContainer, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        loadStokData();

        // Auto-search saat mengetik
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

        btnEdit.addActionListener(e -> editSelectedBarang());
        btnExport.addActionListener(e -> exportToCSV());

        setVisible(true);
    }

    private void navigateTo(String menuName) {
        switch (menuName) {
            case "Dashboard":
                new Dashboard().setVisible(true);
                dispose();
                break;
            case "Stok Barang":
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

    private void loadStokData() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_barang, nama_barang, satuan, stok, keterangan FROM tb_barang")) {

            while (rs.next()) {
                int stok = rs.getInt("stok");
                String status = getStatusText(stok);

                Object[] row = {
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        stok,
                        status,
                        rs.getString("keterangan")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data stok: " + e.getMessage());
        }
    }

    private void searchData(String keyword) {
        tableModel.setRowCount(0);
        String sql = "SELECT id_barang, nama_barang, satuan, stok, keterangan FROM tb_barang " +
                "WHERE nama_barang LIKE ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int stok = rs.getInt("stok");
                String status = getStatusText(stok);

                Object[] row = {
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        stok,
                        status,
                        rs.getString("keterangan")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal melakukan pencarian:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getStatusText(int stok) {
        if (stok == 0) return "❌ Kosong";
        else if (stok <= 5) return "⚠ Rendah";
        else return "✅ Aman";
    }

    private void editSelectedBarang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang ingin diedit.");
            return;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        int id = (int) tableModel.getValueAt(modelRow, 0);
        String nama = (String) tableModel.getValueAt(modelRow, 1);
        String satuan = (String) tableModel.getValueAt(modelRow, 2);
        int stok = (int) tableModel.getValueAt(modelRow, 3);
        String keterangan = (String) tableModel.getValueAt(modelRow, 5);

        JTextField tfNama = new JTextField(nama);
        JTextField tfSatuan = new JTextField(satuan);
        JTextField tfKeterangan = new JTextField(keterangan);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(tfNama);
        panel.add(new JLabel("Satuan:"));
        panel.add(tfSatuan);
        panel.add(new JLabel("Keterangan:"));
        panel.add(tfKeterangan);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Barang", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newNama = tfNama.getText().trim();
            String newSatuan = tfSatuan.getText().trim();
            String newKet = tfKeterangan.getText().trim();

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String update = "UPDATE tb_barang SET nama_barang = ?, satuan = ?, keterangan = ? WHERE id_barang = ?";
                PreparedStatement ps = conn.prepareStatement(update);
                ps.setString(1, newNama);
                ps.setString(2, newSatuan);
                ps.setString(3, newKet);
                ps.setInt(4, id);
                int updated = ps.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "Barang berhasil diperbarui.");
                    loadStokData();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui barang: " + e.getMessage());
            }
        }
    }

    private void exportToCSV() {
        try (FileWriter writer = new FileWriter("stok_barang_export.csv")) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                writer.append('"').append(table.getColumnName(i)).append('"');
                if (i < table.getColumnCount() - 1) writer.append(',');
            }
            writer.append('\n');

            for (int row = 0; row < table.getRowCount(); row++) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Object value = table.getValueAt(row, col);
                    String cell = value == null ? "" : value.toString().replace("\"", "\"\"");
                    writer.append('"').append(cell).append('"');
                    if (col < table.getColumnCount() - 1) writer.append(',');
                }
                writer.append('\n');
            }
            writer.flush();
            JOptionPane.showMessageDialog(this, "Data berhasil diexport ke stok_barang_export.csv");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal export data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StokBarang::new);
    }
}