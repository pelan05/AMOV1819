package com.amov.reversi;

import android.app.Activity;
import android.os.Bundle;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



}
