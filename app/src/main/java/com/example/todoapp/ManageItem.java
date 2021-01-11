package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapp.db.ItemDAO;
import com.example.todoapp.db.ItemDatabase;

import static com.example.todoapp.MainActivity.ITEM_ID;

public class ManageItem extends AppCompatActivity {

    private EditText itemEditField;

    private Button buttonSave;

    private ItemDAO itemDAO;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);

        itemEditField = findViewById(R.id.itemEditField);
        buttonSave = findViewById(R.id.buttonSave);

        itemDAO = Room.databaseBuilder(this, ItemDatabase.class, ItemDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemsDAO();

        item = itemDAO.getItemById(getIntent().getIntExtra(ITEM_ID, -1));
        itemEditField.setText(item.getName());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setName(itemEditField.getText().toString());
                itemDAO.update(item);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}