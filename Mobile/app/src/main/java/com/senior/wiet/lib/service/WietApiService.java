package com.senior.wiet.lib.service;

import com.senior.wiet.lib.model.Account;
import com.senior.wiet.lib.model.Allergy;
import com.senior.wiet.lib.model.InformationRequest;
import com.senior.wiet.lib.model.Location;
import com.senior.wiet.lib.model.RatingRequest;
import com.senior.wiet.lib.model.bookmark.FoodId;
import com.senior.wiet.lib.model.history.HistoryCreateRequest;
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

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lance on 18/February/2020.
 */
public interface WietApiService {

    /*
     * URL to check email
     * @param email need email paramater
     * @return Observable
     */
    @POST("/api/v1.0/auth")
    Observable<Response<LoginResponse>> login(@Body Account account);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("/api/v1.0/user")
    Observable<Response<InformationResponse>> information(@Header("Authorization") String auth, @Body InformationRequest informationRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/survey")
    Observable<Response<SurveyResponse>> survey(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/tags")
    Observable<Response<TagResponse>> getTags(@Header("Authorization") String auth,
                                              @Query("limit") int limit, @Query("offset") int offset,
                                              @Query("search") String word);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/survey/search")
    Observable<Response<SurveySearchTagResponse>> searchTagsInSurvey(@Header("Authorization") String auth,
                                                                     @Query("limit") int limit, @Query("offset") int offset,
                                                                     @Query("search") String word);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/v1.0/rating")
    Observable<Response<RatingResponse>> rating(@Header("Authorization") String auth,
                                                @Body RatingRequest request);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/v1.0/meta_tags/<tag_id>")
    Observable<Response<GetMetaTagsByTagIDResponse>> getMetaTagByTagID(@Header("Authorization") String auth,
                                                                       @Path("tag_id") int tag_id);

    @GET("/api/v1.0/store/{store_id}")
    Observable<Response<StoreResponse>> store(@Path("store_id") int store_id, @Header("Authorization") String accessToken);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/v1.0/bookmark")
    Observable<Response<BookmarkResponse>> bookmark(@Body FoodId food_id, @Header("Authorization") String accessToken);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @DELETE("/api/v1.0/bookmark/{food_id}")
    Observable<Response<UnBookmarkResponse>> unbookmark(@Path("food_id") int food_id, @Header("Authorization") String accessToken);

    @GET("/api/v1.0/tags")
    Observable<Response<TagResponse>> searchTags(@Query("limit") String limit, @Query("search") String search, @Header("Authorization") String header);

    @GET("/api/v1.0/meta_tags")
    Observable<Response<MetaTagResponse>> searchMetaTags(@Header("Authorization") String header, @Query("search") String search, @Query("limit") String limit);

    @GET("/api/v1.0/recommend")
    Observable<Response<RecommendResponse>> recommend(@Header("Authorization") String token, @Query("location") String location);

    @GET("/api/v1.0/bookmarks")
    Observable<Response<BookmarkResponse>> bookmarks(@Header("Authorization") String token);

    @POST("/api/v1.0/allergy")
    Observable<Response<AllergyResponse>> allergy(@Header("Authorization") String header, @Body Allergy allergy);

    @GET("/api/v1.0/allergies")
    Observable<Response<GetAllergyResponse>> getAllergy(@Header("Authorization") String header);

    @DELETE("/api/v1.0/allergy/{allergy_id}")
    Observable<Response<DeleteAllergyResponse>> deleteAllergy(@Header("Authorization") String header, @Path("allergy_id") Integer allergyID);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/rating/top")
    Observable<Response<TopRatingResponse>> getTopRatingDishes(@Header("Authorization") String auth, @Query("top") int top, @Query("offset") int offset);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/foods")
    Observable<Response<TopRatingResponse>> searchDish(@Header("Authorization") String auth, @Query("search") String search, @Query("limit") int limit);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/mealtoday")
    Observable<Response<MealResponse>> mealtoday(@Header("Authorization") String token, @Query("temperature") int temp);

    @GET("/api/v1.0/foods/{food_id}")
    Observable<Response<FoodDetailResponse>> foodDetail(@Path("food_id") int food_id, @Header("Authorization") String accessToken);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PATCH("/api/v1.0/save_last_location")
    Observable<Response<LastLocationResponse>> lastLocation(@Header("Authorization") String token, @Body Location location);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/api/v1.0/histories")
    Observable<Response<HistoryGetListResponse>> getListHistory(@Header("Authorization") String token);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @DELETE("/api/v1.0/history/{history_id}")
    Observable<Response<HistoryDeleteResponse>> deleteHistory(@Header("Authorization") String token, @Path("history_id") int history_id);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api/v1.0/history")
    Observable<Response<HistoryCreateResponse>> createHistory(@Header("Authorization") String token, @Body HistoryCreateRequest food_id);
}
