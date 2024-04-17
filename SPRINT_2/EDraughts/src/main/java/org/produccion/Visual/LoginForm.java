package org.produccion.Visual;

import org.produccion.Controlador.ControladorUsuario;

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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorUsuario controladorUsuario = new ControladorUsuario();
                int estadoInicioSesion=controladorUsuario.verificarInicioSesion(txtUserName,txtContraseña);
                switch (estadoInicioSesion) {
                    case 1:
                        // Inicio de sesión exitoso
                        JOptionPane.showMessageDialog(btnLogin,"Inicio de sesión exitoso");
                        fLogin.dispose();
                        GUI GUI = new GUI();
//                        GUI.setConfig();

                        break;
                    case 2:
                        // Campo vacío
                        JOptionPane.showMessageDialog(btnLogin,"Por favor, complete todos los campos.");
                        break;
                    case 3:
                        // Usuario no válido
                        JOptionPane.showMessageDialog(btnLogin,"Ingrese un usuario válido");
                        break;
                    case 4:
                        // Contraseña no segura
                        JOptionPane.showMessageDialog(btnLogin,"Ingrese una contraseña válida");
                        break;
                    case 5:
                        // Usuario no existe
                        JOptionPane.showMessageDialog(btnLogin,"El nombre de usuario no existe.");
                        break;
                    case 6:
                        // Contraseña incorrecta
                        JOptionPane.showMessageDialog(btnLogin,"La Contraseña es incorrecta.");
                        break;
                    default:
                        // Otro caso no manejado
                        JOptionPane.showMessageDialog(btnLogin,"Error al iniciar sesión.");
                        break;
                }
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
