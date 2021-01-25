package com.example.valoranttracker.models;

import com.example.valoranttracker.models.Playtime.Playtime;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("rank")
    @Expose
    private String rank;

    @SerializedName("playtime")
    @Expose
    private Playtime playtime;

    @SerializedName("matches")
    @Expose
    private int matches;

    @SerializedName("kills")
    @Expose
    private int kills;

    @SerializedName("deathes")
    @Expose
    private int deathes;

    @SerializedName("assists")
    @Expose
    private int assists;

    @SerializedName("kdratio")
    @Expose
    private double kdratio;

    @SerializedName("headshots")
    @Expose
    private int headshots;

    @SerializedName("headshotpercentage")
    @Expose
    private double headshotpercentage;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Playtime getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Playtime playtime) {
        this.playtime = playtime;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getDeathes() {
        return deathes;
    }

    public void setDeathes(int deathes) {
        this.deathes = deathes;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double getKdratio() {
        return kdratio;
    }

    public void setKdratio(double kdratio) {
        this.kdratio = kdratio;
    }

    public int getHeadshots() {
        return headshots;
    }

    public void setHeadshots(int headshots) {
        this.headshots = headshots;
    }

    public double getHeadshotpercentage() {
        return headshotpercentage;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setHeadshotpercentage(double headshotpercentage) {
        this.headshotpercentage = headshotpercentage;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "rank='" + rank + '\'' +
                ", playtime=" + playtime +
                ", matches=" + matches +
                ", kills=" + kills +
                ", deathes=" + deathes +
                ", assists=" + assists +
                ", kdratio=" + kdratio +
                ", headshots=" + headshots +
                ", headshotpercentage=" + headshotpercentage +
                '}';
    }
}
