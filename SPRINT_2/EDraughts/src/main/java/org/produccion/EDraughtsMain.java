package org.produccion;

import org.produccion.Controlador.Partida;
import org.produccion.Visual.GUI;
import org.produccion.Visual.LoginForm;
import org.produccion.Visual.PartidaGUI;
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
        // TableroForm es la GUI
        // Se volvió a agregar la clase Partida en la carpeta Controlador,
        // Y una nueva imagen "TableroMarcado.png" en la carpeta de Imagenes
        //Se proporcionan las entradas haciendo click en los botones
        // o ingresando las posiciones por teclado como en los siguientes ejemplos:  "A6" "G2"  "B8"

        //PartidaGUI partida = new PartidaGUI();
        //partida.setConfig();

    }
}