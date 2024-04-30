package org.produccion.Controlador;

import org.junit.jupiter.api.Test;
import org.produccion.Modelo.Casilla;
import org.produccion.Modelo.Tablero1;
import org.produccion.Visual.Tablero.PanelFichas;
import org.produccion.Visual.Tablero.TableroForm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partida extends Thread{
  private Tablero1 tablero = new Tablero1(); //Crea un nuevo tablero vacío (Sin fichas).
  private ArrayList<int[]> FichasP1pos = new ArrayList<>(); // Lista de int[2] que contiene las posiciones de las fichas del jugador 1
  private ArrayList<int[]> FichasP2pos = new ArrayList<>(); // Lista de int[2] que contiene las posiciones de las fichas del jugador 2

  private int[] fichaSeleccionada; ////Contiene la posición de la ficha seleccionada correctamente
  public int[] posFichaSeleccionadaTablero; //Contiene la posición de la ficha seleccionada en el tablero.
  private ArrayList<int[]> FMD= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las fichas con movimiento disponible
  private ArrayList<int[]> CD= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las casillas disponibles para colocar
  // la ficha seleccionada, o las posiciones de las capturas disponibles(fichas del oponente).
  private ArrayList<int[]> FPC= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las Fichas que Pueden Capturar

  public int Turno; // Indica el turno del jugador (1 o 2).

  private boolean corona; //boolean que indica si la ficha corona

  private String eDJ=""; // string Estado de juego que se imprimirá en el txtPanel

  TableroForm tableroForm; // Referencia al formulario del tablero (Visual).

  public Partida(TableroForm tableroForm){ //Constructor de la partida// Recibe como parámetro al formulario del tablero.
    colocarFichas(1,1); // Coloca las fichas del jugador 1 desde la perspectiva del jugador 1
    colocarFichas(2,1); // Coloca las fichas del jugador 2 desde la perspectiva del jugador 1

    imprimirFichas(1); //Muestra las fichas del jugador 1 en consola
    imprimirFichas(2); //Muestra las fichas del jugador 2 en consola

    this.tableroForm=tableroForm;
  }

  protected void imprimirFichas(int P) { //Muestra las fichas del jugador P en consola
    if (P == 1){ // Si P es el jugador 1
      System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
      if(FichasP1pos.size()!=0) { // Si la lista no está vacía, entonces se imprime cada ficha.
        for (int i = 0; i < FichasP1pos.size(); i++) { // Se recorre la lista de posiciones de las fichas del jugador 1
          System.out.println(tablero.getCasilla(FichasP1pos.get(i)[0], FichasP1pos.get(i)[1]));
        }
      }else{System.out.println("No hay fichas del jugador 1 en el tablero.");}// Si la lista está vacía...
  }
    if (P == 2){// Si P es el jugador 2
      System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
      if(FichasP2pos.size()!=0) {// Si la lista no está vacía, entonces se imprime cada ficha.
        for (int i = 0; i < FichasP2pos.size(); i++) { // Se recorre la lista de posiciones de las fichas del jugador 2
          System.out.println(tablero.getCasilla(FichasP2pos.get(i)[0], FichasP2pos.get(i)[1]));
        }
      }else{System.out.println("No hay fichas del jugador 2 en el tablero.");}// Si la lista está vacía...
    }
  }
  private void colocarFichas(int P,int VP){ // P : Jugador,   VP:  Vista del jugador
    if(VP==1){ //Si la vista del tablero es del jugador 1
      if(P==2){ //Las fichas del jugador 2 (Rojas) se colocan en la parte superior
        for (int n=0; n<12; n++){ // Se recorren 12 fichas
            //Se calcula los índices (i,j) de la posición de la ficha 'n'
          int i=n/4;
          int j=2*(n%4)+(1-i%2);
          // Se coloca la ficha Roja en el tablero
          tablero.placeDraugth(i,j, Casilla.CellState.RED);
          //Añadimos la posición de la ficha del jugador 2 a la lista de posiciones
          int [] posicion={i,j};
          FichasP2pos.add(posicion);

        }
      }//Las fichas del jugador 1 (Negras) se colocan en la parte inferior
      if(P==1){ //Las fichas del jugador 1 (Negras) se colocan en la parte inferior
        for (int n=20; n<32; n++){ // Se recorren 12 fichas
            //Se calcula los índices (i,j) de la posición de la ficha 'n'
          int i=n/4;
          int j=2*(n%4)+(1-i%2);
            // Se coloca la ficha Negra en el tablero
          tablero.placeDraugth(i,j, Casilla.CellState.BLACK);
            //Añadimos la posición de la ficha del jugador 1 a la lista de posiciones
          int [] posicion={i,j};
          FichasP1pos.add(posicion);
        }
      }
    }

    if(VP==2){ //Si la vista del tablero es del jugador 2
      if(P==1) {//Las fichas del jugador 1 (Negras) se colocan en la parte superior
        for (int n = 0; n < 12; n++) {// Se recorren 12 fichas
          tablero.placeDraugth(n / 4, 2 * (n % 4) + (1 - (n / 4) % 2), Casilla.CellState.BLACK);

        }
      }
      if(P==2) {//Las fichas del jugador 2 (Rojas) se colocan en la parte inferior
        for (int n = 20; n < 32; n++) {// Se recorren 12 fichas
          tablero.placeDraugth(n / 4, 2 * (n % 4) + (1 - (n / 4) % 2), Casilla.CellState.RED);
        }
      }
    }
  }

  private boolean getFPC(int P){ // Muestra las posiciones de las Fichas que Pueden Capturar
    FPC.clear(); // Se limpia la lista FPC

    if(P==1){// Si es el turno del jugador 1

      System.out.println("\nFichas del jugador 1 que pueden capturar:");

      // string Estado de juego que se imprimirá en el txtPanel
      eDJ=eDJ+"Turno del Jugador 1";
      eDJ=eDJ+"\nFichas que pueden capturar:"+"\n";

      for (int[] fichaPos: FichasP1pos) { // Recorremos la lista de posiciones de las fichas del jugador 1
        int i = fichaPos[0]; // Posicion X: nro de fila de la ficha
        int j = fichaPos[1]; // Posicion Y: nro de columna de la ficha
        boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";
              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";
              continue;
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas superiores (i-1)
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
        }

      }
    }
    if(P==2){// Si es el turno del jugador 2
      System.out.println("\nFichas del jugador 2 que pueden capturar:");

      // string Estado de juego que se imprimirá en el txtPanel
      eDJ=eDJ+"Turno del Jugador 2";
      eDJ=eDJ+"\nFichas que pueden capturar:"+"\n";

      for (int[] fichaPos: FichasP2pos) { // Recorremos la lista de posiciones de las fichas del jugador 2
        int i = fichaPos[0]; // Posicion X: nro de fila de la ficha
        int j = fichaPos[1]; // Posicion Y: nro de columna de la ficha
        boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";
            }
          }
        }

      }
    }

    if(FPC.size()!=0){//
      return true;
    }else{
      System.out.println("No hay capturas disponibles.");
      return false;
    }
  }

  private boolean isInFPC(int[] posicion){// Verifica si la posicion ingresada está en FPC
    for(int[] e : FPC ){ //Recorre la lista FPC
      if(Arrays.equals(posicion,e)){return true;} //Si la posición ingresada es igual a alguna posición
        // de la lista, entonces devuelve true
    }
    return false;// Si ninguna posición en la lista coincide con la posición ingresada, devuelve false.
  }

  private boolean isInCD(int[] posicion){// Verifica si la posicion está en CD
    for(int[] e : CD ){ //Recorre la lista CD
      if(Arrays.equals(posicion,e)){return true;} //Si la posición ingresada es igual a alguna posición
        // de la lista, entonces devuelve true
    }
    return false; // Si ninguna posición en la lista coincide con la posición ingresada, devuelve false.
  }

  private boolean isInFMD(int[] posicion){ // Verifica si la posicion está en FMD
    for(int[] e : FMD ){ //Recorre la lista FMD
      if(Arrays.equals(posicion,e)){return true;} //Si la posición ingresada es igual a alguna posición
        // de la lista, entonces devuelve true
    }
    return false; // Si ninguna posición en la lista coincide con la posición ingresada, devuelve false.
  }


  private boolean getFMD(int P){ // Muestra las posiciones de las Fichas con Movimientos Disponibles
    FMD.clear(); //Limpia la lista FMD

    if(P==1){// Si es el turno del jugador 1
      System.out.println("\nFichas del jugador 1 con movimiento disponible:");

      // string Estado de juego que se imprimirá en el txtPanel
      eDJ=eDJ+"Turno del Jugador 1";
      eDJ=eDJ+"\nFichas que se pueden mover:"+"\n";

      for (int[] fichaPos: FichasP1pos) { // Recorremos la lista de posiciones de las fichas del jugador 1
        int i = fichaPos[0]; // Posicion X: nro de fila de la ficha
        int j = fichaPos[1]; // Posicion Y: nro de columna de la ficha
        boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i!=0 && j!=0){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas superiores (i-1)
          if(i!=0 && j!=0){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
        }

      }
    }
    if(P==2){// Si es el turno del jugador 2
      System.out.println("\nFichas del jugador 2 con movimiento disponible:");

      // string Estado de juego que se imprimirá en el txtPanel
      eDJ=eDJ+"Turno del Jugador 2";
      eDJ=eDJ+"\nFichas que se pueden mover:"+"\n";

      for (int[] fichaPos: FichasP2pos) { // Recorremos la lista de posiciones de las fichas del jugador 2
        int i = fichaPos[0]; // Posicion X: nro de fila de la ficha
        int j = fichaPos[1]; // Posicion Y: nro de columna de la ficha
        boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i!=0 && j!=0){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));

              // string Estado de juego que se imprimirá en el txtPanel
              eDJ=eDJ+tablero.getCasilla(i,j)+"\n";

            }
          }
        }

      }
    }

    if(FMD.size()!=0){//
      return true;
    }else{
      System.out.println("No hay fichas con movimientos disponibles!!");
      return false;
    }
  }
  private boolean seleccionarFichaDeMovimiento(int i, int j, int P){ // Se selecciona una ficha para efectuar su movimiento y muestra todas las casillas disponibles para colocarla.

    CD.clear(); // Limpia la lista CD

    fichaSeleccionada= new int[]{i, j};

    System.out.println("\nFicha seleccionada para mover:");
    System.out.println(tablero.getCasilla(i,j));

    System.out.println("\nCasillas disponibles para mover la ficha:");

    boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

    if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
      if(i!=0 && j!=0){//Revisa superior izquierda
        if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
          // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces es una casilla disponible.
          int [] fichaPos= new int[]{i - 1, j - 1};
          CD.add(fichaPos);
          System.out.println(tablero.getCasilla(i-1, j-1));
        }
      }
      if(i!=0 && j!=7) {//Revisa superior derecha
        if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
          // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces es una casilla disponible.
          int [] fichaPos= new int[]{i - 1, j + 1};
          CD.add(fichaPos);
          System.out.println(tablero.getCasilla(i - 1, j + 1));
        }
      }
      if(i!=7 && j!=0) {//Revisa inferior izquierda
        if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
          // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces es una casilla disponible.
          int [] fichaPos= new int[]{i+1, j-1};
          CD.add(fichaPos);
          System.out.println(tablero.getCasilla(i+1, j-1));
        }
      }
      if(i!=7 && j!=7) {//Revisa inferior derecha
        if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
          // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces es una casilla disponible.
          int [] fichaPos= new int[]{i+1, j+1};
          CD.add(fichaPos);
          System.out.println(tablero.getCasilla(i+1, j+1));
        }
      }
    } else {
      if(P==1) {
        // Si la ficha no es rey, solo verificamos las casillas superiores (i-1)
        if (i != 0 && j != 0) {//Revisa superior izquierda
          if (tablero.getCasilla(i - 1, j - 1).getSTATE() == Casilla.CellState.EMPTY) {
            // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces es una casilla disponible.
            int[] fichaPos = new int[]{i - 1, j - 1};
            CD.add(fichaPos);
            System.out.println(tablero.getCasilla(i - 1, j - 1));
          }
        }
        if (i != 0 && j != 7) {//Revisa superior derecha
          if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
            // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces es una casilla disponible.
            int[] fichaPos = new int[]{i - 1, j + 1};
            CD.add(fichaPos);
            System.out.println(tablero.getCasilla(i - 1, j + 1));
          }
        }
      }
      if(P==2) {
        // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
        if(i!=7 && j!=0) {//Revisa inferior izquierda
          if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
            // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces es una casilla disponible.
            int [] fichaPos= new int[]{i+1, j-1};
            CD.add(fichaPos);
            System.out.println(tablero.getCasilla(i+1, j-1));
          }
        }
        if(i!=7 && j!=7) {//Revisa inferior derecha
          if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
            // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces es una casilla disponible.
            int [] fichaPos= new int[]{i+1, j+1};
            CD.add(fichaPos);
            System.out.println(tablero.getCasilla(i+1, j+1));
          }
        }
      }
    }
    if(CD.size()!=0){//
      return true;
    }else{
      System.out.println("No hay casillas disponibles para mover la ficha seleccionada!!");
      return false;
    }
  }
  private boolean seleccionarFichaDeCaptura(int i, int j, int P){
    CD.clear();

    fichaSeleccionada= new int[]{i, j};

    System.out.println("\nFicha seleccionada para realizar captura:");
    System.out.println(tablero.getCasilla(i,j));

    System.out.println("\nFichas que se pueden capturar:");
    boolean isKing = tablero.getCasilla(i,j).isKing(); // Verifica si es rey (True/False)

    if(P==1){// Si es el turno del jugador 1

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i - 1, j - 1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i-1, j-1));
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i - 1, j + 1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i - 1, j + 1));
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j-1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j-1));
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j+1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j+1));
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas superiores (i-1)
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i-1, j-1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i-1, j-1));
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i - 1, j + 1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i - 1, j + 1));
            }
          }
        }


    }
    if(P==2){// Si es el turno del jugador 2

        if(isKing){// Si la ficha es rey, verificamos las casillas superiores e inferiores.
          if(i>1 && j>1){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i-2, j-2).getSTATE() == Casilla.CellState.EMPTY)
            {
              // Si la casilla superior(i-1) izquierda(j-1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j-2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i - 1, j - 1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i-1, j-1));
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              int [] fichaPos= new int[]{i - 1, j + 1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i - 1, j + 1));
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j-1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j-1));
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j+1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j+1));
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j-1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j-1));
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              int [] fichaPos= new int[]{i+1, j+1};
              CD.add(fichaPos);
              System.out.println(tablero.getCasilla(i+1, j+1));
            }
          }
        }


    }

    if(CD.size()!=0){//
      return true;
    }else{
      System.out.println("No hay fichas para capturar.");
      return false;
    }

  }
  private void moverFichaSeleccionada(int i, int j,int P){ // (i,j) posición de la casilla objetivo
    corona=false;

    System.out.println("\nFicha seleccionada movida a la casilla:");
    System.out.println(tablero.getCasilla(i,j));

    if(P==1){
      boolean isKing=tablero.getCasilla(fichaSeleccionada[0],fichaSeleccionada[1]).isKing();

      //Coloco el estado de la ficha seleccionada a la celda indicada
      tablero.placeDraugth(i,j, Casilla.CellState.BLACK);
      // Se asigna si es rey o no
      if(isKing || i==0){tablero.getCasilla(i,j).setKing(true); // Si la ficha anterior era Rey o el jugador llega a la frontera superior
        if(!isKing){
          corona=true;// si la ficha no era Rey entonces se corona
          System.out.println("La ficha se corona!\n");
        }

        //Actualizamos la casilla del tableroForm colocando la ficha como Rey
        tableroForm.setPanelFicha(4*(7-i)+j/2,P+2);
        tableroForm.repaint();

      }else{
        //Actualizamos la casilla del tableroForm colocando la ficha normal
        tableroForm.setPanelFicha(4*(7-i)+j/2,P);
        tableroForm.repaint();
      }

      //Actualiza FichasP1pos
      int ind=-1;
      for(int k=0;k<FichasP1pos.size();k++){
        int posfichax=FichasP1pos.get(k)[0];
        int posfichay=FichasP1pos.get(k)[1];
        if(posfichax==fichaSeleccionada[0] && posfichay==fichaSeleccionada[1]){
          ind=k;
          break;
        }
      }
      FichasP1pos.set(ind,new int[]{i, j}); //Actualiza la posición de la ficha movida

      //Limpia la casilla anterior del tablero
      tablero.placeDraugth(fichaSeleccionada[0], fichaSeleccionada[1], Casilla.CellState.EMPTY);
      tablero.getCasilla(fichaSeleccionada[0], fichaSeleccionada[1]).setKing(false);

      //Limpia casilla del tableroForm
      tableroForm.setPanelFicha(4*(7-fichaSeleccionada[0])+fichaSeleccionada[1]/2,-1);
      tableroForm.repaint();


      //Actualiza la ficha seleccionada
      fichaSeleccionada= new int[]{i, j};

    }

    if(P==2){
      boolean isKing=tablero.getCasilla(fichaSeleccionada[0],fichaSeleccionada[1]).isKing(); // ficha seleccionada is king?

      //Coloco el estado de la ficha seleccionada a la celda indicada
      tablero.placeDraugth(i,j, Casilla.CellState.RED);
      // Se asigna si es rey o no
      if(isKing || i==7){tablero.getCasilla(i,j).setKing(true);// Si la ficha anterior era Rey o el jugador llega a la frontera inferior
        if(!isKing){
          corona=true; // si la ficha no era Rey entonces se corona
          System.out.println("La ficha se corona!\n");}

        //Actualizamos la casilla del tableroForm colocando la ficha como Rey
        tableroForm.setPanelFicha(4*(7-i)+j/2,P+2);
        tableroForm.repaint();
      }else{
        //Actualizamos la casilla del tableroForm colocando la ficha normal
        tableroForm.setPanelFicha(4*(7-i)+j/2,P);
        tableroForm.repaint();
      }

      //Actualiza FichasP2pos
      int ind=-1;
      for(int k=0;k<FichasP2pos.size();k++){
        int posfichax=FichasP2pos.get(k)[0];
        int posfichay=FichasP2pos.get(k)[1];
        if(posfichax==fichaSeleccionada[0] && posfichay==fichaSeleccionada[1]){
          ind=k;
          break;
        }
      }
      FichasP2pos.set(ind,new int[]{i, j}); //Actualiza la posición de la ficha movida

      //Limpia la casilla anterior del tablero
      tablero.placeDraugth(fichaSeleccionada[0], fichaSeleccionada[1], Casilla.CellState.EMPTY);
      tablero.getCasilla(fichaSeleccionada[0], fichaSeleccionada[1]).setKing(false);

      //Limpia casilla del tableroForm
      tableroForm.setPanelFicha(4*(7-fichaSeleccionada[0])+fichaSeleccionada[1]/2,-1);
      tableroForm.repaint();

      //Actualiza la ficha seleccionada
      fichaSeleccionada= new int[]{i, j};

    }
  }

  private void capturarFicha(int i, int j , int P){

    if(P==1){

      //Actualiza FichasP2pos eliminando la posicion ficha capturada
      int ind=-1;
      for(int k=0;k<FichasP2pos.size();k++){
        int posfichax=FichasP2pos.get(k)[0];
        int posfichay=FichasP2pos.get(k)[1];
        if(posfichax==i && posfichay==j){
          ind=k;
          break;
        }
      }
      FichasP2pos.remove(ind); // Elimina la posicion de la ficha capturada disminuyendo el nro de fichas

      moverFichaSeleccionada(2*i-fichaSeleccionada[0], 2*j-fichaSeleccionada[1],P);

      System.out.println("Ficha capturada:");
      System.out.println(tablero.getCasilla(i,j)); //Muestra la ficha capturada

      //Limpia la casilla de la ficha eliminada
      tablero.placeDraugth(i, j, Casilla.CellState.EMPTY);
      tablero.getCasilla(i, j).setKing(false);

      //Limpia casilla del tableroForm
      tableroForm.setPanelFicha(4*(7-i)+j/2,-1);
      tableroForm.repaint();

    }
    if(P==2){

      //Actualiza FichasP1pos eliminando la posicion ficha capturada
      int ind=-1;
      for(int k=0;k<FichasP1pos.size();k++){
        int posfichax=FichasP1pos.get(k)[0];
        int posfichay=FichasP1pos.get(k)[1];
        if(posfichax==i && posfichay==j){
          ind=k;
          break;
        }
      }
      FichasP1pos.remove(ind); // Elimina la posicion de la ficha capturada disminuyendo el nro de fichas

      moverFichaSeleccionada(2*i-fichaSeleccionada[0], 2*j-fichaSeleccionada[1],P);

      System.out.println("Ficha capturada:");
      System.out.println(tablero.getCasilla(i,j)); //Muestra la ficha capturada

      //Limpia la casilla de la ficha eliminada
      tablero.placeDraugth(i, j, Casilla.CellState.EMPTY);
      tablero.getCasilla(i, j).setKing(false);

      //Limpia casilla del tableroForm
      tableroForm.setPanelFicha(4*(7-i)+j/2,-1);
      tableroForm.repaint();

    }



  }


  // Método para cambiar la condición
  public synchronized void notificarSelección() {
    // Notificar al hilo que la condición ha cambiado. Condición: fichaSeleccionadaTablero==null
    notify();
  }

  private int[] obtenerPosiciónVálida(){
    int [] posicion={-1,-1};
    /*
  // ---------- Entrada del jugador 2 por teclado - Entrada del jugador 1 por clicks ----------------
    if(Turno==1){
      synchronized (this) {
        while (posFichaSeleccionadaTablero ==null) {
          try {
            // El hilo espera hasta que se cumpla la condición
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        // Aquí se ejecuta el código una vez que se cumple la condición
        //System.out.println("Se seleccionó una ficha y se salió del while");
      }
      posicion[0]= posFichaSeleccionadaTablero[0];
      posicion[1]= posFichaSeleccionadaTablero[1];
      posFichaSeleccionadaTablero =null;

    }else {
      Scanner sc = new Scanner(System.in); //Se crea el lector

      /// Debemos verificar que la posición ingresada sea válida!!
      String posicionString = sc.nextLine(); //Se lee la posición con nextLine() que retorna un String con el dato
      posicion =Partida.traducirStringParOrdenado(posicionString); //traducimos el string a int[2]
    }
    // ----------------------------------------------------------------------------
    */

    // ---------- Entrada de ambos jugadores por clicks ----------------
    synchronized (this) {
      while (posFichaSeleccionadaTablero ==null) {
        try {
          // El hilo espera hasta que se cumpla la condición
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // Aquí se ejecuta el código una vez que se cumple la condición
      //System.out.println("Se seleccionó una ficha y se salió del while");
    }
    // ----------------------------------------------------------------------------

    /*
    // ---------- Entrada de ambos jugadores por teclado ----------------
    Scanner sc = new Scanner(System.in); //Se crea el lector

    /// Debemos verificar que la posición ingresada sea válida!!
    String posicionString = sc.nextLine(); //Se lee la posición con nextLine() que retorna un String con el dato
    posicion =Partida.traducirStringParOrdenado(posicionString); //traducimos el string a int[2]
    // ----------------------------------------------------------------------------
    */


    posicion[0]= posFichaSeleccionadaTablero[0];
    posicion[1]= posFichaSeleccionadaTablero[1];
    posFichaSeleccionadaTablero =null;
    // ----------------------------------------------------------------------------


    //int posicionInt = sc.nextInt();
    //int[] posicion = new int[]{posicionInt/10,posicionInt%10};

    if(isValid(posicion)){
      return posicion;
    }else{
      System.out.println("Entrada inválida!!  Vuelva a ingresar la posición:");
      return obtenerPosiciónVálida();
    }

  }

  private boolean isValid(int [] posicion){
    // Usar REGEX
    return true;
  } /// Terminar!!

  private int[] obtenerPosiciónFPC(){ //Verifica que la posición ingresada sea válida y se encuentre en la lista FPC.

    int[] posicion = obtenerPosiciónVálida(); // Es una posición válida

    if(isInFPC(posicion)){return posicion;}//la posición ingresada se encuentra en la lista FPC.
    else{
      System.out.println("No puedes seleccionar esta ficha!! Ingresa una de las posiciones mostradas:");
      return obtenerPosiciónFPC(); //Vuelve a pedir la posición
    }
  }

  private int[] obtenerPosiciónFMD(){ //Verifica que la posición ingresada sea válida y se encuentre en la lista FMD.

    int[] posicion = obtenerPosiciónVálida(); // Es una posición válida

    if(isInFMD(posicion)){return posicion;}//la posición ingresada se encuentra en la lista FMD.
    else{
      System.out.println("No puedes seleccionar esta ficha!! Ingresa una de las posiciones mostradas:");
      return obtenerPosiciónFMD(); //Vuelve a pedir la posición
    }
  }

  private void MovimientoDeCapturaLegal(int P){
    /* Una vez seleccionada la ficha capturadora, se muestra las fichas enemigas disponibles a capturar
    * y nos pide seleccionar alguna para capturarla*/
    ////---------- Movimiento de captura con la ficha seleccionada ------------///////////
    System.out.println("\nIngrese la posición de la ficha que desea capturar:");

    int [] posicion= obtenerPosiciónVálida(); //Obtiene una posición válida
    //Verificaremos que la posición ingresada sea válida y se encuentre en la lista CD o FPC.

    if(isInFPC(posicion)){// Si la posición ingresada isValid(posicion) && isInFPC(posicion):
      seleccionarFichaDeCaptura(posicion[0], posicion[1], P);
      // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
      MovimientoDeCapturaLegal(P);
    }
    else if(!isInCD(posicion)){//else , si la posición ingresada isValid(posicion) && !isInCD(posicion),
      // Avisa que No se puede capturar la ficha de esa posición
      System.out.println("\nCaptura inválida!! Asegúrate de ingresar una de las posiciones mostradas..");
      // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
      MovimientoDeCapturaLegal(P);
    }
    else if(isInCD(posicion)){//else , si la posición ingresada isValid(posicion) && isInCD(posicion),
      //    entonces captura la ficha:
      FPC.clear();
      capturarFicha(posicion[0], posicion[1], P);
      //Verifica si la ficha se corona o si hay más capturas disponibles desde la ficha seleccionada
      if(corona){// si la ficha se corona entonces termina la jugada
        cambiarTurno();
        //Verificamos la cantidad de fichas
        System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
        System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
        juega(Turno);
      }else if(!seleccionarFichaDeCaptura(fichaSeleccionada[0],fichaSeleccionada[1],P)){
        // si ya no hay más capturas entonces termina la jugada
        cambiarTurno();
        //Verificamos la cantidad de fichas
        System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
        System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
        juega(Turno);
      }else{// Si hay más capturas, vuelve a iniciar el Movimiento de captura con la ficha seleccionada
        // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
        MovimientoDeCapturaLegal(P);
      }
    }

    //----------- Termina el Movimiento de captura con la ficha seleccionada --------------
  }
  private void MovimientoLegal(int P){
    /*Una vez seleccionada la ficha para mover, se pide ingresar la posición de la casilla
    donde deseamos colocarla.*/

    //------- Movimiento legal----------

    System.out.println("\nIngrese la posición de la casilla donde desea mover la ficha:");
    int [] posicion = obtenerPosiciónVálida();

    //Verificaremos que la posición ingresada sea válida y se encuentre en la lista CD o FMD.

    if(isInFMD(posicion))// Si la posición está en FMD:
    {
      seleccionarFichaDeMovimiento(posicion[0], posicion[1], P);
      // ***   vuelve a iniciar el Movimiento legal
      MovimientoLegal(P);
    }
    else if(!isInCD(posicion)){//else , si la posición ingresada NO está CD
      // Avisa que No se puede capturar la ficha de esa posición
      System.out.println("Movimiento inválido!! Asegúrate de ingresar una de las posiciones mostradas ...");
      // ***   vuelve a iniciar el Movimiento legal
      MovimientoLegal(P);
    }
    else if(isInCD(posicion)) {//else , si la posición ingresada está en CD
      //    entonces mueve la ficha:
      CD.clear();
      moverFichaSeleccionada(posicion[0], posicion[1], P);
    }

    //------- Termina el Movimiento legal----------
  }

  private void cambiarTurno(){
    if(Turno==1){ Turno=2; // Si el turno es de P1, entonces se cambia al turno de P2
    }else{Turno=1;} // Si el turno es de P2, entonces se cambia al turno de P1
  }

  public void juega(int P){
    int[] posicion; // se crea un array que guardará la posición que introduciremos en las funciones.

    //if(P==1){

      eDJ="";

      System.out.println("\nTurno del jugador "+P+":");

      //Empieza su jugada

      if(getFPC(P)){ // Si hay capturas disponibles, inicia el proceso de captura
        // Escribimos en el panel de texto
        tableroForm.setTextAreaEstadoDeJuego(eDJ);
        eDJ="";

        System.out.println("\nIngrese la posición de la ficha que desee mover:");//Se pide ingresar la posición
        posicion= obtenerPosiciónFPC();// Nos aseguramos que la posición ingresada se encuentre en FPC

        seleccionarFichaDeCaptura(posicion[0], posicion[1], P); // Seleccionamos la ficha de esa posición y mostramos las capturas disponibles

        MovimientoDeCapturaLegal(P); //Iniciamos el movimiento de captura.
      }else {
        eDJ="";

        if(!getFMD(P)){
          System.out.println("Partida terminada!!!");
          if(P%2==1){
            System.out.println("Gana el Jugador 2!!!");
          }else{System.out.println("Gana el Jugador 1!!!!");}
        }else{

          // Escribimos en el panel de texto
          tableroForm.setTextAreaEstadoDeJuego(eDJ);
          eDJ="";

          System.out.println("\nIngrese la posición de la ficha que desee mover:");//Se pide ingresar la posición
          posicion = obtenerPosiciónFMD(); //Nos aseguramos que la posición ingresada se encuentre en FMD
          seleccionarFichaDeMovimiento(posicion[0], posicion[1], P);

          MovimientoLegal(P); // Se inicia el movimiento

          //Termina su jugada
          cambiarTurno(); //Cambiamos el turno al jugador 2. (Turno=2)
          //Verificamos la cantidad de fichas
          System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
          System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
          juega(Turno); //Inicia la jugada del oponente.
        }
      }

    //}

  }

  public static String traducirParOrdenadoString(int x, int y){
    //Se introduce un par ordenado (x,y) y devuelve un String en notación del juego. Ejm:  B6, C5,...
    String s="";
    char columna = (char) (65+y);
    s=s+columna+(8-x);

    return s;
  }

  public static int[] traducirStringParOrdenado(String s){
    //Se introduce un String en notación del juego. Ejm:  B6, C5,... y devuelve un par ordenado (x,y)
    int y= s.charAt(0)-65;
    int x= 8-s.charAt(1)+'0';
    return new int[]{x,y};
  }

  @Override
  public void run(){
    System.out.println("\nEmpieza la partida!!!");
    Turno = 1;
    juega(Turno);
  }

}
