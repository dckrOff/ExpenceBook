package com.a1tech.expensebook.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Adapter.ObjectAdapter;
import com.a1tech.expensebook.Model.Objects;
import com.a1tech.expensebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ObjectsActivity extends AppCompatActivity {

    private final String TAG = "ObjectActivity";
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    ArrayList<Objects> objects = new ArrayList<Objects>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ObjectAdapter objectAdapter;
    private ObjectAdapter.OnStateClickListener objectClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        initViews();
        setInitialData();
        createAndSetAdapter();
        btnOnClick();
    }

    private void createAndSetAdapter() {
        // создаем адаптер
        objectAdapter = new ObjectAdapter(this, objects, objectClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(objectAdapter);
    }

    private void initViews() {
        // начальная инициализация списка
        recyclerView = findViewById(R.id.objectsList);
        fab = findViewById(R.id.objectFab);
    }

    private void btnOnClick() {
        // определяем слушателя нажатия элемента в списке
        objectClickListener = new ObjectAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Objects objects, int position) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + objects.getObjectName(), Toast.LENGTH_SHORT).show();
            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogDemo();
            }
        });
    }

    private void setInitialData() {
        for (int i = 0; i < 15; i++) {
            objects.add(new Objects("Квартира №" + i, "35 000 000"));

        }
    }

    private void alertDialogDemo() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_object_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(ObjectsActivity.this);

        final EditText etName = dialogView.findViewById(R.id.objectName);

        builder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        objects.add(new Objects(etName.getText().toString(), "12 345 678"));
                        recyclerView.setAdapter(objectAdapter);
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