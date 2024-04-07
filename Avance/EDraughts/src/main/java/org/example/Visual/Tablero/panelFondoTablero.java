package org.example.Visual.Tablero;

import javax.swing.*;
import java.awt.*;

public class panelFondoTablero extends JPanel {
    private ImageIcon imagen;

    @Override
    public void paint(Graphics g){
        imagen = new ImageIcon("recursos/Imagenes/tablero.png");
        g.drawImage(imagen.getImage(),0,0,getWidth(),getHeight(),this);
        //g.drawImage(imagen.getImage(),0,0,500,500,this);
        setOpaque(false);
        super.paint(g);
    }
}
