package com.example.valoranttracker.net.Matches.MatchesInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("gameid")
    @Expose
    private String gameid;

    @SerializedName("modename")
    @Expose
    private String modename;

    @SerializedName("playerhaswon")
    @Expose
    private boolean playerhaswon;

    @SerializedName("map")
    @Expose
    private String map;

    @SerializedName("agentplayed")
    @Expose
    private String agentplayed;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getModename() {
        return modename;
    }

    public void setModename(String modename) {
        this.modename = modename;
    }

    public boolean isPlayerhaswon() {
        return playerhaswon;
    }

    public void setPlayerhaswon(boolean playerhaswon) {
        this.playerhaswon = playerhaswon;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAgentplayed() {
        return agentplayed;
    }

    public void setAgentplayed(String agentplayed) {
        this.agentplayed = agentplayed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "gameid='" + gameid + '\'' +
                ", modename='" + modename + '\'' +
                ", playerhaswon=" + playerhaswon +
                ", map='" + map + '\'' +
                ", agentplayed='" + agentplayed + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
