package org.produccion.Modelo;

public class Casilla {

  private int xpos = -1;
  private int ypos = -1;
  private boolean isKing = false;

  public static enum CellState {EMPTY, WHITE, BLACK}

  ; //EMPTY, WHITE,BLACK, KING

  private CellState cellState;

  public Casilla(int xpos, int ypos, String STATE) {
    this.xpos = xpos;
    this.ypos = ypos;
    cellState = CellState.EMPTY;
  }

  public CellState getSTATE() {
    return cellState;
  }

  @Override
  public String toString() {
    return "ESTADO: " + getSTATE() + "\nx:" + xpos + ",y:" + ypos;
  }
}
