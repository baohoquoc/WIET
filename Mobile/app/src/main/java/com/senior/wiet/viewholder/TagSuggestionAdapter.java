package com.senior.wiet.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.senior.wiet.R;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.lib.model.DishItem;
import com.senior.wiet.lib.model.SurveyItem;

import java.util.ArrayList;
import java.util.List;

public class TagSuggestionAdapter extends RecyclerView.Adapter<TagSuggestionAdapter.ViewHolder> {

    private Context mContext;
    private List<DishItem> list;

    public TagSuggestionAdapter(Context context, List<DishItem> values) {
        mContext = context;
        list = values;
    }

    public void setData(List<DishItem> values) {
        list = values;
    }

    public void clear() {
        list.clear();
    }


    @NonNull
    @Override
    public TagSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull TagSuggestionAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DishItem tmp = new DishItem(list.get(position).getId(), list.get(position).getListMetaTag(), list.get(position).getName(), list.get(position).getImageURL());

                //check Is item contain in Survey available data
                for (SurveyItem item : SurveyActivity.list) {
                    if (item.isContains(tmp)) {
                        Toast.makeText(mContext, mContext.getString(R.string.is_exist_tag), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Log.d("suggestion_item", new Gson().toJson(list.get(position)));
                if (tmp.getListMetaTag().size() == 0) {
                    Toast.makeText(mContext, mContext.getString(R.string.not_support_item), Toast.LENGTH_SHORT).show();
                    return;
                }

                int index = -1;
                for (int i = 0; i < SurveyActivity.list.size(); i++) {
                    if (list.get(position).getListMetaTag().get(0).getId() == SurveyActivity.list.get(i).getId()) {
                        index = i;
                        break;
                    }
                }
                Log.d("index", index + "");
                if (index == -1) {       //create new survey item
                    List<DishItem> list = new ArrayList<>();
                    list.add(tmp);
                    Log.d("tmp", new Gson().toJson(tmp));
                    SurveyItem surveyItem = new SurveyItem();
                    surveyItem.setId(tmp.getListMetaTag().get(0).getId());
                    surveyItem.setName(tmp.getListMetaTag().get(0).getV_name());
                    //surveyItem.setName(tmp.getListMetaTag().get(0).getE_name());   //english name
                    surveyItem.setDishItemList(list);
                    SurveyActivity.list.add(0, surveyItem);
                    SurveyActivity.metaTagItemAdapter.notifyDataSetChanged();
                    SurveyActivity.searchView.setVisibility(View.GONE);
                } else {
                    SurveyActivity.list.get(index).add(tmp);
                    SurveyActivity.metaTagItemAdapter.notifyDataSetChanged();
                    SurveyActivity.searchView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        //private TextView subtitle;
        //private ImageView image;
        //private CardView layout;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            //image = (ImageView) itemView.findViewById(R.id.imageView2);
            title = (TextView) itemView.findViewById(R.id.title);
            //subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            //layout = (CardView) itemView.findViewById(R.id.suggestion_layout);
        }


    }

}
