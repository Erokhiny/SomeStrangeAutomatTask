package com.example.somestrangeautomattask;

import java.util.ArrayList;

public class Student {
    ArrayList<IProduct> wished_products;
    int id, cash;
    Student(int id, int cash){
        this.id = id;
        this.cash = cash;
        wished_products = new ArrayList<IProduct>();
    }

    public void choose_your_destiny(Automate automate){
        wished_products = new ArrayList<IProduct>(automate.products);
        while(wished_products.size()>3){
            wished_products.remove((MainActivity.random.nextInt()%wished_products.size()+wished_products.size())%wished_products.size());
        }
    }

    public int get_sum_of_order(){
        int sum=0;
        for (IProduct i: wished_products) {
            sum+=i.get_cost();
        }
        return sum;
    }
}
