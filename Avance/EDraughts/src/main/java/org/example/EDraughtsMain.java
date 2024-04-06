package org.example;

import org.example.Modelo.ConexionSQL;
import org.example.Visual.LoginForm;

import java.sql.Connection;
import java.sql.SQLException;

public class EDraughtsMain {
    public static void main(String[] args) {
        LoginForm formulario = new LoginForm();
        formulario.setConfig();

        //System.out.println("Check!");
        /*
        ConexionSQL conexionprueba = new ConexionSQL();
        Connection conexion = conexionprueba.getConnection();
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         */
    }
}