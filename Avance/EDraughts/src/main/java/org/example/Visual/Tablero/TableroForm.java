package org.example.Visual.Tablero;

import javax.swing.*;

public class TableroForm extends JFrame{
    private JPanel panelTablero;
    private JButton button2;
    private JButton btnFicha1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelTablero = new panelFondoTablero();
    }

    public void setConfig(){
        this.setContentPane(panelTablero);
        this.setTitle("E-Draughts");
        this.setSize(500,500);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //fLogin.dispose();//Cierra solo la ventana
    }
}
