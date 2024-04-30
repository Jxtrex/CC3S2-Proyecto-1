package org.produccion.Visual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicialForm extends JFrame {

    private OpcionesJugarForm opcionesJugar = new OpcionesJugarForm(this);
    private JPanel pnlMain;
    private JLabel lblBienvenida;
    private JButton jugarUnaPartidaButton;
    private JButton repetirUnaPartidaButton;
    private JButton cerrarSesiónButton;

    private String userName;

    public PantallaInicialForm(String userName){
        this.userName = userName;
        jugarUnaPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                opcionesJugar.setConfig();
            }
        });
        repetirUnaPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abre historial de partidas");
            }
        });
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cierra sesión");
            }
        });
    }

    public void setConfig(){
        this.setContentPane(this.pnlMain);
        this.setTitle("E-Draughts");
        this.setSize(400,300);
        this.lblBienvenida.setText("¡ Bienvenido "+userName+" !");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //fLogin.dispose();//Cierra solo la ventana
    }

}
