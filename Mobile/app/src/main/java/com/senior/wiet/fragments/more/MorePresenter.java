package com.senior.wiet.fragments.more;

import android.content.Context;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.MoreModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */


public class MorePresenter extends BasePresenterImpl<MoreContract.View> implements MoreContract.Presenter {

    private FirebaseAuth mFirebaseAuth;
    private Context mContext;
    private MoreModel moreModel;
    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    public MorePresenter(FirebaseAuth firebaseAuth, Context context, MoreModel moreModel, GoogleSignInOptions googleSignInOptions) {
        this.mFirebaseAuth = firebaseAuth;
        this.mContext = context;
        this.moreModel = moreModel;
        this.mGoogleSignInClient = GoogleSignIn.getClient(mContext, googleSignInOptions);
    }

    @Override
    public void onViewAdded(MoreContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.moreModel);
        moreModel.attachPresenter(this);
    }

    @Override
    public void destroyAPI(Disposable disposable) {

    }

    @Override
    public void onHistoryClick() {
        getView().navigate(1);
    }

    @Override
    public void onBookmarkClick() {
        getView().navigate(2);
    }

    @Override
    public void onProfileClick() {
        getView().navigate(3);
    }

    @Override
    public void onLogoutClick() {
        //Logout with Google
        mGoogleSignInClient.signOut();
        // Logout with firebase
        mFirebaseAuth.getInstance().signOut();
        //Logout with facebook
        LoginManager.getInstance().logOut();
        //Logout with server
        AppSharedPreference.getInstance(mContext).clearSession();
        // Clear Activity Task
        getView().logout();

    }
}
