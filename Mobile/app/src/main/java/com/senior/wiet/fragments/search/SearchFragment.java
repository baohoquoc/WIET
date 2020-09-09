package com.senior.wiet.fragments.search;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.databinding.FragmentSearchBinding;
import com.senior.wiet.fragments.BaseFragment;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.lib.model.SearchModel;
import com.senior.wiet.viewholder.DishItemAdapter;
import com.senior.wiet.viewholder.TopRatingAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class SearchFragment extends BaseFragment<SearchContract.Presenter> implements SearchContract.View {

    @Inject
    SearchPresenter mSearchPresenter;

    /*@Inject
    @Named("vertical")*/
    private LinearLayoutManager mLinearLayoutManager;

    private FragmentSearchBinding binding;

    public static List<RecommendValues> topRatingDishes = new ArrayList<>();
    public static List<RecommendValues> dishSuggestions = new ArrayList<>();
    public static TopRatingAdapter topRatingDishesAdapter;
    public static DishItemAdapter dishSuggestionsAdapter;

    public static boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int offset = 1;   //default top 10

    //attribute to listen user stop typing
    private long delay = 100; // 1 seconds after user stops typing
    private long last_text_edit = 0;
    private Handler handler = new Handler();

    public View view;

    @Inject
    public SearchFragment() {

    }

    private Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                // TODO: do what you need here
                // ............
                // ............
                if (binding.searchBarDish.getText().toString().equals("")) return;
                //mSurveyPresenter.onSearch(binding.searchBarDish.getText().toString().toLowerCase());
                mSearchPresenter.onSearch(binding.searchBarDish.getText().toString().toLowerCase().trim());
                if (binding.searchDishView.getVisibility() == View.GONE)
                    binding.searchDishView.setVisibility(View.VISIBLE);
            }
        }
    };


    /*public void initView(){
     *//*topRatingDishesAdapter = new DishItemAdapter(getContext(),topRatingDishes);
        binding.viewTopFavourite.setAdapter(topRatingDishesAdapter);
        binding.viewTopFavourite.setHasFixedSize(true);
        binding.viewTopFavourite.setLayoutManager(mLinearLayoutManager);*//*
    }*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_search, container, false);
        binding = DataBindingUtil.bind(view);
        binding.setPresenter(mSearchPresenter);
        return view;
    }

    @Override
    public void attachModel(SearchModel searchModel) {
        binding.setModel(searchModel);
    }


    @Override
    public FragmentSearchBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {

    }

    @Override
    public void initView() {
        topRatingDishesAdapter = new TopRatingAdapter(getContext(), topRatingDishes);
        binding.viewTopFavourite.setAdapter(topRatingDishesAdapter);
        binding.viewTopFavourite.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.viewTopFavourite.setLayoutManager(mLinearLayoutManager);
        binding.viewTopFavourite.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) { //check for scroll down

                    visibleItemCount = mLinearLayoutManager.getChildCount();
                    totalItemCount = mLinearLayoutManager.getItemCount();
                    pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            if(offset==100) return;   //1000 item is enough
                            loading = false;
                            //Toast.makeText(getContext(),"Loading",Toast.LENGTH_SHORT).show();
                            // Do pagination.. i.e. fetch new data
                            mSearchPresenter.loadOffset(++offset);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initSearchBar() {
        dishSuggestionsAdapter = new DishItemAdapter(getContext(), dishSuggestions);
        binding.searchDishView.setAdapter(dishSuggestionsAdapter);
        binding.searchDishView.setHasFixedSize(true);
        binding.searchDishView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.clearText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchBarDish.getText().clear();
            }
        });
        binding.searchBarDish.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || s.toString().replace(" ", "").isEmpty()) {
                    dishSuggestions.clear();
                    binding.searchDishView.setVisibility(View.GONE);
                }
                ;
                if (s.length() < 2) return;
                handler.removeCallbacks(input_finish_checker);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().replace(" ", "").isEmpty()) {
                    dishSuggestions.clear();
                    binding.searchDishView.setVisibility(View.GONE);
                }
                ;
                if (s.length() < 2) return;
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(input_finish_checker, delay);
                } else {   //length == 0
                    binding.searchDishView.setVisibility(View.GONE);
                }
            }
        });
    }

}
