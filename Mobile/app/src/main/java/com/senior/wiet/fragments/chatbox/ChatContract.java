package com.senior.wiet.fragments.chatbox;

import com.senior.wiet.databinding.FragmentChatBinding;
import com.senior.wiet.databinding.FragmentMealBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.ChatModel;
import com.senior.wiet.lib.model.MealModel;

public interface ChatContract {
    interface View extends BaseView {
        void attachModel(ChatModel chatModel);

        FragmentChatBinding bindView();
    }

    interface Presenter extends BasePresenter<ChatContract.View> {
    }
}
