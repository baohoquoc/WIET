package com.senior.wiet.lib.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RatingRequest {
    @SerializedName("rating")
    List<Rating> ratings;

    public RatingRequest() {
        this.ratings = new ArrayList<>();
    }

    public RatingRequest(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void add(DishItem tag, int position){     //tag have many meta_tag -> position use to select position of meta_tag_in list
        if(tag.getListMetaTag().size()==0) return;
        int metaTagID = tag.getListMetaTag().get(position).getId(); // getMeta_tag at index "position"
        int index = -1; //index of rating in ratings
        //check have meta_tag jet?
        for(int i=0;i<ratings.size();i++){
            if(ratings.get(i).getMeta_tag_id()==metaTagID){
                index = i;
                break;
            }
        }
        if(index==-1){
            Rating tmp = new Rating();
            tmp.setMeta_tag_id(metaTagID);
            tmp.getTag_ids().add(tag.getId());
            ratings.add(tmp);
        }else {
            ratings.get(index).add(tag.getId());
        }


    }

    public void remove(DishItem tag, int position){      //tag have many meta_tag -> position use to select position of meta_tag_in list
        if(tag.getListMetaTag().size()==0) return;
        int metaTagID = tag.getListMetaTag().get(position).getId();
        int index = -1; //index of rating in ratings
        for(int i=0;i<ratings.size();i++){
            if(ratings.get(i).getMeta_tag_id()==metaTagID){
                index = i;
                break;
            }
        }
        if(index==-1){    //no exist then create new Rating object
            return;//     //nothing to do
        }else {           //exist rating for this meta_tag the remove id from it
            //find index of id
            ratings.get(index).remove(tag.getId());
            if(ratings.get(index).getTag_ids().isEmpty()){
                ratings.remove(index);
            }
        }
    }

}
