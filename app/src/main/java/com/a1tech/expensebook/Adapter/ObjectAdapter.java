package com.a1tech.expensebook.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Model.ObjectsModel;
import com.a1tech.expensebook.R;
import com.a1tech.expensebook.utils.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    @Override
    public void onItemDismiss(int position) {
        objects.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(objects, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(objects, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
//        return true;
    }

    public interface OnStateClickListener {
        void onObjectClick(ObjectsModel state, int position);
    }

    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<ObjectsModel> objects;

    public ObjectAdapter(Context context, List<ObjectsModel> objects, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(ObjectAdapter.ViewHolder holder, int position) {
        ObjectsModel objectsModel = this.objects.get(position);
        holder.price.setText(objectsModel.getObjectPrice());
        holder.nameView.setText(objectsModel.getObjectName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onObjectClick(objectsModel, position);
            }
        });
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