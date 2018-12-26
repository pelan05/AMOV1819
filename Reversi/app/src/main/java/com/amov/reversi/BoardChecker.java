package com.amov.reversi;

public class BoardChecker {

    Cell[][] grid = new Cell[8][8];
    int x = 0, y = 0;

    public BoardChecker(){}

    public boolean check(Cell[][] _grid, int _x, int _y){
        this.grid = _grid;
        this.x = _x;
        this.y = _y;

        //checking process

        return true; //temp TODO
    }

}
