package org.produccion.Modelo;

import java.util.Stack;

public class Tablero {
  private Casilla[][] grid = new Casilla[8][8];
  public Tablero(){
  }
  public Tablero(String start){
    for (int i=0; i<8; i++){
      for (int j=0; j<8;j++){
        grid[i][j] = new Casilla(i,j,"EMPTY");
      }
    }
  }

}
