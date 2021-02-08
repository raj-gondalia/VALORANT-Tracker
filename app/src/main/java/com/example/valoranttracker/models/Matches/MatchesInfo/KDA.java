package com.example.valoranttracker.models.Matches.MatchesInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KDA {

    @SerializedName("kda")
    @Expose
    private String kda;

    @SerializedName("kd")
    @Expose
    private double kd;

    @SerializedName("kills")
    @Expose
    private int kills;

    @SerializedName("deaths")
    @Expose
    private int deaths;

    @SerializedName("assits")
    @Expose
    private int assists;

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "KDA{" +
                "kda='" + kda + '\'' +
                ", kd=" + kd +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                '}';
    }
}
