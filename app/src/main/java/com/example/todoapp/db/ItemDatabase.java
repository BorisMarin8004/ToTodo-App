package com.example.todoapp.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.todoapp.Item;
import com.example.todoapp.db.typeConverters.DateTypeConverter;

@Database(entities = {Item.class}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class ItemDatabase extends RoomDatabase {

    public static final String DB_NAME = "ITEM_DATABASE";
    public static final String ITEMS_TABLE = "ITEMS_TABLE";

    public abstract ItemDAO getItemsDAO();
}
