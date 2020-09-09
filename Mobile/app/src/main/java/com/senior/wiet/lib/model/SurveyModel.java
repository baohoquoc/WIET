package com.senior.wiet.lib.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.senior.wiet.R;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.activities.survey.SurveyPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.DummyJson;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.model.survey.Values;
import com.senior.wiet.lib.response.GetMetaTagsByTagIDResponse;
import com.senior.wiet.lib.response.RatingResponse;
import com.senior.wiet.lib.response.SurveyResponse;
import com.senior.wiet.lib.response.SurveySearchTagResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 21/February/2020.
 */
public class SurveyModel extends BaseObservable {

    private String titleSurvey;
    private Context mContext;
    private SurveyPresenter mPresenter;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private DummyJson mDummyJson;
    private User mUser;

    public SurveyModel(DisposableManager disposableManager, Context context, WietApiService wietApiService, DummyJson dummyJson) {
        mDisposableManager = disposableManager;
        mContext = context;
        mWietApiService = wietApiService;
        mDummyJson = dummyJson;
    }

    @Bindable
    public String getTitleSurvey() {
        return titleSurvey;
    }


    public void attachPresenter(SurveyPresenter surveyPresenter) {
        this.mPresenter = surveyPresenter;
    }

    /*
     * This method handling data from server
     * @param meta_tag
     * @param tag
     */

    public void survey(String accessToken) {
        mPresenter.showProgressDialog();
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.survey(mWietApiService.survey("Bearer " + accessToken), new IDisposableListener<SurveyResponse>() {
                @Override
                public void onComplete() {
                    //surveyItemAdapter.setData(new ArrayList<>());
                    //surveyItemAdapter.notifyDataSetChanged();
                    Log.d("download_survey_screen", "done");
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onHandleData(SurveyResponse surveyResponse) {
                    //List<SurveyItem> list = new ArrayList<>();
                    Log.d("survey_data", new Gson().toJson(surveyResponse.getValues()));
                    for (MetaTagValues metaTag : surveyResponse.getValues()) {
                        Log.d("sub_survey_data", new Gson().toJson(metaTag));
                        List<DishItem> listTag = new ArrayList<>();
                        for (TagValues tag : metaTag.getTags()) {   //import data from response to session dish item in survey item
                            List<MetaTagValues> tmp = new ArrayList<>();
                            tmp.add(new MetaTagValues(metaTag.getId(), metaTag.getE_name(), metaTag.getV_name(), new ArrayList<>()));
                            listTag.add(new DishItem(tag.getId(), tmp, tag.getName(), tag.getImageURL()));
                        }
                        SurveyActivity.list.add(new SurveyItem(metaTag.getId(), metaTag.getV_name(), listTag));

                    }
                    Log.d("list_to_json", new Gson().toJson(SurveyActivity.list));
                    SurveyActivity.metaTagItemAdapter.setData(SurveyActivity.list);
                    SurveyActivity.metaTagItemAdapter.notifyDataSetChanged();

                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.dismissProgressDialog();
                }
            }));

        }

    }

    public void search(String accessToken, int limit, int offset, String search) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.searchTagInSurvey(mWietApiService.searchTagsInSurvey("Bearer " + accessToken, limit, offset, search), new IDisposableListener<SurveySearchTagResponse>() {
                @Override
                public void onComplete() {
                    Log.d("download_search_result", "done");
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onHandleData(SurveySearchTagResponse response) {
                    Log.d("search_data", new Gson().toJson(response.getValues()));
                    SurveyActivity.tagSuggestionAdapter.clear();
                    for (Values tag : response.getValues()) {
                        SurveyActivity.suggestions.add(new DishItem(tag.getId(), tag.getMetaTagList(), tag.getName(), tag.getImageURL()));
                    }
                    if(SurveyActivity.suggestions.isEmpty()){
                        Toast.makeText(mContext,mContext.getString(R.string.not_have_search_result),Toast.LENGTH_SHORT).show();
                    }
                    SurveyActivity.tagSuggestionAdapter.setData(SurveyActivity.suggestions);
                    SurveyActivity.tagSuggestionAdapter.notifyDataSetChanged();

                    /*for(TagValues tag : tagResponse.getValues()){
                        SurveyActivity.suggestions.add(new DishItem(tag.getId(),0,tag.getName(),tag.getImageURL()));
                    }
                    SurveyActivity.mSearchSuggestionAdapter.notifyDataSetChanged();
                    if(!SurveyActivity.searchBar.isSuggestionsVisible())
                        SurveyActivity.searchBar.showSuggestionsList();*/
                    //SurveyActivity.mSearchSuggestionAdapter.notifyDataSetChanged();
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                }

                @Override
                public void onApiFailure(Throwable error) {
                }
            }));

        }

    }

    public void rating(String accessToken, RatingRequest request) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.ratingInSurvey(mWietApiService.rating("Bearer " + accessToken, request), new IDisposableListener<RatingResponse>() {
                @Override
                public void onComplete() {
                    Log.d("rating_result", "done");
                }

                @Override
                public void onHandleData(RatingResponse ratingResponse) {
                    Log.d("rating_response", new Gson().toJson(ratingResponse.getValues()));
                    if (ratingResponse.getSucces() == true) {
                        Toast.makeText(mContext, mContext.getString(R.string.rating_success), Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(mContext, mContext.getString(R.string.rating_failure), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRequestWrongData(int t, String message) {

                }

                @Override
                public void onApiFailure(Throwable error) {

                }
            }));

        }

    }

    public void getMetaTagsByTagID(String accessToken, int tag_id) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getMetaTagsByTagID(mWietApiService.getMetaTagByTagID("Bearer " + accessToken, tag_id), new IDisposableListener<GetMetaTagsByTagIDResponse>() {
                @Override
                public void onComplete() {
                    Log.d("rating_result", "done");
                }

                @Override
                public void onHandleData(GetMetaTagsByTagIDResponse response) {
                    Log.d("rating_response", new Gson().toJson(response.getValues()));
                }

                @Override
                public void onRequestWrongData(int t, String message) {

                }

                @Override
                public void onApiFailure(Throwable error) {

                }
            }));

        }

    }


}