package com.example.valoranttracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {



    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("stats")
    @Expose
    private Stats stats;

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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                ", status='" + status + '\'' +
                ", stats=" + stats +
                '}';
    }
}
