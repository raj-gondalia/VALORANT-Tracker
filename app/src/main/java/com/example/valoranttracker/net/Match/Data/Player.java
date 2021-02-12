package com.example.valoranttracker.net.Match.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("byteam")
    @Expose
    private Byteam byteam;

    public Byteam getByteam() {
        return byteam;
    }

    public void setByteam(Byteam byteam) {
        this.byteam = byteam;
    }

    @Override
    public String toString() {
        return "Player{" +
                "byteam=" + byteam +
                '}';
    }
}
