package appStokBarang.koneksi;

import java.sql.Connection;

public class tesKoneksi {

        public static void main(String[] args) {
            Connection conn = koneksiDB.getConnection();

            if (conn != null) {
                System.out.println("✅ Koneksi BERHASIL ke database!");
            } else {
                System.out.println("❌ Koneksi GAGAL!");
            }
        }
    }