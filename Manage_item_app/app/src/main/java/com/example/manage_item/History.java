package com.example.manage_item;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History")
public class History {
    private String IdUser;
    private String NameId;
    private String result;
    @PrimaryKey
    @NonNull
    private String time;

    public History(String idUser, String nameId, String result, @NonNull String time) {
        IdUser = idUser;
        NameId = nameId;
        this.result = result;
        this.time = time;
    }
    public History(){

    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getNameId() {
        return NameId;
    }

    public void setNameId(String nameId) {
        NameId = nameId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }
}
