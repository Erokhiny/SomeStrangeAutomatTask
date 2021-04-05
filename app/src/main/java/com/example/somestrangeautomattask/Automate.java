package com.example.somestrangeautomattask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Automate {
    ArrayList<IProduct> products;
    String status, name;
    ArrayList<Student> students;
    AutomateFragment automateView;
    MainActivity context;
    Automate instance;
    Handler handler;

    Automate(String status, String name, AutomateFragment automateView, MainActivity context){
        this.name = name;
        this.status = status;
        this.automateView = automateView;
        this.context=context;
        products = new ArrayList<IProduct>();
        students = new ArrayList<Student>();
        instance = this;
        handler = new Handler() {   // создание хэндлера
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                automateView.updateAutomateData(instance);
            }
        };
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
        Thread thread=new Thread(new IdleRunnable());
        thread.start();
//        Idle next = new Idle();
//        next.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class IdleRunnable implements Runnable {
        @Override
        public void run() {
            status = "Простаивает";
            handler.sendEmptyMessage(1);
            try {
                TimeUnit.SECONDS.sleep(2+(int)(Math.random()*3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(students.size()!=0) {
                Thread thread = new Thread(new OrderingRunnable());
                thread.start();
            }
        }
    }

    class OrderingRunnable implements Runnable {
        @Override
        public void run() {
            status = "Приём заказа";
            handler.sendEmptyMessage(1);
            try {
                TimeUnit.SECONDS.sleep(3+(int)(Math.random()*3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            students.get(0).choose_your_destiny(instance);
            if(students.get(0).cash>=students.get(0).get_sum_of_order()){
                Thread thread = new Thread(new PayingRunnable());
                thread.start();
            }
            else{
                Thread thread = new Thread(new CancelingRunnable());
                thread.start();
            }
        }
    }

    class PayingRunnable implements Runnable {
        @Override
        public void run() {
            status = "Оплата заказа";
            handler.sendEmptyMessage(1);
            try {
                TimeUnit.SECONDS.sleep(3+(int)(Math.random()*3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            students.get(0).cash-=students.get(0).get_sum_of_order();
            Thread thread = new Thread(new GivingRunnable());
            thread.start();
        }
    }

    class CancelingRunnable implements Runnable {
        @Override
        public void run() {
            status = "Отмена заказа";
            handler.sendEmptyMessage(1);
            try {
                TimeUnit.SECONDS.sleep(3+(int)(Math.random()*3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            students.remove(0);
            Thread thread = new Thread(new IdleRunnable());
            thread.start();
        }
    }

    class GivingRunnable implements Runnable {
        @Override
        public void run() {
            status = "Выдача заказа";
            handler.sendEmptyMessage(1);
            try {
                TimeUnit.SECONDS.sleep(4+(int)(Math.random()*3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (IProduct i : students.get(0).wished_products) {
                products.remove(i);
            }
            students.remove(0);
            Thread thread = new Thread(new IdleRunnable());
            thread.start();
        }
    }
}
