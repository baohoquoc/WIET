package com.senior.wiet.activities.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.databinding.ActivityProfileBinding;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.ProfileModel;
import com.senior.wiet.lib.model.profile.GetAllergyValues;
import com.senior.wiet.utilities.Utilities;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import co.lujun.androidtagview.TagContainerLayout;
import io.reactivex.disposables.Disposable;

public class ProfileActivity extends BaseActivity<ProfileContract.Presenter> implements ProfileContract.View {

    @Inject
    Context mContext;

    @Inject
    ProfilePresenter mPresenter;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    AppSharedPreference mAppSharedPreference;

    @Inject
    ProfileModel mProfileModel;

    /*
     *This list to show tag to view activity.
     */
    @Inject
    List<String> mListOfTags;


    /*
     *This list to save Allergy Tag object.
     */
    @Inject
    List<GetAllergyValues> mListAllergyTags;

    private ActivityProfileBinding binding;
    private Disposable mDisposable;
    private TagContainerLayout mTagContainerLayout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
        mTagContainerLayout.setTags(mListOfTags);

        //initData();

        initAllergyTags();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume(){
        super.onResume();
        Utilities.closeKeyboard(mContext);
        mListOfTags.clear();
        initData();
    }

  /*  @Override
    public void onPause(){
        super.onPause();

    }*/

//    protected void onRestart() {
//        super.onRestart();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            onResume();
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        //Show full name
        String fullName = AppSharedPreference.getInstance(mContext).getFullName();
        Log.i("profile", fullName);
        binding.etFullName.setText(fullName);
        //Show avatar
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(AppSharedPreference.getInstance(mContext).getAvatar()).apply(options).into(binding.imgAvatar);
        //Show location
        String location = AppSharedPreference.getInstance(mContext).getUserLocation();
        Log.i("profile", location);
        binding.tvLocation.setText(location);
        //Show date of birth
        String dob = AppSharedPreference.getInstance(mContext).getDob();
        if (dob.length()>10 ) {
            dob = convertDateType(dob);
            AppSharedPreference.getInstance(mContext).setDob(dob);
        }
        Log.i("profile", dob);
        binding.tvDateOfBirth.setText(dob);
        Boolean vagetarian = AppSharedPreference.getInstance(mContext).getIsVegetarian();
        Log.i("profile", vagetarian.toString());
        if(vagetarian) binding.tvVegetarian.setText(getText(R.string.isVegetarianProfile).toString());
        else binding.tvVegetarian.setText(getText(R.string.noneVegetarianProfile).toString());
        //handle allergy tags
        mPresenter.getAllergyTags();
        //mListOfTags.add("a");
        Log.i("jwt_token", AppSharedPreference.getInstance(mContext).getAccessToken());
    }

    /*
     *This method to handle data type "Mon, 1 jan 1990 gmt 00:00:00" to "01/01/1990"
     * @param date
     * @return
     */
    public String convertDateType(String date){
        date = date.substring(5, 16);
        date = date.replace(" ", "/");
        String month= date.substring(3,6);
        date = date.replace(month, convertMonth(month));
        /*if(String.valueOf(date.charAt(0)).equals("0")){
            int end = date.length();
            date = date.substring(1, end);
        }*/
        return date;
    }

    public String convertMonth(String month){
        if (month.equals("Jan")) return "01";
        else if (month.equals("Feb")) return "02";
        else if (month.equals("Mar")) return "03";
        else if (month.equals("Apr")) return "04";
        else if (month.equals("May")) return "05";
        else if (month.equals("Jun")) return "06";
        else if (month.equals("July")) return "07";
        else if (month.equals("Aug")) return "08";
        else if (month.equals("Sep")) return "09";
        else if (month.equals("Oct")) return "10";
        else if (month.equals("Nov")) return "11";
        else return "12";
    }

    public void initAllergyTags(){
        /*mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTagClick(int position, String text) {

            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });*/
    }

    public void setAllergyTags(List<GetAllergyValues> getAllergyValues) {
        mListAllergyTags = getAllergyValues;
        for (int i=0; i< mListAllergyTags.size(); i++){
            mListOfTags.add(mListAllergyTags.get(i).getName());
        }
        mTagContainerLayout.setTags(mListOfTags);
    }

    /*
     * This method to handle the event click on btnEdit
     */
    public void editButton(){
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("arraylist", (Serializable) mListAllergyTags);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressBarDialog() {
        if (!isDestroyed() && !mProgressBarDialog.isShowing()) {
            mProgressBarDialog.show();
        }
    }

    @Override
    public void hideProgressBarDialog() {
        if (!isDestroyed() && mProgressBarDialog.isShowing()) {
            mProgressBarDialog.dismiss();
        }
    }

    @Override
    public void attachModel(ProfileModel profileModel) {
        binding.setModel(profileModel);
    }

    @Override
    public ActivityProfileBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void navigate() {

    }
}
