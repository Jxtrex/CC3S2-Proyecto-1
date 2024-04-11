package org.produccion;

import org.produccion.Visual.LoginForm;
import org.produccion.Visual.Tablero.TableroForm;

public class EDraughtsMain {
    public static void main(String[] args) {
        LoginForm formulario = new LoginForm();
        formulario.setConfig();

        //Prueba del tablero-------
        //Dejare esto sin comentar para poder debugear mas rapido sin necesidad de iniciar sesion
        TableroForm tableroForm = new TableroForm();
        tableroForm.setConfig();


    }
}