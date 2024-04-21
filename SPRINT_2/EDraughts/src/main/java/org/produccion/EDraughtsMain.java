package org.produccion;

import org.produccion.Controlador.Partida;
import org.produccion.Visual.GUI;
import org.produccion.Visual.LoginForm;
import org.produccion.Visual.Tablero.TableroForm;

public class EDraughtsMain {
    public static void main(String[] args) {
//        LoginForm formulario = new LoginForm();
//        formulario.setConfig();

        //Prueba del tablero-------
        //Dejare esto sin comentar para poder debugear mas rapido sin necesidad de iniciar sesion
//        GUI GUI = new GUI();
//        GUI.setConfig();


        // Se volvió a agregar la carpeta "Tablero" a la carpeta "Visual"
        // para poder poder visualizar el Tablero  con el formato anterior
        TableroForm tableroForm = new TableroForm();
        tableroForm.setConfig();
        // Usar el tablero como guia visual de la partida hasta poder implementarla con la GUI

        // Se volvió a agregar la clase Partida en la carpeta Controlador,
        // Y una nueva imagen "TableroMarcado.png" en la carpeta de Imagenes

        //Ejecución de la partida
        //Partida partida = new Partida();
        // Ingresa las posiciones como en los siguientes ejemplos:  "A6" "G2"  "B8"
        //System.out.println("Con esto verificamos que realmente la partida se cerró");


    }
}