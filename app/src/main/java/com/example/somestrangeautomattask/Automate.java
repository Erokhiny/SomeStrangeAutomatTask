package com.example.somestrangeautomattask;

import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Automate {
    ArrayList<IProduct> products;
    String status, name;
    ArrayList<Student> students;
    LinearLayout automateView;
    MainActivity context;
    Automate instance;
    Automate(String status, String name, LinearLayout automateView, MainActivity context){
        this.name = name;
        this.status = status;
        this.automateView = automateView;
        this.context=context;
        products = new ArrayList<IProduct>();
        students = new ArrayList<Student>();
        instance = this;
    }

    public int get_length_of_queue(){
        return students.size();
    }

    public void add_product(String name, int type, int cost, int additional_information){
        products.add(DataChecker.get_instance().check_product_data(name, type, cost, additional_information));
    }

    public void add_product(IProduct product){
        products.add(product);
    }

    public void add_student(Student student){
        students.add(student);
    }

    public void execute(){
        Idle next = new Idle();
        next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class Idle extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((TextView)automateView.getChildAt(0)).setText(name);
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText("Простаивает");
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("");
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)students.size()).toString());
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText("");
            RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(context, new ArrayList<IProduct>());
            recyclerView.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(students.size()!=0){
                Oredering next = new Oredering();
                next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }
    class Oredering extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((TextView)automateView.getChildAt(0)).setText(name);
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText("Приём заказа");
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("ID студента: " + students.get(0).id);
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)students.size()).toString());
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText("Расчитывается");
            students.get(0).choose_your_destiny(instance);
            RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(context, students.get(0).wished_products);
            recyclerView.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(students.get(0).cash>=students.get(0).get_sum_of_order()){
                Paying next = new Paying();
                next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else{
                Canceling next = new Canceling();
                next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    class Paying extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((TextView)automateView.getChildAt(0)).setText(name);
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText("Оплата заказа");
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("ID студента: " + students.get(0).id);
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)students.size()).toString());
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText(((Integer)students.get(0).get_sum_of_order()).toString());
            RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(context, students.get(0).wished_products);
            recyclerView.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            students.get(0).cash-=students.get(0).get_sum_of_order();
            Giving next = new Giving();
            next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    class Canceling extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((TextView)automateView.getChildAt(0)).setText(name);
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText("Отмена заказа");
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("ID студента: " + students.get(0).id);
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)students.size()).toString());
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText(((Integer)students.get(0).get_sum_of_order()).toString());
            RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(context, students.get(0).wished_products);
            recyclerView.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            students.remove(0);
            Idle next = new Idle();
            next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    class Giving extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((TextView)automateView.getChildAt(0)).setText(name);
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(0)).setText("Выдача заказа");
            ((TextView)((LinearLayout)automateView.getChildAt(1)).getChildAt(1)).setText("ID студента: " + students.get(0).id);
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(0)).setText(((Integer)students.size()).toString());
            ((TextView)((LinearLayout)automateView.getChildAt(3)).getChildAt(1)).setText(((Integer)students.get(0).get_sum_of_order()).toString());
            RecyclerView recyclerView = (RecyclerView) automateView.getChildAt(2);
            ProductAdapter adapter = new ProductAdapter(context, students.get(0).wished_products);
            recyclerView.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            for (IProduct i: students.get(0).wished_products) {
                products.remove(i);
            }
            students.remove(0);
            Idle next = new Idle();
            next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
