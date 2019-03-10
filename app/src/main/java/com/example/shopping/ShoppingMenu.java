package com.example.shopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ShoppingMenu extends AppCompatActivity {
    private Context context;
    String[] headers = {"No  Product Name","Price"};
    String[][] no_name_price;
    ArrayList<Integer> no = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();

    public static ArrayList<String> p_name = new ArrayList<>();
    public static ArrayList<String> p_price = new ArrayList<>();
    public static double total=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_menu);

        Spinner category = findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        context = ShoppingMenu.this;
        final DataBase dataBase = new DataBase(context);
        /*dataBase.insertDataIntoElec("TV", "2000");
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
*/
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SQLiteDatabase data = dataBase.getReadableDatabase();
                String query;
                Cursor cursor;
                switch(position){
                    case 1:
                        data.beginTransaction();
                        query = "SELECT * FROM " + DataBase.electronics;
                        cursor = data.rawQuery(query,null);
                        //no_name_price = new String[cursor.getColumnCount()][cursor.getColumnCount()][3];
                        if (cursor.getCount() > 0) {
                            while (cursor.moveToNext()) {
                                no.add(cursor.getInt(cursor.getColumnIndex("elec_id")));
                                name.add(cursor.getString(cursor.getColumnIndex("name")));
                                price.add(cursor.getString(cursor.getColumnIndex("price")));
                            }
                        }
                        data.setTransactionSuccessful();
                        data.endTransaction();;
                        data.close();
                        putData();
                        break;
                    case 2:
                        data.beginTransaction();
                        query = "SELECT * FROM " + DataBase.stationary;
                        cursor = data.rawQuery(query,null);
                        if (cursor.getCount() > 0) {
                            while (cursor.moveToNext()) {
                                no.add(cursor.getInt(cursor.getColumnIndex("stat_id")));
                                name.add(cursor.getString(cursor.getColumnIndex("name")));
                                price.add(cursor.getString(cursor.getColumnIndex("price")));
                            }

                        }
                        data.setTransactionSuccessful();
                        data.endTransaction();;
                        data.close();
                        putData();
                        break;
                    case 3:
                        data.beginTransaction();
                        query = "SELECT * FROM " + DataBase.furniture;
                        cursor = data.rawQuery(query,null);
                        //no_name_price = new String[cursor.getColumnCount()][cursor.getColumnCount()][3];
                        if (cursor.getCount() > 0) {
                            while (cursor.moveToNext()) {
                                no.add(cursor.getInt(cursor.getColumnIndex("func_id")));
                                name.add(cursor.getString(cursor.getColumnIndex("name")));
                                price.add(cursor.getString(cursor.getColumnIndex("price")));
                            }
                        }
                        data.setTransactionSuccessful();
                        data.endTransaction();;
                        data.close();
                        putData();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TableView<String[]> head = findViewById(R.id.tablelayout);
        head.addDataClickListener(new TableDataClickListener() {
            @Override
            public void onDataClicked(int rowIndex, Object clickedData) {
                p_name.add(((String[])clickedData)[0]);
                p_price.add(((String[])clickedData)[1]);
                total = total + Integer.parseInt(((String[])clickedData)[1]);
            }
        });
    }

    public void putData(){
        TableView<String[]> head = findViewById(R.id.tablelayout);
        head.setColumnCount(2);
        head.setBackgroundColor(Color.WHITE);
        head.setHeaderAdapter(new SimpleTableHeaderAdapter(ShoppingMenu.this,headers));
        no_name_price = new String[no.size()][2];
        for (int i = 0; i < name.size() && i < price.size(); i++) {
            no_name_price[i][0] = String.valueOf(no.get(i)).concat(" "+name.get(i));
            no_name_price[i][1] = price.get(i);
        }
        head.setDataAdapter(new SimpleTableDataAdapter(ShoppingMenu.this,no_name_price));
        no.clear();
        name.clear();
        price.clear();
    }
    public void ChangeTo(View view) {
    }

    public void viewCart(View view) {

        Data.setData(p_name,p_price,String.valueOf(total));
        Intent cartpage = new Intent(this, CartView.class);
        startActivity(cartpage);
    }

    public void viewBill(View view) {
    }

    public void addToCart(View view) {
    }
}
