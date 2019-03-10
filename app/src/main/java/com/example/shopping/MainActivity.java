package com.example.shopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        final DataBase dataBase = new DataBase(context);

        dataBase.insertDataIntoElec("TV", "2000");
        dataBase.insertDataIntoElec("Monitor", "12000");
        dataBase.insertDataIntoElec("Iphone", "25000");
        dataBase.insertDataIntoElec("Samsung TV", "56000");
        dataBase.insertDataIntoElec("PC", "14000");
        dataBase.insertDataIntoElec("Graphic Card", "1000");
        dataBase.insertDataIntoElec("Keyboard", "15000");
        dataBase.insertDataIntoElec("Mouse", "200");

        dataBase.insertDataIntoFurn("Chair", "450");
        dataBase.insertDataIntoFurn("Table", "2000");
        dataBase.insertDataIntoFurn("Bed", "5000");
        dataBase.insertDataIntoFurn("Kitchen Rack", "25600");
        dataBase.insertDataIntoFurn("Gate Wooden", "1250");
        dataBase.insertDataIntoFurn("Gate Iron", "1500");
        dataBase.insertDataIntoFurn("Gate Fiber", "1600");
        dataBase.insertDataIntoFurn("Sofa", "25000");

        dataBase.insertDataIntoStat("Note Book", "200");
        dataBase.insertDataIntoStat("Pencil", "10");
        dataBase.insertDataIntoStat("Eraser", "15");
        dataBase.insertDataIntoStat("Book Java", "2000");
        dataBase.insertDataIntoStat("C++ Book", "500");
        dataBase.insertDataIntoStat("Android Book", "200");
        dataBase.insertDataIntoStat("Oracle Book", "300");
        dataBase.insertDataIntoStat("Diary", "400");

        dataBase.insertDataIntoLogin("ankesh", "12345678");
        dataBase.insertDataIntoLogin("chillmarrore", "sabmoahmayahai");

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase data = dataBase.getReadableDatabase();
                data.beginTransaction();
                String query = "SELECT username,password FROM " + DataBase.login;
                Cursor cursor = data.rawQuery(query, null);
                EditText user = findViewById(R.id.username);
                String username = String.valueOf(user.getText());
                EditText pass = findViewById(R.id.password);
                String password = String.valueOf(pass.getText());
                Toast toast;
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        if (username.equals(cursor.getString(cursor.getColumnIndex("username"))) && password.equals(cursor.getString(cursor.getColumnIndex("password")))) {
                            toast = Toast.makeText(getApplicationContext(),
                                    "Successfully logged in",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            Intent soppingPage = new Intent(MainActivity.this, ShoppingMenu.class);
                            startActivity(soppingPage);
                            break;
                        } else {
                            toast = Toast.makeText(getApplicationContext(),
                                    "Failed Retry",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                    }
                    data.setTransactionSuccessful();
                    data.endTransaction();;
                    data.close();
                }
            }
        });
    }
}
