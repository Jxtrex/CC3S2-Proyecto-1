package org.produccion.Controlador;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;

import org.produccion.Modelo.ConexionSQL;
import org.produccion.Modelo.ConsultasUsuario;
import org.produccion.Modelo.ModeloUsuarios;

public class ControladorUsuario extends ConexionSQL {

    private ConsultasUsuario consultasUsuario;
    private ModeloUsuarios modeloUsuarios;
    private static final int LONGITUD_USUARIO = 5 ;
    private static final int LONGITUD_CONTRASENIA = 8;
    private static final int LONGITUD_MAXIMA_USUARIO = 20;
    private static final int PBKDF2_ITERATIONS = 1000;
    private static final int HASH_BYTE_SIZE = 256;

    public ControladorUsuario() {
    }

    public boolean verificarExisteUsuario(String usuario) {
        this.consultasUsuario = new ConsultasUsuario();
        if (this.consultasUsuario.verificarUsuarioEnUsuarios(usuario)) {
            System.out.println("El nombre de usuario ya existe");
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarCaracteresUsuario(String usuario) {
        return Pattern.matches("[a-zA-Z0-9_]+", usuario);
    }

    public boolean verificarContraseniaSegura(char[] contrasenia) {
        if (contrasenia.length < LONGITUD_CONTRASENIA) {
            return false;
        } else {
            boolean tieneNumero = false;
            boolean tieneSimbolo = false;
            boolean tieneMayuscula = false;
            char[] var5 = contrasenia;
            int var6 = contrasenia.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                char c = var5[var7];
                if (Character.isDigit(c)) {
                    tieneNumero = true;
                } else if (Character.isUpperCase(c)) {
                    tieneMayuscula = true;
                } else if (!Character.isLetterOrDigit(c)) {
                    tieneSimbolo = true;
                }
            }

            return tieneNumero && tieneSimbolo && tieneMayuscula;
        }
    }

    public byte[] generarSalt() {
        byte[] salt = new byte[512];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    public static String generarHash(byte[] salt, String contrasenia) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(contrasenia.toCharArray(), salt, 1000, 256);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean registrarUsuario(String usuario, char[] contrasenia) {
        this.consultasUsuario = new ConsultasUsuario();
        this.modeloUsuarios = new ModeloUsuarios();
        byte[] salt = this.generarSalt();
        String stringContrasenia = new String(contrasenia);

        try {
            String hash = generarHash(salt, stringContrasenia);
            this.modeloUsuarios.setUsuario(usuario);
            this.modeloUsuarios.setHash(hash);
            this.modeloUsuarios.setSalt(salt);
            return this.consultasUsuario.insertarUsuarios(this.modeloUsuarios);
        } catch (NoSuchAlgorithmException var6) {
            NoSuchAlgorithmException e = var6;
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException var7) {
            InvalidKeySpecException e = var7;
            throw new RuntimeException(e);
        }
    }


    public int verificarRegistroUsuario(JTextField txtUserName, JPasswordField txtContrasenia, JPasswordField txtConfirmarContrasenia){
        //Retorna un int que indique el estado del Registro
        String textoUsuario = txtUserName.getText();//Recibimos el valor del Usuario
        char[] contrasenia = txtContrasenia.getPassword();
        char[] confirmarContrasenia = txtConfirmarContrasenia.getPassword();
        if (txtUserName.getText().isEmpty()) {
            // Campo de nombre de usuario vacío
            return 2;
        }
        if (contrasenia.length == 0 || confirmarContrasenia.length == 0) {
            // Al menos una de las contraseñas está vacía
            return 2;
        }
        if (!Arrays.equals(contrasenia, confirmarContrasenia)) {
            // Las contraseñas no coinciden
            return 3;
        }
        if (!verificarCaracteresUsuario(textoUsuario)) {
            // Usuario no válido
            return 4;
        }
        if(textoUsuario.length()>LONGITUD_MAXIMA_USUARIO){
            // Usuario demasiado largo
            return 5;
        }

        if (!verificarContraseniaSegura(contrasenia)) {
            // Contraseña no segura
            return 6;
        }
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        if (controladorUsuario.verificarExisteUsuario(textoUsuario)) {
            // El nombre de usuario ya existe en la base de datos
            return 7;
        }
        // Registrar el usuario
        if (registrarUsuario(textoUsuario, contrasenia)) {
            return 1; // Registro exitoso
        } else {
            return 0; // Error al registrar el usuario
        }
    }
    public int iniciarSesion(String usuario, char[] contrasenia) {
        //Retornamos 0 si falla la consulta
        int estado = 0;
            ModeloUsuarios modeloUsuariosSesion = new ModeloUsuarios();
            modeloUsuariosSesion.setUsuario(usuario);
            this.consultasUsuario = new ConsultasUsuario();
            if (this.consultasUsuario.leerSaltHash(modeloUsuariosSesion)) {
                try {
                    String hashVerificacion = generarHash(modeloUsuariosSesion.getSalt(), String.valueOf(contrasenia));
                    if (hashVerificacion.equals(modeloUsuariosSesion.getHash())) {
                        estado = 1;
                        //Inicio de sesion exitoso
                    } else {
                        //Contraseñas no coinciden
                        estado = 2;
                    }
                } catch (NoSuchAlgorithmException var6) {
                    NoSuchAlgorithmException e = var6;
                    throw new RuntimeException(e);
                } catch (InvalidKeySpecException var7) {
                    InvalidKeySpecException e = var7;
                    throw new RuntimeException(e);
                }
            }
        return estado;
    }
    public int verificarInicioSesion(JTextField txtUserName,JPasswordField txtContrasenia){
        String textoUsuario = txtUserName.getText();//Recibimos el valor del Usuario
        char[] contrasenia = txtContrasenia.getPassword();
        if (txtUserName.getText().isEmpty()) {
            // Campo de nombre de usuario vacío
            return 2;
        }
        if (contrasenia.length==0) {
            // Campo de contraseña vacia
            return 2;
        }
        //Estas 2 verificaciones nos ahorran llamar a la DB
        if (!verificarCaracteresUsuario(textoUsuario)|| textoUsuario.length()>LONGITUD_MAXIMA_USUARIO) {
            // Usuario no válido
            return 3;
        }
        if (!verificarContraseniaSegura(contrasenia)) {
            // Contraseña no segura
            return 4;
        }
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        if (!controladorUsuario.verificarExisteUsuario(textoUsuario)) {
            // El nombre de usuario NO EXISTE
            return 5;
        }
        int estadoInicioSesion =iniciarSesion(textoUsuario, contrasenia);
        // Registrar el usuario
        return switch (estadoInicioSesion) {
            case 1 -> 1; // Inicio de sesión exitoso
            case 2 -> 6; // Contraseña incorrecta
            default -> 0; // Error al iniciar sesión
        };
    }
}
