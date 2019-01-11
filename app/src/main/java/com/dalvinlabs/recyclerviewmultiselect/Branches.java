package com.dalvinlabs.recyclerviewmultiselect;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Branches {

    @SerializedName("branch")
    private String branch;
    @SerializedName("seats_Filled")
    private String seats_fill;
    @SerializedName("seats_Avaliable")
    private String seats_avali;
    @SerializedName("percentageSeatsFillied")
    private String percent;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSeats_fill() {
        return seats_fill;
    }

    public void setSeats_fill(String seats_fill) {
        this.seats_fill = seats_fill;
    }

    public String getSeats_avali() {
        return seats_avali;
    }

    public void setSeats_avali(String seats_avali) {
        this.seats_avali = seats_avali;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Branches(String branch, String seats_fill, String seats_avali, String percent) {
        this.branch = branch;
        this.seats_fill = seats_fill;
        this.seats_avali = seats_avali;
        this.percent = percent;
    }


}
