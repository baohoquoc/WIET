package com.senior.wiet.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.lib.model.DishItem;
import com.senior.wiet.lib.model.SurveyItem;

import java.util.List;

public class MetaTagItemAdapter extends RecyclerView.Adapter<MetaTagItemAdapter.ViewHolder> {

    private Context context;
    private List<SurveyItem> list;

    public MetaTagItemAdapter(Context context, List<SurveyItem> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<SurveyItem> data){
        list = data;
    }

    @NonNull
    @Override
    public MetaTagItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_tag_item,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MetaTagItemAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        //set adapter for each dish item in survey item
        List<DishItem> dishItems = list.get(position).getDishItemList();
        TagItemSurveyAdapter tagItemSurveyAdapter = new TagItemSurveyAdapter(context,dishItems);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(tagItemSurveyAdapter);
        holder.recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            name = itemView.findViewById(R.id.survey_item_name);
            recyclerView = itemView.findViewById(R.id.survey_item_recycler_view);
        }
    }
}
