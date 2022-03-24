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
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);

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

//        formatter of number(1234567890)-- > (1 234 567 890)
        String formatCount = decimalFormat.format(Double.valueOf(items.getCount()));
        String formatPrice = decimalFormat.format(Double.valueOf(items.getPrice()));

        holder.nameView.setText(items.getName());
        holder.countView.setText(formatCount);
        holder.price.setText(formatPrice);
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