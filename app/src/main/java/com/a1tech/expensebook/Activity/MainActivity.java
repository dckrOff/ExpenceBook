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

import com.a1tech.expensebook.Model.Item;
import com.a1tech.expensebook.Adapter.ItemAdapter;
import com.a1tech.expensebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    ArrayList<Item> items = new ArrayList<Item>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private EditText stateName, cityName;
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setInitialData();

        // создаем адаптер
        adapter = new ItemAdapter(this, items);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        btnOnClick();
    }

    private void initViews() {
        // начальная инициализация списка
        recyclerView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);
        stateName = findViewById(R.id.itemName);
        cityName = findViewById(R.id.itemCount);
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
        items.add(new Item("Яблоко", "6 шт/кг", "35 000"));
//        states.add(new State("Бразилия", "Бразилиа", "35 000 so'm"));
//        states.add(new State("Аргентина", "Буэнос-Айрес", "35 000 so'm"));
//        states.add(new State("Колумбия", "Богота", "35 000 so'm"));
//        states.add(new State("Уругвай", "Монтевидео", "35 000 so'm"));
//        states.add(new State("Чили", "Сантьяго", "35 000 so'm"));
    }

    private void alertDialogDemo() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final EditText etName = (EditText) dialogView.findViewById(R.id.itemName);
        final EditText etCount = (EditText) dialogView.findViewById(R.id.itemCount);
        final EditText etPrice = (EditText) dialogView.findViewById(R.id.itemPrice);

        builder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String formatCount = decimalFormat.format(Double.valueOf(etPrice.getText().toString()));
                        String formatPrice = decimalFormat.format(Double.valueOf(etCount.getText().toString()));

                        items.add(new Item(etName.getText().toString(), formatCount + " шт/кг", formatPrice));
                        recyclerView.setAdapter(adapter);
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