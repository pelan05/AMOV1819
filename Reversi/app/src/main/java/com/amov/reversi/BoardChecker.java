package com.amov.reversi;

import android.app.Activity;

public class BoardChecker {

    public Cell[][] grid = new Cell[8][8];
    public Integer[] values = new Integer[8];
    public int x = 0, y = 0;

    public boolean isWhitesTurn;
    public boolean result;


    public int sum = 0;


    public BoardChecker(){}

    public void cellInitializer(int _i, int _j, Activity _this, int btnID){
        grid[_i][_j] = new Cell(_this, btnID);
    }

    public boolean check(int _x, int _y, boolean _isWhitesTurn){
        this.x = _x;
        this.y = _y;
        this.isWhitesTurn = _isWhitesTurn;

        int value = 0;
        if(isWhitesTurn)
            value = 1;
        else
            value = 2;

        return checker(x, y, value);
    }


    public boolean checker(int _x, int _y, int _cellValue/*cor da celula*/){

        //verificar as abaixo, se encontrar preto ou vazio para. as que tem branco manda para os checkers

        int valueInverted = 0;

        if(_cellValue == 2)
            valueInverted = 1;
        else
            valueInverted = 2;

        sum = 0;

        if (checkcell(_x+1, _y-1, valueInverted)){
            //se for uma celula branca
            if(checkDiagCimDir(_x, _y, valueInverted))
            sum++;
        } //diagonal cima direita

        if (checkcell(_x+1, _y, valueInverted)){
            //se for uma celula branca
            if(checkDir(_x, valueInverted))
            sum++;
        } //direita

        if (checkcell(_x+1, _y+1, valueInverted)){
            //se for uma celula branca
            if(checkDiagBaiDir(_x, _y, valueInverted))
            sum++;
        } //diagonal baixo direita

        if (checkcell(_x, _y-1, valueInverted)){
            //se for uma celula brenca
            if(checkCim(_y, valueInverted))
            sum++;
        }//cima

        if (checkcell(_x, _y+1, valueInverted)){
            //se for uma celula brenca
            if(checkBaixo(_y, valueInverted))
            sum++;
        }//baixo

        if (checkcell(_x-1, _y-1, valueInverted)){
            //se for uma celula brenca
            if(checkDiagCimEsq(_x, _y, valueInverted))
            sum++;
        } //diagonal cima esquerda

        if (checkcell(_x-1, _y, valueInverted)){
            //se for uma celula brenca
            if(checkEsq(_x, valueInverted))
            sum++;
        } //esquerda

        if (checkcell(_x-1, _y+1, valueInverted)){
            //se for uma celula brenca
            if(checkDiagBaiEsq(_x, _y, valueInverted))
            sum++;
        } //diagonal baixo esquerda

    if(sum > 0)
        return true;
    else
        return false;


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
    public boolean checkDiagCimDir(int _x, int _y, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;

        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x++;
            _y--;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }
            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else{
                if(grid[_x][_y].getCellValue() == invertedValue)
                break;
                else{
                    counter = 0;
                    break;
                }
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

        if(counter == 0)
            return false;
        else
            return true;

    }

    public boolean checkDir(int _x, int value) {
        int invertedValue;
        if (value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            _x++;
            if(_x < 0 || y < 0 || _x >= 8 || y >= 8) {
                counter = 0;
                break;
            }

            if (grid[_x][y].getCellValue() == value) {
                counter++;
            } else {
                if (grid[_x][y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
            }
        }
            if (value == 2)
                value = 1;
            else
                value = 2;


            //return x y to original values
            _x = x;

            //replace said cells
            for (int i = 0; i < counter; i++) {
                _x++;

                grid[_x][y].setCellValue(value);
            }

            if (counter == 0)
                return false;
            else
                return true;
        }


    public boolean checkDiagBaiDir(int _x, int _y, int value){
            int invertedValue;
            if(value == 1)
                invertedValue = 2;
            else
                invertedValue = 1;
            int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x++;
            _y++;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[_x][_y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
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

        if(counter == 0)
            return false;
        else
            return true;
    }

    public boolean checkCim(int _y, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _y--;
            if(x < 0 || _y < 0 || x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }

            if(grid[x][_y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[x][_y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _y--;
            grid[x][_y].setCellValue(value);
        }

        if(counter == 0)
            return false;
        else
            return true;
    }

    public boolean checkBaixo(int _y, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {

            _y++;
            if(x < 0 || _y < 0 || x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }
            if(grid[x][_y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[x][_y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _y = y;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _y++;
            grid[x][_y].setCellValue(value);
        }

        if(counter == 0)
            return false;
        else
            return true;
    }

    public boolean checkDiagCimEsq(int _x, int _y, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x--;
            _y--;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[_x][_y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
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

        if(counter == 0)
            return false;
        else
            return true;
    }

    public boolean checkEsq(int _x, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x--;
            if(_x < 0 || y < 0 || _x >= 8 || y >= 8) {
                counter = 0;
                break;
            }

            if(grid[_x][y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[_x][y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
            }
        }
        if(value == 2)
            value = 1;
        else
            value = 2;


        //return x y to original values
        _x = x;

        //replace said cells
        for (int i = 0; i < counter; i++) {
            _x--;
            grid[_x][y].setCellValue(value);
        }

        if(counter == 0)
            return false;
        else
            return true;
    }

    public boolean checkDiagBaiEsq(int _x, int _y, int value){
        int invertedValue;
        if(value == 1)
            invertedValue = 2;
        else
            invertedValue = 1;
        int counter = 0;
        for (int i = 0; i < 8 ; i++) {
            _x--;
            _y++;
            if(_x < 0 || _y < 0 || _x >= 8 || _y >= 8) {
                counter = 0;
                break;
            }

            if(grid[_x][_y].getCellValue() == value) {
                counter++;
            }else {
                if (grid[_x][_y].getCellValue() == invertedValue)
                    break;
                else {
                    counter = 0;
                    break;
                }
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

        if(counter == 0)
            return false;
        else
            return true;
    }


    public void updateBoardNetwork(int values[]){
        int i = 0;
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                grid[j][k].setCellValue(values[i]);
                i++;
            }
        }
    }

    public Integer[] sendBoard(){
        int i = 0;
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                values[i] = grid[j][k].getCellValue();
                i++;
            }
        }

        return values;
    }





}
