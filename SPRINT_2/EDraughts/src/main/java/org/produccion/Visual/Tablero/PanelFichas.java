package org.produccion.Visual.Tablero;

import javax.swing.*;
import java.awt.*;

public class PanelFichas extends JPanel {
    private ImageIcon imagen;
    public int estado;

    public int nroFicha;

    public PanelFichas(int estado, int nroFicha){
        this.estado = estado;
        this.nroFicha=nroFicha;
    }
/*
    public void setEstado(int estado) {
        this.estado = estado;
    }*/

    @Override
    public void paint(Graphics g) {
        String url = new String();

        if (estado == 1) { // Ficha negra
            url = "recursos/Imagenes/FichaNegra.png";

        } else if (estado == 2) { // Ficha roja
            url = "recursos/Imagenes/FichaRoja.png";

        } else if (estado==3){ // Reyna negra
            url = "recursos/Imagenes/ReinaNegra.png" ;

        } else if (estado==4){ // Reyna roja
            url = "recursos/Imagenes/ReinaRoja.png" ;
        }else {
            url = "" ;
        }


        int porc = 90; //porcentaje del tamaño total

        imagen = new ImageIcon(url);
        g.drawImage(imagen.getImage(), getWidth() * (100 - porc) / 200, getHeight() * (100 - porc) / 200, getWidth() * porc / 100, getHeight() * porc / 100, this);
        //g.drawImage(imagen.getImage(),10,10,10,10,this);
        setOpaque(false);
        super.paint(g);
    }
}
