package com.senior.wiet.activities.bookmark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.databinding.ActivityBookmarkBinding;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.BookmarkModel;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;
import com.senior.wiet.viewholder.BookmarkAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;

/**
 * Created by Mitt on 4/3/2020.
 */
public class BookmarkActivity extends BaseActivity<BookmarkContract.Presenter> implements BookmarkContract.View, AdapterView.OnItemSelectedListener, BookmarkAdapter.BookmarkItemAdapterCallBack {

    @Inject
    Context mContext;

    @Inject
    @Named("vertical")
    LinearLayoutManager mLinearLayoutManager;

    @Inject
    BookmarkPresenter mBookmarkPresenter;

    @Inject
    BookmarkAdapter mBookmarkAdapter;


    private List<BookmarkValues> list;

    private RecyclerView mRecyclerView;
    private ActivityBookmarkBinding binding;
    private Disposable mDisposable;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark);
        super.onCreate(savedInstanceState);
        mBookmarkPresenter.onViewAdded(this);
        binding.setPresenter(mBookmarkPresenter);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBookmarkPresenter.getListBookmark();
        initRecyclerView();
        mBookmarkAdapter.setCallBack(this);
        String imgURL = AppSharedPreference.getInstance(mContext).getAvatar();
        Glide.with(getApplicationContext())
                .load(imgURL)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgAvatar);
    }


    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.bookmark_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        list = new ArrayList<>();
        mBookmarkAdapter = new BookmarkAdapter(getApplicationContext(), list, this);
        mRecyclerView.setAdapter(mBookmarkAdapter);
    }

    @Override
    public void attachModel(BookmarkModel bookmarkModel) {
        binding.setModel(bookmarkModel);
    }

    @Override
    public ActivityBookmarkBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose(); // This code to destroy API if LoginActivity is destroyed
        super.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        Utilities.Log("bookmarks", "bookmark: " + list.get(position).getId());
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setBookmarkRecyclerview(List<BookmarkValues> bookmarkValues) {
        list = bookmarkValues;
        mBookmarkAdapter.setListBookmarkFood(list);
        mRecyclerView.setAdapter(mBookmarkAdapter);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressBarDialog() {

    }

    @Override
    public void hideProgressBarDialog() {

    }

    public void navigate() {
        Intent intent = new Intent(this, FoodActivity.class);
        //truyeefn indetx
        startActivity(intent);
    }


    @Override
    public void onGetBookmarkValues(BookmarkValues bookmarkValues) {
        int tid = bookmarkValues.getId();
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra(Constants.FOOD_ID, tid);
        startActivity(intent);
    }

    @Override
    public void onDeleteBookmarkValues(BookmarkAdapter.ViewHolder viewHolder, int idfood) {
        mBookmarkPresenter.removeItem(list.get(viewHolder.getAdapterPosition()).getId());
        list.remove(viewHolder.getAdapterPosition());
        mBookmarkAdapter.notifyDataSetChanged();
        //mBookmarkAdapter.remoteItem(direction);
        int direction = viewHolder.getAdapterPosition();
        Utilities.Log("delete", "bookmark: " + direction);
    }

    public RecommendValues covertObject(BookmarkValues item) {
        double averageScore = item.getAverage_score();
        String createdTime = item.getCreated_time();
        int foodId = item.getFood_id();
        int id = item.getId();
        String image = item.getImage();
        boolean isBookmark = item.getIs_bookmarked();
        String name = item.getName();
        int price = item.getPrice();
        int restaurantId = item.getRestaurant_id();
        int totalView = item.getTotal_view();
        int totalVote = item.getTotal_vote();
        String updatedTime = item.getUpdated_time();
        return new RecommendValues(averageScore, createdTime, foodId, id, image,
                isBookmark, name, price, restaurantId, totalView,
                totalVote, updatedTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBookmarkPresenter.getListBookmark();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
