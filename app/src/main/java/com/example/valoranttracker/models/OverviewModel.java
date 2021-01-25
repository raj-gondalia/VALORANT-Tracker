package com.example.valoranttracker.models;

public class OverviewModel {
    private String key;
    private String value;
    private int image;

    public OverviewModel(String key, String value, int image) {
        this.key = key;
        this.value = value;
        this.image = image;
    }

    public OverviewModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public OverviewModel(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
