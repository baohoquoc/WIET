package com.senior.wiet.viewholder;

import android.content.Context;
import android.content.Intent;
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
import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValues;
import com.senior.wiet.utilities.Constants;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodDetailValues> list;

    public FoodItemAdapter(Context mContext, List<FoodDetailValues> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    public void setData(List<FoodDetailValues> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.ViewHolder holder, int position) {
        FoodDetailValues food = list.get(position);

        holder.txt_dish_name.setText(food.getName());

        RequestOptions options = new RequestOptions()
                .error(R.color.colorGray)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(list.get(position).getImage()).apply(options).into(holder.img_btn);

        holder.img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FoodActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constants.FOOD_ID, list.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton img_btn;
        private TextView txt_dish_name;
        private ImageView tick;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            mContext = ctx;
            img_btn = itemView.findViewById(R.id.tagImage);
            txt_dish_name = itemView.findViewById(R.id.tagTitle);
            tick =  itemView.findViewById(R.id.tick);
        }
    }
}
