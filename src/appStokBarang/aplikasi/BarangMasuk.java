package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

    public class BarangMasuk extends JFrame {

        public BarangMasuk() {
            setTitle("Manajemen Stok Barang");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Panel Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setPreferredSize(new Dimension(200, getHeight()));
            sidebar.setBackground(new Color(255, 204, 229)); // Pink muda
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

            JLabel logo = new JLabel("🌸", JLabel.CENTER);
            logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menuItems) {
                JButton button = new JButton(item);
                button.setFocusPainted(false);
                button.setMaximumSize(new Dimension(180, 40));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                if (item.equals("Barang Masuk")) {
                    button.setBackground(new Color(255, 153, 204)); // Pink highlight
                } else {
                    button.setBackground(new Color(255, 204, 229));
                }
                button.setBorderPainted(false);
                sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
                sidebar.add(button);
            }

            sidebar.add(Box.createVerticalGlue());

            // Panel Header
            JPanel header = new JPanel(new BorderLayout());
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBackground(new Color(255, 105, 180)); // Pink tua

            JLabel title = new JLabel("  Manajemen Stok Barang");
            title.setFont(new Font("SansSerif", Font.BOLD, 24));
            title.setForeground(Color.BLACK);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            JTextField searchField = new JTextField("Cari", 15);
            JButton searchBtn = new JButton("🔍");
            JLabel profileIcon = new JLabel("👤");

            searchPanel.setBackground(new Color(255, 105, 180));
            searchPanel.add(searchField);
            searchPanel.add(searchBtn);
            searchPanel.add(profileIcon);

            header.add(title, BorderLayout.WEST);
            header.add(searchPanel, BorderLayout.EAST);

            // Panel Konten (tabel barang masuk)
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(Color.WHITE);

            JLabel tableTitle = new JLabel("Barang Masuk", JLabel.CENTER);
            tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
            tableTitle.setOpaque(true);
            tableTitle.setBackground(new Color(255, 204, 229));
            tableTitle.setPreferredSize(new Dimension(100, 50));

            String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah"};
            Object[][] data = {
                    {"mei 20, 2025", "Mawar", 15},
                    {"mei 20, 2025", "Tulip", 27}
            };

            JTable table = new JTable(new DefaultTableModel(data, columnNames));
            table.setRowHeight(30);
            table.setFont(new Font("SansSerif", Font.PLAIN, 16));
            table.getTableHeader().setBackground(new Color(221, 160, 221)); // ungu muda
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
            JScrollPane scrollPane = new JScrollPane(table);

            JPanel tableContainer = new JPanel(new BorderLayout());
            tableContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            tableContainer.add(tableTitle, BorderLayout.NORTH);
            tableContainer.add(scrollPane, BorderLayout.CENTER);
            tableContainer.setBackground(Color.WHITE);

            contentPanel.add(tableContainer, BorderLayout.CENTER);

            // Menambahkan semua panel ke frame
            add(sidebar, BorderLayout.WEST);
            add(header, BorderLayout.NORTH);
            add(contentPanel, BorderLayout.CENTER);

            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(BarangMasuk::new);
        }
    }
