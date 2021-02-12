package com.example.valoranttracker.net.Match;

import com.example.valoranttracker.net.Match.Data.Data;
import com.example.valoranttracker.net.Match.Metadata.Metadata;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    @SerializedName("data")
    @Expose
    private Data data;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Match{" +
                "metadata=" + metadata +
                ", data=" + data +
                '}';
    }
}
