package com.example.valoranttracker.models.Matches.MatchesInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("roundswon")
    @Expose
    private int roundswon;

    @SerializedName("roundslost")
    @Expose
    private int roundslost;

    @SerializedName("kda")
    @Expose
    private KDA kda;

    public int getRoundswon() {
        return roundswon;
    }

    public void setRoundswon(int roundswon) {
        this.roundswon = roundswon;
    }

    public int getRoundslost() {
        return roundslost;
    }

    public void setRoundslost(int roundslost) {
        this.roundslost = roundslost;
    }

    public KDA getKda() {
        return kda;
    }

    public void setKda(KDA kda) {
        this.kda = kda;
    }

    @Override
    public String toString() {
        return "Game{" +
                "roundswon=" + roundswon +
                ", roundslost=" + roundslost +
                ", kda=" + kda +
                '}';
    }
}
