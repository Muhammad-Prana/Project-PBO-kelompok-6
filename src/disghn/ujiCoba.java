package disghn;

import javax.swing.*;
import java.awt.*;

public class ujiCoba extends JFrame {
    public ujiCoba() {
        setTitle("Manajemen Stok Bucket Bunga 🌸");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("📦 Aplikasi Stok Bucket Bunga", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        header.setForeground(new Color(219, 112, 147));
        add(header, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabbedPane.setBackground(new Color(255, 228, 240));

        tabbedPane.addTab("🛬 Barang Masuk", createFormPanel("Barang Masuk"));
        tabbedPane.addTab("📦 Barang Disimpan", createFormPanel("Barang Disimpan"));
        tabbedPane.addTab("📤 Barang Keluar", createFormPanel("Barang Keluar"));

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createFormPanel(String jenis) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        form.setBackground(new Color(255, 240, 245));

        form.add(new JLabel("🌸 Nama Barang:"));
        JTextField tfNama = new JTextField();
        form.add(tfNama);

        form.add(new JLabel("🏷️ Kategori:"));
        JTextField tfKategori = new JTextField();
        form.add(tfKategori);

        form.add(new JLabel("🔢 Jumlah:"));
        JTextField tfJumlah = new JTextField();
        form.add(tfJumlah);

        form.add(new JLabel("🏪 Suplier / Petugas:"));
        JTextField tfSuplier = new JTextField();
        form.add(tfSuplier);

        form.add(new JLabel("📅 Tanggal:"));
        JTextField tfTanggal = new JTextField();
        form.add(tfTanggal);

        form.add(new JLabel("📍 Lokasi / Tujuan:"));
        JTextField tfLokasi = new JTextField();
        form.add(tfLokasi);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(255, 240, 245));

        JButton btnSimpan = new JButton("💾 Simpan Data");
        JButton btnLihat = new JButton("👀 Lihat Data");
        JTextArea outputArea = new JTextArea(8, 40);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(255, 250, 250));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.PINK));

        btnSimpan.setBackground(new Color(255, 182, 193));
        btnLihat.setBackground(new Color(255, 192, 203));

        btnPanel.add(btnSimpan);
        btnPanel.add(btnLihat);

        btnSimpan.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Data berhasil disimpan!"));

        btnLihat.addActionListener(e -> {
            String result = "📋 Data " + jenis + ":\n" +
                    "Nama Barang: " + tfNama.getText() + "\n" +
                    "Kategori: " + tfKategori.getText() + "\n" +
                    "Jumlah: " + tfJumlah.getText() + "\n" +
                    "Suplier/Petugas: " + tfSuplier.getText() + "\n" +
                    "Tanggal: " + tfTanggal.getText() + "\n" +
                    "Lokasi/Tujuan: " + tfLokasi.getText();
            outputArea.setText(result);
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ujiCoba::new);
    }
}
