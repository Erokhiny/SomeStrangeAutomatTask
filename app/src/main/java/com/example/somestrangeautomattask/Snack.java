package com.example.somestrangeautomattask;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class Snack implements IProduct {
    private int cost, weight, type;
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
        return "снек";
    }

    @Override
    public String get_additional_information() {
        return "Масса нетто: "+ weight + " г";
    }

    public Snack(String name, int type, int cost, int weight){
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.weight = weight;
    }

    public Snack(Snack a){
        this.name = a.name;
        this.type = 1;
        this.cost = a.cost;
        this.weight = a.weight;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Snack))
            return false;
        Snack tmp = (Snack)obj;
        if(name == tmp.name && weight == tmp.weight && cost == tmp.cost)
            return true;
        return false;
    }
}
