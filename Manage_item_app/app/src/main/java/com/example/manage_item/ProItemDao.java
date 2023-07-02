package com.example.manage_item;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProItemDao {
    @Insert
    void insertItem(ProItem proItem);

    @Query("SELECT * FROM ProItem ORDER BY NameItem ASC ")
    List<ProItem> getListItem() ;

    @Query("SELECT * FROM ProItem WHERE   NameItem = :nameItem AND IdItem = :idItem ")
    List<ProItem> checkItem (String nameItem,String idItem);
    @Delete
    void deleteUser(ProItem proItem);

    @Query("SELECT * FROM ProItem WHERE NameItem LIKE '%' || :strSearch || '%' OR IdItem LIKE '%' || :strSearch || '%' ")
    List<ProItem> SearchItem(String strSearch);



}
