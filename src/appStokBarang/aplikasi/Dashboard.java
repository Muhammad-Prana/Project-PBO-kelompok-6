package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

    public class Dashboard extends JFrame {

        public Dashboard() {
            setTitle("Manajemen Stok Barang");
            setSize(1000, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);

            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setBackground(new Color(255, 204, 229));
            sidebar.setBounds(0, 0, 220, 600);
            sidebar.setLayout(null);

            JLabel logo = new JLabel("🌸");
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
                sidebar.add(btn);
                y += 50;
            }

            add(sidebar);

            // Header
            JPanel header = new JPanel();
            header.setBackground(new Color(255, 105, 180));
            header.setBounds(220, 0, 780, 60);
            header.setLayout(null);

            JLabel title = new JLabel("Manajemen Stok Barang");
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setBounds(20, 10, 300, 40);
            header.add(title);

            JTextField search = new JTextField("Cari");
            search.setBounds(400, 15, 200, 30);
            header.add(search);

            JLabel profileIcon = new JLabel("👤");
            profileIcon.setFont(new Font("Arial", Font.PLAIN, 28));
            profileIcon.setBounds(700, 10, 40, 40);
            header.add(profileIcon);

            add(header);

            // Tombol Tambah Barang
            JButton btnTambah = new JButton("Tambah Barang");
            btnTambah.setBounds(400, 80, 160, 40);
            btnTambah.setBackground(new Color(255, 204, 229));
            add(btnTambah);

            // Tabel Barang
            String[] columnNames = {"No", "Nama Barang", "Stok", "Harga", "Aksi"};
            Object[][] data = {
                    {"1", "Mawar", "10", "15.000", ""},
                    {"2", "Tulip", "20", "20.000", ""}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(250, 140, 700, 300);
            add(scrollPane);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new Dashboard().setVisible(true);
            });
        }
    }
//