package org.bassem;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDatabase {
    private static volatile MyDatabase instance;
    private final Connection connection;
    private final String url = "jdbc:mysql://localhost/" + "examen";
    private final String user = "root";
    private final String password = "";

    public MyDatabase() throws SQLException {
        this.connection = connection();
    }

    public static MyDatabase getInstance() throws SQLException {
        MyDatabase result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MyDatabase.class) {
            if (instance == null) {
                instance = new MyDatabase();
            }
            return instance;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Connection connection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database successfully.");

        return conn;
    }

    public void ajouter(Personne p) throws SQLException {
        var instance = MyDatabase.getInstance();
        var conn = instance.getConnection();

        String stm;
        if (p.getId() != null) {
            stm = String.format(
                    "INSERT INTO examen (id, nom, genre) VALUES (%d, '%s','%s');",
                    p.getId(),
                    p.getNom(),
                    p.getGenre()
            );
        } else {
            stm = String.format(
                    "INSERT INTO examen (nom, genre) VALUES ('%s', '%s');",
                    p.getNom(),
                    p.getGenre()
            );
        }


        var statement = conn.prepareStatement(stm);
        statement.executeUpdate();
    }

    public void remplirTableau(DefaultTableModel model) throws SQLException {
        var instance = MyDatabase.getInstance();
        var conn = instance.getConnection();

        String stm = "SELECT * FROM examen";
        var statement = conn.prepareStatement(stm);
        var rs = statement.executeQuery();

        while (rs.next()) {
            var id = rs.getInt("id");
            var c = model.getDataVector().stream().filter(v -> v.get(0).equals(id)).count();
            if (c == 0) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("genre")
                });
            }
        }
    }
}
