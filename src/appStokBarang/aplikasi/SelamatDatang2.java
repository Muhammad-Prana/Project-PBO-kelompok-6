import javax.swing.*;
import java.awt.*;

public class SelamatDatang2 extends JFrame {

    public SelamatDatang2() {
        setTitle("Manajemen Stok Barang - Welcome");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(255, 105, 180));
        header.setPreferredSize(new Dimension(getWidth(), 50));

        JLabel title = new JLabel("  Manajemen Stok Barang");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 8));
        searchPanel.setBackground(new Color(255, 105, 180));

        JTextField searchField = new JTextField("Cari", 10);
        searchField.setBackground(new Color(255, 182, 193));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton searchButton = new JButton("🔍");
        searchButton.setBackground(new Color(255, 182, 193));
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ===== KONTEN TENGAH =====
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Baris Selamat Datang + Emoji
        JPanel welcomeRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        welcomeRow.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("<html><i>Selamat Datang</i></html>");
        welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 24));
        JLabel sunEmoji = new JLabel("🌞");
        sunEmoji.setFont(new Font("SansSerif", Font.PLAIN, 30));
        welcomeRow.add(welcomeLabel);
        welcomeRow.add(sunEmoji);

        // Tombol Login
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(150, 40));
        loginButton.setBackground(new Color(255, 182, 193));
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Label "Or"
        JLabel orLabel = new JLabel("Or");
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Tombol SignUp
        JButton signupButton = new JButton("SignUp");
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setMaximumSize(new Dimension(150, 40));
        signupButton.setBackground(new Color(255, 182, 193));
        signupButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Tambah komponen ke centerPanel dengan jarak dikurangi
        centerPanel.add(Box.createVerticalStrut(5));        // sedikit spasi atas
        centerPanel.add(welcomeRow);
        centerPanel.add(Box.createVerticalStrut(2));        // jarak SELAMAT DATANG ke tombol login dikurangi
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(5));        // jarak antar tombol
        centerPanel.add(orLabel);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(signupButton);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SelamatDatang2::new);
    }
}
