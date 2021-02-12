package com.example.valoranttracker.net.Match.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Byteam {

    @SerializedName("blue")
    @Expose
    private ArrayList<PlayerInfo> blueTeam;

    @SerializedName("red")
    @Expose
    private ArrayList<PlayerInfo> redTeam;

    public ArrayList<PlayerInfo> getBlueTeam() {
        return blueTeam;
    }

    public void setBlueTeam(ArrayList<PlayerInfo> blueTeam) {
        this.blueTeam = blueTeam;
    }

    public ArrayList<PlayerInfo> getRedTeam() {
        return redTeam;
    }

    public void setRedTeam(ArrayList<PlayerInfo> redTeam) {
        this.redTeam = redTeam;
    }

    @Override
    public String toString() {
        return "Byteam{" +
                "blueTeam=" + blueTeam +
                ", redTeam=" + redTeam +
                '}';
    }
}
