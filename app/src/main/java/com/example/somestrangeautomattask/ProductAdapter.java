package com.example.somestrangeautomattask;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<IProduct> products;

    ProductAdapter(Context context, List<IProduct> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        System.out.println(123);
        IProduct product = products.get(position);
        holder.typeView.setText(product.get_type());
        holder.nameView.setText(product.get_name());
        holder.costView.setText("Цена: " + product.get_cost());
        holder.additional_informationView.setText(product.get_additional_information());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, typeView, costView, additional_informationView;
        ViewHolder(View view){
            super(view);
            typeView = (TextView)view.findViewById(R.id.type);
            nameView = (TextView) view.findViewById(R.id.name);
            costView = (TextView) view.findViewById(R.id.cost);
            additional_informationView = (TextView) view.findViewById(R.id.additional_information);
        }
    }
}