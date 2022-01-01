package org.bassem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Examen extends JFrame {

    JLabel NomL = new JLabel("NOM");
    JTextField NOMT = new JTextField();
    JLabel GenreL = new JLabel("GENRE");
    JRadioButton male = new JRadioButton("Homme", true);
    JRadioButton female = new JRadioButton("Femme");
    ButtonGroup groupe = new ButtonGroup();

    JButton ajouter = new JButton("Ajouter");
    JButton liste = new JButton("Liste");

    public Examen() {
        super("Examen");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel POption = new JPanel();
        POption.setLayout(new GridLayout(1, 2));
        POption.add(male);
        POption.add(female);

        JPanel Pform = new JPanel(new GridLayout(2, 2));
        Pform.setBorder(BorderFactory.createTitledBorder("informations"));
        Pform.add(NomL);
        Pform.add(NOMT);
        Pform.add(GenreL);
        groupe.add(male);
        groupe.add(female);
        Pform.add(POption);

        setVisible(true);


        JPanel pbtn = new JPanel(new FlowLayout());

        pbtn.add(ajouter);
        pbtn.add(liste);


        Container c = getContentPane();

        c.setLayout(new BorderLayout());

        c.add("Center", Pform);
        c.add("South", pbtn);
        setVisible(true);

        ajouter.addActionListener(e -> ajouterDB());
     //   liste.addActionListener(e -> liste());


    }


    void ajouterDB() {
        try {
            String option = "";
            if (male.isSelected())
                option = "Homme";
            else option = "Femme";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
            Statement stm = null;
            stm = con.createStatement();
            String cmd = "insert into examen(Nom,Genre)values('" + NOMT.getText() + "','" + option + "')";
            int rep = stm.executeUpdate(cmd);
            if (rep > 0) {
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


// change this methode
/*
    void liste() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost/javabd", "root", "");
            Statement stm = null;
            stm = con.createStatement();
            String cmd = "update userstable set nom='" + NOMT.getText() + "',adresse='" + AdresseC.getSelectedItem() + "' where nom='" + NOMT.getText() + "'";
            int rep = stm.executeUpdate(cmd);
            if (rep > 0) {
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}





