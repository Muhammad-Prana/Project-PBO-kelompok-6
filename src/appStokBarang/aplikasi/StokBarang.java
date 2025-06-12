import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class StokBarang extends JFrame {

    private DefaultTableModel tableModel;

    public StokBarang() {
        setTitle("Manajemen Stok Barang");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(255, 204, 229));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JLabel logo = new JLabel("🌸", SwingConstants.CENTER);
        logo.setFont(new Font("SansSerif", Font.PLAIN, 48));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);

        String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(item.equals("Stok Barang") ? new Color(255, 153, 204) : new Color(255, 204, 229));
            sidebar.add(Box.createVerticalStrut(10));
            sidebar.add(btn);
        }

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(255, 102, 178));
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setLayout(new BorderLayout(10, 0));

        JLabel title = new JLabel("   Manajemen Stok Barang");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(header.getBackground());
        JTextField searchField = new JTextField("Cari", 15);
        JButton searchButton = new JButton("🔍");
        JButton profileButton = new JButton("👤");
        profileButton.setPreferredSize(new Dimension(40, 40));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(profileButton);
        header.add(searchPanel, BorderLayout.EAST);

        // Konten
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        JLabel stokLabel = new JLabel("Stok Barang", SwingConstants.CENTER);
        stokLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        stokLabel.setOpaque(true);
        stokLabel.setBackground(new Color(255, 204, 229));
        stokLabel.setPreferredSize(new Dimension(getWidth(), 50));
        content.add(stokLabel, BorderLayout.NORTH);

        // Tabel stok barang
        String[] columns = {"Nama Barang", "Stok", "+", "-"};
        Object[][] data = {
                {"Mawar", 10},
                {"Tulip", 20}
        };

        tableModel = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }

            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 1) ? Integer.class : Object.class;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(40);

        // Tambahkan tombol ke kolom "+"
        table.getColumn("+").setCellRenderer(new ButtonRenderer("+"));
        table.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox(), true));

        // Tambahkan tombol ke kolom "−"
        table.getColumn("-").setCellRenderer(new ButtonRenderer("−"));
        table.getColumn("-").setCellEditor(new ButtonEditor(new JCheckBox(), false));

        JScrollPane scrollPane = new JScrollPane(table);
        content.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan ke frame
        add(sidebar, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    // Renderer tombol terlihat seperti label
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setForeground(Color.BLACK);
            setFont(new Font("SansSerif", Font.BOLD, 18));
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor untuk klik tombol
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean isPlus;
        private int row;

        public ButtonEditor(JCheckBox checkBox, boolean isPlus) {
            super(checkBox);
            this.isPlus = isPlus;
            button = new JButton(isPlus ? "+" : "−");
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setForeground(Color.BLACK);
            button.setFont(new Font("SansSerif", Font.BOLD, 18));
            button.addActionListener(e -> {
                int currentValue = (int) tableModel.getValueAt(row, 1);
                if (isPlus) {
                    tableModel.setValueAt(currentValue + 1, row, 1);
                } else {
                    if (currentValue > 0) {
                        tableModel.setValueAt(currentValue - 1, row, 1);
                    }
                }
                fireEditingStopped();
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            return button;
        }

        public Object getCellEditorValue() {
            return button.getText();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StokBarang::new);
    }
}
