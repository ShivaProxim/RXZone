package com.rxzone.HomeActivity.AddPostFragment;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PROXIM on 2/9/2018.
 */

public class AddProductData {
    @SerializedName("gsdbRefID")
    private String gsdbRefID;
    @SerializedName("ndcNum")
    private String ndcNum;
    @SerializedName("lotNum")
    private String lotNum;
    @SerializedName("expDate")
    private String expDate;
    @SerializedName("packageQuantity")
    private String packageQuantity;
    @SerializedName("pkgAvailable")
    private String pkgAvailable;
    @SerializedName("PriceOption")
    private String priceOption;
    @SerializedName("packPrice")
    private String packPrice;
    @SerializedName("wacDsicount")
    private String wacDsicount;

    @SerializedName("imagePath")
    private String imagePath;

    @SerializedName("strength")
    private String strength;

    @SerializedName("package")
    private String packagee;

    @SerializedName("formcp")
    private String formcp;


    @SerializedName("lastRefDate")
    private String lastRefDate;


    @SerializedName("packageConditionVal")
    private String packageConditionVal;


    @SerializedName("tornPharmacyLabel")
    private Boolean tornPharmacyLabel;


    @SerializedName("otherPackageCondition")
    private Boolean otherPackageCondition;


    @SerializedName("otherPackageConditionText")
    private String otherPackageConditionText;


    @SerializedName("groundShippingVal")
    private String groundShippingVal;


    @SerializedName("shippingMethodVal")
    private String shippingMethodVal;


    @SerializedName("productID")
    private String productID;


    @SerializedName("GsddProductID")
    private String gsddProductID;


    @SerializedName("packageName")
    private String packageName;


    @SerializedName("mfgName")
    private String mfgName;

    @SerializedName("wacPrice")
    private String wacPrice;

    @SerializedName("partialQunatity")
    private String partialQunatity;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public AddProductData(String gsdbRefID, String ndcNum, String lotNum, String expDate, String packageQuantity, String pkgAvailable,
                          String priceOption, String packPrice, String wacDsicount, String imagePath, String strength, String packagee,
                          String formcp, String lastRefDate, String packageConditionVal, Boolean tornPharmacyLabel, Boolean otherPackageCondition,
                          String otherPackageConditionText, String groundShippingVal, String shippingMethodVal, String productID, String gsddProductID,
                          String packageName, String mfgName, String wacPrice, String partialQunatity) {
        this.gsdbRefID = gsdbRefID;
        this.ndcNum = ndcNum;
        this.lotNum = lotNum;
        this.expDate = expDate;
        this.packageQuantity = packageQuantity;
        this.pkgAvailable = pkgAvailable;
        this.priceOption = priceOption;
        this.packPrice = packPrice;
        this.wacDsicount = wacDsicount;
        this.imagePath = imagePath;
        this.strength = strength;
        this.packagee = packagee;
        this.formcp = formcp;
        this.lastRefDate = lastRefDate;
        this.packageConditionVal = packageConditionVal;
        this.tornPharmacyLabel = tornPharmacyLabel;
        this.otherPackageCondition = otherPackageCondition;
        this.otherPackageConditionText = otherPackageConditionText;
        this.groundShippingVal = groundShippingVal;
        this.shippingMethodVal = shippingMethodVal;
        this.productID = productID;
        this.gsddProductID = gsddProductID;
        this.packageName = packageName;
        this.mfgName = mfgName;
        this.wacPrice = wacPrice;
        this.partialQunatity = partialQunatity;
        Log.e("saveparams", toString());
    }


    @Override
    public String toString() {
        String params = "";
        Log.e("saveParams", "AddProductData{" +
                "gsdbRefID='" + gsdbRefID + '\'' +
                ", ndcNum='" + ndcNum + '\'' +
                ", lotNum='" + lotNum + '\'' +
                ", expDate='" + expDate + '\'' +
                ", packageQuantity='" + packageQuantity + '\'' +
                ", pkgAvailable='" + pkgAvailable + '\'' +
                ", priceOption='" + priceOption + '\'' +
                ", packPrice='" + packPrice + '\'' +
                ", wacDsicount='" + wacDsicount + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", strength='" + strength + '\'' +
                ", packagee='" + packagee + '\'' +
                ", formcp='" + formcp + '\'' +
                ", lastRefDate='" + lastRefDate + '\'' +
                ", packageConditionVal='" + packageConditionVal + '\'' +
                ", tornPharmacyLabel=" + tornPharmacyLabel +
                ", otherPackageCondition=" + otherPackageCondition +
                ", otherPackageConditionText='" + otherPackageConditionText + '\'' +
                ", groundShippingVal='" + groundShippingVal + '\'' +
                ", shippingMethodVal='" + shippingMethodVal + '\'' +
                ", productID='" + productID + '\'' +
                ", gsddProductID='" + gsddProductID + '\'' +
                ", packageName='" + packageName + '\'' +
                ", mfgName='" + mfgName + '\'' +
                ", wacPrice='" + wacPrice + '\'' +
                ", partialQunatity='" + partialQunatity +
                '}');
        return "AddProductData{" +
                "gsdbRefID='" + gsdbRefID + '\'' +
                ", ndcNum='" + ndcNum + '\'' +
                ", lotNum='" + lotNum + '\'' +
                ", expDate='" + expDate + '\'' +
                ", packageQuantity='" + packageQuantity + '\'' +
                ", pkgAvailable='" + pkgAvailable + '\'' +
                ", priceOption='" + priceOption + '\'' +
                ", packPrice='" + packPrice + '\'' +
                ", wacDsicount='" + wacDsicount + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", strength='" + strength + '\'' +
                ", packagee='" + packagee + '\'' +
                ", formcp='" + formcp + '\'' +
                ", lastRefDate='" + lastRefDate + '\'' +
                ", packageConditionVal='" + packageConditionVal + '\'' +
                ", tornPharmacyLabel=" + tornPharmacyLabel +
                ", otherPackageCondition=" + otherPackageCondition +
                ", otherPackageConditionText='" + otherPackageConditionText + '\'' +
                ", groundShippingVal='" + groundShippingVal + '\'' +
                ", shippingMethodVal='" + shippingMethodVal + '\'' +
                ", productID='" + productID + '\'' +
                ", gsddProductID='" + gsddProductID + '\'' +
                ", packageName='" + packageName + '\'' +
                ", mfgName='" + mfgName + '\'' +
                ", wacPrice='" + wacPrice + '\'' +
                ", partialQunatity='" + partialQunatity +
                '}';
    }
}
