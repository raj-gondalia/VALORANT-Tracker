package com.example.valoranttracker.models;

public class LeaderboardModel {

    private String gameName;
    private String tagLine;
    private int rank;
    private int rankedRating;
    private int numberOfWins;

    public LeaderboardModel() {
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRankedRating() {
        return rankedRating;
    }

    public void setRankedRating(int rankedRating) {
        this.rankedRating = rankedRating;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
}
