package com.a1tech.expensebook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Model.Item;
import com.a1tech.expensebook.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.price.setText(item.getPrice());
        holder.nameView.setText(item.getName());
        holder.capitalView.setText(item.getCount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, capitalView, price;

        ViewHolder(View view) {
            super(view);
            price = view.findViewById(R.id.price);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.count);
        }
    }
}