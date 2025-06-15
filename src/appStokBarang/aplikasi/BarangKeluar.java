package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BarangKeluar extends JFrame {
    private JComboBox<String> cbNamaBarang;
    private JTextField tfJumlah, tfTanggal, tfKeterangan;
    private DefaultTableModel tableModel;

    public BarangKeluar() {
        setTitle("Manajemen Stok Barang - Barang Keluar");
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
            button.setBackground(item.equals("Barang Keluar") ? new Color(255, 153, 204) : new Color(255, 204, 229));
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
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.BLACK);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        searchPanel.setBackground(new Color(255, 105, 180));
        JTextField searchField = new JTextField("Cari", 15);
        JButton searchButton = new JButton("\uD83D\uDD0D");
        JLabel profile = new JLabel("\uD83D\uDC64");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(profile);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel tableTitle = new JLabel("Barang Keluar", JLabel.CENTER);
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));

        String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(221, 160, 221));
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        formPanel.setBackground(Color.WHITE);

        cbNamaBarang = new JComboBox<>();
        tfJumlah = new JTextField();
        tfTanggal = new JTextField("2025-06-15");
        tfKeterangan = new JTextField();

        loadNamaBarang();

        JLabel lblNama = new JLabel("Nama Barang", SwingConstants.RIGHT);
        JLabel lblJumlah = new JLabel("Jumlah", SwingConstants.RIGHT);
        JLabel lblTanggal = new JLabel("Tanggal Keluar", SwingConstants.RIGHT);
        JLabel lblKeterangan = new JLabel("Keterangan", SwingConstants.RIGHT);

        formPanel.add(lblNama);
        formPanel.add(cbNamaBarang);
        formPanel.add(lblJumlah);
        formPanel.add(tfJumlah);
        formPanel.add(lblTanggal);
        formPanel.add(tfTanggal);
        formPanel.add(lblKeterangan);
        formPanel.add(tfKeterangan);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(255, 182, 193));
        btnSimpan.addActionListener(e -> simpanDataBarangKeluar());

        formPanel.add(new JLabel());
        formPanel.add(btnSimpan);

        tablePanel.add(formPanel, BorderLayout.SOUTH);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

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

    private void simpanDataBarangKeluar() {
        String namaBarang = (String) cbNamaBarang.getSelectedItem();
        if (namaBarang == null) {
            JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu.");
            return;
        }

        int jumlah;
        String tanggal = tfTanggal.getText().trim();
        String keterangan = tfKeterangan.getText().trim();

        try {
            jumlah = Integer.parseInt(tfJumlah.getText().trim());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "")) {
            String queryId = "SELECT id_barang, stok FROM tb_barang WHERE nama_barang = ?";
            PreparedStatement psId = conn.prepareStatement(queryId);
            psId.setString(1, namaBarang);
            ResultSet rsId = psId.executeQuery();

            if (rsId.next()) {
                int idBarang = rsId.getInt("id_barang");
                int stokSaatIni = rsId.getInt("stok");

                if (jumlah > stokSaatIni) {
                    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
                    return;
                }

                String queryInsert = "INSERT INTO tb_stok_keluar (id_barang, jumlah, tanggal_keluar, keterangan) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(queryInsert);
                psInsert.setInt(1, idBarang);
                psInsert.setInt(2, jumlah);
                psInsert.setString(3, tanggal);
                psInsert.setString(4, keterangan);

                int rows = psInsert.executeUpdate();
                if (rows > 0) {
                    String updateStok = "UPDATE tb_barang SET stok = stok - ? WHERE id_barang = ?";
                    PreparedStatement psUpdate = conn.prepareStatement(updateStok);
                    psUpdate.setInt(1, jumlah);
                    psUpdate.setInt(2, idBarang);
                    psUpdate.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                    tampilkanDataKeTabel();
                    tfJumlah.setText("");
                    tfTanggal.setText("2025-06-15");
                    tfKeterangan.setText("");
                    cbNamaBarang.setSelectedIndex(0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Barang tidak ditemukan.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage());
        }
    }

    private void tampilkanDataKeTabel() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_stokbarang", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT sk.tanggal_keluar, b.nama_barang, sk.jumlah, sk.keterangan FROM tb_stok_keluar sk JOIN tb_barang b ON sk.id_barang = b.id_barang ORDER BY sk.id_keluar DESC")) {
            while (rs.next()) {
                Object[] row = {
                        rs.getString("tanggal_keluar"),
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BarangKeluar::new);
    }
}
