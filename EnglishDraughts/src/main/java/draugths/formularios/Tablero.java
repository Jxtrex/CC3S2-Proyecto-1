package draugths.formularios;

import javax.swing.*;

public class Tablero {
    private JButton botoón1HolaMundoButton;
    private JTextField textField1;

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
        textField1.setText("Hola mundo , este un mensaje");
    }
}
