package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.room.Room;

import com.example.todoapp.db.ItemDAO;
import com.example.todoapp.db.ItemDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> list;
    private ItemDAO itemDAO;

    public CustomAdapter(Context context, ArrayList<Item> list){
        this.context = context;
        this.list = list;
        itemDAO = Room.databaseBuilder(context, ItemDatabase.class, ItemDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getItemsDAO();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_element, parent, false);
        }
        TextView itemTextField = convertView.findViewById(R.id.elementText);
        TextView itemPriorityField = convertView.findViewById(R.id.elementPriority);
        TextView itemDateField = convertView.findViewById(R.id.elementDate);

        itemTextField.setText(item.getName());
        itemPriorityField.setText(String.valueOf(item.getPriority()));
        itemDateField.setText(String.valueOf(item.getDate()));
        return convertView;
    }
}
