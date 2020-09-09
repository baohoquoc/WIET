package com.senior.wiet.fragments.recommend;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.databinding.FragmentRecommendBinding;
import com.senior.wiet.fragments.BaseFragment;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.RecommendModel;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.viewholder.RecommendItemAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;


public class RecommendFragment extends BaseFragment<RecommendContract.Presenter> implements RecommendContract.View {

    @Inject
    RecommendPresenter mRecommendPresenter;

    @Inject
    @Named("vertical")
    LinearLayoutManager mLinearLayoutManager;


    RecommendItemAdapter mRecommendItemAdapter;

    public View view;
    private FragmentRecommendBinding binding;
    public static List<RecommendValues> list;
    private AlertDialog mAlertDialog;
    private int index = 0;
    private Boolean showAndHide = false;

    @Inject
    public RecommendFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        binding = DataBindingUtil.bind(view);
        //mRecommendPresenter = new RecommendPresenter(this);
        //Objects.requireNonNull(binding).setPresenter(mRecommendPresenter);
        //compare location
        String userlocation = AppSharedPreference.getInstance(getContext()).getUserLocation();
        String devicelocation = AppSharedPreference.getInstance(getContext()).getDevicelocation();
        //AppSharedPreference.getInstance(getContext()).getDevicelocation();
        Log.d("locaton", devicelocation);
        if (!userlocation.equalsIgnoreCase(devicelocation) && devicelocation != null && devicelocation != "") {
            String mes = getString(R.string.dialoglocation);//"hai dia diem khac nhau";

            mAlertDialog = new AlertDialog().createDialog(mes, devicelocation, getContext().getString(R.string.btncancel));
            mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
            mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                @Override
                public void onNegativeListener() {
                    mAlertDialog.dismiss();
                }
            });
            mAlertDialog.setOnPositiveListener(new AlertDialog.OnPositiveListener() {
                @Override
                public void onPositiveListener() {
                    //Toast.makeText(getContext(), "compare location", Toast.LENGTH_LONG).show();
                    mRecommendPresenter.mRecommendModel.lastLocation(devicelocation);
                    //Utilities.Log("list_recommend", new Gson().toJson(list));
                    mAlertDialog.dismiss();
                }
            });
            mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
            return view;
        } else {
            return view;
        }


    }

    @Override
    public void init() {
        //Utilities.Log("list_recommend", new Gson().toJson(list));
        showItemFoodByIndex(index);
        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mRecommendPresenter.onPreviousClick();
                if (list == null || list.size() == 0) {
                    String mes = getString(R.string.mAlertDialog);
                    mAlertDialog = new AlertDialog().createDialog(mes,
                            Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                } else {
                    if (index > 0) {
                        showItemFoodByIndex(--index);
                    } else {
                        showItemFoodByIndex(0);
                    }
                }
            }
        });
        binding.layoutbtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list == null || list.size() == 0) {
                    String mes = getString(R.string.mAlertDialog);
                    mAlertDialog = new AlertDialog().createDialog(mes,
                            Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                } else {
                    if (index > 0) {
                        showItemFoodByIndex(--index);
                    } else {
                        showItemFoodByIndex(0);
                    }
                }
            }
        });

        binding.layoutbtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list == null || list.size() == 0) {
                    String mes = getString(R.string.mAlertDialog);
                    mAlertDialog = new AlertDialog().createDialog(mes,
                            Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                } else {
                    if (index < list.size()) {
                        showItemFoodByIndex(++index);
                    } else {
                        showItemFoodByIndex(index);
                        index = 0;
                    }
                }
            }
        });

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mRecommendPresenter.onSkipClick();
                if (list == null || list.size() == 0) {
                    String mes = getString(R.string.mAlertDialog);
                    mAlertDialog = new AlertDialog().createDialog(mes,
                            Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                } else {
                    if (index < list.size()) {
                        showItemFoodByIndex(++index);
                    } else {
                        showItemFoodByIndex(index);
                        index = 0;
                    }
                }
            }
        });
        binding.btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceItemFoodByIndex();
            }
        });
        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list == null || list.size() == 0) {
                    String mes = getString(R.string.mAlertDialog);
                    mAlertDialog = new AlertDialog().createDialog(mes,
                            Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                } else {
                    if (showAndHide == false) {
                        binding.layoutrecommned.setVisibility(View.VISIBLE);
                        mRecommendItemAdapter = new RecommendItemAdapter(getContext(), list);
                        binding.recommendRecyclerview.setLayoutManager(mLinearLayoutManager);
                        binding.recommendRecyclerview.setHasFixedSize(true);
                        binding.recommendRecyclerview.setAdapter(mRecommendItemAdapter);
                        showAndHide = true;
                    } else {
                        binding.layoutrecommned.setVisibility(View.INVISIBLE);
                        showAndHide = false;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();

    }

    @Override
    public void attachModel(RecommendModel recommendModel) {
        binding.setModel(recommendModel);
    }

    @Override
    public FragmentRecommendBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {

    }

    @Override
    public void showItemFoodByIndex(int indexx) {
        this.index = indexx;
        if (list == null || list.size() == 0) {
            String mes = getString(R.string.mAlertDialog);

            mAlertDialog = new AlertDialog().createDialog(mes,
                    Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
            mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
            mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
            //Toast.makeText(getContext(), "List null", Toast.LENGTH_SHORT).show();
        } else {
            String foodName = "";
            String path = "";
            long price = 0;
            double distance = 0;
            if (index < list.size()) {
                path = list.get(index).getImage();
                foodName = list.get(index).getName();
                price = list.get(index).getPrice();
                double rLat = AppSharedPreference.getInstance(getContext()).getRealLat();
                double rLon = AppSharedPreference.getInstance(getContext()).getRealLong();
                //float lat1 = (float) 16.061363;
                //float lon1 = (float) 108.176593;
                float lat2 = (float) list.get(index).getLatitude();
                float lon2 = (float) list.get(index).getLongitude();
                distance = distance((float) rLat, (float) rLon, lat2, lon2) / 1000;
                binding.txtnamefood.setText(foodName);
                String lang = Resources.getSystem().getConfiguration().locale.getLanguage();
                if (lang.equalsIgnoreCase("en")) {
                    binding.txtPrice.setText("VND " + NumberFormat.getInstance(Locale.US).format(price));
                } else {
                    binding.txtPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(price) + " VNĐ");
                }
                binding.txtDistance.setText(String.format("%.1f", distance) + " " + getContext().getString(R.string.km_from_here));
                Glide.with(getContext()).load(path).into(binding.imgfood);
            } else {
                String mess = getString(R.string.alert_endofrecomment);
                String mess1 = getString(R.string.btnok);
                mAlertDialog = new AlertDialog().createDialog(mess,
                        Constants.EMPTY_STRING, mess1);
                mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
                mAlertDialog.setOnPositiveListener(new AlertDialog.OnPositiveListener() {
                    @Override
                    public void onPositiveListener() {
                        //mRecommendPresenter.setIndexItem(0);
                        mAlertDialog.dismiss();
                        index = 0;
                        showItemFoodByIndex(0);
                    }
                });
            }

        }
    }

    @Override
    public void choiceItemFoodByIndex() {
        if (list == null || list.size() == 0) {
            String mess = getString(R.string.mAlertDialog);
            mAlertDialog = new AlertDialog().createDialog(mess,
                    Constants.EMPTY_STRING, getContext().getString(R.string.btn_ok));
            mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
            mAlertDialog.show(getFragmentManager(), AlertDialog.class.getSimpleName());
            //Toast.makeText(getContext(), "List null", Toast.LENGTH_SHORT).show();
        } else {
            int id = 0;
            if (index < list.size()) {
                id = list.get(index).getId();
            } else {
                id = list.get(4).getId();
            }
            //Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
            //binding.getModel().createHistory(AppSharedPreference.getInstance(getContext()).getAccessToken(), food_id);
            mRecommendPresenter.createHistory(id);
            Intent intent = new Intent(getContext(), FoodActivity.class);
            intent.putExtra(Constants.FOOD_ID, id);
            startActivity(intent);

        }
    }

    public List<RecommendValues> getList() {
        return list;
    }

    public void setList(List<RecommendValues> list) {
        this.list = list;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public double distance(float lat1, float lon1, float lat2, float lon2) {
        double R = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000;
    }

}
