package com.example.juan.examen;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Juan on 4/12/2016.
 */
public class LoggedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_screen_layout);
    }
}
