package org.example.Visual;

import org.example.Controlador.RegistrarUsuario;

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

        btnCrearCuentaNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoUsuario = txtNewUserName.getText();//Le enviamos el valor del usuario para su verificacion
                if(!txtNewUserName.getText().isEmpty()){
                    RegistrarUsuario registrarUsuario = new RegistrarUsuario();
                    //Caso ideal usuario correcto y contraseñas adecuadas
                    if(!registrarUsuario.verificarExisteUsuario(textoUsuario)) {//Falta considerar los casos de si la contraseña es segura y si las contraseñas coincide con confirmar
                        System.out.println("falta implementar registro usuario");

                    }

                else{
                    //Lanzar mensaje "Nombre de usuario vacio", tambien se puede agregar lo de longitud minima, pero no se si agregarlo aqui o en la clase de RegistrarUsuario
                }
                };
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
