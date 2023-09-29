package com.example.androidparking;

public class EventModel {
    String plate;
    String dateTime;
    String filename;
    int countryIndex, parkingIndex;

    public EventModel(String plate, String dateTime, String filename, int countryIndex, int parkingIndex) {
        this.plate = plate;
        this.dateTime = dateTime;
        this.filename = filename;
        this.countryIndex = countryIndex;
        this.parkingIndex = parkingIndex;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCountryIndex() {
        return countryIndex;
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }

    public int getParkingIndex() {
        return parkingIndex;
    }

    public void setParkingIndex(int parkingIndex) {
        this.parkingIndex = parkingIndex;
    }
}
