package com.rxzone.Activities.DashBoardActivity.Fragments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PROXIM on 2/6/2018.
 */

public class AllPostData implements Serializable {
    @SerializedName("packageName")
    private String packageName;

    @SerializedName("expDate")
    private String expDate;

    @SerializedName("package")
    private String packagee;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPackagee() {
        return packagee;
    }

    public void setPackagee(String packagee) {
        this.packagee = packagee;
    }
}
