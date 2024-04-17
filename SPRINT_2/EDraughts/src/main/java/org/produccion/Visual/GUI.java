package org.produccion.Visual;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {

    private JPanel panelMain;

    public GUI() {
        Lienzo lienzo = new Lienzo();
        this.setContentPane(lienzo);
        this.setTitle("E-Draughts");
        this.setSize(640, 640);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //fLogin.dispose();//Cierra solo la ventana

    }

    class Lienzo extends JPanel {
        Lienzo() {

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int rowSelected = e.getY();
                    int colSelected = e.getX();
                    System.out.println("X: " + colSelected + " Y: " + rowSelected + "\n");
                    //          repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.YELLOW);
            drawGridLines(g);
        }

        private void drawGridLines(Graphics g){
            g.setColor(Color.BLACK);
            for (int row = 1; row < 8; ++row) {
                g.fillRoundRect(0, 80 * row - 4,
                        640-1, 8, 8, 8);
            }
            for (int col = 1; col < 8; ++col) {
                g.fillRoundRect(80 * col - 4, 0,
                        8, 640-1, 8, 8);
            }

        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
