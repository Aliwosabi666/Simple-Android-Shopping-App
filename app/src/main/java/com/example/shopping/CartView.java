package com.example.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class CartView extends AppCompatActivity {
    String[] headers = {"No  Product Name","Price"};
    String[][] no_name_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        ArrayList<String> name = Data.name;
        ArrayList<String> price = Data.price;
        TextView total = findViewById(R.id.totals);
        total.setText(Data.total);
        TableView<String[]> head = findViewById(R.id.tablelayout);
        head.setColumnCount(2);
        head.setHeaderAdapter(new SimpleTableHeaderAdapter(CartView.this,headers));
        no_name_price = new String[name.size()][2];
        for (int i = 0; i < name.size() && i < price.size(); i++) {
            no_name_price[i][0] = name.get(i);
            no_name_price[i][1] = price.get(i);
        }
        head.setDataAdapter(new SimpleTableDataAdapter(CartView.this,no_name_price));
        name.clear();
        price.clear();

    }
}
