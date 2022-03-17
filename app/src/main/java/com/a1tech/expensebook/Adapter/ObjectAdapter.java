package com.a1tech.expensebook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Model.Objects;
import com.a1tech.expensebook.R;

import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Objects> objects;

    public ObjectAdapter(Context context, List<Objects> objects) {
        this.inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_object, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Objects objects = this.objects.get(position);
        holder.price.setText(objects.getObjectPrice());
        holder.nameView.setText(objects.getObjectName());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, price;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.objectName);
            price = view.findViewById(R.id.objectPrice);
        }
    }
}