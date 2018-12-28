package com.amov.reversi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CreditsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }





    public void onExit (View v){

        finish();
    }

}
