package com.senior.wiet.activities.information;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.databinding.ActivityInformationBinding;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.InformationModel;
import com.senior.wiet.lib.model.MetaTagValues;
import com.senior.wiet.lib.model.profile.GetAllergyValues;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;
import com.senior.wiet.viewholder.TagItemAdapter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import io.reactivex.disposables.Disposable;

public class InformationActivity extends BaseActivity<InformationContract.Presenter> implements InformationContract.View, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, TagItemAdapter.TagItemAdapterCallback {

    @Inject
    Context mContext;

    @Inject
    InformationActivity mActivity;

    @Inject
    InformationPresenter mPresenter;

    @Inject
    AppSharedPreference mAppSharedPreference;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    TagItemAdapter mTagItemAdapter;

    @Inject
    @Named("vertical")
    LinearLayoutManager mLinearLayoutManager;

    /*
     * This list used to resource for TagContainerLayout to display allergy Tags
     */
    @Inject
    List<String> mListOfTags;

    /*
     * This list used to save allergy object when user pick on recycler view and sent it to server
     */
    @Inject
    List<MetaTagValues> mListAllergyTags;

    private List<MetaTagValues> mList;
    //This array to save the last allergy tags in server (the data sent from profile activity)
    private List<GetAllergyValues> lastAllergyTags;
    private CountDownTimer cntr;
    private RecyclerView mRecyclerView;
    private ActivityInformationBinding binding;
    private Disposable mDisposable;
    TagContainerLayout mTagContainerLayout;
    private AlertDialog mAlertDialog;
    Boolean updateSuccess;

    private String lastLocation;


    private String changeLocation;

    public String getChangeLocation() {
        return changeLocation;
    }

    public void setChangeLocation(String changeLocation) {
        this.changeLocation = changeLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("date", AppSharedPreference.getInstance(mContext).getDob());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        initData();
        initLocationSpinner();
        initRealLocation();
        //This method to handle recyclerView
        initRecyclerForTagItem();
        mTagContainerLayout = (TagContainerLayout) findViewById(R.id.tagcontainerLayout);
        mTagContainerLayout.setTags(mListOfTags);
        mTagItemAdapter.setCallBack(this);
        initAllergyTags();
        initTag();
        updateSuccess = false;
    }

    private void initData() {
        if (!AppSharedPreference.getInstance(mContext).getIsFirstLogin())
            binding.buttonConfirm.setText(mContext.getText(R.string.btndone));
        binding.etFullName.setText(AppSharedPreference.getInstance(mContext).getFullName());
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(AppSharedPreference.getInstance(mContext).getAvatar()).apply(options).into(binding.imgAvatar);
        if (AppSharedPreference.getInstance(mContext).getIsVegetarian())
            binding.rdbVegetarian.check(binding.rbIsVegetarian.getId());
        String userLocation= AppSharedPreference.getInstance(mContext).getUserLocation();
        if(userLocation == null || userLocation.isEmpty() || userLocation.equals("")){
            lastLocation = "";
            changeLocation = "";
        } else {
            lastLocation = userLocation;
            changeLocation = userLocation;
        }
    }

    /*
     *This method to receive data allery tags from Profile (If it have)
     */
    private void initTag() {
        if (AppSharedPreference.getInstance(mContext).getIsFirstLogin()) return;
        lastAllergyTags = (List<GetAllergyValues>) getIntent().getSerializableExtra("arraylist");
        if (lastAllergyTags != null && lastAllergyTags.size() != 0) {
            for (int i = 0; i < lastAllergyTags.size(); i++) {
                mListAllergyTags.add(new MetaTagValues(lastAllergyTags.get(i).getTagId(), lastAllergyTags.get(i).getName(), lastAllergyTags.get(i).getName()));
                mListOfTags.add(lastAllergyTags.get(i).getName());
            }
            mTagContainerLayout.setTags(mListOfTags);
        }
    }

    private void initRecyclerForTagItem() {
        mRecyclerView = findViewById(R.id.tag_item);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mList = new ArrayList<>();
        mRecyclerView.setAdapter(mTagItemAdapter);
    }

    @Override
    public void attachModel(InformationModel informationModel) {
        binding.setModel(informationModel);
    }

    @Override
    public ActivityInformationBinding bindView() {
        return binding;
    }

    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void enableSearchView() {
        binding.searchAllergyBar.requestFocus();
        binding.searchAllergyBar.setIconified(false);
        binding.searchAllergyBar.setOnQueryTextListener(this);
        binding.scvInfo.post(new Runnable() {
            @Override
            public void run() {
                binding.scvInfo.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        mAlertDialog = new AlertDialog().createDialog(message,
                Constants.EMPTY_STRING, mContext.getString(R.string.btn_ok));
        mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
        mAlertDialog.show(mActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
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

    public void initRealLocation() {
        if (AppSharedPreference.getInstance(mContext).getIsFirstLogin()) {
            String state = AppSharedPreference.getInstance(mContext).getDevicelocation();
            if (state != null && state.equals("")) {
                //Set real location city to spinner
                int index = 0;
                for (int i = 0; i < binding.locationSpinner.getCount(); i++) {
                    if (binding.locationSpinner.getItemAtPosition(i).toString().equals(state)) {
                        binding.locationSpinner.setSelection(i);
                        break;
                    }
                }
            }
        } else {
            String state = AppSharedPreference.getInstance(mContext).getUserLocation();
            if (state != null) {
                //Set real location city to spinner
                int index = 0;
                for (int i = 0; i < binding.locationSpinner.getCount(); i++) {
                    if (binding.locationSpinner.getItemAtPosition(i).toString().equals(state)) {
                        binding.locationSpinner.setSelection(i);
                        break;
                    }
                }
            }
        }
    }

    // This method to set data of location to Spinner
    public void initLocationSpinner() {
        // Specify the layout to use when the list of choices appears
        /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( mContext , R.array.location_array, android.R.layout.simple_spinner_item);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.location_array)
        );
        // Apply the adapter to the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        binding.locationSpinner.setAdapter(adapter);
        // Attach OnItemListener to call back result
        binding.locationSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String location = parent.getItemAtPosition(position).toString();
        Utilities.Log(DebugConstant.LOCATION, location);
        changeLocation = location;
    }

    /*
     * @param str covert "Đà Nẵng" into "da-nang"
     * @return
     */
    public String deAccent(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
     * This method to handle user submit text in allergy search bar When User finish input.
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.searchTags(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Reset empty text
        if (newText.length() == 0) {
            mList.clear();
            mTagItemAdapter.setList(mList);
        }

        // Count down for search after period of time
        if (cntr != null) {
            cntr.cancel();
        }
        cntr = new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (newText.length() > 0) {
                    mPresenter.searchTags(newText);
                    Utilities.Log(DebugConstant.ALLERGY_SEARCH_TEXT, newText);
                    binding.scvInfo.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.scvInfo.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        };
        cntr.start();
        return false;
    }

    public void setData(List<MetaTagValues> data) {
        mList = data;
    }

    public void setAllergyTagRecyclerview(List<MetaTagValues> metaTagValues) {
        mList = metaTagValues;
        mTagItemAdapter.setList(metaTagValues);
        mRecyclerView.setAdapter(mTagItemAdapter);
        //This event to scroll view down to show the data search
        binding.scvInfo.post(new Runnable() {
            @Override
            public void run() {
                binding.scvInfo.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onGetTagValues(MetaTagValues tag) {
        mListAllergyTags.add(tag);
        mListOfTags.add(tag.getV_name());
        mListOfTags = mListOfTags.stream().distinct().collect(Collectors.toList());
        mTagContainerLayout.setTags(mListOfTags);
    }

    /*
     * This method to handle event of tagview on info view
     */
    public void initAllergyTags() {
        mTagContainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTagClick(int position, String text) {
                if (!AppSharedPreference.getInstance(mContext).getIsFirstLogin()) {
                    if (lastAllergyTags != null && lastAllergyTags.size() != 0) {
                        for (int i = 0; i < lastAllergyTags.size(); i++) {
                            if (lastAllergyTags.get(i).getTagId() == mListAllergyTags.get(position).getId()) {
                                showMessage(getText(R.string.deleteAllergy).toString());
                                mPresenter.deleteAllergy(lastAllergyTags.get(i).getId());
                                lastAllergyTags.remove(i);
                                break;
                            }
                        }
                    }
                }
                mListOfTags.remove(position);
                mListAllergyTags.remove(position);
                mListOfTags = mListOfTags.stream().distinct().collect(Collectors.toList());
                mTagContainerLayout.setTags(mListOfTags);
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
        });
    }

    /*
     * @return list integer of allergy id, This list used to sent to server side.
     */
    public List<Integer> getAllergyid() {
        List<Integer> tag_ids = new ArrayList<>();
        for (int i = 0; i < mListAllergyTags.size(); i++) {
            tag_ids.add(mListAllergyTags.get(i).getId());
        }
        return tag_ids;
    }

    public void navigateSurvey() {
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
        finish();
    }

    public static void restartApplication(Context context) {
        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage( context.getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    public void checkLocationChange(){
        if (AppSharedPreference.getInstance(mContext).getIsFirstLogin()) {
            navigateSurvey();
        } else {
            if (!changeLocation.equals(lastLocation)) {
                mAlertDialog = new AlertDialog().createDialog(getString(R.string.restartAppMessage), mContext.getString(R.string.btn_ok), mContext.getString(R.string.btncancel));
                mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                    @Override
                    public void onNegativeListener() {
                        mAlertDialog.dismiss();
                        finish();
                    }
                });
                mAlertDialog.setOnPositiveListener(new AlertDialog.OnPositiveListener() {
                    @Override
                    public void onPositiveListener() {
                        mAlertDialog.dismiss();
                        restartApplication(mContext);
                    }
                });
                mAlertDialog.show(mActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
            } else {
                finish();
            }
        }
    }

    public void navigate() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = this.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        mAlertDialog = new AlertDialog().createDialog(getText(R.string.updateInfoSuccessMessage).toString(),
                Constants.EMPTY_STRING, mContext.getString(R.string.btn_ok));
        if (AppSharedPreference.getInstance(mContext).getIsFirstLogin()) {
            mAlertDialog.setOnNegativeListener(() -> checkLocationChange());
        } else {
            mAlertDialog.setOnNegativeListener(() -> checkLocationChange());
        }
        mAlertDialog.show(mActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
    }

}
