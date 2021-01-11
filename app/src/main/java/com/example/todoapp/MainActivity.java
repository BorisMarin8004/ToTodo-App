package com.example.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todoapp.db.ItemDAO;
import com.example.todoapp.db.ItemDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String ITEM_ID = "ITEM_ID";

    private ListView toDoListView;

    private EditText activityNameField;
    private EditText activityPriorityField;

    private Button buttonSubmit;
    private Button buttonDeleteAll;

    private ItemDAO itemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListView = findViewById(R.id.toDoList);
        activityNameField = findViewById(R.id.activityName);
        activityPriorityField = findViewById(R.id.activityPriority);
        buttonSubmit = findViewById(R.id.submit);
        buttonDeleteAll = findViewById(R.id.deleteButton);

        refreshDisplay();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int priority;
                try {
                    priority = Integer.parseInt(activityPriorityField.getText().toString());
                }catch (NumberFormatException e){
                    priority = 0;
                }
                Item newItem = new Item(activityNameField.getText().toString(), priority, LocalDate.now());
                itemDAO.insert(newItem);
                refreshDisplay();
            }
        });
        
        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Item item: itemDAO.getItems()) {
                    itemDAO.delete(item);
                }
                refreshDisplay();
            }
        });
        
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), ManageItem.class);
                intent.putExtra(ITEM_ID, itemDAO.getItems().get(position).getId());
                finish();
                startActivity(intent);
            }
        });

        toDoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                itemDAO.delete(itemDAO.getItems().get(position));
                refreshDisplay();
                return true;
            }
        });
    }

    private void refreshDisplay(){
        if (itemDAO == null){
            itemDAO = Room.databaseBuilder(this, ItemDatabase.class, ItemDatabase.DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                    .getItemsDAO();
        }
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), (ArrayList<Item>) itemDAO.getItems());
        toDoListView.setAdapter(adapter);
    }


}