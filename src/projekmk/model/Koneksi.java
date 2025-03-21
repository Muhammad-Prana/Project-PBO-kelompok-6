package projekmk.model;

public class Koneksi {

    String host = "localhost:3306";
    String database = "db_dzakyhasan";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    String connect = "jdbc:mysql://"+ host + "/" + database;

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public Koneksi() {

    }

    public Koneksi (String host, String database, String user, String password){
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }
}
