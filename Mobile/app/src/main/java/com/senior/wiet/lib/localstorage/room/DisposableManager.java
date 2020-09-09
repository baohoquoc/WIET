package com.senior.wiet.lib.localstorage.room;

import android.util.Log;

import com.google.gson.Gson;
import com.senior.wiet.lib.response.AllergyResponse;
import com.senior.wiet.lib.response.BookmarkResponse;
import com.senior.wiet.lib.response.DeleteAllergyResponse;
import com.senior.wiet.lib.response.FoodDetailResponse;
import com.senior.wiet.lib.response.GetAllergyResponse;
import com.senior.wiet.lib.response.GetMetaTagsByTagIDResponse;
import com.senior.wiet.lib.response.HistoryCreateResponse;
import com.senior.wiet.lib.response.HistoryDeleteResponse;
import com.senior.wiet.lib.response.HistoryGetListResponse;
import com.senior.wiet.lib.response.InformationResponse;
import com.senior.wiet.lib.response.LastLocationResponse;
import com.senior.wiet.lib.response.LoginResponse;
import com.senior.wiet.lib.response.MealResponse;
import com.senior.wiet.lib.response.MetaTagResponse;
import com.senior.wiet.lib.response.RatingResponse;
import com.senior.wiet.lib.response.RecommendResponse;
import com.senior.wiet.lib.response.StoreResponse;
import com.senior.wiet.lib.response.SurveyResponse;
import com.senior.wiet.lib.response.SurveySearchTagResponse;
import com.senior.wiet.lib.response.TagResponse;
import com.senior.wiet.lib.response.TopRatingResponse;
import com.senior.wiet.lib.response.UnBookmarkResponse;
import com.senior.wiet.lib.response.WeatherResponse;
import com.senior.wiet.utilities.Utilities;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by lance on 18/February/2020.
 */
public class DisposableManager {

    @Inject
    public DisposableManager() {

    }

    public Disposable login(Observable<Response<LoginResponse>> responseObservable, IDisposableListener<LoginResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<LoginResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<LoginResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("hehe", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable survey(Observable<Response<SurveyResponse>> responseObservable, IDisposableListener<SurveyResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<SurveyResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<SurveyResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("survey_response", new Gson().toJson(value.body()));
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable ratingInSurvey(Observable<Response<RatingResponse>> responseObservable, IDisposableListener<RatingResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<RatingResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<RatingResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("rating_response", new Gson().toJson(value.body()));
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable getMetaTagsByTagID(Observable<Response<GetMetaTagsByTagIDResponse>> responseObservable, IDisposableListener<GetMetaTagsByTagIDResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<GetMetaTagsByTagIDResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<GetMetaTagsByTagIDResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("get_meta_tag_by_tag_id_response", new Gson().toJson(value.body()));
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable searchTagInSurvey(Observable<Response<SurveySearchTagResponse>> responseObservable, IDisposableListener<SurveySearchTagResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<SurveySearchTagResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<SurveySearchTagResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("search_in_survey", new Gson().toJson(value.body()));
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable recommend(Observable<Response<RecommendResponse>> responseObservable, IDisposableListener<RecommendResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<RecommendResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<RecommendResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable bookmark(Observable<Response<BookmarkResponse>> responseObservable, IDisposableListener<BookmarkResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<BookmarkResponse>>() {
                    @Override
                    public void onNext(Response<BookmarkResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("get_meta_tag_by_tag_id_response", new Gson().toJson(value.body()));
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Disposable information(Observable<Response<InformationResponse>> responseObservable, IDisposableListener<InformationResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<InformationResponse>>() {
                    @Override
                    public void onNext(Response<InformationResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable searchTag(Observable<Response<TagResponse>> responseObservable, IDisposableListener<TagResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<TagResponse>>() {
                    @Override
                    public void onNext(Response<TagResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable searchMetaTags(Observable<Response<MetaTagResponse>> responseObservable, IDisposableListener<MetaTagResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<MetaTagResponse>>() {
                    @Override
                    public void onNext(Response<MetaTagResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable unbookmark(Observable<Response<UnBookmarkResponse>> responseObservable, IDisposableListener<UnBookmarkResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<UnBookmarkResponse>>() {
                    @Override
                    public void onNext(Response<UnBookmarkResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable store(Observable<Response<StoreResponse>> responseObservable, IDisposableListener<StoreResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<StoreResponse>>() {
                    @Override
                    public void onNext(Response<StoreResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable weather(Observable<Response<WeatherResponse>> responseObservable, IDisposableListener<WeatherResponse> listener){
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<WeatherResponse>>() {
                    @Override
                    public void onNext(Response<WeatherResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Log.d("BBB", ""+ value.body().getTemperatureModel().getTemp());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable mealtoday(Observable<Response<MealResponse>> responseObservable, IDisposableListener<MealResponse> listener){
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<MealResponse>>() {
                    @Override
                    public void onNext(Response<MealResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable getTopRatingDishes(Observable<Response<TopRatingResponse>> responseObservable, IDisposableListener<TopRatingResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<TopRatingResponse>>() {
                    @Override
                    public void onNext(Response<TopRatingResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });

    }


    public Disposable allergy(Observable<Response<AllergyResponse>> responseObservable, IDisposableListener<AllergyResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<AllergyResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<AllergyResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable getAllergy(Observable<Response<GetAllergyResponse>> responseObservable, IDisposableListener<GetAllergyResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<GetAllergyResponse>>() {
                    @Override
                    public void onNext(Response<GetAllergyResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable deleteAllergy(Observable<Response<DeleteAllergyResponse>> responseObservable, IDisposableListener<DeleteAllergyResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<DeleteAllergyResponse>>() {
                    @Override
                    public void onNext(Response<DeleteAllergyResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Utilities.Log("Success", value.body().toString());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public Disposable bookmarks(Observable<Response<BookmarkResponse>> responseObservable, IDisposableListener<BookmarkResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<BookmarkResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<BookmarkResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                            Log.d("TEST", value.message());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }
    public Disposable foodDetail(Observable<Response<FoodDetailResponse>> responseObservable, IDisposableListener<FoodDetailResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<FoodDetailResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<FoodDetailResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable getListHistory(Observable<Response<HistoryGetListResponse>> responseObservable, IDisposableListener<HistoryGetListResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<HistoryGetListResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<HistoryGetListResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable createHistory(Observable<Response<HistoryCreateResponse>> responseObservable, IDisposableListener<HistoryCreateResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<HistoryCreateResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<HistoryCreateResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }

    public Disposable deleteHistory(Observable<Response<HistoryDeleteResponse>> responseObservable, IDisposableListener<HistoryDeleteResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<HistoryDeleteResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<HistoryDeleteResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }


    public Disposable lastLocation(Observable<Response<LastLocationResponse>> responseObservable, IDisposableListener<LastLocationResponse> listener) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<LastLocationResponse>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(Response<LastLocationResponse> value) {
                        if (value.isSuccessful()) {
                            listener.onHandleData(value.body());
                        } else {
                            listener.onRequestWrongData(value.code(), value.message());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
    }


}
