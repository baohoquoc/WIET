package com.senior.wiet.lib.customui;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.senior.wiet.R;

/**
 * Create by mitt on 3/4/2020.
 */
public class ProgressBarDialog extends Dialog {

    private Activity mActivity;

    public ProgressBarDialog(Activity activity) {
        super(activity);
        this.mActivity = activity;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.vg_progress_dialog);
        setCancelable(false);
    }

    @Override
    public void show() {
        if (mActivity != null && !mActivity.isFinishing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (mActivity != null && !mActivity.isFinishing()) {
            super.dismiss();
        }
    }
}
