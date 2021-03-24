package com.example.somestrangeautomattask;

public class DataChecker {
    private static  DataChecker instance;

    public static DataChecker get_instance() {
        if(instance==null)
            instance = new DataChecker();
        return instance;
    }

    private DataChecker(){}

    public IProduct check_product_data(String name, int type, int cost, int additional_information){
        IProductCreator creator;
        if(type==1)
            creator = new DrinkCreator();
        else
            creator = new SnackCreator();
        return creator.get_new_product(name, type, cost, additional_information);
    }
}
