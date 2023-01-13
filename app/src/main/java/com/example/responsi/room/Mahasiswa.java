package com.example.responsi.room;



import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Mahasiswa implements Serializable{

    @PrimaryKey(autoGenerate = true) int id;
    private String email;
    private String password;

    public Mahasiswa(String email, String password){
        this.email=email;
        this.password=password;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull  int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
