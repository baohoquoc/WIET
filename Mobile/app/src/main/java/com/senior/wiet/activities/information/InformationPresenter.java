package com.senior.wiet.activities.information;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.senior.wiet.R;
import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.Allergy;
import com.senior.wiet.lib.model.InformationModel;
import com.senior.wiet.lib.model.MetaTagValues;
import com.senior.wiet.lib.model.TagValues;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class InformationPresenter extends BasePresenterImpl<InformationContract.View> implements InformationContract.Presenter, DatePickerDialog.OnDateSetListener {

    private InformationModel informationModel;
    private InformationActivity informationActivity;
    private ProgressBarDialog mProgressBarDialog;
    private Context mContext;


    @Inject
    public InformationPresenter(InformationActivity informationActivity, InformationModel informationModel, ProgressBarDialog progressBarDialog, Context context) {
        this.informationActivity = informationActivity;
        this.informationModel = informationModel;
        this.mProgressBarDialog = progressBarDialog;
        this.mContext = context;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void inputFailure(String message) {
        informationActivity.hideProgressBarDialog();
        informationActivity.showMessage(message);
        informationActivity.updateSuccess = false;
    }

    @Override
    public void searchTags(String query) {
        mProgressBarDialog.show();
        informationModel.searchMetaTags(query);
        mProgressBarDialog.cancel();
    }

    @Override
    public void tagGetSuccess(List<MetaTagValues> metaTagValues) {
        Log.i("LOG_API_tag", metaTagValues.toString());
        informationActivity.setAllergyTagRecyclerview(metaTagValues);
    }

    @Override
    public void deleteAllergy(Integer allergyID) {
        informationModel.deleteAllergy(allergyID);
    }

    @Override
    public void onViewAdded(InformationContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.informationModel);
        informationModel.attachPresenter(this);
    }

    // This method to show datepicker in view and get the date value user picked
    @Override
    public void initDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(informationActivity, android.R.style.Theme_Material_Light_Dialog_MinWidth, this, year, month, day);
        dialog.show();
    }

    @Override
    public void onRadioButtonClicked() {
        // Check which radio button was clicked
        if (informationActivity.bindView().rbNoneVegetarian.isChecked()) {
            AppSharedPreference.getInstance(mContext).setIsVegetarian(false);
        }
        if (informationActivity.bindView().rbIsVegetarian.isChecked()) {
            AppSharedPreference.getInstance(mContext).setIsVegetarian(true);
        }
    }

    @Override
    public void onClickSearchView() {
        Utilities.showKeyboard(mContext);
        getView().enableSearchView();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        // Call back result from DatePicker and assign value
        month = month + 1;
        Utilities.Log(DebugConstant.DATA_PICKER, "onDateSet: dd/mm/yyy: " + dayOfMonth + "/" + month + "/" + year);
        String date = ((dayOfMonth < 10) ? ("0" + dayOfMonth) : dayOfMonth) + "/" + ((month < 10) ? ("0" + month) : month) + "/" + year;
        if ((Calendar.getInstance().get(Calendar.YEAR) - year) > 0) {
            informationModel.setTvDateOfBirth(date);
            informationActivity.bindView().tvDateOfBirth.setText(date);
            AppSharedPreference.getInstance(mContext).setDob(date);
        } else {
            Toast.makeText(mContext, mContext.getText(R.string.invalidDOB), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSummitUserInfo() {
        informationActivity.showProgressBarDialog();
        informationModel.onSummitUserInfo(informationActivity.getChangeLocation());
    }

    public void deleteSuccess() {

    }

    public void unpdateInfoSuccess() {
        /*informationActivity.updateSuccess = true;*/
        if (!informationActivity.getAllergyid().isEmpty()) {
            informationModel.allergy(new Allergy(informationActivity.getAllergyid()));
        }else{
            informationActivity.hideProgressBarDialog();
            getView().navigate();
        }
    }

    public void inputAllergySuccess() {
        /*informationActivity.updateSuccess = true;*/
        informationActivity.hideProgressBarDialog();
        getView().navigate();
    }
}
