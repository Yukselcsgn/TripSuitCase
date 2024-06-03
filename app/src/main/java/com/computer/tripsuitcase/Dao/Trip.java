package com.computer.tripsuitcase.Dao;

public class Trip {
    private String date;
    private String userId;

    public Trip(String date, String userId) {
        this.date = date;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }
}
