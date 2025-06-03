package disghn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class ujiCoba extends JFrame {
    private ArrayList<String[]> masukList = new ArrayList<>();
    private ArrayList<String[]> keluarList = new ArrayList<>();

    private JTextArea outputAreaMasuk, outputAreaKeluar;
    private JLabel summaryLabel;
    private JTextField tfNama, tfKategori, tfJumlah, tfSuplier, tfTanggal, tfLokasi;
    private JTextField tfNamaKeluar, tfJumlahKeluar, tfTujuanKeluar, tfTanggalKeluar;
    private JTextField tfSearchMasuk, tfSearchKeluar;

    public ujiCoba() {
        setTitle("Manajemen Stok Bucket Bunga 🌸");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER DENGAN LOGO
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);

        // Pastikan file logo.png tersedia di folder proyek
        ImageIcon icon = new ImageIcon(getClass().getResource("/disghn/logo.jpg"));
        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));

        JLabel header = new JLabel("Aplikasi Stok Bucket Bunga");
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(new Color(219, 112, 147));
        header.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        headerPanel.add(iconLabel);
        headerPanel.add(header);
        add(headerPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabbedPane.setBackground(new Color(255, 228, 240));

        tabbedPane.addTab("🛬 Barang Masuk", createFormPanelMasuk());
        tabbedPane.addTab("🚚 Barang Keluar", createFormPanelKeluar());
        tabbedPane.addTab("📋 Ringkasan", createSummaryPanel());

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createFormPanelMasuk() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        form.setBackground(new Color(255, 240, 245));

        tfNama = new JTextField(); tfNama.setToolTipText("Masukkan nama barang...");
        tfKategori = new JTextField(); tfKategori.setToolTipText("Masukkan kategori barang...");
        tfJumlah = new JTextField(); tfJumlah.setToolTipText("Masukkan jumlah barang...");
        tfSuplier = new JTextField(); tfSuplier.setToolTipText("Masukkan nama suplier atau petugas...");
        tfTanggal = new JTextField(); tfTanggal.setToolTipText("Masukkan tanggal (format bebas)...");
        tfLokasi = new JTextField(); tfLokasi.setToolTipText("Masukkan lokasi penyimpanan...");

        form.add(new JLabel("🌸 Nama Barang:")); form.add(tfNama);
        form.add(new JLabel("🏷️ Kategori:")); form.add(tfKategori);
        form.add(new JLabel("🔢 Jumlah:")); form.add(tfJumlah);
        form.add(new JLabel("🏪 Suplier / Petugas:")); form.add(tfSuplier);
        form.add(new JLabel("📅 Tanggal:")); form.add(tfTanggal);
        form.add(new JLabel("📍 Lokasi:")); form.add(tfLokasi);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(255, 240, 245));

        JButton btnSimpan = new JButton("💾 Simpan Data");
        JButton btnLihat = new JButton("👀 Lihat Data");
        JButton btnCari = new JButton("🔍 Cari");

        btnSimpan.setBackground(new Color(255, 182, 193));
        btnLihat.setBackground(new Color(255, 192, 203));
        btnCari.setBackground(new Color(255, 182, 193));

        tfSearchMasuk = new JTextField(15);
        tfSearchMasuk.setToolTipText("Cari nama barang masuk...");

        btnSimpan.addActionListener(e -> simpanDataMasuk());
        btnLihat.addActionListener(e -> tampilkanDataMasuk());
        btnCari.addActionListener(e -> cariBarangMasuk());

        btnPanel.add(btnSimpan);
        btnPanel.add(btnLihat);
        btnPanel.add(tfSearchMasuk);
        btnPanel.add(btnCari);

        outputAreaMasuk = new JTextArea(8, 40);
        outputAreaMasuk.setEditable(false);
        outputAreaMasuk.setBackground(new Color(255, 250, 250));
        outputAreaMasuk.setBorder(BorderFactory.createLineBorder(Color.PINK));

        panel.add(form, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputAreaMasuk), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFormPanelKeluar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        form.setBackground(new Color(255, 240, 245));

        tfNamaKeluar = new JTextField(); tfNamaKeluar.setToolTipText("Masukkan nama barang keluar...");
        tfJumlahKeluar = new JTextField(); tfJumlahKeluar.setToolTipText("Masukkan jumlah barang keluar...");
        tfTujuanKeluar = new JTextField(); tfTujuanKeluar.setToolTipText("Masukkan tujuan pengiriman...");
        tfTanggalKeluar = new JTextField(); tfTanggalKeluar.setToolTipText("Masukkan tanggal keluar...");

        form.add(new JLabel("🌸 Nama Barang:")); form.add(tfNamaKeluar);
        form.add(new JLabel("🔢 Jumlah:")); form.add(tfJumlahKeluar);
        form.add(new JLabel("🚚 Tujuan:")); form.add(tfTujuanKeluar);
        form.add(new JLabel("📅 Tanggal:")); form.add(tfTanggalKeluar);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(255, 240, 245));

        JButton btnSimpan = new JButton("💾 Simpan Data Keluar");
        JButton btnLihat = new JButton("👀 Lihat Data Keluar");
        JButton btnCari = new JButton("🔍 Cari");

        btnSimpan.setBackground(new Color(255, 182, 193));
        btnLihat.setBackground(new Color(255, 192, 203));
        btnCari.setBackground(new Color(255, 182, 193));

        tfSearchKeluar = new JTextField(15);
        tfSearchKeluar.setToolTipText("Cari nama barang keluar...");

        btnSimpan.addActionListener(e -> simpanDataKeluar());
        btnLihat.addActionListener(e -> tampilkanDataKeluar());
        btnCari.addActionListener(e -> cariBarangKeluar());

        btnPanel.add(btnSimpan);
        btnPanel.add(btnLihat);
        btnPanel.add(tfSearchKeluar);
        btnPanel.add(btnCari);

        outputAreaKeluar = new JTextArea(8, 40);
        outputAreaKeluar.setEditable(false);
        outputAreaKeluar.setBackground(new Color(255, 250, 250));
        outputAreaKeluar.setBorder(BorderFactory.createLineBorder(Color.PINK));

        panel.add(form, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputAreaKeluar), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        summaryLabel = new JLabel("📊 Ringkasan: Total Masuk = 0 | Total Keluar = 0 | Stok Akhir = 0", JLabel.CENTER);
        summaryLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        summaryLabel.setForeground(new Color(199, 21, 133));

        panel.add(summaryLabel, BorderLayout.NORTH);
        return panel;
    }

    private void simpanDataMasuk() {
        try {
            String[] data = {
                    tfNama.getText(), tfKategori.getText(), tfJumlah.getText(),
                    tfSuplier.getText(), tfTanggal.getText(), tfLokasi.getText()
            };
            masukList.add(data);
            try (PrintWriter pw = new PrintWriter(new FileWriter("stok-masuk.txt", true))) {
                pw.println(String.join(",", data));
            }
            JOptionPane.showMessageDialog(this, "✅ Data Barang Masuk berhasil disimpan!");
            updateSummary();
            clearFieldsMasuk();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Gagal menyimpan data Barang Masuk!");
        }
    }

    private void simpanDataKeluar() {
        try {
            String[] data = {
                    tfNamaKeluar.getText(), tfJumlahKeluar.getText(),
                    tfTujuanKeluar.getText(), tfTanggalKeluar.getText()
            };
            keluarList.add(data);
            try (PrintWriter pw = new PrintWriter(new FileWriter("stok-keluar.txt", true))) {
                pw.println(String.join(",", data));
            }
            JOptionPane.showMessageDialog(this, "✅ Data Barang Keluar berhasil disimpan!");
            updateSummary();
            clearFieldsKeluar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Gagal menyimpan data Barang Keluar!");
        }
    }

    private void tampilkanDataMasuk() {
        StringBuilder sb = new StringBuilder("📋 Data Barang Masuk:\n");
        for (String[] data : masukList) {
            sb.append("Nama: ").append(data[0])
                    .append(", Kategori: ").append(data[1])
                    .append(", Jumlah: ").append(data[2])
                    .append(", Suplier: ").append(data[3])
                    .append(", Tanggal: ").append(data[4])
                    .append(", Lokasi: ").append(data[5])
                    .append("\n");
        }
        outputAreaMasuk.setText(sb.toString());
    }

    private void tampilkanDataKeluar() {
        StringBuilder sb = new StringBuilder("📋 Data Barang Keluar:\n");
        for (String[] data : keluarList) {
            sb.append("Nama: ").append(data[0])
                    .append(", Jumlah: ").append(data[1])
                    .append(", Tujuan: ").append(data[2])
                    .append(", Tanggal: ").append(data[3])
                    .append("\n");
        }
        outputAreaKeluar.setText(sb.toString());
    }

    private void cariBarangMasuk() {
        String keyword = tfSearchMasuk.getText().toLowerCase();
        StringBuilder sb = new StringBuilder("🔍 Hasil Pencarian Barang Masuk:\n");
        for (String[] data : masukList) {
            if (data[0].toLowerCase().contains(keyword)) {
                sb.append("Nama: ").append(data[0])
                        .append(", Kategori: ").append(data[1])
                        .append(", Jumlah: ").append(data[2])
                        .append(", Suplier: ").append(data[3])
                        .append(", Tanggal: ").append(data[4])
                        .append(", Lokasi: ").append(data[5])
                        .append("\n");
            }
        }
        outputAreaMasuk.setText(sb.toString());
    }

    private void cariBarangKeluar() {
        String keyword = tfSearchKeluar.getText().toLowerCase();
        StringBuilder sb = new StringBuilder("🔍 Hasil Pencarian Barang Keluar:\n");
        for (String[] data : keluarList) {
            if (data[0].toLowerCase().contains(keyword)) {
                sb.append("Nama: ").append(data[0])
                        .append(", Jumlah: ").append(data[1])
                        .append(", Tujuan: ").append(data[2])
                        .append(", Tanggal: ").append(data[3])
                        .append("\n");
            }
        }
        outputAreaKeluar.setText(sb.toString());
    }

    private void updateSummary() {
        int totalMasuk = 0;
        int totalKeluar = 0;

        for (String[] d : masukList) {
            try {
                totalMasuk += Integer.parseInt(d[2]);
            } catch (NumberFormatException ignored) {}
        }

        for (String[] d : keluarList) {
            try {
                totalKeluar += Integer.parseInt(d[1]);
            } catch (NumberFormatException ignored) {}
        }

        int stokAkhir = totalMasuk - totalKeluar;
        if (stokAkhir < 0) stokAkhir = 0;

        summaryLabel.setText(String.format("📊 Ringkasan: Total Masuk = %d | Total Keluar = %d | Stok Akhir = %d",
                totalMasuk, totalKeluar, stokAkhir));
    }

    private void clearFieldsMasuk() {
        tfNama.setText("");
        tfKategori.setText("");
        tfJumlah.setText("");
        tfSuplier.setText("");
        tfTanggal.setText("");
        tfLokasi.setText("");
    }

    private void clearFieldsKeluar() {
        tfNamaKeluar.setText("");
        tfJumlahKeluar.setText("");
        tfTujuanKeluar.setText("");
        tfTanggalKeluar.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ujiCoba::new);
    }
}
