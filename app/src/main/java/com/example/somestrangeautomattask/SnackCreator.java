package com.example.somestrangeautomattask;

public class SnackCreator implements IProductCreator{
    @Override
    public IProduct get_new_product(String name, int type, int cost, int additional_information) {
        return new Snack(name, type, cost, additional_information);
    }
}
