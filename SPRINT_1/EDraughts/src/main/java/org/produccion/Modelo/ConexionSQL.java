package org.produccion.Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionSQL {
    public static final String DRIVER= "com.microsoft.sqlserver.jdbc.SQLServerDriver";//usar este si el otro no funciona com.mysql.cj.jdbc.Driver
    public static final String USERNAME = "pruebatest";
    public static final String PASSWORD ="root123#";
    public static final String DBNAME = "EDraughts";
    public static final String HOSTNAME = "edraughts";
    public String url;

    public ConexionSQL(){
        this.url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;" +
                        "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
                ,HOSTNAME,DBNAME,USERNAME,PASSWORD);

        //Formateamos el url que utilizaremos para conectar a azure
    }
    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(this.url,USERNAME,PASSWORD);
            if(connection!=null){
                System.out.println("Conexion establecida");
            }
        }
        catch(ClassNotFoundException | SQLException e){ // Esto debo mejorarlo
            System.out.println("Error al conectar "+e);//Imprimimos la excepcion
        }
        return connection;
    }
}
