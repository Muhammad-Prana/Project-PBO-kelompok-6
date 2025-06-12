import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Signup extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public Signup() {
        setTitle("Manajemen Stok Barang");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 105, 180)); // Pink color
        headerPanel.setPreferredSize(new Dimension(600, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        JLabel titleLabel = new JLabel("Manajemen Stok Barang");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 182, 193)); // Light pink background
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Username field
        usernameField = new JTextField("Username");
        setPlaceholderStyle(usernameField);
        formPanel.add(usernameField, gbc);

        // Email field
        gbc.gridy++;
        emailField = new JTextField("Email");
        setPlaceholderStyle(emailField);
        formPanel.add(emailField, gbc);

        // Password field
        gbc.gridy++;
        passwordField = new JPasswordField("Password");
        setPlaceholderStyle(passwordField);
        formPanel.add(passwordField, gbc);

        // SignUp button
        gbc.gridy++;
        signUpButton = new JButton("SignUp");
        signUpButton.setBackground(new Color(255, 105, 180));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setFocusPainted(false);
        signUpButton.setPreferredSize(new Dimension(120, 40));
        formPanel.add(signUpButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    // Method to set placeholder style and behavior
    private void setPlaceholderStyle(JTextField field) {
        field.setForeground(Color.GRAY);
        field.setFont(new Font("Arial", Font.PLAIN, 16));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field instanceof JPasswordField) {
                    JPasswordField pf = (JPasswordField) field;
                    String passText = new String(pf.getPassword());
                    if (passText.equals("Password")) {
                        pf.setText("");
                        pf.setEchoChar('•');
                        pf.setForeground(Color.BLACK);
                    }
                } else {
                    if (field.getText().equals("Username") || field.getText().equals("Email")) {
                        field.setText("");
                        field.setForeground(Color.BLACK);
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field instanceof JPasswordField) {
                    JPasswordField pf = (JPasswordField) field;
                    String passText = new String(pf.getPassword());
                    if (passText.isEmpty()) {
                        pf.setText("Password");
                        pf.setEchoChar((char) 0);
                        pf.setForeground(Color.GRAY);
                    }
                } else {
                    if (field.getText().isEmpty()) {
                        if (field == usernameField) {
                            field.setText("Username");
                        } else if (field == emailField) {
                            field.setText("Email");
                        }
                        field.setForeground(Color.GRAY);
                    }
                }
            }
        });

        // For password field, initially disable echo char to show placeholder
        if (field instanceof JPasswordField) {
            ((JPasswordField) field).setEchoChar((char) 0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Signup signUpPage = new Signup();
            signUpPage.setVisible(true);
        });
    }
}
//