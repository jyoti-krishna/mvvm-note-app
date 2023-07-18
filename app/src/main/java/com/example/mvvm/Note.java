package com.example.mvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_notes")
public class Note {
    String title;
    String des;
    @PrimaryKey(autoGenerate =true)
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
