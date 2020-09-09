package com.senior.wiet.lib.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MetaTagValues {

    @SerializedName("id")
    private int id;
    @SerializedName("e_name")
    private String e_name;
    @SerializedName("v_name")
    private String v_name;
    @SerializedName("tags")    //tag in metatag
    private List<TagValues> tags;

    public MetaTagValues() {}

    public MetaTagValues(int id, String e_name, String v_name, List<TagValues> tags) {
        this.id = id;
        this.e_name = e_name;
        this.v_name = v_name;
        this.tags = tags;
    }
    public MetaTagValues(int id, String e_name, String v_name) {
        this.id = id;
        this.e_name = e_name;
        this.v_name = v_name;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public List<TagValues> getTags() {
        return tags;
    }

    public void setTags(List<TagValues> tags) {
        this.tags = tags;
    }
}
