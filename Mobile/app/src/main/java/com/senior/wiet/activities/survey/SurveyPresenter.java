package com.senior.wiet.activities.survey;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.senior.wiet.R;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.SurveyModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public class SurveyPresenter extends BasePresenterImpl<SurveyContract.View> implements SurveyContract.Presenter, OnCompleteListener {

    private SurveyModel mSurveyModel;
    private SurveyActivity mSurveyActivity;
    private Disposable mDisposable;
    private ProgressBarDialog mProgressBarDialog;
    private Context mContext;
    private AlertDialog mAlertDialog;
    //String accessToken = AppSharedPreference.getInstance(mContext).getAccessToken();
    //testing token
    //String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";


    @Inject
    public SurveyPresenter(SurveyActivity surveyActivity, SurveyModel surveyModel, ProgressBarDialog progressBarDialog, AlertDialog alertDialog, Context context) {
        //SurveyActivity surveyActivity, SurveyModel surveyModel, Context context, ProgressBarDialog progressBarDialog
        this.mSurveyActivity = surveyActivity;
        this.mSurveyModel = surveyModel;
        this.mContext = context;
        this.mProgressBarDialog = progressBarDialog;
        mAlertDialog = alertDialog;
    }

    @Override
    public void onViewAdded(SurveyContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(mSurveyModel);
        mSurveyModel.attachPresenter(this);
        //String accessToken = AppSharedPreference.getInstance(mContext).getAccessToken();
        mSurveyModel.survey(AppSharedPreference.getInstance(mContext).getAccessToken());
    }

    @Override
    public void onClickSkip() {
        Log.d("Skip", "Clicked");
        if (SurveyActivity.request.getRatings().isEmpty()) {

            mAlertDialog = new AlertDialog().createDialog(String.format(mContext.getString(R.string.skip_rating)), "OK", "Hủy");
            mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                @Override
                public void onNegativeListener() {
                    mAlertDialog.dismiss();
                    //stand at present screen
                }
            });
            mAlertDialog.setOnPositiveListener(new AlertDialog.OnPositiveListener() {
                @Override
                public void onPositiveListener() {
                    mAlertDialog.dismiss();
                    navigate(); // transfer to mainscreen
                    mSurveyModel.rating(AppSharedPreference.getInstance(mContext).getAccessToken(), SurveyActivity.request); //rating with a empty array
                }
            });
            mAlertDialog.show(mSurveyActivity.getSupportFragmentManager(), "skip_rating");
            //Toast.makeText(mSurveyActivity, "Bỏ qua khảo sát", Toast.LENGTH_LONG).show();
        } else {
            mSurveyModel.rating(AppSharedPreference.getInstance(mContext).getAccessToken(), SurveyActivity.request);
            navigate();  // transfer to mainscreen
        }

    }

    @Override
    public void onSearch(String search) {
        mSurveyModel.search(AppSharedPreference.getInstance(mContext).getAccessToken(), 5, 1, search);
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);

    }

    @Override
    public void navigate() {
        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                AppSharedPreference.getInstance(mContext).setIsFirstLogin(false);
                /*have error when transfer to main Activity*/
                Intent intent = new Intent(mSurveyActivity, MainActivity.class);
                intent.putExtra("from", "survey");
                mSurveyActivity.startActivity(intent);
            }
        }.start();

    }

    @Override
    public void onClickSuggestionItem(int tag_id) {
        mSurveyModel.getMetaTagsByTagID(AppSharedPreference.getInstance(mContext).getAccessToken(), tag_id);
    }

    @Override
    public void onComplete(@NonNull Task task) {
        Log.e("Survey Activity", "Completed");
    }

    public void showProgressDialog() {
        mProgressBarDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressBarDialog.dismiss();
    }
}