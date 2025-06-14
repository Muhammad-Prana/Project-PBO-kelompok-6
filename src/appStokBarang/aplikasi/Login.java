import appStokBarang.koneksi.koneksiDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        JLabel title = new JLabel("Manajemen Stok Barang", SwingConstants.CENTER); // tambahkan CENTER alignment
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);

        header.add(title, BorderLayout.CENTER);

        // ===== Form Login =====
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Username");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 35));
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        usernameField.setBackground(new Color(255, 182, 193));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel passLabel = new JLabel("Password");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 35));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBackground(new Color(255, 182, 193));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(150, 35));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(255, 182, 193));
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUsername = usernameField.getText().trim();
                String inputPassword = new String(passwordField.getPassword()).trim();

                // Validasi input terlebih dahulu
                if (inputUsername.isEmpty() && inputPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong.", "Login Gagal", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (inputUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username tidak boleh kosong.", "Login Gagal", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (inputPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password tidak boleh kosong.", "Login Gagal", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection conn = koneksiDB.getConnection();
                    if (conn == null) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke database gagal.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = "SELECT password FROM tb_user WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, inputUsername);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String correctPassword = rs.getString("password");
                        if (inputPassword.equals(correctPassword)) {
                            JOptionPane.showMessageDialog(null, "Login berhasil!");
//                             TODO: buka dashboard
                        } else {
                            JOptionPane.showMessageDialog(null, "Password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username tidak ditemukan.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat login.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
