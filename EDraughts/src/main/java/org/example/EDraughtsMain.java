package org.example;

import org.example.Controlador.ControladorUsuario;
import org.example.Modelo.ConexionSQL;
import org.example.Visual.LoginForm;
import org.example.Visual.Tablero.TableroForm;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

public class EDraughtsMain {
    public static void main(String[] args) {
        LoginForm formulario = new LoginForm();
        formulario.setConfig();

        //Prueba del tablero-------
        TableroForm tableroForm = new TableroForm();
        tableroForm.setConfig();

        //-------------------------

        //System.out.println("Check!");
        /*
        //Codigo de prueba para el salt y hash que usaremos en las contraseñas
        byte[] saltPrueba;
        String hashPrueba;
        String pwd = "peipotoA123134#";
        ControladorUsuario controlprueba = new ControladorUsuario();
        saltPrueba = controlprueba.generarSalt();
        System.out.print("Salt es: ");
        for(byte i:saltPrueba){
            System.out.print(i);
        }
        System.out.println();
        try {
            hashPrueba = controlprueba.generarHash(saltPrueba,pwd);
            System.out.println("Hash es: "+hashPrueba);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

         */

    }
}