package org.produccion.Controlador;

import org.produccion.Modelo.ConsultasUsuario;

import javax.swing.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ControladorUsuarioTest {
    ControladorUsuario controladorUsuario;
    ConsultasUsuario consultasUsuario;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        controladorUsuario = new ControladorUsuario();
         consultasUsuario = new ConsultasUsuario();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void verificarExisteUsuario() {
    }

    @org.junit.jupiter.api.Test
    void verificarCaracteresUsuarioValido() {
        // Arrange
        String stringPrueba= "usuariodeprueb_a123";
        // Act
        boolean resultado = controladorUsuario.verificarCaracteresUsuario(stringPrueba);

        // Assert
        assertEquals(true, resultado, "usuariodeprueb_a123 deberia ser un usuario valido");

    }
    @org.junit.jupiter.api.Test
    void verificarCaracteresUsuarioInvalido() {

        // Arrange
        String stringPrueba = "usuario_puedeFaller123#";
        // Act
        boolean resultado = controladorUsuario.verificarCaracteresUsuario(stringPrueba);

        // Assert
        assertEquals(false, resultado, "usuario_puedeFaller123# deberia ser un usuario invalido");

    }

    @org.junit.jupiter.api.Test
    void verificarContraseniaSegura() {
    }

    @org.junit.jupiter.api.Test
    void generarSalt() {
    }

    @org.junit.jupiter.api.Test
    void generarHash() {
    }

    @org.junit.jupiter.api.Test
    void registrarUsuario() {
    }

    @org.junit.jupiter.api.Test
    void verificarRegistroUsuarioExitoso() {
        // Arrange
        JTextField txtUserName = new JTextField();
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generate a random number between 0 and 100
        txtUserName.setText("probando_133213"+String.format("%03d", randomNumber));// Generamos un nombre de usuario "aleaotorio"
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTest1234#");
        JPasswordField txtConfirmarContrasenia = new JPasswordField();
        txtConfirmarContrasenia.setText("pruebaTest1234#");
        // Act
        int resultado = controladorUsuario.verificarRegistroUsuario(txtUserName,txtContrasenia,txtConfirmarContrasenia);
        // Assert
        assertEquals(1, resultado, "El registro fue exitoso");
    }
    @org.junit.jupiter.api.Test
    void verificarRegistroFallidoUsuarioExistente() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("invencible1234");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTest1234#");
        JPasswordField txtConfirmarContrasenia = new JPasswordField();
        txtConfirmarContrasenia.setText("pruebaTest1234#");
        // Act
        int resultado = controladorUsuario.verificarRegistroUsuario(txtUserName,txtContrasenia,txtConfirmarContrasenia);
        // Assert
        assertEquals(7, resultado, "El usuario ya existe, creacion de cuenta fallida");
    }
    @org.junit.jupiter.api.Test
    void verificarRegistroFallidoContraseniaInvalida() {
        // Arrange
        JTextField txtUserName = new JTextField();
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generate a random number between 0 and 100
        txtUserName.setText("probando_133213"+String.format("%03d", randomNumber));// Generamos un nombre de usuario "aleaotorio"
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("contraseniadeprueba");
        JPasswordField txtConfirmarContrasenia = new JPasswordField();
        txtConfirmarContrasenia.setText("contraseniadeprueba");
        // Act
        int resultado = controladorUsuario.verificarRegistroUsuario(txtUserName,txtContrasenia,txtConfirmarContrasenia);
        // Assert
        assertEquals(6, resultado, "La contraseña es invalida, creacion de cuenta fallida");
    }
    @org.junit.jupiter.api.Test
    void verificarRegistroFallidoUsuarioMalicioso() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("\\' or 1=1; --");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("contraseniadepruebaI124%");
        JPasswordField txtConfirmarContrasenia = new JPasswordField();
        txtConfirmarContrasenia.setText("contraseniadepruebaI124%");
        // Act
        int resultado = controladorUsuario.verificarRegistroUsuario(txtUserName,txtContrasenia,txtConfirmarContrasenia);
        // Assert
        assertEquals(4, resultado, "El usuario es malicioso, creacion de cuenta fallida");
    }

    @org.junit.jupiter.api.Test
    void verificarInicioExitoso() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("invencible1234");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTest123#");
        // Act
        int resultado = controladorUsuario.verificarInicioSesion(txtUserName,txtContrasenia);
        // Assert
        assertEquals(1, resultado, "El inicio de sesion fue exitoso");
    }
    @org.junit.jupiter.api.Test
    void verificarInicioFallidoUsuarioInexistente() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("invencible");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTest123#");
        // Act
        int resultado = controladorUsuario.verificarInicioSesion(txtUserName,txtContrasenia);
        // Assert
        assertEquals(5, resultado, "El usuario no existe, no se realizo el inicio de sesion");
    }
    @org.junit.jupiter.api.Test
    void verificarInicioFallidoContraseniaIncorrecta() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("invencible1234");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTs223#");
        // Act
        int resultado = controladorUsuario.verificarInicioSesion(txtUserName,txtContrasenia);
        // Assert
        assertEquals(6, resultado, "El usuario existe, pero la contraseña es incorrecta, no se realizo el inicio de sesion");
    }
    @org.junit.jupiter.api.Test
    void verificarInicioFallidoUsuarioMalicioso() {
        // Arrange
        JTextField txtUserName = new JTextField();

        txtUserName.setText("\\' or 1=1; --");
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setText("pruebaTs223#");
        // Act
        int resultado = controladorUsuario.verificarInicioSesion(txtUserName,txtContrasenia);
        // Assert
        assertEquals(3, resultado, "El usuario suministrado es malicioso, no se realizo el inicio de sesion");
    }

    @org.junit.jupiter.api.Test
    void verificarInicioSesion() {
    }
}