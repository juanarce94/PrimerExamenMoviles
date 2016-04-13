package com.example.juan.examen;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Juan on 4/12/2016.
 */
public class SingInActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_in);

    }

    public void tryToSingIn(View view) {

        String name = ((EditText)findViewById(R.id.editText4)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText3)).getText().toString();

        String[] columns = new String[] {DataBaseManager.ID,DataBaseManager.USER_NAME,DataBaseManager.USER_PASSWORD};
        Cursor cursor = DataBaseManager.avesDataBase.query(DataBaseManager.USERS_TABLE_NAME, columns, null, null, null, null, null);

        int iName = cursor.getColumnIndex(DataBaseManager.USER_NAME);
        boolean exists = false;

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            if(cursor !=  null)
                if(name.equals(cursor.getString(iName)))
                    exists = true;
        }

        if(exists){
            Toast.makeText(this,"Username already exists!",Toast.LENGTH_LONG).show();
        }
        else {
            User newUser = new User(name,password);
            DataBaseManager.insertDB(newUser,DataBaseManager.USERS_TABLE_NAME);
            Toast.makeText(this,"User created",Toast.LENGTH_LONG).show();
        }

    }
}
