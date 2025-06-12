import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Dashboard2 extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private List<Barang> daftarBarang = new ArrayList<>();
    private List<Barang> hasilPencarian = new ArrayList<>();

    public Dashboard2() {
        setTitle("Manajemen Stok Barang");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header panel
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 105, 180)); // pink
        JLabel title = new JLabel("Manajemen Stok Barang");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        // Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Cari");
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        header.add(searchPanel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Table model and JTable
        model = new DefaultTableModel(new Object[]{"No", "Nama Barang", "Stok", "Harga", "Aksi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(30);

        // Tambahkan data awal
        daftarBarang.add(new Barang(1, "Mawar", 10, 15000));
        daftarBarang.add(new Barang(2, "Tulip", 20, 20000));
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel tombol bawah
        JPanel btnPanel = new JPanel();
        JButton tambahBtn = new JButton("Tambah Barang");
        btnPanel.add(tambahBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // Event tombol
        tambahBtn.addActionListener(e -> tambahBarangDialog());
        searchBtn.addActionListener(e -> cariBarang(searchField.getText()));

        // Menu popup untuk Edit dan Hapus
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem hapusItem = new JMenuItem("Hapus");
        popupMenu.add(editItem);
        popupMenu.add(hapusItem);
        table.setComponentPopupMenu(popupMenu);

        // Event popup menu
        editItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) editBarangDialog(row);
        });

        hapusItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) hapusBarang(row);
        });
    }

    private void refreshTable() {
        model.setRowCount(0);
        hasilPencarian.clear();
        int no = 1;
        for (Barang b : daftarBarang) {
            hasilPencarian.add(b);
            model.addRow(new Object[]{no++, b.getNama(), b.getStok(), String.format("%,d", b.getHarga()), "Edit | Hapus"});
        }
    }

    private void cariBarang(String keyword) {
        model.setRowCount(0);
        hasilPencarian.clear();
        int no = 1;
        for (Barang b : daftarBarang) {
            if (b.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                hasilPencarian.add(b);
                model.addRow(new Object[]{no++, b.getNama(), b.getStok(), String.format("%,d", b.getHarga()), "Edit | Hapus"});
            }
        }
    }

    private void tambahBarangDialog() {
        JTextField namaField = new JTextField();
        JTextField stokField = new JTextField();
        JTextField hargaField = new JTextField();

        Object[] message = {
                "Nama Barang:", namaField,
                "Stok:", stokField,
                "Harga:", hargaField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Tambah Barang", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nama = namaField.getText();
                int stok = Integer.parseInt(stokField.getText());
                int harga = Integer.parseInt(hargaField.getText());

                daftarBarang.add(new Barang(daftarBarang.size() + 1, nama, stok, harga));
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editBarangDialog(int row) {
        Barang b = hasilPencarian.get(row);

        JTextField namaField = new JTextField(b.getNama());
        JTextField stokField = new JTextField(String.valueOf(b.getStok()));
        JTextField hargaField = new JTextField(String.valueOf(b.getHarga()));

        Object[] message = {
                "Nama Barang:", namaField,
                "Stok:", stokField,
                "Harga:", hargaField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Edit Barang", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                b.setNama(namaField.getText());
                b.setStok(Integer.parseInt(stokField.getText()));
                b.setHarga(Integer.parseInt(hargaField.getText()));
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void hapusBarang(int row) {
        Barang b = hasilPencarian.get(row);

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus barang ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            daftarBarang.remove(b);
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard2().setVisible(true));
    }

    // Kelas Barang
    class Barang {
        private int no;
        private String nama;
        private int stok;
        private int harga;

        public Barang(int no, String nama, int stok, int harga) {
            this.no = no;
            this.nama = nama;
            this.stok = stok;
            this.harga = harga;
        }

        public int getNo() { return no; }
        public void setNo(int no) { this.no = no; }
        public String getNama() { return nama; }
        public void setNama(String nama) { this.nama = nama; }
        public int getStok() { return stok; }
        public void setStok(int stok) { this.stok = stok; }
        public int getHarga() { return harga; }
        public void setHarga(int harga) { this.harga = harga; }
    }
}
//