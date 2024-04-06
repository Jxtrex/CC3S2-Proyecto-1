package org.EDraughts.Vista;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JFrame {
    private JPanel panelMain;

    Tablero(){
        this.setContentPane(new TestPane());
        this.setTitle("EDraughts");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);
        this.setVisible(true);
    }
    public class TestPane extends JPanel {

        public TestPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
            int width = getWidth() - (size * 2);
            int height = getHeight() - (size * 2);

            int y = (getHeight() - (size * 10)) / 2;
            for (int horz = 0; horz < 10; horz++) {
                int x = (getWidth() - (size * 10)) / 2;
                for (int vert = 0; vert < 10; vert++) {
                    g.drawRect(x, y, size, size);
                    x += size;
                }
                y += size;
            }
            g2d.dispose();
        }

    }
}
