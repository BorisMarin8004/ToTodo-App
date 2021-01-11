package com.example.todoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void insert(Item... items);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Item... items);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM " + ItemDatabase.ITEMS_TABLE)
    List<Item> getItems();

    @Query("SELECT * FROM " + ItemDatabase.ITEMS_TABLE+ " WHERE id = :id")
    Item getItemById(int id);
}
