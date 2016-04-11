package com.example.juan.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DataBaseManager(this, "EXAMEN_DATA_BASE");
    }

    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            dbManager.insertDB(newUser, DataBaseManager.USERS_TABLE_NAME);
        }
    }


}
