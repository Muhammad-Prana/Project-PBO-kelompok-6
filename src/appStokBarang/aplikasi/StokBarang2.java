package appStokBarang.aplikasi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class StokBarang2 extends JFrame {
    private List<Barang> daftarBarang = new ArrayList<>();
    private BarangTableModel tableModel;

    public StokBarang2() {
        setTitle("Manajemen Stok Barang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        daftarBarang.add(new Barang("Mawar", 10));
        daftarBarang.add(new Barang("Tulip", 20));

        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);

        JPanel mainContent = createMainContent();
        add(mainContent, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(255, 182, 193));
        sidebar.setPreferredSize(new Dimension(150, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        String[] menuItems = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
        for (String item : menuItems) {
            JLabel label = new JLabel(item);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(new EmptyBorder(10, 10, 10, 10));
            if (item.equals("Stok Barang")) {
                label.setOpaque(true);
                label.setBackground(new Color(255, 182, 193));
            }
            sidebar.add(label);
        }
        return sidebar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 105, 180));
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Manajemen Stok Barang");
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30));
        searchField.setToolTipText("Cari");
        JButton searchButton = new JButton("\uD83D\uDD0D");
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchButton.setFocusPainted(false);
        searchButton.setBackground(new Color(255, 182, 193));
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JLabel userIcon = new JLabel("\uD83D\uDC64", SwingConstants.CENTER);
        userIcon.setPreferredSize(new Dimension(40, 40));
        userIcon.setOpaque(true);
        userIcon.setBackground(new Color(186, 85, 211));
        userIcon.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        userIcon.setForeground(Color.WHITE);
        userIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(searchPanel);
        rightPanel.add(userIcon);
        header.add(rightPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Stok Barang", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(255, 182, 193));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        tableModel = new BarangTableModel(daftarBarang);
        JTable table = new JTable(tableModel);
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(1).setCellRenderer(new StokCellRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new StokCellEditor());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 182, 193), 2));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    static class Barang {
        private String nama;
        private int stok;
        public Barang(String nama, int stok) {
            this.nama = nama;
            this.stok = stok;
        }
        public String getNama() { return nama; }
        public int getStok() { return stok; }
        public void setStok(int stok) { this.stok = stok; }
    }

    class BarangTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Nama Barang", "Stok"};
        private List<Barang> data;
        public BarangTableModel(List<Barang> data) {
            this.data = data;
        }
        public int getRowCount() { return data.size(); }
        public int getColumnCount() { return columnNames.length; }
        public String getColumnName(int col) { return columnNames[col]; }
        public Object getValueAt(int row, int col) {
            Barang barang = data.get(row);
            return col == 0 ? barang.getNama() : barang.getStok();
        }
        public boolean isCellEditable(int row, int col) {
            return col == 1;
        }
        public void setValueAt(Object value, int row, int col) {
            if (col == 1 && value instanceof Integer) {
                data.get(row).setStok((Integer) value);
                fireTableCellUpdated(row, col);
            }
        }
    }

    class StokCellRenderer extends JPanel implements TableCellRenderer {
        private JLabel stokLabel;
        private JButton plusButton, minusButton;
        public StokCellRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            stokLabel = new JLabel();
            stokLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            plusButton = new JButton("+");
            minusButton = new JButton("−");
            plusButton.setEnabled(false);
            minusButton.setEnabled(false);
            plusButton.setFocusable(false);
            minusButton.setFocusable(false);
            plusButton.setBorderPainted(false);
            minusButton.setBorderPainted(false);
            plusButton.setContentAreaFilled(false);
            minusButton.setContentAreaFilled(false);
            add(minusButton);
            add(stokLabel);
            add(plusButton);
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            stokLabel.setText(String.valueOf(value));
            setBackground(row % 2 == 0 ? new Color(255, 228, 225) : new Color(255, 240, 245));
            return this;
        }
    }

    class StokCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private JLabel stokLabel;
        private JButton plusButton, minusButton;
        private int currentValue;
        private int currentRow;
        public StokCellEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            stokLabel = new JLabel();
            stokLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            plusButton = new JButton("+");
            minusButton = new JButton("−");
            plusButton.setFocusable(false);
            minusButton.setFocusable(false);
            plusButton.setBorderPainted(false);
            minusButton.setBorderPainted(false);
            plusButton.setContentAreaFilled(false);
            minusButton.setContentAreaFilled(false);
            plusButton.addActionListener(e -> {
                currentValue++;
                stokLabel.setText(String.valueOf(currentValue));
                tableModel.setValueAt(currentValue, currentRow, 1);
            });
            minusButton.addActionListener(e -> {
                if (currentValue > 0) {
                    currentValue--;
                    stokLabel.setText(String.valueOf(currentValue));
                    tableModel.setValueAt(currentValue, currentRow, 1);
                }
            });
            panel.add(minusButton);
            panel.add(stokLabel);
            panel.add(plusButton);
        }
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentValue = (int) value;
            currentRow = row;
            stokLabel.setText(String.valueOf(currentValue));
            panel.setBackground(row % 2 == 0 ? new Color(255, 228, 225) : new Color(255, 240, 245));
            return panel;
        }
        public Object getCellEditorValue() {
            return currentValue;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StokBarang2().setVisible(true));
    }
}
//