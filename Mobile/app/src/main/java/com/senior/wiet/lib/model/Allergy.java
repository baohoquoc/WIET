package com.senior.wiet.lib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Allergy {

    @SerializedName("meta_tag_ids")
    @Expose
    private List<Integer> tagIds = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Allergy() {
    }

    /**
     *
     * @param tagIds
     */
    public Allergy(List<Integer> tagIds) {
        super();
        this.tagIds = tagIds;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}