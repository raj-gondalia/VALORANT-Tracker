package com.example.valoranttracker.models.Profile;

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

    @SerializedName("deaths")
    @Expose
    private int deaths;

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

    @SerializedName("wins")
    @Expose
    private int wins;

    @SerializedName("winpercentage")
    @Expose
    private double winpercentage;

    @SerializedName("firstbloods")
    @Expose
    private int firstbloods;

    @SerializedName("aces")
    @Expose
    private int aces;

    @SerializedName("clutches")
    @Expose
    private int clutches;

    @SerializedName("flawless")
    @Expose
    private int flawless;

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

    public void setHeadshotpercentage(double headshotpercentage) {
        this.headshotpercentage = headshotpercentage;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public double getWinpercentage() {
        return winpercentage;
    }

    public void setWinpercentage(double winpercentage) {
        this.winpercentage = winpercentage;
    }

    public int getFirstbloods() {
        return firstbloods;
    }

    public void setFirstbloods(int firstbloods) {
        this.firstbloods = firstbloods;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getClutches() {
        return clutches;
    }

    public void setClutches(int clutches) {
        this.clutches = clutches;
    }

    public int getFlawless() {
        return flawless;
    }

    public void setFlawless(int flawless) {
        this.flawless = flawless;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "rank='" + rank + '\'' +
                ", playtime=" + playtime +
                ", matches=" + matches +
                ", kills=" + kills +
                ", deathes=" + deaths +
                ", assists=" + assists +
                ", kdratio=" + kdratio +
                ", headshots=" + headshots +
                ", headshotpercentage=" + headshotpercentage +
                ", wins=" + wins +
                ", winpercentage=" + winpercentage +
                ", firstbloods=" + firstbloods +
                ", aces=" + aces +
                ", clutches=" + clutches +
                ", flawless=" + flawless +
                '}';
    }
}
