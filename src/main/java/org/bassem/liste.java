package org.bassem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class liste extends  JFrame
{
    public static void main(String[] args)
    {
        try
        {
            String url = "jdbc:mysql://localhost/examen";
            String user = "root";
            String password = "";

            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM examen";

            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);

            String columns[] = { "ID", "Nom", "Genre" };
            String data[][] = new String[8][3];

            int i = 0;
            while (res.next()) {
                int id = res.getInt("ID");
                String nom = res.getString("Nom");
                String genre = res.getString("Genre");
                data[i][0] = id + "";
                data[i][1] = nom;
                data[i][2] = genre;
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(table);
            JFrame f = new JFrame("Examen");
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(pane);
            f.add(panel);
          //  panel.add(canvas);
            pane.setBorder(BorderFactory.createTitledBorder("Liste des utilisateurs"));
            f.setSize(500, 350);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }}

