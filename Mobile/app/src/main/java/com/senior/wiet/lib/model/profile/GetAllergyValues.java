package com.senior.wiet.lib.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAllergyValues implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meta_tag_id")
    @Expose
    private Integer tagId;

    /*
     * No args constructor for use in serialization
     *
     */
    public GetAllergyValues() {
    }

    /*
     *
     * @param tagId
     * @param id
     */
    public GetAllergyValues(Integer id, String name, Integer tagId) {
        super();
        this.id = id;
        this.name = name;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}
