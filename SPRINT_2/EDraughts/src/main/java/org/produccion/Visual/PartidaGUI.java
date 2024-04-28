package org.produccion.Visual;

import org.produccion.Visual.Tablero.TableroForm;

import javax.swing.*;

public class PartidaGUI extends JFrame{
    private TableroForm tableroForm1;
    private JTextPane textPane1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel pnlMain;


    public void setConfig(){

        this.setContentPane(pnlMain);
        this.setTitle("E-Draughts");
        this.setSize(800,700);
        this.setResizable(true);
        this.setExtendedState(MAXIMIZED_BOTH); // Ventana Maximizada
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
