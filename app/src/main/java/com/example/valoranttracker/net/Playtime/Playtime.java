package com.example.valoranttracker.net.Playtime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playtime {

    @SerializedName("playtimepatched")
    @Expose
    private String playtimepatched;

    public String getPlaytimepatched() {
        return playtimepatched;
    }

    public void setPlaytimepatched(String playtimepatched) {
        this.playtimepatched = playtimepatched;
    }

    @Override
    public String toString() {
        return "Playtime{" +
                "playtimepatched='" + playtimepatched + '\'' +
                '}';
    }
}
