package com.senior.wiet.lib.localstorage.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.utilities.Constants;

/**
 * Created by lance on 18/February/2020.
 */
public class AppSharedPreference {

    private static final String TOKEN = "access_token";
    private static final String FCM_TOKEN = "fcm_token";
    private static final String USERNAME = "username";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String AVATAR = "avatar";
    private static final String CREATED_AT = "created_at";
    private static final String DOB = "dob";
    private static final String EMAIL = "email";
    private static final String FULL_NAME = "full_name";
    private static final String IS_FIRST_LOGIN = "is_first_login";
    private static final String IS_VEGETARIAN = "is_vegetarian";
    private static final String UID = "uid";
    private static final String USERLOCATION = "user_location";
    private static final String DEVICELOCATION = "device_location";
    private static final String REAL_LONG = "real_longitude";
    private static final String REAL_LAT = "real_latitude";
    private static final String STORE_NAME = "store_name";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String PREF_NAME = "wiet_sharedPref";
    private static final String TEMP = "temp";
    private static final String BMEAL = "bmeal";
    private static final String LMEAL = "lmeal";
    private static final String DMEAL = "dmeal";
    private static final String TMEAL = "tmeal";
    private static final String BIMAGE = "bimage";
    private static final String LIMAGE = "limage";
    private static final String DIMAGE = "dimage";
    private static final String TIMAGE = "timage";
    private static final String BLAT = "blat";
    private static final String LLAT = "llat";
    private static final String DLAT = "dlat";
    private static final String TLAT = "tlat";
    private static final String BLON = "blon";
    private static final String LLON = "llon";
    private static final String DLON = "dlon";
    private static final String TLON = "tlon";
    private static final String BID = "bid";
    private static final String LID = "lid";
    private static final String DID = "did";
    private static final String TID = "tid";
    private static final String FDNAME = "fd_name";
    private static final String FDPRICE = "fd_price";
    private static final String FDSTORENAME = "fd_store_name";
    private static final String FDADDRESS = "fd_address";
    private static final String FDLAT = "fd_lat";
    private static final String FDLON = "fd_lon";
    private static final String FDFOODID = "fd_food_id";
    private static final String FDIMAGE = "fd_image";
    private static final String FDISBOOKMARK = "fd_is_bookmark";
    private static final String FOODID = "food_id";
    private static AppSharedPreference instance;
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private static RecommendValues food;

    public AppSharedPreference(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public static AppSharedPreference getInstance(Context context) {
        if (instance == null) {
            instance = new AppSharedPreference(context);
        }
        return instance;
    }

    public String getToken() {
        return pref.getString(TOKEN, Constants.EMPTY_STRING);
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getFcmtoken() {
        return pref.getString(FCM_TOKEN, Constants.EMPTY_STRING);
    }

    public void setFcmtoken(String fcmtoken) {
        editor.putString(FCM_TOKEN, fcmtoken);
        editor.apply();
    }


    public String getUserame() {
        return pref.getString(USERNAME, Constants.EMPTY_STRING);
    }

    public void setUsername(String token) {
        editor.putString(USERNAME, token);
        editor.apply();
    }


    public String getAvatar() {
        return pref.getString(AVATAR, Constants.EMPTY_STRING);
    }

    public void setAvatar(String avatar) {
        editor.putString(AVATAR, avatar);
        editor.apply();
    }

    public String getCreatedAt() {
        return pref.getString(CREATED_AT, Constants.EMPTY_STRING);
    }

    public void setCreatedAt(String created_at) {
        editor.putString(CREATED_AT, created_at);
        editor.apply();
    }

    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, Constants.EMPTY_STRING);
    }

    public void setAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public String getDob() {
        return pref.getString(DOB, Constants.EMPTY_STRING);
    }

    public void setDob(String dob) {
        editor.putString(DOB, dob);
        editor.apply();
    }

    public double getRealLat() {
        return (double) (pref.getFloat(REAL_LAT, (float) Constants.FLOAT));
    }

    public void setRealLat(double realLate) {
        editor.putFloat(REAL_LAT, (float) realLate);
        editor.apply();
    }

    public double getRealLong() {
        return (double) (pref.getFloat(REAL_LONG, (float) Constants.FLOAT));
    }

    public void setRealLong(double realLong) {
        editor.putFloat(REAL_LONG, (float) realLong);
        editor.apply();
    }

    public String getFullName() {
        return pref.getString(FULL_NAME, Constants.EMPTY_STRING);
    }

    public void setFullName(String fullName) {
        editor.putString(FULL_NAME, fullName);
        editor.apply();
    }

    public String getEmail() {
        return pref.getString(EMAIL, Constants.EMPTY_STRING);
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public Boolean getIsFirstLogin() {
        return pref.getBoolean(IS_FIRST_LOGIN, Constants.TRUE_FALSE);
    }

    public void setIsFirstLogin(Boolean isFirstLogin) {
        editor.putBoolean(IS_FIRST_LOGIN, isFirstLogin);
        editor.apply();
    }

    public Boolean getIsVegetarian() {
        return pref.getBoolean(IS_VEGETARIAN, Constants.TRUE_FALSE);
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        editor.putBoolean(IS_VEGETARIAN, isVegetarian);
        editor.apply();
    }

    public String getUid() {
        return pref.getString(UID, Constants.EMPTY_STRING);
    }

    public void setUid(String uid) {
        editor.putString(UID, uid);
        editor.apply();
    }

    public String getStoreName() {
        return pref.getString(STORE_NAME, Constants.EMPTY_STRING);
    }

    public void setStoreName(String storeName) {
        editor.putString(STORE_NAME, storeName);
        editor.apply();
    }

    public String getUserLocation() {
        return pref.getString(USERLOCATION, Constants.EMPTY_STRING);
    }

    public void setUserLocation(String location) {
        editor.putString(USERLOCATION, location);
        editor.apply();
    }

    public String getDevicelocation() {
        return pref.getString(DEVICELOCATION, Constants.EMPTY_STRING);
    }

    public void setDevicelocation(String location) {
        editor.putString(DEVICELOCATION, location);
        editor.apply();
    }

    public float getLatitude() {
        return pref.getFloat(LATITUDE, Constants.FLOAT);
    }

    public void setLatitude(float latitude) {
        editor.putFloat(LATITUDE, latitude);
        editor.apply();
    }

    public float getLongitude() {
        return pref.getFloat(LONGITUDE, Constants.FLOAT);
    }

    public void setLongitude(float longitude) {
        editor.putFloat(LONGITUDE, longitude);
        editor.apply();
    }

    public static RecommendValues getFood() {
        return food;
    }

    public static void setFood(RecommendValues food) {
        AppSharedPreference.food = food;
    }

    public void clearSession() {
        editor.remove(TOKEN);
        editor.remove(FCM_TOKEN);
        editor.remove(USERNAME);
        editor.remove(ACCESS_TOKEN);
        editor.remove(AVATAR);
        editor.remove(CREATED_AT);
        editor.remove(DOB);
        editor.remove(EMAIL);
        editor.remove(FULL_NAME);
        editor.remove(IS_FIRST_LOGIN);
        editor.remove(IS_VEGETARIAN);
        editor.remove(UID);
        editor.remove(USERLOCATION);
        editor.remove(REAL_LONG);
        editor.remove(REAL_LAT);
        editor.remove(STORE_NAME);
        editor.remove(LATITUDE);
        editor.remove(LONGITUDE);
        editor.clear();
        editor.commit();
    }

    public int getTemp(){return pref.getInt(TEMP, Constants.INT);}

    public void setTemp(int temp){
        editor.putInt(TEMP, temp);
        editor.apply();
    }

    public String getBmeal(){
        return pref.getString(BMEAL, Constants.EMPTY_STRING);
    }

    public void setBmeal(String bmeal){
        editor.putString(BMEAL, bmeal);
        editor.apply();
    }

    public String getLmeal(){
        return pref.getString(LMEAL, Constants.EMPTY_STRING);
    }

    public void setLmeal(String lmeal){
        editor.putString(LMEAL, lmeal);
        editor.apply();
    }

    public String getDmeal(){
        return pref.getString(DMEAL, Constants.EMPTY_STRING);
    }

    public void setDmeal(String dmeal){
        editor.putString(DMEAL, dmeal);
        editor.apply();
    }

    public String getTmeal(){
        return pref.getString(TMEAL, Constants.EMPTY_STRING);
    }

    public void setTmeal(String tmeal){
        editor.putString(TMEAL, tmeal);
        editor.apply();
    }

    public String getBimage(){
        return pref.getString(BIMAGE, Constants.EMPTY_STRING);
    }

    public void setBimage(String bimage){
        editor.putString(BIMAGE, bimage);
        editor.apply();
    }

    public String getLimage(){
        return pref.getString(LIMAGE, Constants.EMPTY_STRING);
    }

    public void setLimage(String limage){
        editor.putString(LIMAGE, limage);
        editor.apply();
    }

    public String getDimage(){
        return pref.getString(DIMAGE, Constants.EMPTY_STRING);
    }

    public void setDimage(String dimage){
        editor.putString(DIMAGE, dimage);
        editor.apply();
    }

    public String getTimage(){
        return pref.getString(TIMAGE, Constants.EMPTY_STRING);
    }

    public void setTimage(String timage){
        editor.putString(TIMAGE, timage);
        editor.apply();
    }

    public float getBLatitude(){
        return pref.getFloat(BLAT, Constants.FLOAT);
    }

    public void setBLatitude(float bLatitude){
        editor.putFloat(BLAT, bLatitude);
        editor.apply();
    }
    public float getLLatitude(){
        return pref.getFloat(LLAT, Constants.FLOAT);
    }

    public void setLLatitude(float lLatitude){
        editor.putFloat(LLAT, lLatitude);
        editor.apply();
    }
    public float getDLatitude(){
        return pref.getFloat(DLAT, Constants.FLOAT);
    }

    public void setDLatitude(float dLatitude){
        editor.putFloat(DLAT, dLatitude);
        editor.apply();
    }
    public float getTLatitude(){
        return pref.getFloat(TLAT, Constants.FLOAT);
    }

    public void setTLatitude(float tLatitude){
        editor.putFloat(TLAT, tLatitude);
        editor.apply();
    }
    public float getBLongitude(){
        return pref.getFloat(BLON, Constants.FLOAT);
    }

    public void setBLongitude(float bLongitude){
        editor.putFloat(BLON, bLongitude);
        editor.apply();
    }
    public float getLLongitude(){
        return pref.getFloat(LLON, Constants.FLOAT);
    }

    public void setLLongitude(float lLongitude){
        editor.putFloat(LLON, lLongitude);
        editor.apply();
    }
    public float getDLongitude(){
        return pref.getFloat(DLON, Constants.FLOAT);
    }

    public void setDLongitude(float dLongitude){
        editor.putFloat(DLON, dLongitude);
        editor.apply();
    }
    public float getTLongitude(){
        return pref.getFloat(TLON, Constants.FLOAT);
    }

    public void setTLongitude(float tLongitude){
        editor.putFloat(TLON, tLongitude);
        editor.apply();
    }

    public int getBid(){return pref.getInt(BID, Constants.INT);}

    public void setBid(int bid){
        editor.putInt(BID, bid);
        editor.apply();
    }

    public int getLid(){return pref.getInt(LID, Constants.INT);}

    public void setLid(int lid){
        editor.putInt(LID, lid);
        editor.apply();
    }

    public int getDid(){return pref.getInt(DID, Constants.INT);}

    public void setDid(int did){
        editor.putInt(DID, did);
        editor.apply();
    }

    public int getTid(){return pref.getInt(TID, Constants.INT);}

    public void setTid(int tid){
        editor.putInt(TID, tid);
        editor.apply();
    }

    public String getFdname(){
        return pref.getString(FDNAME, Constants.EMPTY_STRING);
    }

    public void setFdname(String fdname){
        editor.putString(FDNAME, fdname);
        editor.apply();
    }

    public int getFdprice(){return pref.getInt(FDPRICE, Constants.INT);}

    public void setFdprice(int fdprice){
        editor.putInt(FDPRICE, fdprice);
        editor.apply();
    }

    public String getFdstorename(){
        return pref.getString(FDSTORENAME, Constants.EMPTY_STRING);
    }

    public void setFdstorename(String fdstorename){
        editor.putString(FDSTORENAME, fdstorename);
        editor.apply();
    }

    public String getFdaddress(){
        return pref.getString(FDADDRESS, Constants.EMPTY_STRING);
    }

    public void setFdaddress(String fdaddress){
        editor.putString(FDADDRESS, fdaddress);
        editor.apply();
    }

    public String getFdimage(){
        return pref.getString(FDIMAGE, Constants.EMPTY_STRING);
    }

    public void setFdimage(String fdimage){
        editor.putString(FDIMAGE, fdimage);
        editor.apply();
    }

    public int getFdfoodid(){return pref.getInt(FDFOODID, Constants.INT);}

    public void setFdfoodid(int fdfoodid){
        editor.putInt(FDFOODID, fdfoodid);
        editor.apply();
    }

    public float getFdlat(){
        return pref.getFloat(FDLAT, Constants.FLOAT);
    }

    public void setFdlat(float fdlat){
        editor.putFloat(FDLAT, fdlat);
        editor.apply();
    }

    public float getFdlon(){
        return pref.getFloat(FDLON, Constants.FLOAT);
    }

    public void setFdlon(float fdlon){
        editor.putFloat(FDLON, fdlon);
        editor.apply();
    }

    public int getFoodid(){return pref.getInt(FOODID, Constants.INT);}

    public void setFoodid(int foodid){
        editor.putInt(FOODID, foodid);
        editor.apply();
    }

    public Boolean getFdisbookmark() {
        return pref.getBoolean(FDISBOOKMARK, Constants.TRUE_FALSE);
    }

    public void setFdisbookmark(Boolean fdisbookmark) {
        editor.putBoolean(FDISBOOKMARK, fdisbookmark);
        editor.apply();
    }


}
