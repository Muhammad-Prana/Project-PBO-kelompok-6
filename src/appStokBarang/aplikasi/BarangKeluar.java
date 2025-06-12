package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

    public class BarangKeluar extends JFrame {

        public BarangKeluar() {
            setTitle("Manajemen Stok Barang");
            setSize(1000, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setPreferredSize(new Dimension(200, getHeight()));
            sidebar.setBackground(new Color(255, 204, 229)); // pink muda
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

            JLabel logo = new JLabel("🌸", JLabel.CENTER);
            logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
            sidebar.add(logo);
            sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

            String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menuItems) {
                JButton button = new JButton(item);
                button.setFocusPainted(false);
                button.setMaximumSize(new Dimension(180, 40));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                if (item.equals("Barang Keluar")) {
                    button.setBackground(new Color(255, 153, 204)); // active
                } else {
                    button.setBackground(new Color(255, 204, 229));
                }
                button.setBorderPainted(false);
                sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
                sidebar.add(button);
            }

            // Header
            JPanel header = new JPanel(new BorderLayout());
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBackground(new Color(255, 105, 180)); // pink tua

            JLabel title = new JLabel("  Manajemen Stok Barang");
            title.setFont(new Font("SansSerif", Font.BOLD, 22));
            title.setForeground(Color.BLACK);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            searchPanel.setBackground(new Color(255, 105, 180));
            JTextField searchField = new JTextField("Cari", 15);
            JButton searchButton = new JButton("🔍");
            JLabel profile = new JLabel("👤");

            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(profile);

            header.add(title, BorderLayout.WEST);
            header.add(searchPanel, BorderLayout.EAST);

            // Tabel Barang Keluar
            String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah"};
            Object[][] data = {
                    {"mei 21, 2025", "Mawar", 5},
                    {"mei 21, 2025", "Tulip", 7}
            };

            JTable table = new JTable(new DefaultTableModel(data, columnNames)) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            table.setRowHeight(35);
            table.setFont(new Font("SansSerif", Font.PLAIN, 16));
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
            table.getTableHeader().setBackground(new Color(238, 174, 238)); // lavender
            table.setGridColor(new Color(230, 230, 250)); // soft grid

            // Center align all cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            JScrollPane scrollPane = new JScrollPane(table);

            JLabel tableTitle = new JLabel("Barang Keluar", JLabel.CENTER);
            tableTitle.setOpaque(true);
            tableTitle.setBackground(new Color(255, 204, 229));
            tableTitle.setPreferredSize(new Dimension(100, 50));
            tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));

            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.setBackground(Color.WHITE);
            tablePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            tablePanel.add(tableTitle, BorderLayout.NORTH);
            tablePanel.add(scrollPane, BorderLayout.CENTER);

            // Add panels to frame
            add(sidebar, BorderLayout.WEST);
            add(header, BorderLayout.NORTH);
            add(tablePanel, BorderLayout.CENTER);

            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(BarangKeluar::new);
        }
    }
