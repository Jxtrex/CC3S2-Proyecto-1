package org.produccion.Visual;

import org.produccion.Visual.Tablero.TableroForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesJugarForm extends JFrame {

    private PantallaInicialForm pantallaInicial;
    private JPanel pnlMain;
    private JButton juegaLocalmenteButton;
    private JButton juegaEnLíneaButton;
    private JButton juegaContraLaComputadoraButton;
    private JButton btnCerrarSesión;
    private JButton btnVolver;

    public OpcionesJugarForm(PantallaInicialForm pantalla) {


        pantallaInicial= pantalla;

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantallaInicial.setVisible(true);
                setVisible(false);
            }
        });
        btnCerrarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cierra sesión");
            }
        });
        juegaContraLaComputadoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Juega contra la computadora");
            }
        });
        juegaEnLíneaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Juega en línea");
            }
        });
        juegaLocalmenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TableroForm tableroForm = new TableroForm(1);
                tableroForm.setConfig();
            }
        });
    }

    public void setConfig(){
        this.setContentPane(this.pnlMain);
        this.setTitle("E-Draughts");
        this.setSize(400,300);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //fLogin.dispose();//Cierra solo la ventana
    }
}
