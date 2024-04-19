package org.produccion.Modelo;

import java.util.Arrays;

public class Tablero {

  public enum Celda {VACIA, ROJA, NEGRA, REINAROJA, REINANEGRA}

  public enum EstadoDelJuego {ENCURSO, EMPATE, ROJAS_GANA, NEGRAS_GANA}

  private static final int TOTALROWS = 8;
  private static final int TOTALCOLUMNS = 8;

  private Celda[][] malla;
  private char turno;

  private EstadoDelJuego estadoActualDelJuego;

  public Tablero() {
    malla = new Celda[TOTALROWS][TOTALCOLUMNS];
    initBoard();
  }

  public void initBoard() {
    for (int row = 0; row < TOTALROWS; ++row) {
      for (int col = 0; col < TOTALCOLUMNS; ++col) {
        malla[row][col] = Celda.VACIA;
      }
    }
    estadoActualDelJuego = EstadoDelJuego.ENCURSO;
    //Empiezan las negras
    turno = 'N';
  }

  public int getTotalRows() {
    return TOTALROWS;
  }

  public int getTotalColumns() {
    return TOTALCOLUMNS;
  }

  public Celda getCelda(int row, int column) {
    if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
      return malla[row][column];
    } else {
      return null;
    }
  }

  public char getTurno() {
    return turno;
  }

  public void movimientoLegal(int row, int column) {
    //TODO Implementar los movimientos legales
//    if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
//        && malla[row][column] == Celda.VACIA) {
//      malla[row][column] = (turno == 'X') ? Celda.ROJA : Celda.VACIA;
//      updateGameState(turno, row, column);
//      turno = (turno == 'X') ? 'O' : 'X';
  }


  private void updateGameState(char turno, int row, int column) {
    if (hasWon(turno, row, column)) {
      estadoActualDelJuego =
          (turno == 'X') ? EstadoDelJuego.ROJAS_GANA : EstadoDelJuego.NEGRAS_GANA;
    } else if (isDraw()) {
      estadoActualDelJuego = EstadoDelJuego.EMPATE;
    }

  }

  private boolean isDraw() {
    //TODO Implementar las condiciones para el empate
    return true;

//    for (int row = 0; row < TOTALROWS; ++row) {
//      for (int col = 0; col < TOTALCOLUMNS; ++col) {
//        if (malla[row][col] == Celda.VACIA) {
//          return false;
//        }
//      }
//    }
  }

  private boolean hasWon(char turno, int row, int column) {
    //TODO Implementar las condiciones para ganar
    return false;
//    Celda token = (turno == 'X') ? Celda.ROJA : Celda.NEGRA;
//    return (malla[row][0] == token
//        && malla[row][1] == token && malla[row][2] == token
//        || malla[0][column] == token
//        && malla[1][column] == token && malla[2][column] == token
//        || row == column
//        && malla[0][0] == token && malla[1][1] == token && malla[2][2] == token
//        || row + column == 2
//        && malla[0][2] == token && malla[1][1] == token && malla[2][0] == token);
  }

  //Rendirse
  private void concede(char turno) {
    if (turno == 'R') {
      estadoActualDelJuego = EstadoDelJuego.NEGRAS_GANA;
    } else if (turno == 'N') {
      estadoActualDelJuego = EstadoDelJuego.ROJAS_GANA;
    }
  }

  public EstadoDelJuego getGameState() {
    return estadoActualDelJuego;
  }

  //Solo para pruebas
  public void flipCelda(int posx, int posy) {
    malla[posx][posy] = Celda.REINANEGRA;
  }

  @Override
  public String toString() {

    String temp = new String();
    temp = temp + "[ ";
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        temp = temp + malla[i][j] + " ";
      }
      temp = temp + "\n";
    }
    temp = temp + " ]";
    return temp;
  }
}

