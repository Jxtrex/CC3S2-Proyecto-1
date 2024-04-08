package org.example.Visual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    public static LoginForm fLogin = new LoginForm();
    private JPanel panelLogin;
    private JTextField txtUserName;
    private JPasswordField txtContraseña;
    private JButton btnLogin;
    private JButton btnRegistrar;

    public LoginForm() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroForm formulario = new RegistroForm();
                formulario.setConfig();
                fLogin.dispose();
            }
        });
    }

    public void setConfig(){
        fLogin.setContentPane(fLogin.panelLogin);
        fLogin.setTitle("E-Draughts");
        fLogin.setSize(400,300);
        fLogin.setResizable(false);
        fLogin.setVisible(true);
        fLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //fLogin.dispose();//Cierra solo la ventana
    }
}
