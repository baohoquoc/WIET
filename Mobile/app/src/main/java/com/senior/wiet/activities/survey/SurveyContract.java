package com.senior.wiet.activities.survey;

import com.senior.wiet.databinding.ActivitySurveyBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.SurveyModel;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public interface SurveyContract {

    interface View extends BaseView {

        void attachModel(SurveyModel surveyModel);

        ActivitySurveyBinding bindView();

        void destroyAPI(Disposable disposable);

        void navigate();
    }

    interface Presenter extends BasePresenter<View> {

        void onClickSkip();

        void onSearch(String search);

        void destroyAPI(Disposable disposable);

        void navigate();

        void onClickSuggestionItem(int tag_id);

    }

}
