package com.example.juan.examen;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DataBaseManager(this, "EXAMEN_DATA_BASE");

        int num = DataBaseManager.avesDataBase.rawQuery("SELECT " +
                                                        DataBaseManager.ID +
                                                        " FROM " +
                                                        DataBaseManager.AVES_TABLE_NAME, null).getCount();

        if(num < 1){
            addDefaultUsersToDataBase();
            addDefaultAvessToDataBase();
        }

    }


    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            dbManager.insertDB(newUser, DataBaseManager.USERS_TABLE_NAME);
        }
    }
    private void addDefaultAvessToDataBase(){
        for(int i = 0; i < 6; i++){
            Ave newAve = new Ave("Nombre"+String.valueOf(i),"NombreComun"+String.valueOf(i),"Descripcion"+String.valueOf(i),"Generalidades"+String.valueOf(i));
            dbManager.insertDB(newAve, DataBaseManager.AVES_TABLE_NAME);
        }
    }

    public void logInClicked(View view){

        TextView textViewName = (TextView) findViewById(R.id.editText);
        TextView textViewPassword = (TextView) findViewById(R.id.editText2);

        String name = textViewName.getText().toString();
        String password = textViewPassword.getText().toString();

        boolean mayEnter = dbManager.logRequest(name,password);

        if(mayEnter){
            Intent loggedScreenIntent = new Intent(getApplicationContext(),MainMenu.class);
            final int result = 1;
            startActivityForResult(loggedScreenIntent, result);
        }
        else{
            Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void singInClicked(View view) {

        Intent singInActivity = new Intent(this,SingInActivity.class);
        final int result = 1;
        startActivityForResult(singInActivity, result);

    }
}
