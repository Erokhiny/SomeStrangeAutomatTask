package com.example.somestrangeautomattask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    LinearLayout main_thing;
    public static Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Automate> automates = new ArrayList<Automate>();
        update_Automate_data(automates);
        automates.get(0).execute();
        automates.get(1).execute();
        automates.get(2).execute();
        automates.get(3).execute();
    }

    void update_Automate_data(List<Automate> automates){
        automates.add(new Automate("Простаивает", "Первый Автомат", (AutomateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.automate1), this));
        automates.add(new Automate("Простаивает", "Второй Автомат", (AutomateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.automate2), this));
        automates.add(new Automate("Простаивает", "Третий Автомат", (AutomateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.automate3), this));
        automates.add(new Automate("Простаивает", "Четвёртый Автомат", (AutomateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.automate4), this));
        for (Automate i: automates) {
            RecyclerView recyclerView = (RecyclerView) ((LinearLayout)i.automateView.getView()).getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(this, new ArrayList<IProduct>());
            recyclerView.setAdapter(adapter);
        }
        ArrayList<IProduct> allproducts = new ArrayList<IProduct>();
        allproducts.add(DataChecker.get_instance().check_product_data("Нюка-Кола", 1, 100, 600));
        allproducts.add(DataChecker.get_instance().check_product_data("Сливочное пиво", 1, 150, 400));
        allproducts.add(DataChecker.get_instance().check_product_data("Молоко динозавров", 1, 200, 500));
        allproducts.add(DataChecker.get_instance().check_product_data("Бобы Берти-Ботс", 0, 200, 40));
        allproducts.add(DataChecker.get_instance().check_product_data("Сникерс", 0, 50, 80));
        allproducts.add(DataChecker.get_instance().check_product_data("Принглс", 0, 150, 100));
        for (Automate i: automates) {
            for(int j = (random.nextInt()%6+6)%6+20; j>0; j--){
                int a = (random.nextInt() % 3 + 3) % 3;
                System.out.println(a);
                System.out.println(allproducts.get(a).get_name());
                i.products.add(allproducts.get(a));
                i.products.add(allproducts.get((random.nextInt() % 3 + 3) % 3 + 3));
            }
        }
        for (int i = 0; i < 20; i++) {
            Student s = new Student(i, random.nextInt() % 2000 + 3000);
            automates.get((random.nextInt() % 4 + 4) % 4).add_student(s);
        }
    }
}