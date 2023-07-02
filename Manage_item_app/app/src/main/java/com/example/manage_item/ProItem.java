package com.example.manage_item;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ProItem")
public class ProItem implements Serializable {
    @PrimaryKey
    @NonNull
    private String IdItem;
    private String NameItem;


    public ProItem(@NonNull String idItem, String nameItem) {
        IdItem = idItem;
        NameItem = nameItem;
    }
    public ProItem() {
    }




    @NonNull
    public String getIdItem() {
        return IdItem;
    }

    public void setIdItem(@NonNull String idItem) {
        IdItem = idItem;
    }

    public String getNameItem() {
        return NameItem;
    }

    public void setNameItem(String nameItem) {
        NameItem = nameItem;
    }
}

