package com.senior.wiet.fragments.chatbox;

import com.senior.wiet.framework.presenter.BasePresenterImpl;

import javax.inject.Inject;

public class ChatPresenter extends BasePresenterImpl<ChatContract.View> implements ChatContract.Presenter {

    @Inject
    public ChatPresenter() {
    }
}
