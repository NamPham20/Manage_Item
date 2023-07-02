package com.example.manage_item;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser (User user);
    @Query("SELECT * FROM User ORDER BY FullName ASC")
   List<User> getListUser();
    @Query("SELECT * FROM User WHERE   UserNameId = :id_User AND Password = :password ")
    List<User> checkAccount (String id_User,String password);

    @Query("SELECT * FROM User WHERE   UserNameId = :id_User")
    List<User> checkUser (String id_User);

    @Query("SELECT TypeUser FROM User WHERE UserNameId = :userNameId")
    String getTypeById (String userNameId);

    @Query("SELECT * FROM User WHERE UserNameId = :id_user")
    User getUserById (String id_user);

    @Update
    void updateUser (User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM User")
    void deleteAllUser();

    @Query("SELECT * FROM User WHERE UserNameId LIKE '%' || :strSearch || '%' OR FullName LIKE '%' || :strSearch || '%' OR Address LIKE '%' || :strSearch || '%'" +
            "OR TypeUser LIKE '%' || :strSearch || '%' ")
    List<User> SearchUser(String strSearch);

}
