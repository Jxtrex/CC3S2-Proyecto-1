package org.produccion.Visual;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.produccion.Modelo.Tablero;
import org.produccion.Modelo.Tablero.Celda;

public class GUI extends JFrame {

  private JPanel panelMain;
  private Lienzo lienzo = new Lienzo();
  private Fichas fichas = new Fichas();

  private Tablero tablero;

  public GUI(Tablero tablero) {
    //POSICIÓN DE LAS 4 ESQUINAS DEL TABLERO SIN LOS BORDES ESTÉTICOS
    // x0 = 13, y0 = 12; x1 = 393, y0 = 12
    // x0 = 13, y1 = 388; x1 = 393, y1 = 388
    this.tablero = tablero;
    lienzo.add(fichas);
    this.setContentPane(lienzo);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack(); //Ajusta la ventana su componente más pequeño
    this.setTitle("E-Draughts");
    this.setVisible(true);
  }

  class Lienzo extends JPanel {

    Lienzo() {
      setPreferredSize(new Dimension(404, 403));
      setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage((new ImageIcon("recursos/Imagenes/Tablero.png")).getImage(), 0, 0, null);
      setOpaque(false);
    }
  }

  class Fichas extends JPanel {

    int temp = 0;

    Fichas() {
      setBounds(0, 0, 404, 403);
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          int rowSelected = e.getY();
          int colSelected = e.getX();

          int cellRow = (rowSelected - 12) / 47;
          int cellCol = (colSelected - 13) / 47;
          System.out.println("X: " + colSelected + " Y: " + rowSelected);
          if (rowSelected >= 12 && rowSelected <= 388 && colSelected >= 13 && colSelected <= 393) {
            System.out.println(
                "ESTAS EN LA CASILLA: [" + (colSelected - 13) / 47 + "][" +
                    (rowSelected - 12) / 47 + "]");
          }

          tablero.flipCelda(cellRow, cellCol);
          System.out.println(tablero.toString());
          repaint();
        }
      });

    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      initboard(g);
      drawGridLines(g);
//      drawDraughts(g);
    }

    public void initboard(Graphics g) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          g.drawImage((new ImageIcon("recursos/Imagenes/FichaRoja.png")).getImage(), 18 + i * 47,
              16 + j * 47, 40, 40, this);
        }
      }
      setOpaque(false);
    }


    //FIXME No funciona y no sé por qué
    private void drawDraughts(Graphics g) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          Celda celda = tablero.getCelda(i, j);
          g.drawImage(getResource(celda), 18 + i * 47, 16 + j * 47, 40, 40, null);
        }
      }
    }

    private void drawGridLines(Graphics g) {
      g.setColor(Color.MAGENTA);
      for (int row = 0; row <= 8; ++row) {
        g.drawLine(13, 12 + 47 * row, 393, 12 + 47 * row);
      }
      for (int col = 0; col <= 8; ++col) {
        g.drawLine(13 + 47 * col, 12, 13 + 47 * col, 388);
      }
    }

    private void drawRulers(Graphics g) {
      //REGLA VERTICAL PARA MEDIR LOS BORDES DEL TABLERO
      g.drawLine(13, 0, 13, 400);
      g.drawLine(393, 0, 393, 400);
      //REGLA HORIZONTAL PARA MEDIR LOS BORDES DEL TABLERO
      g.drawLine(0, 12, 400, 12);
      g.drawLine(0, 388, 400, 388);
    }
  }

  private Image getResource(Celda tipoDeFicha) {
    String url = new String();

    switch (tipoDeFicha) {
      case NEGRA:
        url = "recursos/Imagenes/FichaNegra.png";
        break;
      case ROJA:
        url = "recursos/Imagenes/FichaRoja.png";
        break;
      case REINANEGRA:
        url = "recursos/Imagenes/FichaReinaNegra.png";
        break;
      case REINAROJA:
        url = "recursos/Imagenes/FichaReinaRoja.png";
        break;
      default:
        url = "";
        break;
    }
    return (new ImageIcon(url)).getImage();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new GUI(new Tablero());
      }
    });
  }
}
