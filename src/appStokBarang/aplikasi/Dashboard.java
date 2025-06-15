package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Dashboard extends JFrame {

    private JComboBox<String> cbNamaBarang;
    private JTextField tfJumlah, tfTanggal, tfKeterangan;
    private DefaultTableModel model;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_stokbarang";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Dashboard() {
        setTitle("Manajemen Stok Barang - Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(255, 204, 229));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("🌸", JLabel.CENTER);
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
            if (item.equals("Dashboard")) {
                button.setBackground(new Color(255, 153, 204));
            } else {
                button.setBackground(new Color(255, 204, 229));
            }
            button.setBorderPainted(false);

            final String menuName = item;
            button.addActionListener(ev -> {
                switch (menuName) {
                    case "Barang Masuk":
                        new BarangMasuk().setVisible(true);
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
        JButton searchBtn = new JButton("🔍");
        JLabel profileIcon = new JLabel("👤");

        searchPanel.setBackground(new Color(255, 105, 180));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(profileIcon);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel tableTitle = new JLabel("Dashboard", JLabel.CENTER);
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(new Color(255, 204, 229));
        tableTitle.setPreferredSize(new Dimension(100, 50));

        String[] columnNames = {"No", "Nama Barang", "Satuan", "Keterangan"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
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

        contentPanel.add(tableContainer, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        loadDataFromDatabase();
        setVisible(true);
    }

    private void loadDataFromDatabase() {
        model.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT b.id_barang, b.nama_barang, b.satuan, IFNULL(sm.keterangan, '') AS keterangan FROM tb_barang b LEFT JOIN tb_stok_masuk sm ON b.id_barang = sm.id_barang GROUP BY b.id_barang")) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("satuan"),
                        rs.getString("keterangan")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data dari database:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}
