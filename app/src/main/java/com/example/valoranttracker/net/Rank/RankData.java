package com.example.valoranttracker.net.Rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankData {
    @SerializedName("currenttier")
    @Expose
    private int currentTier;

    @SerializedName("currenttierpatched")
    @Expose
    private String currentTierPatched;

    @SerializedName("ranking_in_tier")
    @Expose
    private int rankingInTier;

    @SerializedName("mmr_change_to_last_game")
    @Expose
    private int mmrChangeToLastGame;

    @SerializedName("elo")
    @Expose
    private int elo;

    public int getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(int currentTier) {
        this.currentTier = currentTier;
    }

    public String getCurrentTierPatched() {
        return currentTierPatched;
    }

    public void setCurrentTierPatched(String currentTierPatched) {
        this.currentTierPatched = currentTierPatched;
    }

    public int getRankingInTier() {
        return rankingInTier;
    }

    public void setRankingInTier(int rankingInTier) {
        this.rankingInTier = rankingInTier;
    }

    public int getMmrChangeToLastGame() {
        return mmrChangeToLastGame;
    }

    public void setMmrChangeToLastGame(int mmrChangeToLastGame) {
        this.mmrChangeToLastGame = mmrChangeToLastGame;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    @Override
    public String toString() {
        return "RankData{" +
                "currentTier=" + currentTier +
                ", currentTierPatched='" + currentTierPatched + '\'' +
                ", rankingInTier=" + rankingInTier +
                ", mmrChangeToLastGame=" + mmrChangeToLastGame +
                ", elo=" + elo +
                '}';
    }
}
