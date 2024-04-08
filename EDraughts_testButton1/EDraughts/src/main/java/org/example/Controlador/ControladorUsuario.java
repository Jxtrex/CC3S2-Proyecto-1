package org.example.Controlador;

import org.example.Modelo.ConexionSQL;
import org.example.Modelo.ConsultasUsuario;
import org.example.Modelo.ModeloUsuarios;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class ControladorUsuario extends ConexionSQL {
    private String usuario;
    private ConsultasUsuario consultasUsuario;
    private ModeloUsuarios modeloUsuarios;
    private static final int LONGITUD = 8;//longitud de contrasenia minima
    private static final int PBKDF2_ITERATIONS = 1000; //cantidad de iteraciones que usara el hash
    private static final int HASH_BYTE_SIZE = 256; // Tamaño del hash
    public ControladorUsuario() {

    }
    public boolean verificarExisteUsuario(String usuario){ //devuelve true si el usuario existe en la tabla Usuarios
        consultasUsuario = new ConsultasUsuario();//Si alguien sabe una manera practica de volverlo null al terminar la operacion me avisa

        if(consultasUsuario.verificarUsuarioEnUsuarios(usuario)){
            System.out.println("El nombre de usuario ya existe");
            return true;

        }
        else{
            return false;
        }

    }
    public boolean verificarCaracteristicasUsuario(String usuario){//Retorna true si cumple con seguridad y caracteristicas
        // Aqui podemos hacer las verificaciones sobre el usuario malicioso y la estructura(length>7)
        return false;
    }
    public boolean verificarContraseniaSegura(char[] contrasenia){//devuelve true si es segura
        //minimo 8 length, por lo menos 1 numero, 1 simbolo, 1 mayuscula

       if(contrasenia.length<LONGITUD){ //Verificamos longitud minima
           return false;
       }
       boolean tieneNumero = false;
       boolean tieneSimbolo = false;
       boolean tieneMayuscula = false;
       for(char c: contrasenia){//loopeamos sobre el array de characteres
           if(Character.isDigit(c)){ //Verificamos que tenga por lo menos 1 digito
               tieneNumero = true;
           } else if (Character.isUpperCase(c)) { //Verificamos por lo menos 1 mayuscula
               tieneMayuscula = true;
           } else if (!Character.isLetterOrDigit(c)) { //Verificamos por lo menos 1 simbolo
               tieneSimbolo = true;
           }
       }
        return tieneNumero && tieneSimbolo && tieneMayuscula;
    }
    public byte[] generarSalt(){
        // Genera "salt" aleatorio en bytes[] para poder generar un hash apropiado a la contraseña
        byte[] salt = new byte[512];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }// Considerando que este metodo no usa nada de aqui podriamos ponerlo en otra clase Utilidades
    public static String generarHash(byte[] salt, String contrasenia) throws NoSuchAlgorithmException, InvalidKeySpecException {
            //Genera el string Hash que sera guardado en la base de datos
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(contrasenia.toCharArray(),
                    salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);//configuramos con la cantidad de iteraciones y cantidad de bytes
            byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();//generamos el hash
            return Base64.getEncoder().encodeToString(hash);
    }
    public boolean registrarUsuario(String usuario, char[] contrasenia){
        //Retorna true si el registro del usuario fue exitoso
        consultasUsuario = new ConsultasUsuario();
        modeloUsuarios = new ModeloUsuarios();
        byte[] salt = generarSalt();
        String stringContrasenia = new String(contrasenia);//Si algo falla es esto, inicialmente habia trabajado con un String
        try {
            String hash = generarHash(salt,stringContrasenia);
            modeloUsuarios.setUsuario(usuario);
            modeloUsuarios.setHash(hash);
            modeloUsuarios.setSalt(salt);
            if(consultasUsuario.insertarUsuarios(modeloUsuarios)){
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    //Quiza deba modificarlo a que devuelva int 0 , 1, 2 para saber si fallo el usuario o la contraseña
    public boolean iniciarSesion(String usuario, char[] contrasenia){//retorna True si el inicio de sesion fue exitoso
        if(verificarExisteUsuario(usuario)){//1ro Verificamos si existe el usuario
            ModeloUsuarios modeloUsuariosSesion = new ModeloUsuarios();
            modeloUsuariosSesion.setUsuario(usuario);
            consultasUsuario = new ConsultasUsuario();
            if(consultasUsuario.leerSaltHash(modeloUsuariosSesion)){// Leemos el salt y hash que corresponden al usuario en la tabla Usuarios
                try {
                    //1ro calculamos el hash generado por la contrasenia dada y el salt de la tabla
                    String hashVerificacion = generarHash(modeloUsuariosSesion.getSalt(),String.valueOf(contrasenia));
                    //Comparamos si el hash generado coincide con el hash guardado en la tabla Usuarios
                    if(hashVerificacion.equals(modeloUsuariosSesion.getHash())){
                        return true;
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

}
