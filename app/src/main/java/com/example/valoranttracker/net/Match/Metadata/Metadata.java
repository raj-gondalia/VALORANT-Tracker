package com.example.valoranttracker.net.Match.Metadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("gameid")
    @Expose
    private String gameid;

    @SerializedName("modeimage")
    @Expose
    private String modeimage;

    @SerializedName("modename")
    @Expose
    private String modename;

    @SerializedName("duration")
    @Expose
    private long duration;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("map")
    @Expose
    private String map;

    @SerializedName("rounds")
    @Expose
    private int rounds;

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getModeimage() {
        return modeimage;
    }

    public void setModeimage(String modeimage) {
        this.modeimage = modeimage;
    }

    public String getModename() {
        return modename;
    }

    public void setModename(String modename) {
        this.modename = modename;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "gameid='" + gameid + '\'' +
                ", modeimage='" + modeimage + '\'' +
                ", modename='" + modename + '\'' +
                ", duration=" + duration +
                ", timestamp='" + timestamp + '\'' +
                ", map='" + map + '\'' +
                ", rounds=" + rounds +
                '}';
    }
}
