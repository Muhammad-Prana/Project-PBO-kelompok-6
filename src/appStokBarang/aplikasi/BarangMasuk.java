package appStokBarang.aplikasi;

import appStokBarang.aplikasi.Dashboard; // ✅ Tambahkan baris ini
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BarangMasuk extends JFrame {
    private JComboBox<String> cbNamaBarang;
    private JTextField tfJumlah, tfTanggal, tfKeterangan;
    private DefaultTableModel tableModel;

    public BarangMasuk() {
        setTitle("Manajemen Stok Barang - Barang Masuk");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
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
            if (item.equals("Barang Masuk")) {
                button.setBackground(new Color(255, 153, 204));
            } else {
                button.setBackground(new Color(255, 204, 229));
            }
            button.setBorderPainted(false);

            final String menuName = item;
            button.addActionListener(ev -> {
                switch (menuName) {
                    case "Dashboard":
                        new Dashboard().setVisible(true);
                        dispose();
                        break;
                }
            });

            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(button);
        }

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBackground(new Color(255, 105, 180));

        JLabel title = new JLabel("  Manajemen Stok Barang");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.BLACK);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JTextField searchField = new JTextField("Cari", 15);
        JButton searchBtn = new JButton("\uD83D\uDD0D");
        JLabel profileIcon = new JLabel("\uD83D\uDC64");

        searchPanel.setBackground(new Color(255, 105, 180));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(profileIcon);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel tableTitle = new JLabel("Barang Masuk", JLabel.CENTER);
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));

        String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(221, 160, 221));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        tableContainer.add(tableTitle, BorderLayout.NORTH);
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        tableContainer.setBackground(Color.WHITE);

        // Form Input
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        formPanel.setBackground(Color.WHITE);

        cbNamaBarang = new JComboBox<>();
        loadNamaBarang();

        JPanel barangPanel = new JPanel(new BorderLayout(5, 0));
        JButton btnTambahBarang = new JButton("+");
        btnTambahBarang.setToolTipText("Tambah Nama Barang Baru");
        btnTambahBarang.addActionListener(e -> tambahNamaBarangBaru());

        barangPanel.add(cbNamaBarang, BorderLayout.CENTER);
        barangPanel.add(btnTambahBarang, BorderLayout.EAST);

        tfJumlah = new JTextField();
        tfTanggal = new JTextField("2025-06-15");
        tfKeterangan = new JTextField();

        formPanel.add(new JLabel("Nama Barang"));
        formPanel.add(barangPanel);
        formPanel.add(new JLabel("Jumlah"));
        formPanel.add(tfJumlah);
        formPanel.add(new JLabel("Tanggal Masuk"));
        formPanel.add(tfTanggal);
        formPanel.add(new JLabel("Keterangan"));
        formPanel.add(tfKeterangan);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(255, 182, 193));
        btnSimpan.addActionListener(e -> simpanDataBarangMasuk());

        formPanel.add(new JLabel());
        formPanel.add(btnSimpan);

        tableContainer.add(formPanel, BorderLayout.SOUTH);

        contentPanel.add(tableContainer, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        tampilkanDataKeTabel();
        setVisible(true);
    }

    private void loadNamaBarang() {
        cbNamaBarang.removeAllItems();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nama_barang FROM tb_barang")) {

            while (rs.next()) {
                cbNamaBarang.addItem(rs.getString("nama_barang"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat nama barang: " + e.getMessage());
        }
    }

    private void tambahNamaBarangBaru() {
        String namaBaru = JOptionPane.showInputDialog(this, "Masukkan nama barang baru:");
        if (namaBaru != null && !namaBaru.trim().isEmpty()) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "")) {
                String cekQuery = "SELECT COUNT(*) FROM tb_barang WHERE nama_barang = ?";
                PreparedStatement cekStmt = conn.prepareStatement(cekQuery);
                cekStmt.setString(1, namaBaru.trim());
                ResultSet rs = cekStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Barang sudah terdaftar.");
                    return;
                }

                String insertQuery = "INSERT INTO tb_barang (nama_barang, kategori, satuan, harga, keterangan) VALUES (?, 'lainnya', 'pcs', 0, '')";
                PreparedStatement stmt = conn.prepareStatement(insertQuery);
                stmt.setString(1, namaBaru.trim());
                stmt.executeUpdate();

                loadNamaBarang();
                cbNamaBarang.setSelectedItem(namaBaru.trim());

                JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan barang: " + e.getMessage());
            }
        }
    }

    private void tampilkanDataKeTabel() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT sm.tanggal_masuk, b.nama_barang, sm.jumlah, sm.keterangan FROM tb_stok_masuk sm JOIN tb_barang b ON sm.id_barang = b.id_barang ORDER BY sm.id_masuk DESC")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getString("tanggal_masuk"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getString("keterangan")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data tabel: " + e.getMessage());
        }
    }

    private void simpanDataBarangMasuk() {
        String namaBarang = (String) cbNamaBarang.getSelectedItem();
        int jumlah;
        String tanggal = tfTanggal.getText().trim();
        String keterangan = tfKeterangan.getText().trim();

        try {
            jumlah = Integer.parseInt(tfJumlah.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "")) {

            String queryId = "SELECT id_barang FROM tb_barang WHERE nama_barang = ?";
            PreparedStatement psId = conn.prepareStatement(queryId);
            psId.setString(1, namaBarang);
            ResultSet rsId = psId.executeQuery();

            if (rsId.next()) {
                int idBarang = rsId.getInt("id_barang");

                String queryInsert = "INSERT INTO tb_stok_masuk (id_barang, jumlah, tanggal_masuk, keterangan) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(queryInsert);
                psInsert.setInt(1, idBarang);
                psInsert.setInt(2, jumlah);
                psInsert.setString(3, tanggal);
                psInsert.setString(4, keterangan);

                int rows = psInsert.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                    tampilkanDataKeTabel();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Barang tidak ditemukan.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BarangMasuk::new);
    }
}
