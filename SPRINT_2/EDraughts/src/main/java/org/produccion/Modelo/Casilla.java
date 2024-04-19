package org.produccion.Modelo;

import org.produccion.Controlador.Partida;

public class Casilla {

  private int xpos = -1;
  private int ypos = -1;
  private boolean isKing = false;

  public static enum CellState {EMPTY, RED, BLACK}

  ; //EMPTY, RED,BLACK, KING

  private CellState cellState;

  public Casilla(int xpos, int ypos) {
    this.xpos = xpos;
    this.ypos = ypos;
    cellState = CellState.EMPTY;
  }

  public CellState getSTATE() {
    return cellState;
  }

  public void setSTATE(CellState cellState){
    this.cellState = cellState;
  }

  public boolean isKing() {
    return isKing;
  }

  public void setKing(boolean king) {
    isKing = king;
  }

  @Override
  public String toString() {
    //return "Posición: x= " + xpos + ", y= " + ypos + " ; ESTADO: " + getSTATE() +" ; Rey: "+ isKing;
    return "Posición: " + Partida.traducirParOrdenadoString(xpos,ypos) + " ; ESTADO: " + getSTATE() +" ; Rey: "+ isKing;
  }
}
