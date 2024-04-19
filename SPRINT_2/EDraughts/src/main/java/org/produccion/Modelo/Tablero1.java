package org.produccion.Modelo;

import org.produccion.Modelo.Casilla.CellState;

public class Tablero1 {
  private Casilla[][] grid = new Casilla[8][8];
  public Tablero1(){  // Se genera un tablero con fichas vacías
    for (int i=0; i<8; i++){
      for (int j=0; j<8;j++){
        grid[i][j] = new Casilla(i,j);
      }
    }
  }
  public void placeDraugth(int posx,int posy, CellState cellState/*,boolean isKing*/){
    this.grid[posx][posy].setSTATE(cellState);
    //this.grid[posx][posy].setKing(isKing);
  }

  public Casilla getCasilla(int i, int j) {
    return grid[i][j];
  }

}
