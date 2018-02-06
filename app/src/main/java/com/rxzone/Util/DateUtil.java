package com.rxzone.Util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateUtil {
    public static final int SECOND_MILLIS = 1000;

    public static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;

    public static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;

    public static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String dateToEpoch(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }

    public static String dateOnlyAccess(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static long dob(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }


    public static String ageCal(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static String epochToDate(long epoch) {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(epoch));
    }

    public static String fetchGraphDate(long epoch) {
        return new SimpleDateFormat("MMM yy").format(new Date(epoch));
    }


    public static String colepochToDate(long epoch) {
        return new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(new Date(epoch));
    }

    public static String dateTimeToEpoch(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static long colTime(String enteredDate) {

        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }


    public static long timeequlality(String enteredDate) {
        enteredDate.replace("AM", "am");
        enteredDate.replace("PM", "pm");
        Log.e("entereddate", enteredDate + "");
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }

    public static String coldateC(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }

    public static String currentDate() {
        String updateDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simDf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            updateDate = simDf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateDate;
    }

    public static double mintsTohoursCovert(String minutes) {
        return Double.parseDouble(minutes) / 60.0;
    }

    public static String mintsToCoverthoursMin(String minutes) {
        double hoursAndMin = Double.parseDouble(minutes) / 60.0;
        String[] strarray = String.format("%.2f", hoursAndMin).split("\\.");
        return String.valueOf(Integer.parseInt(strarray[0]) + " Hrs "
                + Integer.parseInt(strarray[1]) + " Min").toString();
    }


    public static String currentTime() {
        String currentTime = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        try {
            currentTime = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTime;
    }

    public static String serverSentDateChange(String serverDate) {
        Log.e("serverDate", serverDate);
        String changeFormatDate = "";
        try {
            String bar = serverDate.toString();
            serverDate = bar.substring(0, 10);
            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd");
            Date yourDate = simDf.parse(serverDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yourDate);
            calendar.get(Calendar.YEAR); //Day of the Year :)
            calendar.get(Calendar.DATE); //Day of the day :)
            int orderMonth = calendar.get(Calendar.MONTH) + 1;
            changeFormatDate = calendar.get(Calendar.DATE)
                    + "-" + orderMonth
                    + "-" + calendar.get(Calendar.YEAR);
            Log.e("changedate", changeFormatDate + "");

        } catch (Exception e) {

        }
        return changeFormatDate;
    }

    public static String serverSentTimeChange(String servertime) {
        Log.e("serverDate", servertime);
        String chnagesTime = "";
        try {
            String output = servertime.toString().replace("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date now = sdf.parse(output); //new Date();
            chnagesTime = sdf.format(now);
            System.out.println(chnagesTime);
            return chnagesTime;
        } catch (Exception e) {

        }
        return chnagesTime;
    }

    public static String currentdateandTime() {
        String chnagesTime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date now = new Date();
            chnagesTime = sdf.format(now);
            System.out.println(chnagesTime);
            return chnagesTime;
        } catch (Exception e) {

        }
        return chnagesTime;
    }

    //



    /*
    public static String serverSentDateChange(String serverDate) {
        Log.e("serverDate", serverDate);
        String changeFormatDate = "";
        try {
            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd");
            Date yourDate = simDf.parse(serverDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yourDate);
            calendar.get(Calendar.YEAR); //Day of the Year :)
            calendar.get(Calendar.DATE); //Day of the day :)
            int orderMonth = calendar.get(Calendar.MONTH) + 1;
            changeFormatDate = calendar.get(Calendar.DATE)
                    + "-" + orderMonth
                    + "-" + calendar.get(Calendar.YEAR);
            Log.e("changedate", changeFormatDate + "");

        } catch (Exception e) {

        }
        return changeFormatDate;
    }*/


    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime()
                    - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static String getTimeAgo(long time) {
        long now = (long) 0.0;
        if (time < 1000000000000L) {
            time *= 1000;
        }
        try {
            //1515502800000
            //1515065235277

            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String currentDateTime = DateUtil.currentdateandTime();
            Date currentDateTimedate = simDf.parse(currentDateTime);
            now = currentDateTimedate.getTime();
        } catch (Exception e) {

        }


//        long now = currentmillSec;// System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }
        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static String getTimeLeft(long time) {
        long now = (long) 0.0;
        if (time < 1000000000000L) {
            time *= 1000;
        }
        try {
            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String currentDateTime = DateUtil.currentdateandTime();
            Date currentDateTimedate = simDf.parse(currentDateTime);
            now = currentDateTimedate.getTime();
        } catch (Exception e) {
        }
        final long diff = time - now;
        if (time > now || time <= 0) {
            if (diff > 48 * HOUR_MILLIS) {
                return diff / DAY_MILLIS + " days Left";
            } else if (diff > 24 * HOUR_MILLIS) {
//                return diff / HOUR_MILLIS + " hours Left";
                return diff / HOUR_MILLIS + " hours Left";
            } else if (diff > 90 * MINUTE_MILLIS) {
                return diff / HOUR_MILLIS + " hours Left";
//                return "an hour Left";
            } else if (diff > 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " minutes Left";
//                return diff / MINUTE_MILLIS + " minutes Left";
            } else if (diff > 2 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " minutes left";
                //return "a minute left";
            } else {
                return diff / DAY_MILLIS + " days Left";
            }
//            return null;
        } else {
            return diff / DAY_MILLIS + " days Left";
        }
        // TODO: localize

    }
}
