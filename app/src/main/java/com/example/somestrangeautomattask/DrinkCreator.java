package com.example.somestrangeautomattask;

public class DrinkCreator implements IProductCreator{
    @Override
    public IProduct get_new_product(String name, int type, int cost, int additional_information) {
        return new Drink(name, type, cost, additional_information);
    }
}
