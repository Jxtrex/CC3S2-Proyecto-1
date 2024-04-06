package org.EDraughts.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenudelJuego extends JFrame {
    private JButton crearPartidaButton;
    private JButton verPartidaButton;
    private JPanel panelMain;

    MenudelJuego() {
        this.setContentPane(panelMain);
        this.setTitle("EDraughts");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);
        this.setVisible(true);
        crearPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
                Tablero tablero = new Tablero();
            }
        });
    }
}
