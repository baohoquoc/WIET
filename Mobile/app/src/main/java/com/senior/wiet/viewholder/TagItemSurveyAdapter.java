package com.senior.wiet.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.senior.wiet.R;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.lib.model.DishItem;

import java.util.List;

public class TagItemSurveyAdapter extends RecyclerView.Adapter<TagItemSurveyAdapter.ViewHolder> {

    private Context context;
    private List<DishItem> listItem;

    public TagItemSurveyAdapter(Context context, List<DishItem> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public TagItemSurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item_survey,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull TagItemSurveyAdapter.ViewHolder holder, int position) {
        //Picasso.get().load(listItem.get(position).getImageURL()).error(R.drawable.default_image).into(holder.img_btn);
        RequestOptions options = new RequestOptions()
                .error(R.color.colorGray)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(context).load(listItem.get(position).getImageURL()).apply(options).into(holder.img_btn);
        holder.txt_dish_name.setText(listItem.get(position).getName());
        if(!listItem.get(position).isSelected()){
            holder.tick.setVisibility(View.GONE);
            holder.img_btn.setAlpha(1f);
        }else {
            holder.tick.setVisibility(View.VISIBLE);
            holder.img_btn.setAlpha(0.3f);
        }
        holder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Tag", "Clicked");
                if(!listItem.get(position).isSelected()) {
                    listItem.get(position).setSelected(true);
                    holder.tick.setVisibility(View.VISIBLE);
                    holder.img_btn.setAlpha(0.3f);
                    SurveyActivity.request.add(listItem.get(position),0);  //defaut get first meta_tag of tag
                    Log.d("list_rating",new Gson().toJson(SurveyActivity.request));
                }else {
                    listItem.get(position).setSelected(false);
                    holder.tick.setVisibility(View.GONE);
                    holder.img_btn.setAlpha(1f);
                    SurveyActivity.request.remove(listItem.get(position),0); //defaut get first meta_tag of tag
                    Log.d("list_rating",new Gson().toJson(SurveyActivity.request));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton img_btn;
        private TextView txt_dish_name;
        private ImageView tick;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            img_btn = itemView.findViewById(R.id.tagImage);
            txt_dish_name = itemView.findViewById(R.id.tagTitle);
            tick =  itemView.findViewById(R.id.tick);
        }
    }
}
