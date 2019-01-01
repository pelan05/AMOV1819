package com.amov.reversi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HistoricActivity extends Activity {

    public TextView tv;
    public FileInputStream in;
    public String finalString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tv = findViewById(R.id.historyTxtView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //atualizar textos

        try {
            in = openFileInput("gameScores");
            BufferedReader br = new BufferedReader( new InputStreamReader(in));

                StringBuilder sb = new StringBuilder();
                String line;

                while(( line = br.readLine()) != null ) {
                    sb.append( line );
                    sb.append( '\n' );
                }

                finalString = sb.toString();

            in.close();

            tv.setText(finalString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        public void onDelete(View v){
        File dir = getFilesDir();
        File file = new File(dir, "gameScores");
        boolean deleted = file.delete();

        if(deleted){
            tv.setText(getResources().getString(R.string.nothingToShow));
            Toast.makeText(getApplicationContext(),R.string.deletedYes, Toast.LENGTH_SHORT).show();
        }else{
            tv.setText(getResources().getString(R.string.nothingToShow));
            Toast.makeText(getApplicationContext(),R.string.deletedNo, Toast.LENGTH_SHORT).show();
        }

    }

    public void onExit (View v){
        finish();
    }
}
