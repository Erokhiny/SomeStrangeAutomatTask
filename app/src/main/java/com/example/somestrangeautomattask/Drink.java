package com.example.somestrangeautomattask;

import androidx.annotation.Nullable;

public class Drink implements IProduct{
    private int cost, volume, type;
    private String name;

    @Override
    public int get_cost() {
        return cost;
    }

    @Override
    public String get_name() {
        return name;
    }

    @Override
    public String get_type() {
        return "напиток";
    }

    @Override
    public String get_additional_information() {
        return "Объём: "+ volume + " мл";
    }

    public Drink(String name, int type, int cost, int volume){
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.volume = volume;
    }

    public Drink(Drink a){
        this.name = a.name;
        this.type = 1;
        this.cost = a.cost;
        this.volume = a.volume;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Drink))
            return false;
        Drink tmp = (Drink) obj;
        if(name == tmp.name && volume == tmp.volume && cost == tmp.cost)
            return true;
        return false;
    }
}
