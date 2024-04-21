package org.produccion.Visual.Tablero;

import org.produccion.Controlador.Partida;

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

    Partida partida = new Partida(this);
    PanelFichas [] panelFicha= new PanelFichas[32];
    public TableroForm() {

        panelFicha[0] = pnlFicha1;
        panelFicha[1] = pnlFicha2;
        panelFicha[2] = pnlFicha3;
        panelFicha[3] = pnlFicha4;
        panelFicha[4] = pnlFicha5;
        panelFicha[5] = pnlFicha6;
        panelFicha[6] = pnlFicha7;
        panelFicha[7] = pnlFicha8;
        panelFicha[8] = pnlFicha9;
        panelFicha[9] = pnlFicha10;
        panelFicha[10] = pnlFicha11;
        panelFicha[11] = pnlFicha12;
        panelFicha[12] = pnlFicha13;
        panelFicha[13] = pnlFicha14;
        panelFicha[14] = pnlFicha15;
        panelFicha[15] = pnlFicha16;
        panelFicha[16] = pnlFicha17;
        panelFicha[17] = pnlFicha18;
        panelFicha[18] = pnlFicha19;
        panelFicha[19] = pnlFicha20;
        panelFicha[20] = pnlFicha21;
        panelFicha[21] = pnlFicha22;
        panelFicha[22] = pnlFicha23;
        panelFicha[23] = pnlFicha24;
        panelFicha[24] = pnlFicha25;
        panelFicha[25] = pnlFicha26;
        panelFicha[26] = pnlFicha27;
        panelFicha[27] = pnlFicha28;
        panelFicha[28] = pnlFicha29;
        panelFicha[29] = pnlFicha30;
        panelFicha[30] = pnlFicha31;
        panelFicha[31] = pnlFicha32;


        partida.start();

        btnFicha1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha1);
            }
        });
        btnFicha2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha2.estado=pnlFicha2.estado*(-1);
                click(pnlFicha2);

            }
        });
        btnFicha3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha3.estado=pnlFicha3.estado*(-1);
                click(pnlFicha3);

            }
        });
        btnFicha4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha4.estado=pnlFicha4.estado*(-1);
                click(pnlFicha4);

            }
        });
        btnFicha5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha5.estado=pnlFicha5.estado*(-1);
                click(pnlFicha5);

            }
        });
        btnFicha6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha6.estado=pnlFicha6.estado*(-1);
                click(pnlFicha6);

            }
        });
        btnFicha7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha7.estado=pnlFicha7.estado*(-1);
                click(pnlFicha7);

            }
        });
        btnFicha8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha8.estado=pnlFicha8.estado*(-1);
                click(pnlFicha8);

            }
        });
        btnFicha9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha9.estado=pnlFicha9.estado*(-1);
                click(pnlFicha9);
            }
        });
        btnFicha10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha10.estado=pnlFicha10.estado*(-1);
                click(pnlFicha10);

            }
        });
        btnFicha11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha11.estado=pnlFicha11.estado*(-1);
                click(pnlFicha11);

            }
        });
        btnFicha12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha12.estado=pnlFicha12.estado*(-1);
                click(pnlFicha12);
            }
        });
        btnFicha13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click(pnlFicha13);
            }
        });
        btnFicha14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click(pnlFicha14);
            }
        });
        btnFicha15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                click(pnlFicha15);
            }
        });
        btnFicha16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha16);

            }
        });
        btnFicha17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha17);

            }
        });
        btnFicha18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha18);

            }
        });
        btnFicha19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha19);

            }
        });
        btnFicha20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha1.estado=pnlFicha1.estado*(-1);
                click(pnlFicha20);

            }
        });
        btnFicha21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha21.estado=pnlFicha21.estado*(-1);
                click(pnlFicha21);

            }
        });
        btnFicha22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha22.estado=pnlFicha22.estado*(-1);
                click(pnlFicha22);

            }
        });
        btnFicha23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha23.estado=pnlFicha23.estado*(-1);
                click(pnlFicha23);

            }
        });
        btnFicha24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha24.estado=pnlFicha24.estado*(-1);
                click(pnlFicha24);

            }
        });
        btnFicha25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha25.estado=pnlFicha25.estado*(-1);
                click(pnlFicha25);

            }
        });
        btnFicha26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha26.estado=pnlFicha26.estado*(-1);
                click(pnlFicha26);

            }
        });
        btnFicha27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha27.estado=pnlFicha27.estado*(-1);
                click(pnlFicha27);

            }
        });
        btnFicha28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha28.estado=pnlFicha28.estado*(-1);
                click(pnlFicha28);

            }
        });
        btnFicha29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha29.estado=pnlFicha29.estado*(-1);
                click(pnlFicha29);

            }
        });
        btnFicha30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha30.estado=pnlFicha30.estado*(-1);
                click(pnlFicha30);

            }
        });
        btnFicha31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha31.estado=pnlFicha31.estado*(-1);
                click(pnlFicha31);

            }
        });
        btnFicha32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pnlFicha32.estado=pnlFicha32.estado*(-1);
                click(pnlFicha32);

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panelTablero = new PanelFondoTablero();

          pnlFicha1 = new PanelFichas(1,0);
          pnlFicha2 = new PanelFichas(1,1);
          pnlFicha3 = new PanelFichas(1,2);
          pnlFicha4 = new PanelFichas(1,3);
          pnlFicha5 = new PanelFichas(1,4);
          pnlFicha6 = new PanelFichas(1,5);
          pnlFicha7 = new PanelFichas(1,6);
          pnlFicha8 = new PanelFichas(1,7);
          pnlFicha9 = new PanelFichas(1,8);
         pnlFicha10 = new PanelFichas(1,9);
        pnlFicha11 = new PanelFichas(1,10);
        pnlFicha12 = new PanelFichas(1,11);
        pnlFicha13 = new PanelFichas(0,12);
        pnlFicha14 = new PanelFichas(0,13);
        pnlFicha15 = new PanelFichas(0,14);
        pnlFicha16 = new PanelFichas(0,15);
        pnlFicha17 = new PanelFichas(0,16);
        pnlFicha18 = new PanelFichas(0,17);
        pnlFicha19 = new PanelFichas(0,18);
        pnlFicha20 = new PanelFichas(0,19);
        pnlFicha21 = new PanelFichas(2,20);
        pnlFicha22 = new PanelFichas(2,21);
        pnlFicha23 = new PanelFichas(2,22);
        pnlFicha24 = new PanelFichas(2,23);
        pnlFicha25 = new PanelFichas(2,24);
        pnlFicha26 = new PanelFichas(2,25);
        pnlFicha27 = new PanelFichas(2,26);
        pnlFicha28 = new PanelFichas(2,27);
        pnlFicha29 = new PanelFichas(2,28);
        pnlFicha30 = new PanelFichas(2,29);
        pnlFicha31 = new PanelFichas(2,30);
        pnlFicha32 = new PanelFichas(2,31);

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

    private void click(PanelFichas panel){

        //Traducimos el nroFicha del panel a un par ordenado (x,y)
        int x=7-panel.nroFicha/4;
        int y=2*(panel.nroFicha%4)+(panel.nroFicha/4)%2;


        //if(partida.Turno==1){ // Comentar para enviar las entradas por click en ambos turnos

            partida.posFichaSeleccionadaTablero =new int[]{x,y}; //Envia la ficha seleccionada en el tablero a la clase partida
            partida.notificarSelección(); // Notifica que ya se seleccionó una ficha

        //}


    }

    public void setPanelFicha(int nroPanel,int estado){ //Actualiza el estado del Panel con el número dado
        panelFicha[nroPanel].estado=estado;
    }
}
