package com.nchenari.news.utils;

import com.google.firebase.crash.FirebaseCrash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nchenari on 12/6/17.
 */

public class DateUtils {

    public static String formatNewsApiDate(String inputDate) {
        // check if input is null so that we don't get a NullPointerException
        if(inputDate == null)
            return null;

        try {
            String inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            String outputDateFormat = "EEE, d MMM yyyy KK:mm";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);


            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            // add crash reporting through Firebase
            FirebaseCrash.report(e);
        }
        return inputDate;
    }
}
