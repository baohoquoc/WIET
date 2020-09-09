package com.senior.wiet.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.activities.bookmark.BookmarkActivity;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mitt on 4/3/2020.
 */
public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private Context mContext;
    public List<BookmarkValues> mlistBookmarkFood;
    private BookmarkItemAdapterCallBack mBookmarkItemAdapterCallBack;
    private AlertDialog mAlertDialog;
    private BookmarkActivity mBookmarkActivity;

    public BookmarkAdapter(Context context, List<BookmarkValues> listBookmarkFood, BookmarkActivity bookmarkActivity) {
        mContext = context;
        mlistBookmarkFood = listBookmarkFood;
        mBookmarkActivity = bookmarkActivity;
    }

    public void setListBookmarkFood(List<BookmarkValues> listBookmarkFood) {
        mlistBookmarkFood = listBookmarkFood;
    }

    public void clear() {
        mlistBookmarkFood.clear();
    }

    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        return new ViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
        holder.name.setText(mlistBookmarkFood.get(position).getName());
        holder.restaurantAddress.setText(mlistBookmarkFood.get(position).getAddress());
        String path = mlistBookmarkFood.get(position).getImage();
        //Picasso.get().load(path).into(holder.img);
        if (path.compareTo("/style/images/deli-dish-no-image.png") == 0) {
            Picasso.get().load(Constants.IMG_URI).into(holder.img);
        } else {
            Picasso.get().load(path).into(holder.img);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.Log("delete", "bookmark: ");
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
                        //Toast.makeText(getContext(), "compare location", Toast.LENGTH_LONG).show();
                        mBookmarkItemAdapterCallBack.onDeleteBookmarkValues(holder, mlistBookmarkFood.get(position).getId());
                        //Utilities.Log("list_recommend", new Gson().toJson(list));
                        mAlertDialog.dismiss();
                    }
                });

                mAlertDialog.show(mBookmarkActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookmarkItemAdapterCallBack.onGetBookmarkValues(mlistBookmarkFood.get(position));
                Utilities.Log("choice", "bookmark: " + position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlistBookmarkFood.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView restaurantAddress;
        private TextView name;
        private LinearLayout relativeLayout;
        private ImageView delete;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            mContext = ctx;
            img = itemView.findViewById(R.id.imgfood);
            restaurantAddress = itemView.findViewById(R.id.txtrestaurantAddress);
            name = itemView.findViewById(R.id.txtfoodName);
            relativeLayout = itemView.findViewById(R.id.relative_itembookmark);
            delete = itemView.findViewById(R.id.btnDelete);
            cardView = itemView.findViewById(R.id.itemBookmark_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public void setData(List<BookmarkValues> data) {
        this.mlistBookmarkFood.addAll(data);
        notifyDataSetChanged();
    }

    public void remoteItem(int position) {
        mlistBookmarkFood.remove(position);
        notifyItemChanged(position);
        //goi server

    }

    public void showItem(BookmarkValues item) {

    }

    public interface BookmarkItemAdapterCallBack {
        void onGetBookmarkValues(BookmarkValues bookmarkValues);

        void onDeleteBookmarkValues(BookmarkAdapter.ViewHolder holder, int idfood);
    }

    public void setCallBack(BookmarkItemAdapterCallBack bookmarkItemAdapterCallBack) {
        mBookmarkItemAdapterCallBack = bookmarkItemAdapterCallBack;
    }

}
