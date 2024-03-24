package draugths.formularios;

import javax.swing.*;

public class Board extends JFrame {
    private JButton botoón1HolaMundoButton;
    private JTextField textField1;

    public Board(){
        setTitle("English Draughts");
        setSize(480,360);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Board myBoard = new Board();
    }
}
