package com.a1tech.expensebook.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1tech.expensebook.Adapter.ItemAdapter;
import com.a1tech.expensebook.Model.ItemsModel;
import com.a1tech.expensebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {

    private final String TAG = "ItemsActivity";
    private final String ITEM_SHARED_NAME = "itemsList";
    private final String SHARED_PREFS_NAME = "sharedPrefsData";
    private final String pattern = "###,###,###.###";
    private final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private ArrayList<ItemsModel> itemsArrayList = new ArrayList<>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ImageView btnBack;
    private TextView tvTotalAmmount;
    private long totalAmmountSumm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        initViews();
        loadData();
        buildRecyclerView();
        btnOnClick();
        totalAmmount();
        swipeToDeleteitem();
    }

    private void swipeToDeleteitem() {
        // on below line we are creating a method to create item touch helper
        // method for adding swipe to delete functionality.
        // in this we are specifying drag direction and position to right
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                ItemsModel deletedCourse = itemsArrayList.get(viewHolder.getAdapterPosition());

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                itemsArrayList.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                itemAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                saveData();

                // below line is to display our snackbar with action.
                Snackbar.make(recyclerView, deletedCourse.getName(), Snackbar.LENGTH_LONG).setAction("Отмена", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        itemsArrayList.add(position, deletedCourse);

                        // below line is to notify item is
                        // added to our adapter class.
                        itemAdapter.notifyItemInserted(position);

                        saveData();
                    }
                }).show();
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView);
    }

    private void buildRecyclerView() {
        // инициализируем наш класс адаптера.
        itemAdapter = new ItemAdapter(this, itemsArrayList);

        // добавление менеджера компоновки в наше представление ресайклера.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        // настройка менеджера компоновки для нашего представления ресайклера.
        recyclerView.setLayoutManager(manager);

        // настройка адаптера для нашего вида ресайклера.
        recyclerView.setAdapter(itemAdapter);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.itemsList);
        fab = findViewById(R.id.itemFab);
        btnBack = findViewById(R.id.btnBack);
        tvTotalAmmount = findViewById(R.id.tvTotalAmmount);
    }

    private void btnOnClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogDemo();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this, ObjectsActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private void totalAmmount() {
        for (int i = 0; i < itemsArrayList.size(); i++) {
            long listPrice = itemsArrayList.get(i).getPrice();
            totalAmmountSumm += listPrice;
        }
        String totalSumm = decimalFormat.format(Double.valueOf(totalAmmountSumm));
        tvTotalAmmount.setText(totalSumm);
    }

    private void loadData() {
        Log.e(TAG, "loadData ga kirdi");
        // метод загрузки массива из общих настроек инициализируя наши общие настройки с именем как общие предпочтения.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        // ниже строки, чтобы добраться до строки, представленной из нашего общие настройки, если они отсутствуют, устанавливая их как нулевые.
        String json = sharedPreferences.getString(ITEM_SHARED_NAME, null);

        // создание переменной для gson.
        Gson gson = new Gson();

        // ниже строка, чтобы получить тип нашего списка массивов.
        Type type = new TypeToken<ArrayList<ItemsModel>>() {
        }.getType();

        // в строке ниже мы получаем данные от gson
        // и сохраняем его в наш список массивов
        itemsArrayList = gson.fromJson(json, type);

        // проверка ниже, если список массивов пуст или нет
        if (itemsArrayList == null) {
            // если список массивов пуст
            // создание нового списка массивов.
            itemsArrayList = new ArrayList<>();
        }
        Log.e(TAG, "loadData dan ketdi");
    }

    private void saveData() {
        Log.e(TAG, "saveData ga kirdi");
        // способ сохранения данных в списке массивов. Создание переменной для хранения данных в общие предпочтения.
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        // создание переменной для редактора хранить данные в общих настройках.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // создание переменной для gson.
        Gson gson = new Gson();

        // получение данных от gson и сохранение их в строке.
        String json = gson.toJson(itemsArrayList);

        // нижняя строка предназначена для сохранения данных в общем prefs в виде строки.
        editor.putString(ITEM_SHARED_NAME, json);

        // нижняя строка для применения изменений и сохранить данные в общих файлах настроек.
        editor.apply();

        Log.e(TAG, "saveData dan chikdi");
        Log.e(TAG, json);
    }

    private void alertDialogDemo() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(ItemsActivity.this);

        final EditText etName = dialogView.findViewById(R.id.itemName);
        final EditText etCount = dialogView.findViewById(R.id.itemCount);
        final EditText etPrice = dialogView.findViewById(R.id.itemPrice);

        builder.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (!etName.getText().toString().isEmpty() && !etCount.getText().toString().isEmpty() && !etPrice.getText().toString().isEmpty()) {

                    String name = etName.getText().toString().trim();
                    int count = Integer.parseInt(etCount.getText().toString());
                    long price = Long.parseLong(etPrice.getText().toString());

                    itemsArrayList.add(new ItemsModel(name, count, price));
                    recyclerView.setAdapter(itemAdapter);

                    saveData();
                    totalAmmount();
                    dialog.dismiss();
                } else {
                    Toast.makeText(ItemsActivity.this, "Ошибка: Заполните пустые поля!", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}