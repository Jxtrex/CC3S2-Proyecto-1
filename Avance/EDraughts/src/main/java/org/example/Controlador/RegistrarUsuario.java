package org.example.Controlador;

import org.example.Modelo.ConexionSQL;
import org.example.Modelo.ConsultasUsuario;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrarUsuario extends ConexionSQL {
    private String usuario;
    private ConsultasUsuario consultasUsuario;

    public RegistrarUsuario() {

    }
    public boolean verificarExisteUsuario(String usuario){ //devuelve true si el usuario existe en la tabla Usuarios
        consultasUsuario = new ConsultasUsuario();//Si alguien sabe una manera practica de volverlo null al terminar la operacion me avisa
        //Falta considerar el caso que la string obtenida usuario sea malicioso
        if(consultasUsuario.verificarUsuarioEnUsuarios(usuario)){
            System.out.println("El nombre de usuario ya existe");
            return true;

        }
        else{
            return false;
        }

    }
    public boolean verificarContraseniaSegura(String contrasenia){//devuelve true si es segura
        //Falta implementar
        return false;
    }
    public boolean registrarUsuario(String usuario, String contrasenia){ // Solo es llamado cuando usuario y contrasenia son adecuados
        consultasUsuario = new ConsultasUsuario();
        //Falta implementar
        return false;
    }

}
