package com.senior.wiet.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.utilities.Constants;

import java.util.List;


public class DishItemAdapter extends RecyclerView.Adapter<DishItemAdapter.ViewHolder> {

    private Context mContext;
    private List<RecommendValues> list;

    public DishItemAdapter(Context mContext, List<RecommendValues> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public DishItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    public void setData(List<RecommendValues> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull DishItemAdapter.ViewHolder holder, int position) {
        RecommendValues food = list.get(position);
        holder.title.setText(food.getName());
        holder.subTitle.setText(food.getDescription());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,"Clicked",Toast.LENGTH_SHORT).show();
                //mContext.startActivity(new Intent(mContext, FoodActivity.class));

                /*RecommendValues recommendValues = new RecommendValues(food.getAverage_score(), food.getCreated_time(), food.getFood_id(), food.getId(), food.getImage(), false,
                        food.getName(), food.getPrice(), food.getRestaurant_id(), food.getTotal_view(), food.getTotal_vote(), food.getUpdated_time());*/
                Intent intent = new Intent(mContext, FoodActivity.class);
                intent.putExtra(Constants.FOOD_ID, list.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        /*String image = food.getImage().toString();
        if (!image.contains("http"))
            image = "https:" + image;
        if (image.contains("/style/")) {
            holder.dishImage.setImageResource(R.drawable.default_image);
            return;
        }*/
        RequestOptions options = new RequestOptions()
                .error(R.color.colorGray)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(list.get(position).getImage()).apply(options).into(holder.dishImage);
        //Picasso.get().load(image).error(R.drawable.default_image).into(holder.dishImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView dishImage;
        private TextView title;
        private TextView subTitle;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            dishImage = itemView.findViewById(R.id.dishName);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            constraintLayout = itemView.findViewById(R.id.dish_layout);
        }
    }
}
