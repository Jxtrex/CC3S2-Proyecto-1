package org.example.Visual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroForm extends JFrame{
    public static RegistroForm fRegistro = new RegistroForm();
    private JPanel panelRegistro;
    private JTextField txtNewUserName;
    private JPasswordField txtContraseñaNU;
    private JPasswordField txtConfirmContraseñaNU;
    private JButton btnCrearCuentaNueva;
    private JButton btnVolver;

    public RegistroForm() {
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm formulario = new LoginForm();
                formulario.setConfig();
                fRegistro.dispose();
            }
        });
    }

    public void setConfig(){
        fRegistro.setContentPane(fRegistro.panelRegistro);
        fRegistro.setTitle("E-Draughts");
        fRegistro.setSize(400, 300);
        fRegistro.setResizable(false);
        fRegistro.setVisible(true);
        fRegistro.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
