package com.amov.reversi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Cell {

    public Activity activity;
    int idCelula;
    int cellValue; //0-Empty, 1-White, 2-Black



    //findViewById(R.id.btn4_4).setBackgroundResource(R.drawable.white_circle);


    public Cell(Activity _activity, int _idCelula){
        this.activity = _activity;
        this.idCelula = _idCelula;
        this.cellValue = 0;

    }

    public int getIdCelula(){ return this.idCelula; }

    public int getCellValue() { return this.cellValue; }

    public void setCellValue(int val){this.cellValue = val;}

    public void changeBlack(){
        this.cellValue = 2;
    }

    public void changeWhite(){
        this.cellValue = 1;
    }





}
