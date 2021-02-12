package com.example.valoranttracker.models;

public class MatchDetailModel {

    private int agentPlayed;
    private String team;
    private String gameName;
    private String tagLine;
    private String kda;
    private int tier;

    public MatchDetailModel() {

    }

    public int getAgentPlayed() {
        return agentPlayed;
    }

    public void setAgentPlayed(int agentPlayed) {
        this.agentPlayed = agentPlayed;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }



}
