package com.senior.wiet.lib.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    @SerializedName("meta_tag_id")
    private int meta_tag_id;
    @SerializedName("tag_ids")
    private List<Integer> tag_ids;

    public Rating(){
        meta_tag_id  = 0;
        tag_ids = new ArrayList<>();
    }

    public Rating(int meta_tag_id, List<Integer> tag_ids) {
        this.meta_tag_id = meta_tag_id;
        this.tag_ids = tag_ids;
    }

    public int getMeta_tag_id() {
        return meta_tag_id;
    }

    public void setMeta_tag_id(int meta_tag_id) {
        this.meta_tag_id = meta_tag_id;
    }

    public List<Integer> getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(List<Integer> tag_ids) {
        this.tag_ids = tag_ids;
    }

    public void add(int tag_id){
        if(tag_ids.contains(tag_id)){
            return;   //if had have
        }else {
            tag_ids.add(tag_id);
        }
    }

    public void remove(int tag_id){
        if(tag_ids.contains(tag_id)){
            tag_ids.remove(tag_ids.indexOf(tag_id));
        }else {
            return;
        }
    }


}
