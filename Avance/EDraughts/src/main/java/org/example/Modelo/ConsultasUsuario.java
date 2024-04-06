package org.example.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ConsultasUsuario extends ConexionSQL{
    // Operaciones Crear, Leer de la Tabla de Usuarios
    public boolean insertarUsuarios(ModeloUsuarios usuario) {
        PreparedStatement declaracionPreparada = null; //Objeto que llevara la consulta SQL
        Connection conexion = getConnection();//objeto que recibira la conexion a la DB
        String consulta = "INSERT INTO Usuarios "
        + "( usuario, salt, hash)"
        + "values( ?, ?, ?)";
        try{
            declaracionPreparada = conexion.prepareStatement(consulta);// Preparamos la consulta
            declaracionPreparada.setString(1, usuario.getUsuario());
            declaracionPreparada.setBytes(2,usuario.getSalt());
            declaracionPreparada.setString(3, usuario.getHash());
            declaracionPreparada.execute();
            declaracionPreparada.close();
            return true;
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.err.println(e);
            return false;
        }
        catch(SQLException e){
            System.err.println(e);
            return false;
        }
        finally{
            try{
                conexion.close();//Siempre cerramos la conexion
            }
            catch(SQLException e){
                System.err.println(e);
            }


        }


    }
}
