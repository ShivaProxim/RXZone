package com.rxzone.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PROXIM on 2/8/2018.
 */

public class CommonDropDownData implements Serializable {
    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;


    public CommonDropDownData(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
