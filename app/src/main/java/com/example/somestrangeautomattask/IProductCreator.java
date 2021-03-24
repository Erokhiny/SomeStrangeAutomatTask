package com.example.somestrangeautomattask;

public interface IProductCreator {
    public IProduct get_new_product(String name, int type, int cost, int additional_information);
}
