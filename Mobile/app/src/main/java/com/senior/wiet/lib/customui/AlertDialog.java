package com.senior.wiet.lib.customui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.senior.wiet.R;
import com.senior.wiet.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlertDialog extends DialogFragment {


    @BindView(R.id.tvMessage)
    TextView tvMessage;

    @BindView(R.id.btnPositive)
    TextView btnPositive;

    @BindView(R.id.btnNegative)
    TextView btnNegative;

    private OnPositiveListener mOnPositiveListener;
    private OnNegativeListener mOnNegativeListener;

    public AlertDialog() {

    }

    public AlertDialog createDialog(String message, String positive, String negative) {
        AlertDialog frag = new AlertDialog();
        Bundle args = new Bundle();
        args.putString(Constants.AlertDialog.MESSAGE, message);
        args.putString(Constants.AlertDialog.POSITIVE, positive);
        args.putString(Constants.AlertDialog.NEGATIVE, negative);
        frag.setArguments(args);
        frag.setCancelable(false);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.view.View view = inflater.cloneInContext(new ContextThemeWrapper(getContext(), R.style.FontTheme))
                .inflate(R.layout.fragment_dialog_alert, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMessage.setText(getArguments().getString(Constants.AlertDialog.MESSAGE, Constants.EMPTY_STRING));
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto);
        tvMessage.setTypeface(typeface);
        btnNegative.setTypeface(typeface);
        btnPositive.setTypeface(typeface);
        btnPositive.setText(getArguments().getString(Constants.AlertDialog.POSITIVE, Constants.EMPTY_STRING));
        btnPositive.setVisibility(TextUtils.isEmpty(getArguments().getString(Constants.AlertDialog.POSITIVE, Constants.EMPTY_STRING)) ? View.GONE : View.VISIBLE);

        btnPositive.setOnClickListener(v -> {
            if (mOnPositiveListener != null) {
                mOnPositiveListener.onPositiveListener();
            }
            dismiss();
        });

        btnNegative.setText(getArguments().getString(Constants.AlertDialog.NEGATIVE, Constants.EMPTY_STRING));
        btnNegative.setVisibility(TextUtils.isEmpty(getArguments().getString(Constants.AlertDialog.NEGATIVE, Constants.EMPTY_STRING)) ? View.GONE : View.VISIBLE);

        btnNegative.setOnClickListener(v -> {
            if (mOnNegativeListener != null) {
                mOnNegativeListener.onNegativeListener();
            }
            dismiss();
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (getActivity() != null && getActivity().isFinishing()) {
            return;
        }

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    public void setOnPositiveListener(OnPositiveListener onPositiveListener) {
        this.mOnPositiveListener = onPositiveListener;
    }

    public void setOnNegativeListener(OnNegativeListener onNegativeListener) {
        this.mOnNegativeListener = onNegativeListener;
    }

    public interface OnPositiveListener {
        void onPositiveListener();
    }

    public interface OnNegativeListener {
        void onNegativeListener();
    }

}
