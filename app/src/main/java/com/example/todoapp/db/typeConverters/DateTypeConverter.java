package com.example.todoapp.db.typeConverters;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTypeConverter {
    @TypeConverter
    public String convertDateToLong(LocalDate date){
        return date.toString();
    }

    @TypeConverter
    public LocalDate convertLongToDate(String time){
        return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-d"));
    }
}
