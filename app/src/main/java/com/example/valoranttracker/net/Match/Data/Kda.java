package com.example.valoranttracker.net.Match.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kda {

    @SerializedName("kda")
    @Expose
    private String kda;

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    @Override
    public String toString() {
        return "Kda{" +
                "kda='" + kda + '\'' +
                '}';
    }
}
