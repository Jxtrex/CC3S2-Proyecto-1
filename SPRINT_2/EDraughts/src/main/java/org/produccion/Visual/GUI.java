package org.produccion.Visual;

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
    this.setSize(650,648);
    this.setResizable(false);
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    //fLogin.dispose();//Cierra solo la ventana

  }

  class Lienzo extends JPanel {
    Lienzo(){
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int rowSelected = e.getY();
            int colSelected = e.getX();
          System.out.println("X: " + colSelected+" Y: "+rowSelected+"\n");
            //          repaint();
        }
      });
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
