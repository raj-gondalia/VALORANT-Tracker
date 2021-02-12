package com.example.valoranttracker.net.Match.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerInfo {

    @SerializedName("user")
    @Expose
    private String gameName;

    @SerializedName("agentused")
    @Expose
    private String agent;

    @SerializedName("rank")
    @Expose
    private String tier;

    @SerializedName("kda")
    @Expose
    private Kda kda;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Kda getKda() {
        return kda;
    }

    public void setKda(Kda kda) {
        this.kda = kda;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "gameName='" + gameName + '\'' +
                ", agent='" + agent + '\'' +
                ", tier='" + tier + '\'' +
                ", kda=" + kda +
                '}';
    }
}
