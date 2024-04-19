package org.produccion.Controlador;

import org.produccion.Modelo.Casilla;
import org.produccion.Modelo.Tablero1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partida {
  private Tablero1 tablero = new Tablero1(); //Crea un nuevo tablero vacío (Sin fichas).
  private ArrayList<int[]> FichasP1pos = new ArrayList<>(); // Lista de int[2] que contiene las posiciones de las fichas del jugador 1
  private ArrayList<int[]> FichasP2pos = new ArrayList<>(); // Lista de int[2] que contiene las posiciones de las fichas del jugador 2

  private int[] fichaSeleccionada;
  private ArrayList<int[]> FMD= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las fichas con movimiento disponible
  private ArrayList<int[]> CD= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las casillas disponibles para colocar
  // la ficha seleccionada, o las posiciones de las capturas disponibles(fichas del oponente).
  private ArrayList<int[]> FPC= new ArrayList<>(); //Lista de int[2] que contiene las posiciones de las Fichas que Pueden Capturar

  private int Turno;

  private boolean corona;

  public Partida(){
    colocarFichas(1,1); // Coloca las fichas del jugador 1 desde la perspectiva del jugador 1
    colocarFichas(2,1); // Coloca las fichas del jugador 2 desde la perspectiva del jugador 1

    imprimirFichas(1);
    imprimirFichas(2);

    System.out.println("\nEmpieza la partida!!!");
    Turno = 1;

    juega(Turno);

  }

  protected void imprimirFichas(int P) {
    if (P == 1){
      System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
      if(FichasP1pos.size()!=0) {
        for (int i = 0; i < FichasP1pos.size(); i++) {
          System.out.println(tablero.getCasilla(FichasP1pos.get(i)[0], FichasP1pos.get(i)[1]));
        }
      }else{System.out.println("No hay fichas del jugador 1 en el tablero.");}
  }
    if (P == 2){
      System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
      if(FichasP2pos.size()!=0) {
        for (int i = 0; i < FichasP2pos.size(); i++) {
          System.out.println(tablero.getCasilla(FichasP2pos.get(i)[0], FichasP2pos.get(i)[1]));
        }
      }else{System.out.println("No hay fichas del jugador 2 en el tablero.");}
    }

  }
  private void colocarFichas(int P,int VP){ // P : Jugador,   VP:  Vista del jugador
    if(VP==1){
      if(P==2){
        for (int n=0; n<12; n++){
          int i=n/4;
          int j=2*(n%4)+(1-i%2);
          tablero.placeDraugth(i,j, Casilla.CellState.RED);
          int [] posicion={i,j};
          FichasP2pos.add(posicion);

        }
      }
      if(P==1){
        for (int n=20; n<32; n++){
          int i=n/4;
          int j=2*(n%4)+(1-i%2);
          tablero.placeDraugth(i,j, Casilla.CellState.BLACK);
          int [] posicion={i,j};
          FichasP1pos.add(posicion);
        }
      }
    }

    if(VP==2){
      if(P==1) {
        for (int n = 0; n < 12; n++) {
          tablero.placeDraugth(n / 4, 2 * (n % 4) + (1 - (n / 4) % 2), Casilla.CellState.BLACK);
        }
      }
      if(P==2) {
        for (int n = 20; n < 32; n++) {
          tablero.placeDraugth(n / 4, 2 * (n % 4) + (1 - (n / 4) % 2), Casilla.CellState.RED);
        }
      }
    }
  }

  private boolean getFPC(int P){ // Muestra las posiciones de las Fichas que Pueden Capturar
    FPC.clear();
    if(P==1){// Si es el turno del jugador 1
      System.out.println("\nFichas del jugador 1 que pueden capturar:");
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
              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
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
              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.RED && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
        }

      }
    }
    if(P==2){// Si es el turno del jugador 2
      System.out.println("\nFichas del jugador 2 que pueden capturar:");
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
              continue;
            }
          }
          if(i>1 && j<6) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i-2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está ocupadada por el oponente
              // y la casilla subsiguiente (i-2, j+2) está vacía, entonces la ficha puede realizar una captura.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
          if(i<6 && j>1) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j-2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i<6 && j<6) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.BLACK && tablero.getCasilla(i+2, j+2).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FPC.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
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

  private boolean isInFPC(int[] posicion){// Verifica si la posicion está en FPC
    for(int[] e : FPC ){
      if(Arrays.equals(posicion,e)){return true;}
    }
    return false;
  }

  private boolean isInCD(int[] posicion){// Verifica si la posicion está en CD
    for(int[] e : CD ){
      if(Arrays.equals(posicion,e)){return true;}
    }
    return false;
  }

  private boolean isInFMD(int[] posicion){ // Verifica si la posicion está en FMD
    for(int[] e : FMD ){
      if(Arrays.equals(posicion,e)){return true;}
    }
    return false;
  }


  private boolean getFMD(int P){ // Muestra las posiciones de las Fichas con Movimientos Disponibles
    FMD.clear();

    if(P==1){// Si es el turno del jugador 1
      System.out.println("\nFichas del jugador 1 con movimiento disponible:");
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
              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas superiores (i-1)
          if(i!=0 && j!=0){//Revisa superior izquierda
            if (tablero.getCasilla(i-1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
        }

      }
    }
    if(P==2){// Si es el turno del jugador 2
      System.out.println("\nFichas del jugador 2 con movimiento disponible:");
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
              continue;
            }
          }
          if(i!=0 && j!=7) {//Revisa superior derecha
            if (tablero.getCasilla(i - 1, j + 1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla superior(i-1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i, j));
              continue;
            }
          }
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
            }
          }
        } else { // Si la ficha no es rey, solo verificamos las casillas inferiores (i+1)
          if(i!=7 && j!=0) {//Revisa inferior izquierda
            if (tablero.getCasilla(i+1, j-1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) izquierda(j-1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
              continue;
            }
          }
          if(i!=7 && j!=7) {//Revisa inferior derecha
            if (tablero.getCasilla(i+1, j+1).getSTATE() == Casilla.CellState.EMPTY) {
              // Si la casilla inferior(i+1) derecha(j+1) está vacía, entonces la ficha tiene un movimiento disponible.
              FMD.add(fichaPos);
              System.out.println(tablero.getCasilla(i,j));
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
  private boolean seleccionarFichaDeMovimiento(int i, int j, int P){
    CD.clear();

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
  private void moverFichaSeleccionada(int i, int j,int P){
    corona=false;

    System.out.println("\nFicha seleccionada movida a la casilla:");
    System.out.println(tablero.getCasilla(i,j));

    if(P==1){
      boolean isKing=tablero.getCasilla(fichaSeleccionada[0],fichaSeleccionada[1]).isKing();

      //Coloco el estado de la ficha seleccionada a la celda indicada
      tablero.placeDraugth(i,j, Casilla.CellState.BLACK);
      // Se asigna si es rey o no
      if(isKing || i==0){tablero.getCasilla(i,j).setKing(true);
        if(!isKing){
          corona=true;// si la ficha no era Rey entonces se corona
          System.out.println("La ficha se corona!\n");
        }
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


      //Actualiza la ficha seleccionada
      fichaSeleccionada= new int[]{i, j};

    }

    if(P==2){
      boolean isKing=tablero.getCasilla(fichaSeleccionada[0],fichaSeleccionada[1]).isKing();

      //Coloco el estado de la ficha seleccionada a la celda indicada
      tablero.placeDraugth(i,j, Casilla.CellState.RED);
      // Se asigna si es rey o no
      if(isKing || i==7){tablero.getCasilla(i,j).setKing(true);
        if(!isKing){ // si la ficha no era Rey entonces se corona
          corona=true; // si la ficha no era Rey entonces se corona
          System.out.println("La ficha se corona!\n");}
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

    }



  }

  private int[] obtenerPosiciónVálida(){
    Scanner sc = new Scanner(System.in); //Se crea el lector

    /// Debemos verificar que la posición ingresada sea válida!!

    String posicionString = sc.nextLine(); //Se lee la posición con nextLine() que retorna un String con el dato
    int [] posicion =Partida.traducirStringParOrdenado(posicionString); //traducimos el string a int[2]

    //int posicionInt = sc.nextInt();
    //int[] posicion = new int[]{posicionInt/10,posicionInt%10};

    if(isValid(posicion)){
      return posicion;
    }else{
      System.out.println("Entrada inválida!!  Vuelva a ingresar la posición:");
      return obtenerPosiciónVálida();
    }

  } /// Terminar!!

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
      }else{// Si hay más capturas, vuelve a iniciar el Moviemiento de captura con la ficha seleccionada
        // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
        MovimientoDeCapturaLegal(P);
      }
    }

    //----------- Termina el Movimiento de captura con la ficha seleccionada --------------
  }
  private void MovimientoLegal(int P){
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

  private void juega(int P){
    int[] posicion; // se crea un array que guardará la posición que introduciremos en las funciones.

    //if(P==1){

      System.out.println("\nTurno del jugador "+P+":");

      //Empieza su jugada

      if(getFPC(P)){ // Si hay capturas disponibles, inicia el proceso de captura
        System.out.println("\nIngrese la posición de la ficha que desee mover:");//Se pide ingresar la posición
        posicion= obtenerPosiciónFPC();// Nos aseguramos que la posición ingresada se encuentre en FPC

        seleccionarFichaDeCaptura(posicion[0], posicion[1], P); // Seleccionamos la ficha de esa posición y mostramos las capturas disponibles

        MovimientoDeCapturaLegal(P);
        /*
        ////---------- Movimiento de captura con la ficha seleccionada ------------///////////
        System.out.println("\nIngrese la posición de la ficha que desea capturar:");

        posicion= obtenerPosición(); //*****
        //Verificaremos que la posición ingresada sea válida y se encuentre en la lista CD o FPC.

        if(isInFPC(posicion))// Si la posición ingresada isValid(posicion) && isInFPC(posicion):
        {
          seleccionarFichaDeCaptura(posicion[0], posicion[1], P);
        // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
        }
        else if(!isInCD(posicion)){//else , si la posición ingresada isValid(posicion) && !isInCD(posicion),
          // Avisa que No se puede capturar la ficha de esa posición
          System.out.println("Captura inválida!!");
          // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
        }
        else if(isInCD(posicion)){//else , si la posición ingresada isValid(posicion) && isInCD(posicion),
        //    entonces captura la ficha:
          FPC=null;
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
          }else{// Si hay más capturas, vuelve a iniciar el Moviemiento de captura con la ficha seleccionada
            //System.out.println("Vuelve a iniciar el movim Captura");
            // ***   vuelve a iniciar el Movimiento de captura con la ficha seleccionada
          }
        }

        //----------- Termina el Movimiento de captura con la ficha seleccionada --------------
        */
        //???????
        /*

        //Si no hay más capturas disponibles
        //Termina su jugada
        cambiarTurno(); //Cambiamos el turno al jugador 2. (Turno=2)
        //Verificamos la cantidad de fichas
        System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
        System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
        juega(Turno);
        */
        ///???????
      }else {
        if(!getFMD(P)){
          System.out.println("Partida terminada!!!");
          if(P%2==1){
            System.out.println("Gana el Jugador 2!!!");
          }else{System.out.println("Gana el Jugador 1!!!!");}
        }else{

          System.out.println("\nIngrese la posición de la ficha que desee mover:");//Se pide ingresar la posición
          posicion = obtenerPosiciónFMD(); //Nos aseguramos que la posición ingresada se encuentre en FMD
          seleccionarFichaDeMovimiento(posicion[0], posicion[1], P);

          MovimientoLegal(P);
          /*
          //------- Movimiento legal----------

          System.out.println("\nIngrese la posición de la casilla donde desea mover la ficha:");
          posicion = obtenerPosiciónVálida();

          //Verificaremos que la posición ingresada sea válida y se encuentre en la lista CD o FMD.

          if(isInFMD(posicion))// Si la posición está en FMD:
          {
            seleccionarFichaDeMovimiento(posicion[0], posicion[1], P);
            // ***   vuelve a iniciar el Movimiento legal
          }
          else if(!isInCD(posicion)){//else , si la posición ingresada NO está CD
            // Avisa que No se puede capturar la ficha de esa posición
            System.out.println("Movimiento inválido!! Asegúrate de ingresar una de las posiciones mostradas ...");
            // ***   vuelve a iniciar el Movimiento legal
          }
          else if(isInCD(posicion)) {//else , si la posición ingresada está en CD
            //    entonces mueve la ficha:
            CD = null;
            moverFichaSeleccionada(posicion[0], posicion[1], P);
          }
          //------- Termina el Movimiento legal----------
          */

          //Termina su jugada
          cambiarTurno(); //Cambiamos el turno al jugador 2. (Turno=2)
          //Verificamos la cantidad de fichas
          System.out.println("\nFichas del jugador 1:  " + FichasP1pos.size());
          System.out.println("\nFichas del jugador 2:  " + FichasP2pos.size());
          juega(Turno);
        }
      }

    //}

  }

  public static String traducirParOrdenadoString(int x, int y){
    String s="";
    char columna = (char) (65+y);
    //System.out.println(value_char);
    s=s+columna+(8-x);

    return s;
  }

  public static int[] traducirStringParOrdenado(String s){
    int y= s.charAt(0)-65;
    int x= 8-s.charAt(1)+'0';
    return new int[]{x,y};
  }

}
