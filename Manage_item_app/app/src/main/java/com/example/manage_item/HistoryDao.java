package com.example.manage_item;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insertHistory(History history);


    @Query("SELECT * FROM History  ")
    List<History> getListHistory() ;

    @Query("SELECT * FROM History WHERE NameId LIKE '%' || :strSearch || '%' OR IdUser LIKE '%' || :strSearch || '%' OR result LIKE '%' || :strSearch || '%'" +
            "OR time LIKE '%' || :strSearch || '%' ")
    List<History> SearchHistory(String strSearch);

    @Query("DELETE FROM History")
    void deleteAllHistory();

    @Query("SELECT * FROM History WHERE   IdUser = :id_User")
    List<History> getListHistoryById(String id_User);


}
