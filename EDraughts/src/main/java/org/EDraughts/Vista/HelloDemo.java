package org.EDraughts.Vista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class HelloDemo extends JFrame{
    private JPanel panelMain;
    private JTextField txtName;
    private JButton btnClick;

    public HelloDemo() {

        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnClick,txtName.getText()+", Hello");
            }
        });
    }
    public void configuracionPrueba(HelloDemo helloprueba){
        helloprueba.setContentPane(helloprueba.panelMain);
        helloprueba.setTitle("Hello");
        helloprueba.setSize(300,400);
        helloprueba.setVisible(true);
        helloprueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
