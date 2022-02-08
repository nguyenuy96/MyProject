package com.app.utils;

import java.util.Date;

public class MyDateTime {

    private MyDateTime() {

    }

    public static Date longToDate(long timeAsLong) {
        return new Date(timeAsLong);
    }
}
