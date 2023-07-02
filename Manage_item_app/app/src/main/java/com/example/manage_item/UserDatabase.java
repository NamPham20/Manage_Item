package com.example.manage_item;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String  DATABASE_NAME ="user.db";
    private static UserDatabase instance;
    public static synchronized UserDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback(){
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // Kiểm tra xem bảng có dữ liệu không
                            Cursor cursor = db.query("SELECT * FROM User");
                            if (cursor != null && cursor.moveToFirst()) {
                                int count = cursor.getInt(0);
                                if (count == 0) {
                                    // Thêm dữ liệu mẫu nếu bảng không có dữ liệu
                                    UserDatabase.getInstance(context).userDao().insertUser(new User("ET20203804", "05062002","Pham Phuong Nam"
                                            ,20,"Nam","Manager","Ha Noi","0367289425"));
                                    UserDatabase.getInstance(context).userDao().insertUser(new User("ET20218346", "28082002","Pham Thi Duy Linh"
                                            ,20,"Nu","Employee","Hai Duong","0333234567"));
                                }
                                cursor.close();
                            }
                        }
                    })
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();
}
