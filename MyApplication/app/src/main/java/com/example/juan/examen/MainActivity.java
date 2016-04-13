package com.example.juan.examen;

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
    private ListView listView;
    private ArrayList<String> stringArray = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DataBaseManager(this, "EXAMEN_DATA_BASE");

        //addDefaultUsersToDataBase();
        //dbManager.deleteTable(DataBaseManager.USERS_TABLE_NAME);

        stringArray = dbManager.getData(DataBaseManager.USERS_TABLE_NAME);

        listView = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(adapter);

        //dbManager.dropTable(DataBaseManager.USERS_TABLE_NAME);
    }


    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            dbManager.insertDB(newUser, DataBaseManager.USERS_TABLE_NAME);
        }
    }

    public void logInClicked(View view){

        TextView textViewName = (TextView) findViewById(R.id.editText);
        TextView textViewPassword = (TextView) findViewById(R.id.editText2);

        String name = textViewName.getText().toString();
        String password = textViewPassword.getText().toString();

        boolean mayEnter = dbManager.logRequest(name,password);

        if(mayEnter){
            Intent loggedScreenIntent = new Intent(this,LoggedActivity.class);
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
