package com.senior.wiet.activities.information;

import com.senior.wiet.databinding.ActivityInformationBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.InformationModel;
import com.senior.wiet.lib.model.MetaTagValues;
import com.senior.wiet.lib.model.TagValues;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface InformationContract {
    interface View extends BaseView {
        void attachModel(InformationModel informationModel);

        //binding
        ActivityInformationBinding bindView();

        void destroyAPI(Disposable disposable);

        void enableSearchView();

        void navigate();
    }

    interface Presenter extends BasePresenter<View> {

        void initDatePicker();

        void onRadioButtonClicked();

        void onClickSearchView();

        void onSummitUserInfo();

        void destroyAPI(Disposable disposable);

        void inputFailure(String message);

        void searchTags(String query);

        void tagGetSuccess(List<MetaTagValues> tagValues);

        void deleteAllergy(Integer allergyID);
    }

}
