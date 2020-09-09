package com.senior.wiet.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.fragments.recommend.RecommendPresenter;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mitt on 4/20/2020.
 */

public class RecommendItemAdapter extends RecyclerView.Adapter<RecommendItemAdapter.ViewHolder> {
    private Context mContext;
    public List<RecommendValues> mlistRecommendValues;
    private RecommendItemAdapter.RecommendkItemAdapterCallBack recommendkItemAdapterCallBack;

    public RecommendItemAdapter(Context mContext, List<RecommendValues> mlistRecommendValues) {
        this.mContext = mContext;
        this.mlistRecommendValues = mlistRecommendValues;
    }

    public void setMlistRecommendValues(List<RecommendValues> mlistRecommendValues) {
        this.mlistRecommendValues = mlistRecommendValues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommned, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String path = mlistRecommendValues.get(position).getImage();
        String foodName = mlistRecommendValues.get(position).getName();
        long price =mlistRecommendValues.get(position).getPrice();
        holder.txtrFoodname.setText(foodName);
        //holder.txtrPrice.setText(price);
        holder.txtrPrice.setText(NumberFormat.getInstance(Locale.US).format(price) + " VND");

        //Picasso.get().load(path).into(holder.img);
        if (path.compareTo("/style/images/deli-dish-no-image.png") == 0) {
            Picasso.get().load(Constants.IMG_URI).into(holder.imgrFood);
        } else {
            Picasso.get().load(path).into(holder.imgrFood);
        }
        //distance
        double distance = 0;
        double rLat = AppSharedPreference.getInstance(mContext).getRealLat();
        double rLon = AppSharedPreference.getInstance(mContext).getRealLong();
        float lat2 = (float) mlistRecommendValues.get(position).getLatitude();
        float lon2 = (float) mlistRecommendValues.get(position).getLongitude();
        distance = distance((float) rLat, (float) rLon, lat2, lon2) / 1000;
        holder.txtrDistance.setText(String.format("%.1f", distance) + " " + mContext.getString(R.string.km_from_here));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                id=mlistRecommendValues.get(position).getId();
                //HistoryPresenter.mHistoryModel.deleteHistory(AppSharedPreference.getInstance(mContext).getAccessToken(), list.get(position).getId());
                RecommendPresenter.mRecommendModel.createHistory(AppSharedPreference.getInstance(mContext).getAccessToken(), id);
                Utilities.Log("choice", "recommend: " + id);
                Intent intent = new Intent(mContext, FoodActivity.class);
                intent.putExtra(Constants.FOOD_ID, id);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mlistRecommendValues.size();
    }

    public void setData(List<RecommendValues> data) {
        this.mlistRecommendValues.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgrFood;
        private TextView txtrFoodname;
        private TextView txtrPrice;
        private TextView txtrDistance;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            mContext = ctx;
            imgrFood = itemView.findViewById(R.id.imgfood);
            txtrFoodname = itemView.findViewById(R.id.txtrFoodName);
            txtrPrice = itemView.findViewById(R.id.txtrPrice);
            txtrDistance = itemView.findViewById(R.id.txtrDistance);
            constraintLayout = itemView.findViewById(R.id.itemRecommend_layout);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface RecommendkItemAdapterCallBack {
        void onGetRecommendValues(RecommendValues recommendValues);

        void onDeleteRecommendValues(RecommendItemAdapter.ViewHolder holder, int idfood);
    }


    public double distance(float lat1, float lon1, float lat2, float lon2) {
        double R = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000;
    }
}
