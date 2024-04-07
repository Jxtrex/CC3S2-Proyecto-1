package org.EDraughts.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuIniciodeSesion extends JFrame {
    private JPanel panelMain;
    private JTextField txtUsuario;
    private JTextField txtContraseña;
    private JButton btnIniciarSesion;
    private JButton btnRegistrate;
    private JLabel lblEresNuevo;
    private JButton btnClick;

    public MenuIniciodeSesion() {
        this.setContentPane(panelMain);
        this.setTitle("EDraughts");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);
        this.setVisible(true);
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenudelJuego menudelJuego = new MenudelJuego();
            }
        });
    }

    public void configuracionPrueba(MenuIniciodeSesion helloprueba) {
        helloprueba.setContentPane(helloprueba.panelMain);
        helloprueba.setTitle("Hello");
        helloprueba.setSize(300, 400);
        helloprueba.setVisible(true);
        helloprueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
