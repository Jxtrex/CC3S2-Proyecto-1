package org.produccion.Modelo;

import java.util.Stack;
import org.produccion.Modelo.Casilla.CellState;

public class Tablero {
  private Casilla[][] grid = new Casilla[8][8];
  public Tablero(){
    for (int i=0; i<8; i++){
      for (int j=0; j<8;j++){
        grid[i][j] = new Casilla(i,j);
      }
    }
  }
  void placeDraugth(int posx,int posy, CellState cellState){
    this.grid[posx][posy].setSTATE(cellState);
  }
}
