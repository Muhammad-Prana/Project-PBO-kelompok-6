import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {
        setTitle("Manajemen Stok Barang");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Header =====
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBackground(new Color(255, 105, 180)); // pink terang

        JLabel title = new JLabel("  Manajemen Stok Barang");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        searchPanel.setBackground(new Color(255, 105, 180));
        JTextField searchField = new JTextField(10);
        searchField.setBackground(new Color(255, 182, 193));
        JButton searchButton = new JButton("🔍");
        searchButton.setBackground(new Color(255, 182, 193));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        header.add(title, BorderLayout.WEST);
        header.add(searchPanel, BorderLayout.EAST);

        // ===== Form Login =====
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        usernameField.setBackground(new Color(255, 182, 193));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBackground(new Color(255, 182, 193));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(150, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(255, 182, 193));
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Tambahkan ke panel
        loginPanel.add(Box.createVerticalStrut(50));
        loginPanel.add(userLabel);
        loginPanel.add(usernameField);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(passLabel);
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(loginButton);

        add(header, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
//