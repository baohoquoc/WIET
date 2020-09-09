package com.senior.wiet.activities.survey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.databinding.ActivitySurveyBinding;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.DishItem;
import com.senior.wiet.lib.model.RatingRequest;
import com.senior.wiet.lib.model.SurveyItem;
import com.senior.wiet.lib.model.SurveyModel;
import com.senior.wiet.viewholder.MetaTagItemAdapter;
import com.senior.wiet.viewholder.TagSuggestionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;

//import com.senior.wiet.BuildConfig;

public class SurveyActivity extends BaseActivity<SurveyContract.Presenter> implements SurveyContract.View {

    @Inject
    Context mContext;

    @Inject
    AppSharedPreference provideAppSharedPreference;

    @Inject
    @Named("vertical")
    LinearLayoutManager mLinearLayoutManager;

    //@Inject SurveyItemAdapter surveyItemAdapter;

    @Inject
    SurveyPresenter mSurveyPresenter;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    AlertDialog mAlertDialog;

    //@Inject List<DishItem> suggestions;

    public static List<SurveyItem> list = new ArrayList<>();

    //private String token = "";
    //private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";

    //public static List<SurveyItem> list = new ArrayList<>();
    public static MetaTagItemAdapter metaTagItemAdapter;
    private ActivitySurveyBinding binding;
    private Disposable mDisposable;
    private Button skip_btn;

    // List to save tag id when you check one tag or uncheck it
    public static RatingRequest request = new RatingRequest();

    //item use for search bar
    //public static MaterialSearchBar searchBar;
    public static EditText searchBar;

    public static RecyclerView searchView;

    public static TagSuggestionAdapter tagSuggestionAdapter;
    //private List<DishItem> suggestions = new ArrayList<>();
    public static List<DishItem> suggestions = new ArrayList<>();

    //public static SearchSuggestionAdapter mSearchSuggestionAdapter;

    //attribute to listen user stop typing
    private long delay = 100; // 1 seconds after user stops typing
    private long last_text_edit = 0;
    private Handler handler = new Handler();
    private ImageButton clearText;


    public void init() {
        //token = provideAppSharedPreference.getAccessToken();
        skip_btn = findViewById(R.id.btn_skip);
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSurveyPresenter.onClickSkip();
                //navigate();
            }
        });
    }
    

    private Runnable input_finish_checker = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                // TODO: do what you need here
                // ............
                // ............
                if (searchBar.getText().toString().equals("")) return;
                mSurveyPresenter.onSearch(searchBar.getText().toString().toLowerCase().trim());
                if (searchView.getVisibility() == View.GONE)
                    searchView.setVisibility(View.VISIBLE);
            }
        }
    };

    public void initSearchBar() {
        searchBar = findViewById(R.id.search_bar);
        searchView = findViewById(R.id.search_view);
        clearText = findViewById(R.id.clear_text);
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.getText().clear();
                searchView.setVisibility(View.GONE);
            }
        });
        tagSuggestionAdapter = new TagSuggestionAdapter(getApplicationContext(), suggestions);
        searchView.setAdapter(tagSuggestionAdapter);
        searchView.setHasFixedSize(true);
        searchView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || s.toString().replace(" ", "").isEmpty()) {
                    suggestions.clear();
                    searchView.setVisibility(View.GONE);
                }
                ;
                if (s.length() < 2) return;
                handler.removeCallbacks(input_finish_checker);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().replace(" ", "").isEmpty()) {
                    suggestions.clear();
                    searchView.setVisibility(View.GONE);
                }
                ;
                if (s.length() < 2) return;
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(input_finish_checker, delay);
                } else {   //length == 0
                    searchView.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_survey);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        if(!AppSharedPreference.getInstance(mContext).getIsFirstLogin())
        {
            Toast.makeText(mContext,mContext.getString(R.string.only_rating_one_time),Toast.LENGTH_LONG).show();
            navigate();
        }
        mProgressBarDialog = new ProgressBarDialog(this);
        binding.surveyView.setHasFixedSize(true);
        binding.surveyView.setLayoutManager(mLinearLayoutManager);
        metaTagItemAdapter = new MetaTagItemAdapter(this, new ArrayList<>());
        metaTagItemAdapter.setData(list);
        binding.surveyView.setAdapter(metaTagItemAdapter);
        init();
        initSearchBar();
    }

    @Override
    public void attachModel(SurveyModel surveyModel) {
        binding.setModel(surveyModel);
    }

    @Override
    public ActivitySurveyBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void showMessage(String message) {
        Log.e("Survey message", message);
    }

    @Override
    public void showProgressBarDialog() {

    }

    @Override
    public void hideProgressBarDialog() {

    }

    @Override
    public void navigate() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("from", "survey");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose(); // This code to destroy API if LoginActivity is destroyed
        super.onDestroy();
    }

}
