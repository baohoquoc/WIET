package com.senior.wiet.lib.localstorage.room.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.localstorage.room.RoomConstant;

/**
 * Created by lance on 18/February/2020.
 */
@Entity(tableName = RoomConstant.Tables.User)
public class User implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = RoomConstant.User.ID)
    @SerializedName("id")
    @Expose
    private int id;

    @ColumnInfo(name = RoomConstant.User.USERNAME)
    @SerializedName("username")
    @Expose
    private String username;

    @ColumnInfo(name = RoomConstant.User.NAME)
    @SerializedName("name")
    @Expose
    private String name;

    public User(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    @Ignore
    public User(){
    }

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
