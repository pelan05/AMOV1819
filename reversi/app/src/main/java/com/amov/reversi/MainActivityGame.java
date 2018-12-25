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

    public int[] btnList= {//array de ids de botao
            R.id.btn0_0, R.id.btn0_1, R.id.btn0_2, R.id.btn0_3, R.id.btn0_4, R.id.btn0_5, R.id.btn0_6, R.id.btn0_7,
            R.id.btn1_0, R.id.btn1_1, R.id.btn1_2, R.id.btn1_3, R.id.btn1_4, R.id.btn1_5, R.id.btn1_6, R.id.btn1_7,
            R.id.btn2_0, R.id.btn2_1, R.id.btn2_2, R.id.btn2_3, R.id.btn2_4, R.id.btn2_5, R.id.btn2_6, R.id.btn2_7,
            R.id.btn3_0, R.id.btn3_1, R.id.btn3_2, R.id.btn3_3, R.id.btn3_4, R.id.btn3_5, R.id.btn3_6, R.id.btn3_7,
            R.id.btn4_0, R.id.btn4_1, R.id.btn4_2, R.id.btn4_3, R.id.btn4_4, R.id.btn4_5, R.id.btn4_6, R.id.btn4_7,
            R.id.btn5_0, R.id.btn5_1, R.id.btn5_2, R.id.btn5_3, R.id.btn5_4, R.id.btn5_5, R.id.btn5_6, R.id.btn5_7,
            R.id.btn6_0, R.id.btn6_1, R.id.btn6_2, R.id.btn6_3, R.id.btn6_4, R.id.btn6_5, R.id.btn6_6, R.id.btn6_7,
            R.id.btn7_0, R.id.btn7_1, R.id.btn7_2, R.id.btn7_3, R.id.btn7_4, R.id.btn7_5, R.id.btn7_6, R.id.btn7_7
    };

    public Cell[][] grid = new Cell[8][8];

    //public ArrayList<Cell> listaCelulas;//se calhar nao arraylist?
    //quero uma especie de tabela 8*8
    //data formats?




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int k = 0;                          //inicialização de array de celulas
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                grid[i][j] = new Cell(this, btnList[k]);
                k++;
            }
        }


        findViewById(R.id.btn3_3).setBackgroundResource(R.drawable.ic_white_circle);
        findViewById(R.id.btn3_4).setBackgroundResource(R.drawable.ic_black_circle);
        findViewById(R.id.btn4_3).setBackgroundResource(R.drawable.ic_black_circle);
        findViewById(R.id.btn4_4).setBackgroundResource(R.drawable.ic_white_circle);


        /*MUDAR PARA PRETO (SAMPLE)
         * grid[1][1].changeBlack();
         * findViewById(grid[1][1].getIdCelula()).setBackgroundResource(R.drawable.black_circle);
         * */
        /*MUDAR PARA BRANCO (SAMPLE)
         * grid[1][1].changeWhite();
         * findViewById(grid[1][1].getIdCelula()).setBackgroundResource(R.drawable.white_circle);
         * */

    }



    public void onClickGameButton(View v){

    }







    public void onExit(View v){

        finish();
    }





}
