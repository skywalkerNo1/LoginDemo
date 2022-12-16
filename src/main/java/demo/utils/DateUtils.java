package demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtils {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");

    public static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据日趋获取 星期几
     *
     * @param date
     * @return
     */
    public static int getWeekDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }

    /**
     * 获取当前月    0:1月
     *
     * @return
     */
    public static int getCurrMonth() {
        Calendar currCal = Calendar.getInstance();
        return currCal.get(Calendar.MONTH);
    }


    /**
     * 获取当前季度  春\夏\秋\冬
     *
     * @return
     */
    public static String getCurrQuarter() {
        int currentMonth = getCurrMonth();
        String quater = "";
        switch (currentMonth) {
            case 12:
            case 1:
            case 2:
                quater = "春";
                break;
            case 3:
            case 4:
            case 5:
                quater = "夏";
                break;
            case 6:
            case 7:
            case 8:
                quater = "秋";
                break;
            case 9:
            case 10:
            case 11:
                quater = "冬";
                break;
            default:
                break;
        }

        return quater;
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);

        return calendar.getTime();
    }

    /**
     * 得到某月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static Date getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取本周的第一天
     *
     * @return
     */
    public static Date getNowWeekBegin() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        return currentDate.getTime();
    }

    /**
     * 获取本周日日期
     *
     * @return
     */
    public static Date getNowWeekEnd() {
        Calendar cal = Calendar.getInstance();
        // 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 增加一个星期，才是我们中国人理解的本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        return cal.getTime();
    }

    /**
     * 获取当天的 yyyy-mm-dd 00:00:00
     *
     * @return Date
     */
    public static Date getNowDayStart() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当天的 yyyy-mm-dd 23:59:59
     *
     * @return Date
     */
    public static Date getNowDayEnd() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 再日期上做加减天数
     *
     * @param date
     * @param days 天数 可以为负数
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 再日期上做加减月数
     *
     * @param date
     * @param month 月数 可以为负数
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * 再日期上做加减年
     *
     * @param date
     * @param years 年数 可以为负数
     * @return
     */
    public static Date addYear(Date date, int years) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);// 属性很多也有月等等，可以操作各种时间日期
        return c.getTime();
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurYear() {
        String yearStr = format(new Date(), "yyyy");
        return Integer.parseInt(yearStr);
    }

    /**
     * 格式化日期 格式为yyyy-MM-dd
     */
    public static String formatYmd(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat3.format(date);
    }

    /**
     * 格式化日期 格式为 HH:mm:ss
     */
    public static String formatHms(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat2.format(date);
    }

    /**
     * 格式化日期 格式为yyyy-MM-dd HH:mm:ss
     */
    public static String formatYmdHms(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化日期 格式为yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 格式化日期 格式为yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getCurrentMillisecond() {
        Date now = new Date();
        return now.getTime();

    }

    public static long getDifferentMillisecond(long start, long end) {
        return end - start;

    }

    // Patterns
    /**
     * 1970
     */
    public static final String PATTERN_YYYY = "yyyy";
    /**
     * 197001
     */
    public static final String PATTERN_YYYYMM = "yyyyMM";
    /**
     * 1970-01
     */
    public static final String PATTERN_YYYY_MM = "yyyy-MM";
    /**
     * 19700101
     */
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    /**
     * 1970-01-01
     */
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 19700101010101
     */
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * 1970-01-01 01:01:01
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 1970-01-01 01:01:01.123
     */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 获取日期时间。
     *
     * @param source 日期时间字符串
     * @return 日期时间
     */
    public static Date parse(String source) {
        String pattern;
        switch (source.length()) {
            case 4:
                pattern = PATTERN_YYYY;
                break;
            case 6:
                pattern = PATTERN_YYYYMM;
                break;
            case 7:
                pattern = PATTERN_YYYY_MM;
                break;
            case 8:
                pattern = PATTERN_YYYYMMDD;
                break;
            case 10:
                pattern = PATTERN_YYYY_MM_DD;
                break;
            case 14:
                pattern = PATTERN_YYYYMMDDHHMMSS;
                break;
            case 19:
                pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
                break;
            case 23:
                pattern = PATTERN_YYYY_MM_DD_HH_MM_SS_SSS;
                break;
            default:
                pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
                break;
        }
        return parse(source, pattern);
    }

}
