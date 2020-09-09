package com.senior.wiet.lib.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllergyValue {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("meta_tag_id")
    @Expose
    private Integer tagId;

    /*
     * No args constructor for use in serialization
     *
     */
    public GetAllergyValue() {
    }

    /*
     *
     * @param tagId
     * @param id
     */
    public GetAllergyValue(Integer id, Integer tagId) {
        super();
        this.id = id;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}
