package com.senior.wiet.viewholder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.history.HistoryPresenter;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.history.Values;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ViewHolder> {

    private Context mContext;
    private List<Values> list;
    private HistoryActivity mHistoryActivity;
    //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODYyNTI0ODksIm5iZiI6MTU4NjI1MjQ4OSwianRpIjoiYzgzMGIyN2QtYTMxYy00NDgzLTkzYzMtYjkzNjlmZmU3NjM0IiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.wbDFEUPcGKsVh6dZpOUUxOoFQYjgVipOh9Yi0GsiBBQ";
    private AlertDialog mAlertDialog;

    public HistoryItemAdapter(Context mContext, List<Values> list, HistoryActivity historyActivity) {
        this.mContext = mContext;
        this.list = list;
        this.mHistoryActivity = historyActivity;
    }

    private String modifyDateLayout(String inputDate) throws ParseException {
        inputDate = inputDate.replace(" GMT", "");
        Date date = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.US).parse(inputDate);
        Log.d("date_time", date.toString());
        return new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
    }


    @NonNull
    @Override
    public HistoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    public void setData(List<Values> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getFood_name());
        try {
            holder.subTitle.setText(modifyDateLayout(list.get(position).getCreatedTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            Utilities.Log("create_time", e.getMessage());
            holder.subTitle.setText("date_time_error");
        }
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //delete item and call API to delete item in server

                String mes = mContext.getString(R.string.delete_history_item);
                String btnOk = mContext.getString(R.string.btnok);
                String btnCancel = mContext.getString(R.string.btncancel);
                mAlertDialog = new AlertDialog().createDialog(mes, btnOk, btnCancel);
                mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                    @Override
                    public void onNegativeListener() {
                        mAlertDialog.dismiss();
                    }
                });
                mAlertDialog.setOnPositiveListener(new AlertDialog.OnPositiveListener() {
                    @Override
                    public void onPositiveListener() {
                        Toast.makeText(mContext, mContext.getString(R.string.delete_history_message), Toast.LENGTH_SHORT).show();
                        HistoryPresenter.mHistoryModel.deleteHistory(AppSharedPreference.getInstance(mContext).getAccessToken(), list.get(position).getId());
                        list.remove(position);
                        HistoryActivity.historyItemAdapter.notifyDataSetChanged();
                        mAlertDialog.dismiss();
                    }
                });

                mAlertDialog.show(mHistoryActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());

            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open detail food activity
                Intent intent = new Intent(mContext, FoodActivity.class);
                intent.putExtra(Constants.FOOD_ID, list.get(position).getFood_id());
                mContext.startActivity(intent);
            }
        });
        RequestOptions options = new RequestOptions()
                .error(R.color.colorGray)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(list.get(position).getImageURL()).apply(options).into(holder.dishImage);
        //Picasso.get().load(list.get(position).getImageURL()).into(holder.dishImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView dishImage;
        private ImageView delete_icon;
        private TextView title;
        private TextView subTitle;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            dishImage = itemView.findViewById(R.id.dishName);
            delete_icon = itemView.findViewById(R.id.trash_bin);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            cardView = itemView.findViewById(R.id.dish_layout);
        }
    }
}
