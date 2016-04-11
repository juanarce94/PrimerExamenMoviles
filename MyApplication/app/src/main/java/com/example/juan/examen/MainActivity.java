package com.example.juan.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        stringArray = dbManager.getData(DataBaseManager.USERS_TABLE_NAME);

        listView = (ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(adapter);






    }


    private void addDefaultUsersToDataBase(){
        for(int i = 0; i < 6; i++){
            User newUser = new User("name"+String.valueOf(i),"pass"+String.valueOf(i));
            dbManager.insertDB(newUser, DataBaseManager.USERS_TABLE_NAME);
        }
    }


}
