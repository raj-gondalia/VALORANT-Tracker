package com.example.valoranttracker.net.Leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Leaderboard {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private ArrayList<LeaderboardData> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<LeaderboardData> getData() {
        return data;
    }

    public void setData(ArrayList<LeaderboardData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
