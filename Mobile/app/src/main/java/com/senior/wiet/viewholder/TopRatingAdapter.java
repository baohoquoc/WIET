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

public class TopRatingAdapter extends RecyclerView.Adapter<TopRatingAdapter.ViewHolder> {

    private Context mContext;
    private List<RecommendValues> list;

    public TopRatingAdapter(Context mContext, List<RecommendValues> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public TopRatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_rating_item,parent,false);
        //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_rating_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    public void setData(List<RecommendValues> list) {
        this.list = list;
    }

    public void addData(List<RecommendValues> list_to_add){
        for (RecommendValues values:list_to_add) {
            list.add(values);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatingAdapter.ViewHolder holder, int position) {
        RecommendValues food = list.get(position);
        holder.title.setText(food.getName());
        holder.subTitle.setText(food.getDescription());
        holder.topNumber.setText((position+1)+"");
        //holder.subTitle.setText("Top " + (position+1));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FoodActivity.class);
                intent.putExtra(Constants.FOOD_ID, list.get(position).getId());
                mContext.startActivity(intent);
            }
        });
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
        private TextView topNumber;
        private TextView title;
        private TextView subTitle;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            dishImage = itemView.findViewById(R.id.dishName);
            topNumber = itemView.findViewById(R.id.top_number);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            cardView = itemView.findViewById(R.id.dish_layout);
        }
    }
}
