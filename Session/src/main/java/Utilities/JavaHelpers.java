package Utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class JavaHelpers
{
    public String getCurrentTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }

    public String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i <n; i++) {

            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public String getFuturePastCurrentDateAndTime(int date,int time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.DATE, date);
        c.add(Calendar.MINUTE, time);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);

    }

    public String getRandomNumber(int n)
    {

        String Numeric = "0123456789";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i <n; i++) {

            int index
                    = (int)(Numeric.length()
                    * Math.random());
            sb.append(Numeric
                    .charAt(index));
        }

        return sb.toString();
    }
}
