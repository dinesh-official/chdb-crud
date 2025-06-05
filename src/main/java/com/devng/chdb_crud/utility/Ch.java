package com.devng.chdb_crud.utility;

public class Ch {

    private static final String URL = "jdbc:clickhouse://216.48.176.217:8123/default";
    private static final String USER = "default";
    private static final String PASSWORD = "";

    private Ch() {
        // Prevent instantiation
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
