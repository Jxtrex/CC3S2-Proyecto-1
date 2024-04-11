package pruebas;

import org.produccion.Controlador.ControladorUsuario;
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
    // AC-1.1 Creación exitosa de una cuenta de usuario válida
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
    //AC-1.2 Creación de una cuenta fallida con un nombre de usuario existente
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
    //AC-1.3 Creación de una cuenta fallida con una contraseña no válida
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
    //AC-1.4 Prevención de creación de cuenta con un nombre de usuario malicioso
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
    //AC-3.1 Inicio de sesión exitoso con credenciales válidas
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
    //AC-3.2 Inicio de sesión fallido con un nombre de usuario que no existe
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
    //AC-3.3 Inicio de sesión fallido con una contraseña incorrecta
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
    //AC-3.4 Prevención de inicios de sesión con un nombre de usuario malicioso
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



}