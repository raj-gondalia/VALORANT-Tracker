package com.example.valoranttracker.models;

public class CareerModel {

    private int agent;
    private boolean playerhaswon;
    private int modeSrc;
    private String mode;
    private int roundswon;
    private int roundslost;
    private String map;
    private String mvp;
    private String kda;
    private String timestamp;
    private String gameid;

    public CareerModel() {
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public boolean isPlayerhaswon() {
        return playerhaswon;
    }

    public void setPlayerhaswon(boolean playerhaswon) {
        this.playerhaswon = playerhaswon;
    }

    public int getModeSrc() {
        return modeSrc;
    }

    public void setModeSrc(int modeSrc) {
        this.modeSrc = modeSrc;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMvp() {
        return mvp;
    }

    public void setMvp(String mvp) {
        this.mvp = mvp;
    }

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
