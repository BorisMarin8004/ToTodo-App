package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.todoapp.db.ItemDatabase;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = ItemDatabase.ITEMS_TABLE)
public class Item {

    private String name;
    private int priority;
    private LocalDate date;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Item(String name, int priority, LocalDate date){
        this.name = name;
        this.priority = priority;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getPriority() == item.getPriority() &&
                getId() == item.getId() &&
                getName().equals(item.getName()) &&
                getDate().equals(item.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPriority(), getDate(), getId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", date=" + date +
                '}';
    }
}
