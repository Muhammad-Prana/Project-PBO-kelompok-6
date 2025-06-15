package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BarangMasuk extends JFrame {
    private JComboBox<String> cbNamaBarang;
    private JTextField tfJumlah, tfTanggal, tfKeterangan;
    private DefaultTableModel tableModel;
    private JTable table;

    public BarangMasuk() {
        setTitle("Manajemen Stok Barang - Barang Masuk");
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
            button.setBackground(item.equals("Barang Masuk") ? new Color(255, 153, 204) : new Color(255, 204, 229));
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
        JTextField searchField = new JTextField("Cari", 15);
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

        JLabel tableTitle = new JLabel("Barang Masuk", JLabel.CENTER);
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));

        String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(221, 160, 221));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnHapus = new JButton("Hapus Riwayat");
        btnHapus.setBackground(new Color(255, 150, 150));
        btnHapus.addActionListener(e -> hapusDataBarangMasuk());

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        tableContainer.add(tableTitle, BorderLayout.NORTH);
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        tableContainer.add(btnHapus, BorderLayout.SOUTH);
        tableContainer.setBackground(Color.WHITE);

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

        JLabel lblNama = new JLabel("Nama Barang", SwingConstants.RIGHT);
        JLabel lblJumlah = new JLabel("Jumlah", SwingConstants.RIGHT);
        JLabel lblTanggal = new JLabel("Tanggal Masuk", SwingConstants.RIGHT);
        JLabel lblKet = new JLabel("Keterangan", SwingConstants.RIGHT);

        tfJumlah = new JTextField ();
        tfTanggal = new JTextField("2025-06-15");
        tfKeterangan = new JTextField();

        formPanel.add(lblNama);
        formPanel.add(barangPanel);
        formPanel.add(lblJumlah);
        formPanel.add(tfJumlah);
        formPanel.add(lblTanggal);
        formPanel.add(tfTanggal);
        formPanel.add(lblKet);
        formPanel.add(tfKeterangan);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(255, 182, 193));
        btnSimpan.addActionListener(e -> simpanDataBarangMasuk());

        formPanel.add(new JLabel());
        formPanel.add(btnSimpan);

        contentPanel.add(tableContainer, BorderLayout.CENTER);
        contentPanel.add(formPanel, BorderLayout.SOUTH);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        tampilkanDataKeTabel();
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

                String insertQuery = "INSERT INTO tb_barang (nama_barang, kategori, satuan, harga, keterangan, stok) VALUES (?, 'lainnya', 'pcs', 0, '', 0)";
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
                    String updateStok = "UPDATE tb_barang SET stok = stok + ? WHERE id_barang = ?";
                    PreparedStatement psUpdate = conn.prepareStatement(updateStok);
                    psUpdate.setInt(1, jumlah);
                    psUpdate.setInt(2, idBarang);
                    psUpdate.executeUpdate();

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

    private void hapusDataBarangMasuk() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus.");
            return;
        }

        String tanggal = tableModel.getValueAt(selectedRow, 0).toString();
        String namaBarang = tableModel.getValueAt(selectedRow, 1).toString();
        int jumlah = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
        String keterangan = tableModel.getValueAt(selectedRow, 3).toString();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "")) {
            String getIdQuery = "SELECT id_barang, stok FROM tb_barang WHERE nama_barang = ?";
            PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery);
            getIdStmt.setString(1, namaBarang);
            ResultSet rs = getIdStmt.executeQuery();

            if (rs.next()) {
                int idBarang = rs.getInt("id_barang");
                int stokSaatIni = rs.getInt("stok");

                if (stokSaatIni < jumlah) {
                    JOptionPane.showMessageDialog(this, "Tidak bisa menghapus karena stok saat ini lebih kecil dari jumlah yang ingin dihapus.");
                    return;
                }

                String deleteQuery = "DELETE FROM tb_stok_masuk WHERE id_barang = ? AND tanggal_masuk = ? AND jumlah = ? AND keterangan = ? LIMIT 1";
                PreparedStatement psDelete = conn.prepareStatement(deleteQuery);
                psDelete.setInt(1, idBarang);
                psDelete.setString(2, tanggal);
                psDelete.setInt(3, jumlah);
                psDelete.setString(4, keterangan);

                int affected = psDelete.executeUpdate();
                if (affected > 0) {
                    String updateStok = "UPDATE tb_barang SET stok = stok - ? WHERE id_barang = ?";
                    PreparedStatement psUpdate = conn.prepareStatement(updateStok);
                    psUpdate.setInt(1, jumlah);
                    psUpdate.setInt(2, idBarang);
                    psUpdate.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Riwayat berhasil dihapus dan stok diperbarui.");
                    tampilkanDataKeTabel();
                } else {
                    JOptionPane.showMessageDialog(this, "Data tidak ditemukan atau gagal dihapus.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BarangMasuk::new);
    }
}
