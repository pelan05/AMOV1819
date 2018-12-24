package com.amov.reversi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onLocal(View v)
    {
        Context context = getApplicationContext();
        CharSequence text = "Vs. Computer Game Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intentLocal =new Intent(this, MainActivityGame.class);
        intentLocal.putExtra("mode", MainActivityGame.SINGLEPLAYER);
        startActivity(intentLocal);
    }


    public void onMulti(View v)
    {
        Context context = getApplicationContext();
        CharSequence text = "Local Multiplayer Game Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intentMulti =new Intent(this, MainActivityGame.class);
        intentMulti.putExtra("mode", MainActivityGame.MULTILOCAL);
        startActivity(intentMulti);
    }


    public void onServer(View v)
    {
        Context context = getApplicationContext();
        CharSequence text = "Server Game Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intentServer=new Intent(this, MainActivityGame.class);
        intentServer.putExtra("mode", MainActivityGame.SERVER);
        startActivity(intentServer);
    }

    public void onClient(View v)
    {
        Context context = getApplicationContext();
        CharSequence text = "Client Game Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        Intent intentClient=new Intent(this, MainActivityGame.class);
        intentClient.putExtra("mode", MainActivityGame.CLIENT);
        startActivity(intentClient);
    }







}
