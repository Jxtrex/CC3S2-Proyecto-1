package org.produccion.Visual;

import org.produccion.Controlador.ControladorUsuario;

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

        btnCrearCuentaNueva.addActionListener(new ActionListener() {//Cuando presionemos crear cuenta nueva
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorUsuario controladorUsuario = new ControladorUsuario();
                int estadoRegistro = controladorUsuario.verificarRegistroUsuario(txtNewUserName,txtContraseñaNU,txtConfirmContraseñaNU);
                switch (estadoRegistro) {
                    case 1:
                        // Registro exitoso
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Registro exitoso!");
                        break;
                    case 2:
                        // Algun campo vacio
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Por favor, complete todos los campos!");
                        break;
                    case 3:
                        // Contraseñas no coinciden
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Las contraseñas no coinciden!");
                        break;
                    case 4:
                        // Usuario no válido
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Usuario no válido!");
                        break;
                    case 5:
                        // Usuario demasiado largo
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Por favor use un nombre de usuario mas corto!");
                        break;
                    case 6:
                        // Contraseña no segura
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Por favor use una contraseña que contenga por lo menos 1 mayúscula, 1 número, 1 símbolo y longitud 8!");
                        break;
                    case 7:
                        // El nombre de usuario ya existe
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡El nombre de usuario ya está en uso!");
                        break;
                    default:
                        // Otro caso no manejado
                        JOptionPane.showMessageDialog(btnCrearCuentaNueva, "¡Ocurrió un error durante el registro!");
                        break;
                }
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
