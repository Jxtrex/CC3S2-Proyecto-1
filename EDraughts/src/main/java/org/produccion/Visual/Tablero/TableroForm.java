package org.produccion.Visual.Tablero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableroForm extends JFrame{
    private JPanel panelTablero;
    private PanelFichas pnlFicha1;
    private PanelFichas pnlFicha2;
    private PanelFichas pnlFicha3;
    private PanelFichas pnlFicha4;
    private PanelFichas pnlFicha5;
    private PanelFichas pnlFicha6;
    private PanelFichas pnlFicha7;
    private PanelFichas pnlFicha8;
    private PanelFichas pnlFicha9;
    private PanelFichas pnlFicha10;
    private PanelFichas pnlFicha11;
    private PanelFichas pnlFicha12;
    private PanelFichas pnlFicha13;
    private PanelFichas pnlFicha14;
    private PanelFichas pnlFicha15;
    private PanelFichas pnlFicha16;
    private PanelFichas pnlFicha17;
    private PanelFichas pnlFicha18;
    private PanelFichas pnlFicha19;
    private PanelFichas pnlFicha20;
    private PanelFichas pnlFicha21;
    private PanelFichas pnlFicha22;
    private PanelFichas pnlFicha23;
    private PanelFichas pnlFicha24;
    private PanelFichas pnlFicha25;
    private PanelFichas pnlFicha26;
    private PanelFichas pnlFicha27;
    private PanelFichas pnlFicha28;
    private PanelFichas pnlFicha29;
    private PanelFichas pnlFicha30;
    private PanelFichas pnlFicha31;
    private PanelFichas pnlFicha32;
    private JButton btnFicha1;
    private JButton btnFicha2;
    private JButton btnFicha3;
    private JButton btnFicha4;
    private JButton btnFicha5;
    private JButton btnFicha6;
    private JButton btnFicha7;
    private JButton btnFicha8;
    private JButton btnFicha9;
    private JButton btnFicha10;
    private JButton btnFicha11;
    private JButton btnFicha12;
    private JButton btnFicha13;
    private JButton btnFicha14;
    private JButton btnFicha15;
    private JButton btnFicha16;
    private JButton btnFicha17;
    private JButton btnFicha18;
    private JButton btnFicha19;
    private JButton btnFicha20;
    private JButton btnFicha21;
    private JButton btnFicha22;
    private JButton btnFicha23;
    private JButton btnFicha24;
    private JButton btnFicha25;
    private JButton btnFicha26;
    private JButton btnFicha27;
    private JButton btnFicha28;
    private JButton btnFicha29;
    private JButton btnFicha30;
    private JButton btnFicha31;
    private JButton btnFicha32;


    public TableroForm() {
        btnFicha1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnlFicha9.estado == 1){
                    pnlFicha9.estado = 2;
                }else if(pnlFicha9.estado == 2){
                    pnlFicha9.estado = 3;
                }else if(pnlFicha9.estado == 3){
                    pnlFicha9.estado = 4;
                }else if(pnlFicha9.estado == 4){
                    pnlFicha9.estado = 0;
                }else if(pnlFicha9.estado == 0){
                    pnlFicha9.estado = 1;
                }


            }
        });
        btnFicha10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFicha32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelTablero = new PanelFondoTablero();

        pnlFicha1 = new PanelFichas(1);
        pnlFicha2 = new PanelFichas(1);
        pnlFicha3 = new PanelFichas(1);
        pnlFicha4 = new PanelFichas(1);
        pnlFicha5 = new PanelFichas(1);
        pnlFicha6 = new PanelFichas(1);
        pnlFicha7 = new PanelFichas(1);
        pnlFicha8 = new PanelFichas(1);
        pnlFicha9 = new PanelFichas(1);
        pnlFicha10 = new PanelFichas(1);
        pnlFicha11 = new PanelFichas(1);
        pnlFicha12 = new PanelFichas(1);
        pnlFicha13 = new PanelFichas(0);
        pnlFicha14 = new PanelFichas(0);
        pnlFicha15 = new PanelFichas(0);
        pnlFicha16 = new PanelFichas(0);
        pnlFicha17 = new PanelFichas(0);
        pnlFicha18 = new PanelFichas(0);
        pnlFicha19 = new PanelFichas(0);
        pnlFicha20 = new PanelFichas(0);
        pnlFicha21 = new PanelFichas(2);
        pnlFicha22 = new PanelFichas(2);
        pnlFicha23 = new PanelFichas(2);
        pnlFicha24 = new PanelFichas(2);
        pnlFicha25 = new PanelFichas(2);
        pnlFicha26 = new PanelFichas(2);
        pnlFicha27 = new PanelFichas(2);
        pnlFicha28 = new PanelFichas(2);
        pnlFicha29 = new PanelFichas(2);
        pnlFicha30 = new PanelFichas(2);
        pnlFicha31 = new PanelFichas(2);
        pnlFicha32 = new PanelFichas(2);


    }

    public void setConfig(){
        this.setContentPane(panelTablero);
        this.setTitle("E-Draughts");
        this.setSize(650,648);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //fLogin.dispose();//Cierra solo la ventana
    }
}
