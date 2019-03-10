package com.example.shopping;

import java.util.ArrayList;

public class Data {
    public static ArrayList<String> name = new ArrayList<>();
    public static ArrayList<String> price = new ArrayList<>();
    public static String total;

    public static  void setData(ArrayList<String> nameeee,ArrayList<String> priceeee, String totalll){
        name = nameeee;
        price = priceeee;
       total = totalll;
    }
}
