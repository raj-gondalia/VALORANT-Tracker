package com.example.valoranttracker.models.Matches.MatchesInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchesInfo {

    @SerializedName("isAvailable")
    @Expose
    private boolean isAvailable;

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    @SerializedName("game")
    @Expose
    private Game game;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "MatchesInfo{" +
                "isAvailable=" + isAvailable +
                ", metadata=" + metadata +
                ", game=" + game +
                '}';
    }
}
