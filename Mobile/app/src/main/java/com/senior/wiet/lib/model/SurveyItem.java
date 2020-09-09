package com.senior.wiet.lib.model;

import java.util.List;

public class SurveyItem {

    private int id;        //meta_tag id
    private String name;   //section name ex : rice, noodle
    private List<DishItem> dishItemList;   //list of tags in meta_tag

    public SurveyItem() {
    }

    public SurveyItem(int id,String name, List<DishItem> dishItemList) {
        this.id = id;
        this.name = name;
        this.dishItemList = dishItemList;
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

    public List<DishItem> getDishItemList() {
        return dishItemList;
    }

    public void setDishItemList(List<DishItem> dishItemList) {
        this.dishItemList = dishItemList;
    }

    public void add(DishItem item){
        if(dishItemList.contains(item))
                return;
        dishItemList.add(0,item);

    }

    public boolean isContains(DishItem dishItem){
        for (DishItem item: dishItemList) {
            if(item.getId()==dishItem.getId())
                return true;
        }
        return false;
    }
}
