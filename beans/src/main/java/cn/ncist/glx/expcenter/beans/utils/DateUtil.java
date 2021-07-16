package cn.ncist.glx.expcenter.beans.utils;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个用于转换日期格式的工具类
 */
public class DateUtil {

    /**
     * 计算日期相差周数
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getWeak(String startDate, String endDate) {

        LocalDate date1 = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        long daysDiff = ChronoUnit.WEEKS.between(date1, date2);

        String x ="第" + (daysDiff + 1) + "周周" + date2.getDayOfWeek().getValue();
        //System.out.println(x);

        return x;


    }

    /**
     * 日期转为String
     * @param date
     * @return
     */
    public static String DateToString(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        //System.out.println(format);
        return format;
    }

    /**
     * String转日期
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(date);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取日期是周几
     * @param date
     * @return
     */
    public static int dateToWeak(Date date){
        LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return date1.getDayOfWeek().getValue();

    }


    public static String numberToWeak(Integer number){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"1-2节");
        map.put(2,"3-4节");
        map.put(3,"5-6节");
        map.put(4,"7-8节");
        map.put(5,"9-10节");

        return map.get(number);
    }

    public static Integer weakToNumber(String weak){
        Map<String,Integer> map = new HashMap<>();
        map.put("1-2节",1);
        map.put("3-4节",2);
        map.put("5-6节",3);
        map.put("7-8节",4);
        map.put("9-10节",5);

        return map.get(weak);
    }

}
