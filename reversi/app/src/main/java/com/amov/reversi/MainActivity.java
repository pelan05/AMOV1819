package com.amov.reversi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    public void onLocal(View v)
    {
        Intent intent=new Intent(this, MainActivityGame.class);
        intent.putExtra("mode", MainActivityGame.SINGLEPLAYER);
        startActivity(intent);
    }


    public void onMulti(View v)
    {
        Intent intent=new Intent(this, MainActivityGame.class);
        intent.putExtra("mode", MainActivityGame.MULTILOCAL);
        startActivity(intent);
    }


    public void onServer(View v)
    {
        Intent intent=new Intent(this, MainActivityGame.class);
        intent.putExtra("mode", MainActivityGame.SERVER);
        startActivity(intent);
    }

    public void onClient(View v)
    {
        Intent intent=new Intent(this, MainActivityGame.class);
        intent.putExtra("mode", MainActivityGame.CLIENT);
        startActivity(intent);
    }









}
