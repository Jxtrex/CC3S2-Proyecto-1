package org.produccion.Visual.Tablero;

import javax.swing.*;
import java.awt.*;

public class PanelFondoTablero extends JPanel {
    private ImageIcon imagen;

    @Override
    public void paint(Graphics g){
        imagen = new ImageIcon("recursos/Imagenes/Tablero.png");
        g.drawImage(imagen.getImage(),0,0,getWidth(),getHeight(),this);
        //g.drawImage(imagen.getImage(),0,0,500,500,this);
        setOpaque(false);
        super.paint(g);
    }
}
