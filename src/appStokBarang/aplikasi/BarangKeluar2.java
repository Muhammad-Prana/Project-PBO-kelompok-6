package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

    public class BarangKeluar2 extends JFrame {

        public BarangKeluar2() {
            setTitle("Manajemen Stok Barang - Barang Keluar");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 500);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Warna pink sesuai gambar
            Color pinkDark = new Color(255, 105, 180);
            Color pinkLight = new Color(255, 182, 193);
            Color pinkLighter = new Color(255, 192, 203);
            Color pinkSidebar = new Color(255, 182, 193);
            Color pinkSidebarHighlight = new Color(255, 105, 180);

            // Sidebar panel
            JPanel sidebar = new JPanel();
            sidebar.setBackground(pinkSidebar);
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
            sidebar.setPreferredSize(new Dimension(180, getHeight()));

            // Logo flower icon (simple circle + petal shapes)
            JLabel logo = new JLabel("\uD83C\uDF3A"); // emoji bunga sebagai placeholder
            logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
            logo.setForeground(pinkDark);
            logo.setBorder(new EmptyBorder(20, 20, 20, 20));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidebar.add(logo);

            // Menu items
            String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menuItems) {
                JLabel label = new JLabel(item);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                label.setForeground(Color.BLACK);
                label.setOpaque(true);
                label.setBorder(new EmptyBorder(10, 20, 10, 20));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                if (item.equals("Barang Keluar")) {
                    label.setBackground(pinkSidebarHighlight);
                    label.setForeground(Color.WHITE);
                }
                sidebar.add(label);
            }

            add(sidebar, BorderLayout.WEST);

            // Header panel (pink bar with title and search + user icon)
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(pinkDark);
            header.setPreferredSize(new Dimension(getWidth(), 60));
            header.setBorder(new EmptyBorder(10, 20, 10, 20));

            JLabel title = new JLabel("Manajemen Stok Barang");
            title.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
            title.setForeground(Color.WHITE);
            header.add(title, BorderLayout.WEST);

            // Search panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            searchPanel.setOpaque(false);

            JTextField searchField = new JTextField(15);
            searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            searchField.setPreferredSize(new Dimension(150, 30));
            searchPanel.add(searchField);

            JButton searchButton = new JButton("\uD83D\uDD0D"); // icon kaca pembesar
            searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            searchButton.setBackground(pinkLight);
            searchButton.setFocusPainted(false);
            searchPanel.add(searchButton);

            // User icon (circle with user silhouette)
            JLabel userIcon = new JLabel("\uD83D\uDC64"); // emoji user sebagai placeholder
            userIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
            userIcon.setOpaque(true);
            userIcon.setBackground(pinkLight);
            userIcon.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            searchPanel.add(userIcon);

            header.add(searchPanel, BorderLayout.EAST);

            add(header, BorderLayout.NORTH);

            // Main content panel for Barang Keluar
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

            // Title panel
            JPanel titlePanel = new JPanel();
            titlePanel.setBackground(pinkLight);
            titlePanel.setPreferredSize(new Dimension(getWidth(), 50));
            JLabel mainTitle = new JLabel("Barang Keluar");
            mainTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
            mainTitle.setForeground(Color.BLACK);
            titlePanel.add(mainTitle);
            mainPanel.add(titlePanel, BorderLayout.NORTH);

            // Table data
            String[] columnNames = {"Tanggal", "Nama Barang", "Jumlah"};
            Object[][] data = {
                    {"mei 21, 2025", "Mawar", 5},
                    {"mei 21, 2025", "Tulip", 7}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                // Membuat kolom tidak bisa diedit langsung
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable table = new JTable(model);
            table.setRowHeight(30);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
            table.getTableHeader().setBackground(pinkLight);
            table.getTableHeader().setForeground(Color.BLACK);
            table.setGridColor(pinkLight);

            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            add(mainPanel, BorderLayout.CENTER);
        }

        public static void main(String[] args) {
            // Set look and feel ke system default agar lebih bagus
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            SwingUtilities.invokeLater(() -> {
                BarangKeluar2 app = new BarangKeluar2();
                app.setVisible(true);
            });
        }
    }
