package com.a1tech.expensebook.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Adapter.ItemAdapter;
import com.a1tech.expensebook.Model.Items;
import com.a1tech.expensebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {

    private final String TAG = "ItemsActivity";
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    ArrayList<Items> items = new ArrayList<Items>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private EditText stateName, cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        initViews();
        setInitialData();
        createAndSetAdapter();
        btnOnClick();
    }

    private void createAndSetAdapter() {
        // создаем адаптер
        itemAdapter = new ItemAdapter(this, items);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(itemAdapter);
    }

    private void initViews() {
        // начальная инициализация списка
        recyclerView = findViewById(R.id.itemsList);
        fab = findViewById(R.id.itemFab);
    }

    private void btnOnClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogDemo();
            }
        });
    }

    private void setInitialData() {
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
        items.add(new Items("Яблоко", "6 шт/кг", "35 000"));
    }

    private void alertDialogDemo() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(ItemsActivity.this);

        final EditText etName = dialogView.findViewById(R.id.itemName);
        final EditText etCount = dialogView.findViewById(R.id.itemCount);
        final EditText etPrice = dialogView.findViewById(R.id.itemPrice);

        builder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // formatter of number (1234567890) --> (1 234 567 890)
                        String formatCount = decimalFormat.format(Double.valueOf(etPrice.getText().toString()));
                        String formatPrice = decimalFormat.format(Double.valueOf(etCount.getText().toString()));

                        items.add(new Items(etName.getText().toString(), formatCount + " шт/кг", formatPrice));
                        recyclerView.setAdapter(itemAdapter);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}