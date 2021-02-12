package com.example.valoranttracker.net.Leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeaderboardData {

    @SerializedName("Subject")
    @Expose
    private String subject;

    @SerializedName("GameName")
    @Expose
    private String gameName;

    @SerializedName("TagLine")
    @Expose
    private String tagLine;

    @SerializedName("LeaderboardRank")
    @Expose
    private int leaderboardRank;

    @SerializedName("RankedRating")
    @Expose
    private int rankedRating;

    @SerializedName("NumberOfWins")
    @Expose
    private int numberOfWins;

    @SerializedName("PlayerCardID")
    @Expose
    private String playerCardID;

    @SerializedName("TitleID")
    @Expose
    private String TitleID;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public int getLeaderboardRank() {
        return leaderboardRank;
    }

    public void setLeaderboardRank(int leaderboardRank) {
        this.leaderboardRank = leaderboardRank;
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

    public String getPlayerCardID() {
        return playerCardID;
    }

    public void setPlayerCardID(String playerCardID) {
        this.playerCardID = playerCardID;
    }

    public String getTitleID() {
        return TitleID;
    }

    public void setTitleID(String titleID) {
        TitleID = titleID;
    }

    @Override
    public String toString() {
        return "LeaderboardData{" +
                "subject='" + subject + '\'' +
                ", gameName='" + gameName + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", leaderboardRank=" + leaderboardRank +
                ", rankedRating=" + rankedRating +
                ", numberOfWins=" + numberOfWins +
                ", playerCardID='" + playerCardID + '\'' +
                ", TitleID='" + TitleID + '\'' +
                '}';
    }
}
