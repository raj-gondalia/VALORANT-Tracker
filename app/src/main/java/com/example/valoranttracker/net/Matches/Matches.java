package com.example.valoranttracker.net.Matches;

import com.example.valoranttracker.net.Matches.MatchesInfo.MatchesInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Matches {

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("matches")
    @Expose
    private ArrayList<MatchesInfo> matches;

    @SerializedName("message")
    @Expose
    private String message;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<MatchesInfo> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchesInfo> matches) {
        this.matches = matches;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "user='" + user + '\'' +
                ", status='" + status + '\'' +
                ", matches=" + matches +
                ", message='" + message + '\'' +
                '}';
    }
}
