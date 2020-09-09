package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.bookmark.BookmarkValue;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;

import java.util.List;

public class BookmarkResponse implements Parcelable {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("success")
    @Expose
    private Boolean succes;

    @SerializedName("value")
    @Expose
    private BookmarkValue bookmarkValue;

    @SerializedName("values")
    @Expose
    private List<BookmarkValues> bookmarkValues;

    public BookmarkResponse(){

    }

    public BookmarkResponse(String error, String message, int status, Boolean succes, BookmarkValue bookmarkValue, List<BookmarkValues> bookmarkValues) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.bookmarkValue = bookmarkValue;
        this.bookmarkValues = bookmarkValues;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public BookmarkValue getBookmarkValue() {
        return bookmarkValue;
    }

    public void setBookmarkValue(BookmarkValue bookmarkValue) {
        this.bookmarkValue = bookmarkValue;
    }

    public List<BookmarkValues> getBookmarkValues() {
        return bookmarkValues;
    }

    public void setBookmarkValues(List<BookmarkValues> bookmarkValues) {
        this.bookmarkValues = bookmarkValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(message);
        dest.writeInt(status);
        dest.writeByte((byte) (succes == null ? 0 : succes ? 1 : 2));
    }

    protected BookmarkResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Creator<BookmarkResponse> CREATOR = new Creator<BookmarkResponse>() {
        @Override
        public BookmarkResponse createFromParcel(Parcel in) {
            return new BookmarkResponse(in);
        }

        @Override
        public BookmarkResponse[] newArray(int size) {
            return new BookmarkResponse[size];
        }
    };


}
