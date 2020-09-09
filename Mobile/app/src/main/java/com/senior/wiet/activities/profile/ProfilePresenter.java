package com.senior.wiet.activities.profile;

import android.content.Context;
import android.widget.Toast;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.model.ProfileModel;
import com.senior.wiet.lib.model.profile.GetAllergyValues;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ProfilePresenter extends BasePresenterImpl<ProfileContract.View> implements ProfileContract.Presenter{

    private ProfileModel profileModel;
    private ProfileActivity profileActivity;
    private ProgressBarDialog mProgressBarDialog;
    private Context mContext;


    @Inject
    public ProfilePresenter( ProfileActivity profileActivity,  ProfileModel profileModel, ProgressBarDialog progressBarDialog, Context context) {
        this.profileActivity = profileActivity;
        this.profileModel = profileModel;
        this.mProgressBarDialog = progressBarDialog;
        this.mContext = context;
    }

    @Override
    public void onViewAdded(ProfileContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.profileModel);
        profileModel.attachPresenter(this);
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void onEditButton() {
        profileActivity.editButton();
    }


    public void inputSuccess(Object o) {
        //Toast.makeText(mContext, "Cập nhật thành công",Toast.LENGTH_SHORT).show();
    }

    public void inputFailure(String message) {
        Toast.makeText(mContext, "Cập nhật thất bại. Lỗi: \n"+message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAllergyTags() {
        profileModel.getAllergyTags();
    }

    public void allergyGetSuccess(List<GetAllergyValues> values) {
        profileActivity.setAllergyTags(values);
    }
}
