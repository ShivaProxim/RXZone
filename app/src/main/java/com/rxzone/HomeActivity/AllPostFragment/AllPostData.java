package com.rxzone.HomeActivity.AllPostFragment;

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

    @SerializedName("ndcNum")
    private String ndcNum;

    @SerializedName("wacPrice")
    private String wacPrice;

    @SerializedName("packageQuantity")
    private String packageQuantity;

    public String getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(String packageQuantity) {
        this.packageQuantity = packageQuantity;
    }
    //

    public String getWacPrice() {
        return wacPrice;
    }

    public void setWacPrice(String wacPrice) {
        this.wacPrice = wacPrice;
    }

    public String getNdcNum() {
        return ndcNum;
    }

    public void setNdcNum(String ndcNum) {
        this.ndcNum = ndcNum;
    }

    @SerializedName("packPrice")
    private String packPrice;

    public String getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(String packPrice) {
        this.packPrice = packPrice;
    }

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
