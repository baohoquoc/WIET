package com.senior.wiet.lib.model;

import java.util.List;

public class DishItem {
    private int id;           //id of tag
    private List<MetaTagValues> listMetaTag;   //list meta_tag_contain_this_tag
    //private int parent_id;    //id of represent metatag avoid show many view
    //private List<Integer> parent_id_list;   //id of metatag
    private String name;      //Specifically dish ex: cơm tấm, cơm chiên, bún giò v.v
    private String imageURL;
    private boolean selected = false;

    public DishItem() {
    }

    public DishItem(int id, List<MetaTagValues> listMetaTag,String name, String imageURL) {
        this.id = id;
        //this.parent_id = parent_id;
        //this.parent_id_list = parent_id_list;
        this.listMetaTag = listMetaTag;
        this.name = name;
        this.imageURL = imageURL;
        this.selected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MetaTagValues> getListMetaTag() {
        return listMetaTag;
    }

    public void setListMetaTag(List<MetaTagValues> listMetaTag) {
        this.listMetaTag = listMetaTag;
    }

    /*public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<Integer> getParent_id_list() {
        return parent_id_list;
    }

    public void setParent_id_list(List<Integer> parent_id_list) {
        this.parent_id_list = parent_id_list;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        if(imageURL.replace(" ","").equals(""))
            return "https://2.pik.vn/2020a9785cc9-86c1-4b88-a4f8-43da784c06f9.jpg";  //defaut image
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
