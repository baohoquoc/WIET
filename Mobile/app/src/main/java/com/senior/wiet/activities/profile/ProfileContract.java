package com.senior.wiet.activities.profile;

import com.senior.wiet.databinding.ActivityProfileBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.ProfileModel;
import com.senior.wiet.lib.model.profile.GetAllergyValues;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface ProfileContract {
    interface View extends BaseView {
        void attachModel(ProfileModel ProfileModel);

        ActivityProfileBinding bindView();

        void destroyAPI(Disposable disposable);

        void navigate();
    }

    interface Presenter extends BasePresenter<View> {
        void destroyAPI(Disposable disposable);

        void onEditButton();

        public void getAllergyTags();

        public void allergyGetSuccess(List<GetAllergyValues> values);
    }
}
