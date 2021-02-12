package com.example.valoranttracker.net.Rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rank {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private RankData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RankData getData() {
        return data;
    }

    public void setData(RankData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
