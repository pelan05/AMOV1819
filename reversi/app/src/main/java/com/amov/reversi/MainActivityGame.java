package com.amov.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivityGame extends Activity {

    public static final int SINGLEPLAYER = 0;
    public static final int MULTILOCAL = 1;
    public static final int SERVER = 2;
    public static final int CLIENT = 3;
    int mode = SERVER;

    public int counterWhite = 0, counterBlack = 0;
    public int x = 0, y = 0;
    public int i = 0, j = 0;

    private static final int PORT = 8899;
    private static final int PORTaux = 9988; // to test with emulators

    public int moveCounterWhite = 0;
    public int moveCounterBlack = 0;
    public boolean hasUsedPlay2xBlack = false;
    public boolean hasUsedPlay2xWhite = false;
    public boolean hasUsedSkipWhite = false;
    public boolean hasUsedSkipBlack = false;
    public boolean isWhitesTurn = false;
    public boolean usingPlay2x = false;

    public int[] btnList = {//array de ids de botao
            R.id.btn0_0, R.id.btn0_1, R.id.btn0_2, R.id.btn0_3, R.id.btn0_4, R.id.btn0_5, R.id.btn0_6, R.id.btn0_7,
            R.id.btn1_0, R.id.btn1_1, R.id.btn1_2, R.id.btn1_3, R.id.btn1_4, R.id.btn1_5, R.id.btn1_6, R.id.btn1_7,
            R.id.btn2_0, R.id.btn2_1, R.id.btn2_2, R.id.btn2_3, R.id.btn2_4, R.id.btn2_5, R.id.btn2_6, R.id.btn2_7,
            R.id.btn3_0, R.id.btn3_1, R.id.btn3_2, R.id.btn3_3, R.id.btn3_4, R.id.btn3_5, R.id.btn3_6, R.id.btn3_7,
            R.id.btn4_0, R.id.btn4_1, R.id.btn4_2, R.id.btn4_3, R.id.btn4_4, R.id.btn4_5, R.id.btn4_6, R.id.btn4_7,
            R.id.btn5_0, R.id.btn5_1, R.id.btn5_2, R.id.btn5_3, R.id.btn5_4, R.id.btn5_5, R.id.btn5_6, R.id.btn5_7,
            R.id.btn6_0, R.id.btn6_1, R.id.btn6_2, R.id.btn6_3, R.id.btn6_4, R.id.btn6_5, R.id.btn6_6, R.id.btn6_7,
            R.id.btn7_0, R.id.btn7_1, R.id.btn7_2, R.id.btn7_3, R.id.btn7_4, R.id.btn7_5, R.id.btn7_6, R.id.btn7_7
    };

    //public Cell[][] grid = new Cell[8][8];

    BoardChecker checker;
    TextView textWhite;
    TextView textBlack;
    TextView infoBox;
    Button btnGameBack;
    Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Intent intent = getIntent();
        if (intent != null)
            mode = intent.getIntExtra("mode", SERVER);


        this.btnGameBack = (Button) findViewById(R.id.btnGameBack);
        btnGameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isWhitesTurn) {
                    if (hasUsedPlay2xWhite) {
                        Context context = getApplicationContext();
                        CharSequence text = "Already Used";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterBlack < 5 || moveCounterWhite < 5) {
                        Context context = getApplicationContext();
                        CharSequence text = "Available after 5th move";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterBlack >= 5 || moveCounterWhite >= 5 && !hasUsedPlay2xWhite) {
                        //Play 2x procedure

                        usingPlay2x = true;


                        Context context = getApplicationContext();
                        CharSequence text = "Play 2x selected";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        btnGameBack.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedPlay2xWhite = true;
                    }
                } else {
                    if (hasUsedPlay2xBlack) {
                        Context context = getApplicationContext();
                        CharSequence text = "Already Used";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterBlack < 5 || moveCounterWhite < 5) {
                        Context context = getApplicationContext();
                        CharSequence text = "Available after 5th move";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterBlack >= 5 || moveCounterWhite >= 5 && !hasUsedPlay2xBlack) {
                        //Play 2x procedure

                        usingPlay2x = true;


                        Context context = getApplicationContext();
                        CharSequence text = "Play 2x selected";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        btnGameBack.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedPlay2xBlack = true;
                    }
                }


            }
        });
        this.btnSkip = (Button) findViewById(R.id.btnGameSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isWhitesTurn) {
                    if (hasUsedSkipWhite) {
                        Context context = getApplicationContext();
                        CharSequence text = "Already Used";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterWhite < 5 || moveCounterBlack < 5) {
                        Context context = getApplicationContext();
                        CharSequence text = "Available after 5th move";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if ((moveCounterWhite >= 5 || moveCounterBlack >= 5) && !hasUsedSkipWhite) {
                        //skipProcedure

                        if (isWhitesTurn)
                            moveCounterWhite--;
                        else
                            moveCounterBlack--;

                        triggerTurn();

                        if (isWhitesTurn)
                            infoBox.setText(getResources().getString(R.string.whiteTurn));
                        else
                            infoBox.setText(getResources().getString(R.string.blackTurn));


                        Context context = getApplicationContext();
                        CharSequence text = "Skip move selected";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        btnSkip.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedSkipWhite = true;
                    }

                } else {
                    if (hasUsedSkipBlack) {
                        Context context = getApplicationContext();
                        CharSequence text = "Already Used";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if (moveCounterWhite < 5 || moveCounterBlack < 5) {
                        Context context = getApplicationContext();
                        CharSequence text = "Available after 5th move";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else if ((moveCounterWhite >= 5 || moveCounterBlack >= 5) && !hasUsedSkipBlack) {
                        //skipProcedure

                        if (isWhitesTurn)
                            moveCounterWhite--;
                        else
                            moveCounterBlack--;

                        triggerTurn();

                        if (isWhitesTurn)
                            infoBox.setText(getResources().getString(R.string.whiteTurn));
                        else
                            infoBox.setText(getResources().getString(R.string.blackTurn));


                        Context context = getApplicationContext();
                        CharSequence text = "Skip move selected";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        btnSkip.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedSkipBlack = true;
                    }
                }

            }
        });

        checker = new BoardChecker();

        this.textWhite = (TextView) findViewById(R.id.txtViewWhite);
        this.textBlack = (TextView) findViewById(R.id.txtViewBlack);
        this.infoBox = (TextView) findViewById(R.id.txtInfo);

        int k = 0;                          //inicialização de array de celulas
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checker.cellInitializer(i, j, this, btnList[k]);
                k++;
            }
        }


        checker.grid[3][3].changeWhite();
        findViewById(R.id.btn3_3).setBackgroundResource(R.drawable.ic_white_circle);
        checker.grid[3][4].changeBlack();
        findViewById(R.id.btn3_4).setBackgroundResource(R.drawable.ic_black_circle);
        checker.grid[4][3].changeBlack();
        findViewById(R.id.btn4_3).setBackgroundResource(R.drawable.ic_black_circle);
        checker.grid[4][4].changeWhite();
        findViewById(R.id.btn4_4).setBackgroundResource(R.drawable.ic_white_circle);


        //codigo check cells
        int counterWhite = 0, counterBlack = 0;
        for (int i = 0; i < 64; i++) {
            if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())) {
                counterWhite++;
            } else if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
                counterBlack++;
            }
        }

        textWhite.setText(String.format(Locale.US, ": %d", counterWhite));
        textBlack.setText(String.format(Locale.US, ": %d", counterBlack));

        /*MUDAR PARA PRETO (SAMPLE)
         * grid[1][1].changeBlack();
         * findViewById(grid[1][1].getIdCelula()).setBackgroundResource(R.drawable.black_circle);
         * */
        /*MUDAR PARA BRANCO (SAMPLE)
         * grid[1][1].changeWhite();
         * findViewById(grid[1][1].getIdCelula()).setBackgroundResource(R.drawable.white_circle);
         * */

    }


    public void onClickGameButton(View v) {


        if (v.getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())
                || v.getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
            //invalid move - cell already taken
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, getResources().getString(R.string.invalid), duration);
            toast.show();
        } else {


            //gamemodes?
            switch (mode) {
                case 0://SinglePlayer - falta AI

                    x = 0;
                    y = 0;
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (v.getId() == checker.grid[i][j].getIdCelula()) {
                                x = i;
                                y = j;
                            }
                        }
                    }


                    //verificaCoords se é jogada valida
                    if (checker.check(x, y, false)) {//false means it's black's turn
                        //MUDA SELECAO

                        this.moveCounterBlack++;
                        checker.grid[x][y].changeBlack();
                        for (i = 0; i < 8; i++) {
                            for (j = 0; j < 8; j++) {
                                if (checker.grid[i][j].getCellValue() == 2) {//2 é o val de preto
                                    findViewById(checker.grid[i][j].getIdCelula()).setBackgroundResource(R.drawable.ic_black_circle);
                                }
                            }
                        }



                    //calcula trocas de cor

                    //replace cells


                    //atualiza counters
                    counterWhite = 0;
                    counterBlack = 0;
                    for (i = 0; i < 64; i++) {
                        if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())) {
                            counterWhite++;
                        } else if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
                            counterBlack++;
                        }
                    }

                    textWhite.setText(String.format(Locale.US, ": %d", counterWhite));
                    textBlack.setText(String.format(Locale.US, ": %d", counterBlack));




                    if (usingPlay2x) {
                        usingPlay2x = false;
                        break;
                    } else {
                        infoBox.setText(getResources().getString(R.string.whiteTurn));

                        /*try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        //receber celula alvo (x, y) nao usados?
                        //x=?;
                        //y=?;
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (checker.check(i, j, true)) {//true meANS IT'S whites turn
                                    this.moveCounterWhite++;
                                    checker.grid[i][j].changeWhite();
                                    for (i = 0; i < 8; i++) {
                                        for (j = 0; j < 8; j++) {
                                            if (checker.grid[i][j].getCellValue() == 1) {//1 é o val de branco
                                                findViewById(checker.grid[i][j].getIdCelula()).setBackgroundResource(R.drawable.ic_white_circle);
                                            }
                                        }
                                    }
                                    i = j = 8; break;
                                }
                            }
                        }


                            counterWhite = 0;
                            counterBlack = 0;
                            for (int i = 0; i < 64; i++) {
                                if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())) {
                                    counterWhite++;
                                } else if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
                                    counterBlack++;
                                }
                            }

                            textWhite.setText(String.format(Locale.US, ": %d", counterWhite));
                            textBlack.setText(String.format(Locale.US, ": %d", counterBlack));


                        infoBox.setText(getResources().getString(R.string.blackTurn));
                    }

                    //fim do jogo
                    if (counterBlack + counterWhite == 64)
                        if (counterWhite > counterBlack)
                            infoBox.setText(getResources().getString(R.string.whiteWon));
                        else
                            infoBox.setText(getResources().getString(R.string.blackWon));


            }else{
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, getResources().getString(R.string.invalid), duration);
                toast.show();
            }


                    break;
                case 1://MultiLocal - done 100% implemented


                    x = 0;
                    y = 0;
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (v.getId() == checker.grid[i][j].getIdCelula()) {
                                x = i;
                                y = j;
                            }
                        }
                    }


                    //verificaCoords se é jogada valida
                    if (checker.check(x, y, isWhitesTurn)) {
                        //MUDA SELECAO
                        if (isWhitesTurn) {
                            this.moveCounterWhite++;
                            checker.grid[x][y].changeWhite();
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    if (checker.grid[i][j].getCellValue() == 1) {//1 é o val de branco
                                        findViewById(checker.grid[i][j].getIdCelula()).setBackgroundResource(R.drawable.ic_white_circle);
                                    }
                                }
                            }
                        } else {
                            this.moveCounterBlack++;
                            checker.grid[x][y].changeBlack();
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                    if (checker.grid[i][j].getCellValue() == 2) {//2 é o val de preto
                                        findViewById(checker.grid[i][j].getIdCelula()).setBackgroundResource(R.drawable.ic_black_circle);
                                    }
                                }
                            }
                        }


                        //calcula trocas de cor

                        //replace cells


                        //atualiza counters
                        counterWhite = 0;
                        counterBlack = 0;
                        for (i = 0; i < 64; i++) {
                            if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())) {
                                counterWhite++;
                            } else if (findViewById(btnList[i]).getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
                                counterBlack++;
                            }
                        }

                        textWhite.setText(String.format(Locale.US, ": %d", counterWhite));
                        textBlack.setText(String.format(Locale.US, ": %d", counterBlack));


                        //proxima jogada( , )
                        if (usingPlay2x) {
                            usingPlay2x = false;
                            break;
                        } else {

                            triggerTurn();
                            if (isWhitesTurn)
                                infoBox.setText(getResources().getString(R.string.whiteTurn));
                            else
                                infoBox.setText(getResources().getString(R.string.blackTurn));

                        }

                        //fim do jogo
                        if (counterBlack + counterWhite == 64)
                            if (counterWhite > counterBlack)
                                infoBox.setText(getResources().getString(R.string.whiteWon));
                            else
                                infoBox.setText(getResources().getString(R.string.blackWon));


                } else {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, getResources().getString(R.string.invalid), duration);
                    toast.show();
                }



                        break;
                        case 2://Server - missing


                            break;
                        case 3://Client - missing


                            break;
                    }
            }
        }


        public void onExit (View v){

            finish();
        }

        public void triggerTurn () {
            if (isWhitesTurn)
                isWhitesTurn = false;
            else
                isWhitesTurn = true;
        }



}
