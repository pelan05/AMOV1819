package com.amov.reversi;

import android.app.Activity;

public class BoardChecker {

    public Cell[][] grid = new Cell[8][8];
    public int x = 0, y = 0;
    public boolean isWhitesTurn;
    public boolean result;
    public int sum = 0;

    public BoardChecker(){}

    public void cellInitializer(int _i, int _j, Activity _this, int btnID){
        grid[_i][_j] = new Cell(_this, btnID);
    }

    public boolean check(Cell[][] _grid, int _x, int _y, boolean _isWhitesTurn){
        this.grid = _grid;
        this.x = _x;
        this.y = _y;
        this.isWhitesTurn = _isWhitesTurn;


        //checking process
        //if has 1 neighbour cell with other color in it
        if(isWhitesTurn){
            result = checkBlack();
        }else{
            result = checkWhite();
        }

        return result; //temp TODO
    }
    public boolean checkBlack(){

        checker(x, y, grid[x][y].getCellValue());

        if(sum > 0)
            return true; //temp TODO
        else
            return false;
    }


    public boolean checkWhite(){


        if(sum > 0)
            return true; //temp TODO
        else
            return false;
    }

    public void checker(int _x, int _y, int _cellValue){

        //verificar as abaixo, se encontrar preto ou vazio para. as que tem branco manda para os checkers
        int value = 0;
        if(_cellValue == 2)
            value = 1;
        else
            value = 2;



        if (checkcell(_x+1, _y+1, value)){
            //se for uma celula brenca
            checkDiagCimDir(_x, _y, value);
        } //diagonal cima direita

        if (checkcell(_x+1, _y, value)){
            //se for uma celula brenca
            checkDir(_x, value);
        } //direita

        if (checkcell(_x+1, _y-1, value)){
            //se for uma celula brenca
            checkDiagBaiDir(_x, _y, value);
        } //diagonal baixo direita

        if (checkcell(_x, _y+1, value)){
            //se for uma celula brenca
            checkCim(_y, value);
        }//cima

        if (checkcell(_x, _y-1, value)){
            //se for uma celula brenca
            checkBaixo(_y, value);
        }//baixo

        if (checkcell(_x-1, _y+1, value)){
            //se for uma celula brenca
            checkDiagCimEsq(_x, _y, value);
        } //diagonal cima esquerda

        if (checkcell(_x-1, _y, value)){
            //se for uma celula brenca
            checkEsq(_x, value);
        } //esquerda

        if (checkcell(_x-1, _y-1, value)){
            //se for uma celula brenca
            checkDiagBaiEsq(_x, _y, value);
        } //diagonal baixo esquerda


    }

    public boolean checkcell(int _x, int _y, int cellValue){
        if(_x >= 0 && _y >= 0 && _x < 8 && _y < 8) {
            if (grid[_x][_y].getCellValue() == cellValue) {
                return true;
            }
        }
        return false;
    }

    //
    //LINE CHECKING METHODS
    //
    public void checkDiagCimDir(int _x, int _y, int value){
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x++;
            _y++;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8)
                break;

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else{
                break;
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _x = x; _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _x++;
            _y++;
            grid[_x][_y].setCellValue(value);
        }

    }

    public void checkDir(int _x, int value){

    }

    public void checkDiagBaiDir(int _x, int _y, int value){
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x++;
            _y--;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8)
                break;

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else{
                break;
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _x = x; _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _x++;
            _y--;
            grid[_x][_y].setCellValue(value);
        }
    }

    public void checkCim(int _y, int value){

    }

    public void checkBaixo(int _y, int value){

    }

    public void checkDiagCimEsq(int _x, int _y, int value){
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x--;
            _y++;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8)
                break;

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else{
                break;
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _x = x; _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _x--;
            _y++;
            grid[_x][_y].setCellValue(value);
        }
    }

    public void checkEsq(int _x, int value){

    }

    public void checkDiagBaiEsq(int _x, int _y, int value){
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x--;
            _y--;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8)
                break;

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else{
                break;
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _x = x; _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _x--;
            _y--;
            grid[_x][_y].setCellValue(value);
        }
    }





}
