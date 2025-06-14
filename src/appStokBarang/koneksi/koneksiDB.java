package appStokBarang.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class koneksiDB {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_stokbarang";
            String user = "root";
            String pass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Koneksi ke database gagal:\n" + e.getMessage(),
                    "Koneksi Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }
}

