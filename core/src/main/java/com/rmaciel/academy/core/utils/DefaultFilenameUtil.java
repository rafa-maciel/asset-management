package com.rmaciel.academy.core.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public abstract class DefaultFilenameUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String build() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }
}
