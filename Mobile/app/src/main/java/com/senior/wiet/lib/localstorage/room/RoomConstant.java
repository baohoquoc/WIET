package com.senior.wiet.lib.localstorage.room;

/**
 * Created by lance on 18/February/2020.
 */
public class RoomConstant {

    static final int DB_VERSION = 2;

    static final String DB_NAME = "fitnyum";

    public static class Tables {
        public static final String User = "user";
    }

    public static class User {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String NAME = "name";
    }
}
