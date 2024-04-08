package org.example.Modelo;

import java.sql.*;

public class ConsultasUsuario extends ConexionSQL{
    // Operaciones Crear, Leer de la Tabla de Usuarios
    public boolean insertarUsuarios(ModeloUsuarios usuario) {// Inserta usuario, salt y hash en la tabla Usuarios
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
    public boolean verificarUsuarioEnUsuarios(String usuario){//Consulta a la tabla y retorna true si ya existe en la tabla
        PreparedStatement declaracionPreparada = null; //Objeto que llevara la consulta SQL
        Connection conexion = getConnection();//objeto que recibira la conexion a la DB
        String consulta = "SELECT COUNT(*) AS count FROM Usuarios WHERE usuario = ?";
        try{
            declaracionPreparada = conexion.prepareStatement(consulta);
            //Usamos el parametro usuario para rellenar la consulta
            declaracionPreparada.setString(1,usuario);
            //Ejecutamos la consulta
            ResultSet resultado = declaracionPreparada.executeQuery();
            //Usamos la cantidad de veces que aparece el nombre de usuario(unico) para verificar que existe
            if(resultado.next()){
                int count = resultado.getInt("count");
                if(count>0){
                    System.out.println("El nombre de usuario ya existe");
                    return true;
                }
                else{
                    System.out.println("El nombre de usuario no existe");
                    return false;
                }
            }
        }
        catch(SQLException e){
            System.err.println(e);

        }
        finally{
            try{
                conexion.close();//Siempre cerramos la conexion
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        return false;
    }
    public boolean leerSaltHash(ModeloUsuarios modeloUsuarios){// Retorna true si pudo leer salt y hash que corresponden al usuario en la tabla Usuarios
        PreparedStatement declaracionPreparada = null; //Objeto que llevara la consulta SQL
        Connection conexion = getConnection();//objeto que recibira la conexion a la DB
        ResultSet resultado = null;//Objeto que recibira el resultado de la consulta
        String consulta = "SELECT salt,hash FROM Usuarios WHERE usuario = ?";
        try{
            declaracionPreparada = conexion.prepareStatement(consulta);
            declaracionPreparada.setString(1,modeloUsuarios.getUsuario());
            resultado = declaracionPreparada.executeQuery();
            if(resultado.next()){
                modeloUsuarios.setSalt(resultado.getBytes("salt"));
                modeloUsuarios.setHash(resultado.getString("hash"));
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            try{
                conexion.close();//Siempre cerramos la conexion
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        return false;
    }
}
