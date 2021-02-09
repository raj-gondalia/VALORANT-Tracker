package com.example.valoranttracker.models.Puuid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("puuid")
    @Expose
    private String puuid;

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    @Override
    public String toString() {
        return "Data{" +
                "puuid='" + puuid + '\'' +
                '}';
    }
}
