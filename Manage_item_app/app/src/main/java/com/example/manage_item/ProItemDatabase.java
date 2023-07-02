package com.example.manage_item;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProItem.class},version = 1)
public abstract class ProItemDatabase extends RoomDatabase {
    private static final String  DATABASE_NAME ="proitem.db";
    private static ProItemDatabase instance;
    public static synchronized ProItemDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ProItemDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract ProItemDao proItemDao();
}
