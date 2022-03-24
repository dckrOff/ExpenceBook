package com.a1tech.expensebook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Model.ItemsModel;
import com.a1tech.expensebook.R;

import java.text.DecimalFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ItemsModel> items;

    public ItemAdapter(Context context, List<ItemsModel> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemsModel items = this.items.get(position);
        holder.nameView.setText(items.getName());
        holder.countView.setText(items.getCount());
        holder.price.setText(items.getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, countView, price;

        ViewHolder(View view) {
            super(view);
            price = view.findViewById(R.id.price);
            nameView = view.findViewById(R.id.name);
            countView = view.findViewById(R.id.count);
        }
    }
}