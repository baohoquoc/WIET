package com.senior.wiet.lib.model.survey;

import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.MetaTagValues;

import java.util.ArrayList;
import java.util.List;

public class Values {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageURL")
    private String imageURL;
    @SerializedName("meta_tags")
    private List<MetaTagValues> metaTagList;

    public Values(){
    }

    public Values(int id, String name, String imageURL, List<MetaTagValues> list) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.metaTagList = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<MetaTagValues> getMetaTagList() {
        return metaTagList;
    }

    public void setMetaTagList(List<MetaTagValues> metaTagList) {
        this.metaTagList = metaTagList;
    }

    public List<Integer> getListMetaTagID(){
        List<Integer> tmp = new ArrayList<>();
        for (MetaTagValues metatag: metaTagList) {
            tmp.add(metatag.getId());
        }
        return tmp;
    }
}
