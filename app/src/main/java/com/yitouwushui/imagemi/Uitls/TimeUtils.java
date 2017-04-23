package com.yitouwushui.imagemi.Uitls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yitouwushui on 2017/4/23.
 */

public class TimeUtils {
    /**
     * 将长整型转换为时间字符串
     *
     * @param l 时间戳
     * @return
     */
    public static String getDateAndTimeString(Long l) {
        Date date = new Date();
        String time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date.setTime(l);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将长整型转换为时间字符串
     *
     * @param l 时间戳
     * @return
     */
    public static String getDateToTime(Long l) {
        Date date = new Date();
        String time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date.setTime(l);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
    /**
     * 获取时间日期部分
     *
     * @param time
     * @return
     */
    public String getDateString(Long time) {
        Date date = new Date();
        String data = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
            date.setTime(time);
            data = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

//    /**
//     * 获取时间日期部分
//     *
//     * @param time
//     * @return
//     */
//    public long getDateString(long time) {
//        Date date = new Date();
//        String data = "";
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
//            date.setTime(time);
//            data = sdf.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }

    /**
     * 获取时间时间部分
     *
     * @param t
     * @return
     */
    public String getTimeString(Long t) {
        Date date = new Date();
        String data = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            date.setTime(t);
            data = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @return
     */
    public long getAddMinuteTime(Date time, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime().getTime();
    }



}
