package com.amov.reversi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityGame extends Activity {

    public static final int SINGLEPLAYER = 0;
    public static final int MULTILOCAL = 1;
    public static final int SERVER = 2;
    public static final int CLIENT = 3;

    public static final int ME = 0;
    public static final int OTHER = 1;

    private static final int PORT = 8899;
    private static final int PORTaux = 9988; // to test with emulators

    int mode = SERVER;

    public ArrayList<Cell> listaCelulas;//se calhar nao arraylist?
    //quero uma especie de tabela 8*8
    //data formats?




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //new Cell(this, R.id.btn0_0);


        /*MUDAR PARA PRETO (SAMPLE)
        *
        * celula.changeBlack();
        * findViewById(celula.getIdCelula).setBackgroundResource(R.drawable.black_circle);
        * */
    }




    /*
        findViewById(R.id.btn3_3).setBackgroundResource(R.drawable.white_circle);
        findViewById(R.id.btn3_4).setBackgroundResource(R.drawable.black_circle);
        findViewById(R.id.btn4_3).setBackgroundResource(R.drawable.black_circle);
        findViewById(R.id.btn4_4).setBackgroundResource(R.drawable.white_circle);

      celulas iniciais para por as pecas
      3-3 branco, 3-4 preto, 4-3 preto, 4-4 branco

    * */




    public void onExit(View v){

        finish();
    }





}
