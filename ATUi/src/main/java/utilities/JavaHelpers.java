package utilities;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class JavaHelpers {

    //Time-stamps

    /**
     * Get current time-stamp in given format
     *
     * @param format  e.g. "yyyy MMM dd", 'yyyyMMdd_HHmmss' etc.
     * @return String timestamp
     */
    public String getTimeStamp(String format) {
        /*
         * Example format are :
         *
         * "yyyy MMM dd" for "2013 Nov 28"
         *
         * "yyyyMMdd_HHmmss" for "20130131000000"
         *
         * "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
         *
         *
         * "dd MMM yyyy" for "28 Nov 2017"
         */
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Get current time-stamp in "_yyyyMMdd_HHmmss" format
     *
     * @return String timestamp
     */
    public String timeStamp() {
        return getTimeStamp("_yyyyMMdd_HHmmss");
    }

    /**
     * Update time string to required timezone time string
     *
     * @param actualTimeFormat   Time Format for time input
     * @param time
     * @param expectedTimeFormat Time Format we want our result to be
     * @param increamentDate     number by what we need to increment date to
     * @param increamentHour     Amount of time we need to increment hour to
     * @param increamentMinute   Amount of time we need to increment minutes to
     * @param increamentSeconds  Amount of time we need to increment seconds
     *                           to
     * @return String converted time
     * @throws ParseException Example for date format are :
     *                        <p>
     *                        "yyyy MMM dd" for "2013 Nov 28"
     *                        <p>
     *                        "yyyyMMdd_HHmmss" for "20130131000000"
     *                        <p>
     *                        "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
     *                        <p>
     *                        "dd MMM yyyy" for "28 Nov 2017"
     *                        <p>
     *                        <p>
     *                        <p>
     *                        Example for time format:
     *                        <p>
     *                        "HH:mm:ss" for "16:00:00"(24 hr format)
     *                        <p>
     *                        "hh:mm:ss" for "4:00:00"(12 hr format)
     */
    public String updateTime(String actualTimeFormat,
                             String time,
                             String expectedTimeFormat,
                             int increamentDate,
                             int increamentHour,
                             int increamentMinute,
                             int increamentSeconds
    ) throws ParseException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        DateFormat resultDateFormat = new SimpleDateFormat(expectedTimeFormat);
        Date date = new SimpleDateFormat(actualTimeFormat).parse(time + " " + year); // we're parsing current year
        // incase year not passed
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, increamentDate);
        calendar.add(Calendar.HOUR, increamentHour);
        calendar.add(Calendar.MINUTE, increamentMinute);
        calendar.add(Calendar.SECOND, increamentSeconds);
        return resultDateFormat.format(calendar.getTime());
    }

    public boolean isTimeBetween(String from, String to, String checkTime, String format) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        Date date_from = formatter.parse(from);
        Date date_to = formatter.parse(to);
        Date dateNow = formatter.parse(checkTime);
        if (date_from.equals(dateNow) || (date_from.before(dateNow) && date_to.after(dateNow))) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param date    Date
     * @param pattern Date and Time Pattern (Format)
     * @param locale  Locale Region e.g (US, UK)
     * @return
     */
    public String changeLocalDate(Date date, String pattern, Locale locale) {
        String dateString = null;
        SimpleDateFormat formatter = null;
        if (locale == null) {
            formatter = new SimpleDateFormat(pattern);
        } else {
            formatter = new SimpleDateFormat(pattern, locale);
        }

        dateString = formatter.format(date);
        return dateString;
    }


    //Java Methods

    /**
     * Get method name where this method is called
     *
     * @return String method name
     */
    public String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }


    //Data Reader

    /**
     * Get Property value
     *
     * @param propertyfile property file name
     * @param propertyname property name
     * @return String property value
     */
    public static String getPropertyValue(String propertyfile, String propertyname) {
        Properties prop = accessPropertiesFile(propertyfile);
        return prop.getProperty(propertyname);
    }

    /**
     * Access property file
     *
     * @param propertyfile property file name
     * @return Properties object
     */
    public static Properties accessPropertiesFile(String propertyfile) {
        Properties prop = new Properties();
        // try retrieve data from file
        try {
            prop.load(new FileInputStream(propertyfile));
        }
        // catch exception in case properties file does not exist
        catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


    //Folder Operations

    /**
     * Delete all files from given folder
     *
     * @param folderPath folder path
     */
    public void deleteAllFilesFromFolder(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.isDirectory()) {
            return;
        }
        File[] listFiles = dir.listFiles();
        for (File file : listFiles) {
            try {
                file.delete();
            } catch (Exception e) {
                //Exception occurred while deleting a file. We'll still move on.
            }
        }
    }


    //Run Executable file
    public void runExeFile(String filePath) throws IOException, InterruptedException {
        Runtime.getRuntime().exec(filePath);
        Thread.sleep(6000);
    }


    //Data Processing

    /**
     * Is list contains another list
     *
     * @param outer is array string
     * @param inner is array string can compare to array string outer
     */

    public static boolean isListContainsAnotherList(String[] outer, String[] inner) {
        return Arrays.asList(outer).containsAll(Arrays.asList(inner));
    }

    /**
     * extract number from string given
     *
     * @param word is the String that we want to extract
     *             Example : "Rp. 13.000 / bulan" > 13000
     */
    public static int extractNumber(String word) {
        String str = word.replaceAll("[A-Z a-z . /]", "").trim();
        return Integer.parseInt(str);
    }

    /**
     * remove number from string given and trim it
     *
     * @param word is the String that we want to extract
     *             Example : "Min. 2 Bln" > "Min.Bln"
     */
    public static String removeNumber(String word) {
        return word.replaceAll("[0-9 \\s+ .]", "").trim();
    }


    //Reading system properties

    /**
     * Set system variable - set it from system variable first, if not found -set it from properties file
     *
     * @param name                 variable name
     * @param propertyFileLocation properties file location
     * @return variable value
     */
    public static String setSystemVariable(String propertyFileLocation, String name) {
        //Reading from system properties.
        String variable = System.getProperty(name);

        //if not specified via command line, take it from constants.properties file
        if (variable == null || variable.isEmpty()) {
            variable = JavaHelpers.getPropertyValue(propertyFileLocation, name);
        }
        return variable;
    }

    //QR Code

    /**
     * Read Text from QR code
     *
     * @param path QR code document path e.g. ATUi/src/test/resources/QR1.png
     * @return QR code content
     * @throws IOException                        exception
     * @throws com.google.zxing.NotFoundException exception
     */
    public static String readQRCode(String path) throws IOException, com.google.zxing.NotFoundException {
        BinaryBitmap binaryBitmap
                = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                ImageIO.read(
                                        new FileInputStream(path)))));

        Result result = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }


    /**
     * Get user default download path
     *
     * @return download path
     */
    public String getDownloadPath() {
        String home = System.getProperty("user.home");
        return home + "/downloads/";
    }


    /**
     * Verify whether downloaded file is present in download folder
     *
     * @param downloadPath download path
     * @param fileName     file name
     * @return true if downloaded file is present
     */
    public boolean isFilePresent(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (File dir_content : dir_contents) {
            if (dir_content.getName().equals(fileName))
                return true;
        }

        return false;
    }
}