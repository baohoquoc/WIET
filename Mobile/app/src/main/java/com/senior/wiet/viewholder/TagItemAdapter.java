package com.senior.wiet.viewholder;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.lib.model.MetaTagValues;

import java.util.List;

public class TagItemAdapter extends RecyclerView.Adapter<TagItemAdapter.ViewHolder> {

    private Context context;
    private List<MetaTagValues> listItem;

    // Declare global variable interface
    private TagItemAdapterCallback mTagItemAdapterCallback;

    public void setData(List<MetaTagValues> data){
        listItem = data;
    }

    public TagItemAdapter(Context context, List<MetaTagValues> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public TagItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagItemAdapter.ViewHolder holder, int position) {
        holder.tag_Name.setText(listItem.get(position).getV_name());
        holder.itemView.setOnClickListener(v -> {
            mTagItemAdapterCallback.onGetTagValues(listItem.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    // Define interface
    public interface TagItemAdapterCallback {
        void onGetTagValues(MetaTagValues tag);
    }

    // Write method and set interface from InformationActivity
    public void setCallBack(TagItemAdapterCallback tagItemAdapterCallback) {
        mTagItemAdapterCallback = tagItemAdapterCallback;
    }

    public void setList(List<MetaTagValues> tagValues){
        this.listItem = tagValues;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tag_Name;
        private TextView tag_ID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tag_Name = itemView.findViewById(R.id.tagName_title);
            tag_ID = itemView.findViewById(R.id.tagID_title);
            tag_ID.setVisibility(View.INVISIBLE);
        }
    }
}
