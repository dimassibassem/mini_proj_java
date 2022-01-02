package org.bassem;

import javax.swing.*;
import java.awt.*;

public class MyForm extends JFrame {

    JLabel NomL = new JLabel("NOM");
    JTextField NOMT = new JTextField();
    JLabel GenreL = new JLabel("GENRE");
    JRadioButton male = new JRadioButton("Homme", true);
    JRadioButton female = new JRadioButton("Femme");


    ButtonGroup groupe = new ButtonGroup();


    JButton ajouter = new JButton("Ajouter");
    JButton liste = new JButton("Liste");


    public MyForm() {
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

        female.setActionCommand("Femme");
        male.setActionCommand("Homme");
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

        ajouter.addActionListener(new MyEvents(this));
        liste.addActionListener(e -> new MyListe());
    }

    public static void main(String[] args) {

        new MyForm();

    }


    public JTextField getNomTextField() {
        return NOMT;
    }

    public ButtonGroup getGenreButtonGroup() {
        return groupe;
    }

    public JButton getAjouterButton() {
        return ajouter;
    }
}





