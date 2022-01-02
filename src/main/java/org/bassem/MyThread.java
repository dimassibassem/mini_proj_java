package org.bassem;

import java.awt.*;
import java.sql.SQLException;
import java.util.Locale;

public class MyThread extends Thread {
    private Graphics2D g;
    private final MyListe ml;

    public MyThread(MyListe ml) {
        this.ml = ml;
        this.g = ml.getGraph();
    }

    public void histogramme(int numberOfPeople) {
        var femaleCount = ml.getModel().getDataVector().stream().filter(v -> v.get(2).toString().toLowerCase(Locale.ROOT).equals("femme")).count();
        var maleCount = ml.getModel().getDataVector().stream().filter(v -> v.get(2).toString().toLowerCase(Locale.ROOT).equals("homme")).count();

        int maleHeight = 0;
        int femaleHeight = 0;
        if (numberOfPeople != 0) {
            femaleHeight = (int) (240 * femaleCount / numberOfPeople);
            maleHeight = (int) (240 * maleCount / numberOfPeople);
        }

        g.setColor(Color.BLUE);
        g.fillRect(200, 280 - maleHeight, 100, maleHeight);

        g.setColor(Color.PINK);
        g.fillRect(400, 280 - femaleHeight, 100, femaleHeight);

        g.setColor(Color.BLUE);
        g.drawLine(25, 280 - maleHeight, 15, 280 - maleHeight);
        g.drawString(String.valueOf(maleCount), 30, 280 - maleHeight + 5);

        g.setColor(Color.PINK);
        g.drawLine(25, 280 - femaleHeight, 15, 280 - femaleHeight);
        g.drawString(String.valueOf(femaleCount), 30, 280 - femaleHeight + 5);
    }

    public void axesXY(int numberOfPeople, String labelX, String labelY) {
        g.setColor(Color.RED);
        g.drawString(labelX, 20, 20);
        g.drawString(labelY, 730, 295);

        g.setColor(Color.GREEN);
        g.drawLine(20, 20, 20, 280);
        g.drawLine(20, 20, 25, 30);
        g.drawLine(20, 20, 15, 30);

        g.drawLine(20, 280, 780, 280);
        g.drawLine(780, 280, 770, 275);
        g.drawLine(780, 280, 770, 285);

        g.setColor(Color.YELLOW);
        g.drawLine(25, 40, 15, 40);
        g.drawString(String.valueOf(numberOfPeople), 30, 45);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                var model = ml.getModel();
                ml.getDatabase().remplirTableau(model);

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 800, 300);
                axesXY(model.getRowCount(), "Nbr", "Genre");
                histogramme(model.getRowCount());
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
