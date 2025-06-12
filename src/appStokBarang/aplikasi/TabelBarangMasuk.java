package appStokBarang.aplikasi;

import javax.swing.*;
import java.awt.*;

    public class TabelBarangMasuk extends JFrame {
        public TabelBarangMasuk() {
            setTitle("Manajemen Stok Barang");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(900, 600);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setBackground(new Color(255, 192, 203));
            sidebar.setPreferredSize(new Dimension(220, getHeight()));
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

            String[] menu = {"Dashboard", "Stok Barang", "Barang Masuk", "Barang Keluar", "Pengaturan"};
            for (String item : menu) {
                JLabel label = new JLabel(item);
                label.setFont(new Font("Arial", Font.PLAIN, 22));
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 10));
                if (item.equals("Barang Masuk")) {
                    label.setBackground(new Color(255, 182, 193));
                } else {
                    label.setBackground(new Color(255, 192, 203));
                }
                sidebar.add(label);
            }

            // Header
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(new Color(255, 105, 180));
            header.setPreferredSize(new Dimension(getWidth(), 70));

            JLabel title = new JLabel("Manajemen Stok Barang");
            title.setFont(new Font("Arial Black", Font.BOLD, 32));
            title.setForeground(Color.BLACK);
            title.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
            header.add(title, BorderLayout.WEST);

            // Search bar
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 18));
            searchPanel.setOpaque(false);
            JTextField searchField = new JTextField("Cari", 12);
            searchField.setFont(new Font("Arial", Font.PLAIN, 18));
            searchField.setBackground(new Color(255, 228, 240));
            searchField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            searchPanel.add(searchField);
            JLabel searchIcon = new JLabel("\uD83D\uDD0D"); // Unicode magnifier
            searchIcon.setFont(new Font("Arial", Font.PLAIN, 22));
            searchPanel.add(searchIcon);

            // User icon
            JLabel userIcon = new JLabel("\uD83D\uDC64");
            userIcon.setFont(new Font("Arial", Font.PLAIN, 38));
            userIcon.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            searchPanel.add(userIcon);

            header.add(searchPanel, BorderLayout.EAST);

            // Main form panel
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(18, 0, 0, 0);
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Form fields
            JTextField namaBarang = createInputField("Nama Barang");
            JTextField jumlah = createInputField("Jumlah");
            JTextField harga = createInputField("Harga");
            JTextField tanggal = createInputField("Tanggal");

            gbc.gridy = 0; mainPanel.add(namaBarang, gbc);
            gbc.gridy = 1; mainPanel.add(jumlah, gbc);
            gbc.gridy = 2; mainPanel.add(harga, gbc);
            gbc.gridy = 3; mainPanel.add(tanggal, gbc);

            // Tambahkan button
            gbc.gridy = 4;
            JButton tambahBtn = new JButton("Tambahkan");
            tambahBtn.setFont(new Font("Arial", Font.BOLD, 22));
            tambahBtn.setBackground(new Color(255, 182, 193));
            tambahBtn.setForeground(Color.BLACK);
            tambahBtn.setFocusPainted(false);
            tambahBtn.setBorder(BorderFactory.createEmptyBorder(12, 60, 12, 60));
            tambahBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // Shadow effect
            tambahBtn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 6, 0, new Color(200, 180, 190)),
                    tambahBtn.getBorder()
            ));
            gbc.insets = new Insets(30, 0, 0, 0);
            mainPanel.add(tambahBtn, gbc);

            // Layout
            add(sidebar, BorderLayout.WEST);
            add(header, BorderLayout.NORTH);
            add(mainPanel, BorderLayout.CENTER);
        }

        private JTextField createInputField(String placeholder) {
            JTextField field = new JTextField();
            field.setFont(new Font("Arial", Font.PLAIN, 22));
            field.setPreferredSize(new Dimension(400, 45));
            field.setBackground(new Color(245, 245, 245));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, 6, 0),
                    BorderFactory.createEmptyBorder(0, 20, 0, 0)
            ));
            field.setText(placeholder);
            field.setForeground(Color.GRAY);

            // Placeholder effect
            field.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (field.getText().equals(placeholder)) {
                        field.setText("");
                        field.setForeground(Color.BLACK);
                    }
                }
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (field.getText().isEmpty()) {
                        field.setForeground(Color.GRAY);
                        field.setText(placeholder);
                    }
                }
            });
            return field;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new TabelBarangMasuk().setVisible(true);
            });
        }
    }
