package org.bassem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MyListe extends JFrame {

    private MyDatabase database;
    private Graphics2D graph;
    private DefaultTableModel model;

    public MyDatabase getDatabase() {
        return database;
    }

    public Graphics2D getGraph() {
        return graph;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public MyListe() {
        try {
            String data[][] = {};
            String column[] = {"ID", "NOM", "GENRE"};
            model = new DefaultTableModel(data, column);
            database = MyDatabase.getInstance();
            database.remplirTableau(model);

            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(table);
            JFrame f = new JFrame("Examen");
            JPanel panel = new JPanel(new GridLayout(2, 1));
            JPanel graphP = new JPanel (new FlowLayout());
            graphP.setBackground(Color.BLACK);
            panel.add(pane);
            f.add(panel);
            panel.add(graphP);
            pane.setBorder(BorderFactory.createTitledBorder("Liste des utilisateurs"));
            f.setSize(800, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

            graph = (Graphics2D) graphP.getGraphics();
            var thread = new MyThread(this);
            thread.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

