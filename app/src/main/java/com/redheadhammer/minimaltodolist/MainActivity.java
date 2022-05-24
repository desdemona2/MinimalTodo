package com.redheadhammer.minimaltodolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button save;
    ListView list;
    ArrayList<String> items;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.item);
        save = findViewById(R.id.save);
        list = findViewById(R.id.list);

        items = FileHelper.readData(this);
        writeItems();

        save.setOnClickListener(this::save);

        list.setOnItemClickListener(this::remove);
    }

    public void save(View view) {
        String itemName = String.valueOf(item.getText());
        if (itemName.length() > 0) {
            items.add(itemName);
            arrayAdapter.notifyDataSetChanged();
            item.setText("");
            FileHelper.writeData(items, getApplicationContext());
        }
    }

    public void remove(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Item");
        alert.setMessage("Do you want to delete the item?");
        alert.setPositiveButton("Yes", (dialogInterface, k) -> {
            items.remove(i);
            arrayAdapter.notifyDataSetChanged();
            FileHelper.writeData(items, getApplicationContext());
        });
        alert.setNegativeButton("No", (dialogInterface, m) -> dialogInterface.cancel());

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void writeItems() {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,
                android.R.id.text1, items);
        list.setAdapter(arrayAdapter);
    }
}