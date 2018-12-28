package com.amov.reversi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
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

    public Integer move[] = new Integer[64];

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


    ProgressDialog pd = null;
    ServerSocket serverSocket=null;
    Socket socketGame = null;
    BufferedReader input;
    PrintWriter output;
    Handler procMsg = null;

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


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, R.string.error_netconn, Toast.LENGTH_LONG).show();
            finish();
        }


        Intent intent = getIntent();
        if (intent != null)
            mode = intent.getIntExtra("mode", SERVER);


        this.btnGameBack = (Button) findViewById(R.id.btnGameBack);
        btnGameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isWhitesTurn) {
                    if (hasUsedPlay2xWhite) {
                        CharSequence text = "Already Used";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterBlack < 5 || moveCounterWhite < 5) {
                        CharSequence text = "Available after 5th move";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterBlack >= 5 || moveCounterWhite >= 5 && !hasUsedPlay2xWhite) {
                        //Play 2x procedure

                        usingPlay2x = true;


                        CharSequence text = "Play 2x selected";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

                        //btnGameBack.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedPlay2xWhite = true;
                    }
                } else {
                    if (hasUsedPlay2xBlack) {
                        CharSequence text = "Already Used";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterBlack < 5 || moveCounterWhite < 5) {
                        CharSequence text = "Available after 5th move";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterBlack >= 5 || moveCounterWhite >= 5 && !hasUsedPlay2xBlack) {
                        //Play 2x procedure

                        usingPlay2x = true;

                        CharSequence text = "Play 2x selected";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

                        //btnGameBack.setBackgroundColor(getResources().getColor(R.color.red));
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
                        CharSequence text = "Already Used";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterWhite < 5 || moveCounterBlack < 5) {
                        CharSequence text = "Available after 5th move";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
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


                        CharSequence text = "Skip move selected";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

                        //btnSkip.setBackgroundColor(getResources().getColor(R.color.red));
                        hasUsedSkipWhite = true;
                    }

                } else {
                    if (hasUsedSkipBlack) {
                        CharSequence text = "Already Used";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
                    } else if (moveCounterWhite < 5 || moveCounterBlack < 5) {
                        CharSequence text = "Available after 5th move";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
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


                        CharSequence text = "Skip move selected";
                        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

                        //btnSkip.setBackgroundColor(getResources().getColor(R.color.red));
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



    @Override
    protected void onResume() {
        super.onResume();
        if (mode == SERVER)
            server();
        else if(mode == CLIENT)  // CLIENT
            clientDlg();
        else{
            //do nothing?
        }
    }



    public void onClickGameButton(View v) {


        if (v.getBackground().getConstantState().equals(getDrawable(R.drawable.ic_white_circle).getConstantState())
                || v.getBackground().getConstantState().equals(getDrawable(R.drawable.ic_black_circle).getConstantState())) {
            //invalid move - cell already taken
            Toast.makeText(getApplicationContext(),R.string.invalid, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(),R.string.invalid, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),R.string.invalid, Toast.LENGTH_SHORT).show();
                }



                        break;
                        case 2://Server - missing


                            break;
                        case 3://Client - missing


                            break;
                    }
            }
        }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    void server() {
        // WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        // String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        String ip = getLocalIpAddress();
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.serverdlg_msg) + "\n(IP: " + ip
                + ")");
        pd.setTitle(R.string.serverdlg_title);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
                if (serverSocket!=null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                    }
                    serverSocket=null;
                }
            }
        });
        pd.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(PORT);
                    socketGame = serverSocket.accept();
                    serverSocket.close();
                    serverSocket=null;
                    commThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    socketGame = null;
                }
                procMsg.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        if (socketGame == null)
                            finish();
                    }
                });
            }
        });
        t.start();
    }

    void clientDlg() {
        final EditText edtIP = new EditText(this);
        edtIP.setText("10.0.2.2");
        AlertDialog ad = new AlertDialog.Builder(this).setTitle("RPS Client")
                .setMessage("Server IP").setView(edtIP)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client(edtIP.getText().toString(), PORT); // to test with emulators: PORTaux);
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                }).create();
        ad.show();
    }


    void client(final String strIP, final int Port) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("RPS", "Connecting to the server  " + strIP);
                    socketGame = new Socket(strIP, Port);
                } catch (Exception e) {
                    socketGame = null;
                }
                if (socketGame == null) {
                    procMsg.post(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                    return;
                }
                commThread.start();
            }
        });
        t.start();
    }

    Thread commThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socketGame.getInputStream()));
                output = new PrintWriter(socketGame.getOutputStream());
                while (!Thread.currentThread().isInterrupted()) {
                    String read = input.readLine();

                    for (int i = 0; i < 64; i++) {
                        move[i] = Integer.parseInt(read);
                    }
                    
                    procMsg.post(new Runnable() {
                        @Override
                        public void run() {

                            //code to save the received data

                            //moveOtherPlayer(move); change here
                        }
                    });
                }
            } catch (Exception e) {
                procMsg.post(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Toast.makeText(getApplicationContext(),R.string.game_finished, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    });

    protected void onPause() {
        super.onPause();
        try {
            commThread.interrupt();
            if (socketGame != null)
                socketGame.close();
            if (output != null)
                output.close();
            if (input != null)
                input.close();
        } catch (Exception e) {
        }
        input = null;
        output = null;
        socketGame = null;
    };

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //



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
