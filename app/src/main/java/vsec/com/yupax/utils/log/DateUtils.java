package vsec.com.yupax.utils.log;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



/**
 * Created by nguyenthanhdong0109@gmail.com on 8/24/16.
 * I win.
 */
public class DateUtils {

    public static Date getDateBeforeTime(Date date, int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -time); // 2 weeks
        return calendar.getTime();
    }

    public static Date getDateFromString(String str) {
        Date theDate = null;
        try {
            theDate = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH)
                    .parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return theDate;
    }

    public static String getSimpleDateTime(String jsonDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        String s = jsonDate.replace("Z", "+00:00");
        String result = "";
        try {
            Date date = sdf.parse(s);
            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public static String getSimpleDateForComment(String jsonDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        String s = jsonDate.replace("Z", "+00:00");
        String result = "";
        try {
            Date date = sdf.parse(s);
            sdf = new SimpleDateFormat("MM/dd/yy");
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public static String getCurrentDate() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(currentTime);
        return sdf.format(date);
    }

    public static String getCurrentDateForTransfer() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy, hh.mm a");
        Date date = new Date(currentTime);
        return sdf.format(date);
    }

    public static String getSimpleDate(String jsonDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a", Locale.US);
        String result = "";
        try {
            Date date = sdf.parse(jsonDate);
            sdf = new SimpleDateFormat("dd/MM");
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }


    public static String getCurrentTime() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        Date date = new Date(currentTime);

        return sdf.format(date);
    }

    public static String getCurrentTimeForOnline() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");//2016-11-21T01:07:25.684Z
        Date date = new Date(currentTime);

        return sdf.format(date);
    }

    public static String getTimeString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }


    public static String getDateFromCal(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static String getDateTimeFromCal(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static Calendar getCalendarTimeFromString(String date, Context context) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            cal.setTime(sdf.parse(date));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }


    public static String getTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(calendar.getTime());
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    }

    public static String convertMinusFromMilliSecond(long milliseconds, Context context) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);

        if (minutes == 0) {
            return seconds + " seconds";
        } else {
            return minutes + " minutes" + " " + seconds + " seconds";
        }
    }

    public static String converTimeFromLong(long minisecond) {
        return new SimpleDateFormat("MM/dd/yyyy hh:mm").format(new Date(minisecond));
    }

    public static String converTimeFromLongForBirthDay(long minisecond) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(minisecond));
    }
    public static Long convertTimeFromString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        return millis;
    }
}
