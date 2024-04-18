package org.produccion.Visual;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {

  private JPanel panelMain;

  public GUI() {
    //POSICIÓN REAL DEL TABLERO SIN LOS BORDES ESTÉTICOS
    // x0 = 13, y0 = 12; x1 = 393, y0 = 12
    // x0 = 13, y1 = 388; x1 = 393, y1 = 388
    Insets insets = this.getInsets();
    int addedWidth = insets.left + insets.right;
    int addedHeight = insets.top + insets.bottom;

    final int HEIGHT = 403 + addedHeight;
    final int WIDTH = 407 + addedWidth;

    Lienzo lienzo = new Lienzo();
    this.setContentPane(lienzo);
    this.pack();
    this.setTitle("E-Draughts");
//    this.setSize(WIDTH, HEIGHT);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    this.pack();
    this.setVisible(true);
    //fLogin.dispose();//Cierra solo la ventana
  }

  class Lienzo extends JPanel {

    Lienzo() {
      setPreferredSize(new Dimension(407, 403));
//              try {
//                Image image = ImageIO.read(super.getClass().getResource("/recursos/Imagenes/FichaNegra.png"));
//              }catch (Exception e)
//              {
//                System.err.println("Error reading file.");
//                System.exit(1);
//              }
      ImageIcon imageIcon = new ImageIcon("resources/Imagenes/FichaNegra.png");
      add(new JLabel(imageIcon));
      repaint();
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
//      setBackground(Color.YELLOW);
      drawGridLines(g);
    }

    @Override
    public void paint(Graphics g) {

      g.drawImage((new ImageIcon("recursos/Imagenes/Tablero.png")).getImage(), 0, 0, null);
      g.drawImage((new ImageIcon("recursos/Imagenes/FichaNegra.png")).getImage(), 210, 210, 75, 75,
          this);
      setOpaque(false);
      super.paint(g);
      g.drawImage((new ImageIcon("recursos/Imagenes/FichaRoja.png")).getImage(), 320, 320, 75, 75,
          this);
      super.paint(g);
    }

    private void drawGridLines(Graphics g) {
//      g.setColor(Color.CYAN);
      //REGLA VERTICAL PARA MEDIR LOS BORDES DEL TABLERO
//      g.drawLine(13,0,13,400);
//      g.drawLine(393,0,393,400);

      g.setColor(Color.MAGENTA);
      //REGLA HORIZONTAL PARA MEDIR LOS BORDES DEL TABLERO
//      g.drawLine(0,12,400,12);
//      g.drawLine(0,388,400,388);
      for (int row = 0; row <= 8; ++row) {
//        g.fillRoundRect(0, 80 * row - 4, 640 - 1, 8, 8, 8);
        g.drawLine(13, 12 + 47 * row, 393, 12 + 47 * row);
      }
      for (int col = 0; col <= 8; ++col) {
//        g.fillRoundRect(80 * col - 4, 0, 8, 640 - 1, 8, 8);
        g.drawLine(13 + 47 * col, 12, 13 + 47 * col, 388);
      }

    }

    private void drawSomething(Image image, int dx, int dy, Graphics2D graphics2D) {
      graphics2D.drawImage(image, 320, 320, 40, 40, null);
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
