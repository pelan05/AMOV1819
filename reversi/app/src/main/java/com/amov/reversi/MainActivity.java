package com.amov.reversi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button changeLang = findViewById(R.id.btnLang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLangSwapDialog();
            }
        });
    }

    private void showLangSwapDialog() {
        final String[] listItems = {"English", "Português"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose language");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    //Englando
                    setLocale("en");
                    recreate();
                }
                else if(i == 1){
                    //Tugalando
                    setLocale("pt");
                    recreate();;
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog = builder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Language", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My Language", "");
        setLocale(language);
    }


    public void onLocal(View v)
    {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.opt0);
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
        CharSequence text = getResources().getString(R.string.opt1);
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
        CharSequence text = getResources().getString(R.string.opt2);
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
        CharSequence text = getResources().getString(R.string.opt3);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        Intent intentClient=new Intent(this, MainActivityGame.class);
        intentClient.putExtra("mode", MainActivityGame.CLIENT);
        startActivity(intentClient);
    }
    public void onCredits (View v)
    {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.optCrd);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        Intent intentClient=new Intent(this, CreditsActivity.class);
        startActivity(intentClient);
    }







}
