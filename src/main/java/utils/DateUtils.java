package utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author KCWang
 * @version 1.0
 * @date 2024/1/2 上午10:36
 */
public class DateUtils {

    public static void main(String[] args) {

        /**
         * LocalDate
         */
        // A.获取
        //（1）获取当前日期 2022-04-20
        System.out.println(LocalDate.now());
        //（2）获取指定日期 2014-03-18
        System.out.println(LocalDate.of(2014, 3, 18));
        //（3）获取日期的年份 2022
        System.out.println(LocalDate.now().getYear());
        //（4）获取日期的月份 4
        System.out.println(LocalDate.now().getMonthValue());
        //（5）获取日期的日子 20
        System.out.println(LocalDate.now().getDayOfMonth());
        //（6）获取日期的星期 WEDNESDAY
        System.out.println(LocalDate.now().getDayOfWeek());
        //（7）当天所在这一年的第几天 110
        System.out.println(LocalDate.now().getDayOfYear());
        //（8）获取当年天数 365
        System.out.println(LocalDate.now().lengthOfYear());
        //（9）获取当月天数 30
        System.out.println(LocalDate.now().lengthOfMonth());
        //（10）与时间纪元（1970年1月1日）相差的天数，负数表示在时间纪元之前多少天 19102
        System.out.println(LocalDate.now().toEpochDay());

        // B.运算
        //（1）加一天
        System.out.println("加1天：" + LocalDate.now().plusDays(1));
        //（2）加一周
        System.out.println("加1周：" + LocalDate.now().plusWeeks(1));
        //（3）加一月
        System.out.println("加1月：" + LocalDate.now().plusMonths(1));
        //（4）加一年
        System.out.println("加1年：" + LocalDate.now().plusYears(1));
        //（5）减一天
        System.out.println("减1天：" + LocalDate.now().minusDays(1));
        //（6）减一周
        System.out.println("减1周：" + LocalDate.now().minusWeeks(1));
        //（7）减一月
        System.out.println("减1月：" + LocalDate.now().minusMonths(1));
        //（8）减一年
        System.out.println("减1年：" + LocalDate.now().minusYears(1));

        // C.替换
        //（1）替换年份
        System.out.println("替换年份为1：" + LocalDate.now().withYear(1));
        //（2）替换月份
        System.out.println("替换月份为1：" + LocalDate.now().withMonth(1));
        //（3）替换日子
        System.out.println("替换日期为1：" + LocalDate.now().withDayOfMonth(1));
        //（4）替换天数
        System.out.println("替换天数为1：" + LocalDate.now().withDayOfYear(1));

        // D.比较
        //（1）是否在当天之前
        System.out.println("是否在当天之前：" + LocalDate.now().minusDays(1).isBefore(LocalDate.now()));
        //（2）是否在当天之后
        System.out.println("是否在当天之后：" + LocalDate.now().plusDays(1).isAfter(LocalDate.now()));
        //（3）是否在当天
        System.out.println("是否在当天：" + LocalDate.now().isEqual(LocalDate.now()));
        //（4）是否是闰年
        System.out.println("今年是否是闰年：" + LocalDate.now().isLeapYear());

        /**
         *LocalTime
         */

            // A.获取
            //（1）获取默认时区的当前时间 14:11:31.294
                    System.out.println(LocalTime.now());
            //（2）获取指定时区的当前时间 14:11:31.392
                    System.out.println(LocalTime.now(ZoneId.of("Asia/Shanghai")));
            //（3）从指定时钟获取当前时间 14:11:31.392
                    System.out.println(LocalTime.now(Clock.systemDefaultZone()));
            //（4）指定获取时分秒
                    System.out.println(LocalTime.of(12, 30, 30));
            //（5）指定获取时分
                    System.out.println(LocalTime.of(12, 30));
            //（6）指定获取时分秒纳秒
                    System.out.println(LocalTime.of(12, 30, 30, 123));
            //（7）获取小时字段
                    System.out.println("时: " + LocalTime.now().getHour());
            //（8）获取分钟字段
                    System.out.println("时: " + LocalTime.now().getMinute());
            //（9）获取秒字段
                    System.out.println("时: " + LocalTime.now().getSecond());
            //（10）获取纳秒字段
                    System.out.println("时: " + LocalTime.now().getNano());

            // B.计算
            //（1）增加一小时
                    System.out.println("增加1小时: " + LocalTime.now().plusHours(1));
            //（2）增加三十分钟
                    System.out.println("增加30分钟: " + LocalTime.now().plusMinutes(30));
            //（3）增加三十秒
                    System.out.println("增加30秒: " + LocalTime.now().plusSeconds(30));
            //（4）增加一万纳秒
                    System.out.println("增加10000纳秒:" + LocalTime.now().plusNanos(10000));
            //（5）减少一小时
                    System.out.println("减少1小时: " + LocalTime.now().minusHours(1));
            //（6）减少三十分钟
                    System.out.println("减少30分钟: " + LocalTime.now().minusMinutes(30));
            //（7）减少三十秒
                    System.out.println("减少30秒: " + LocalTime.now().minusSeconds(30));
            //（8）减少一万纳秒
                    System.out.println("减少10000纳秒:" + LocalTime.now().minusNanos(10000));

            // C.比较
            //（1）时间与另一个时间比较 0（相等）正数（大）负数（小）
                    System.out.println(LocalTime.now().compareTo(LocalTime.now()));
            //（2）检查时间是否在指定时间之后
                    System.out.println(LocalTime.now().isAfter(LocalTime.now()));
            //（3）检查时间是否在指定时间之前
                    System.out.println(LocalTime.now().isBefore(LocalTime.now()));

        /**
         * LocalDateTime
         */

        // A.获取
            //（1）获取默认时区的当前日期时间
                    System.out.println(LocalDateTime.now());
            //（2）获取指定时区的当前日期时间
                    System.out.println(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
            //（3）从指定时钟获取当前日期时间
                    System.out.println(LocalDateTime.now(Clock.systemDefaultZone()));
            //（4）根据日期和时间对象获取LocalDateTime实例
                    System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
            //（5）根据指定的年、月、日、时、分、秒获取LocalDateTime实例
                    System.out.println(LocalDateTime.of(2019, 12, 7, 21, 48, 50));
            //（6）获取年份
                    System.out.println("年 : " + LocalDateTime.now().getYear());
            //（7）使用月份枚举类获取月份
                    System.out.println("月（英文） : " + LocalDateTime.now().getMonth());
            //（8) 使用月份数字类获取月份
                    System.out.println(" 月（数字英文）: " + LocalDateTime.now().getMonth().getValue());
            //（9）获取日期在该月是第几天
                    System.out.println("天 : " + LocalDateTime.now().getDayOfMonth());
            //（10）获取日期是星期几（英文）
                    System.out.println("星期几（英文） : " + LocalDateTime.now().getDayOfWeek());
            //（11）获取日期是星期几（数字英文）
                    System.out.println("星期几（数字英文） : " + LocalDateTime.now().getDayOfWeek().getValue());
            //（12）获取日期在该年是第几天
                    System.out.println("本年的第几天 : " + LocalDateTime.now().getDayOfYear());
            //（13）获取小时
                    System.out.println("时: " + LocalDateTime.now().getHour());
            //（14）获取分钟
                    System.out.println("分: " + LocalDateTime.now().getMinute());
            //（15）获取秒
                    System.out.println("秒: " + LocalDateTime.now().getSecond());
            //（16）获取纳秒
                    System.out.println("纳秒: " + LocalDateTime.now().getNano());
            //（17）获取日期部分
                    System.out.println(LocalDateTime.now().toLocalDate());
            //（18）获取时间部分
                    System.out.println(LocalDateTime.now().toLocalTime());

            // B.计算
            //（1）增加天数
                    System.out.println("增加天数 : " + LocalDateTime.now().plusDays(1));
            //（2）增加周数
                    System.out.println("增加周数 : " + LocalDateTime.now().plusWeeks(1));
            //（3）增加月数
                    System.out.println("增加月数 : " + LocalDateTime.now().plusMonths(1));
            //（4）增加年数
                    System.out.println("增加年数 : " + LocalDateTime.now().plusYears(1));
            //（5）减少天数
                    System.out.println("减少天数 : " + LocalDateTime.now().minusDays(1));
            //（6）减少月数
                    System.out.println("减少月数 : " + LocalDateTime.now().minusMonths(1));
            //（7）减少周数
                    System.out.println("减少周数 : " + LocalDateTime.now().minusWeeks(1));
            //（8）减少年数
                    System.out.println("减少年数 : " + LocalDateTime.now().minusYears(1));
            //（9）增加小时
                    System.out.println("增加1小时: " + LocalDateTime.now().plusHours(1));
            //（10）增加分钟
                    System.out.println("增加30分钟: " + LocalDateTime.now().plusMinutes(30));
            //（11）增加秒数
                    System.out.println("增加30秒: " + LocalDateTime.now().plusSeconds(30));
            //（12）增加纳秒
                    System.out.println("增加10000纳秒:" + LocalDateTime.now().plusNanos(10000));
            //（13）减少小时
                    System.out.println("减少1小时:" + LocalDateTime.now().minusHours(1));
            //（14）减少分钟
                    System.out.println("减少30分钟:" + LocalDateTime.now().minusMinutes(30));
            //（15）减少秒数
                    System.out.println("减少30秒: " + LocalDateTime.now().minusSeconds(30));
            //（16）减少纳秒
                    System.out.println("减少10000纳秒:" + LocalDateTime.now().minusNanos(10000));

            // C.比较
            //（1）判断日期时间是否相等
                    System.out.println(LocalDateTime.now().isEqual(LocalDateTime.now()));
            //（2）检查是否在指定日期时间之前
                    System.out.println(LocalDateTime.now().isBefore(LocalDateTime.now()));
            //（3）检查是否在指定日期时间之后
                    System.out.println(LocalDateTime.now().isAfter(LocalDateTime.now()));


        /**
         * LocalDate转化为LocalDateTime
         */
        //        LocalDateTime localDateTime = LocalDateTime.now();
        //        LocalDate localDate = localDateTime.toLocalDate();
        //        System.out.println(localDate);

        //（2）LocalDate转化为LocalDateTime
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime1 = localDate.atStartOfDay();
        LocalDateTime localDateTime2 = localDate.atTime(8,20,33);
        LocalDateTime localDateTime3 = localDate.atTime(LocalTime.now());

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);

        /**
         * LocalDateTime转化为Date
         */

            //（1）LocalDateTime转化为Date
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

            //（2）Date转化为LocalDateTime
        Date todayDate = new Date();
        LocalDateTime ldt = todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(ldt);


        /**
         *   localDate转化为Date
         */
        //（1）localDate转化为Date
        //                LocalDate localDate = LocalDate.now();
        //                ZoneId zoneId = ZoneId.systemDefault();
//                        Date date = Date.from(localDate.atStartOfDay().atZone(zoneId).toInstant());
        //                System.out.println(date);

        //（2）Date转化为localDate
                Date date1 = new Date();
                ZoneId zoneId1 = ZoneId.systemDefault();
                LocalDate localDate1 = date1.toInstant().atZone(zoneId1).toLocalDate();
                System.out.println(localDate1);

        /**
         * LocalDate 与 String 之间的转换
         */
        //（1）从文本字符串获取LocalDate实例
        LocalDate localdate = LocalDate.parse("2022-04-21");
        System.out.println(localdate);

        //（2）使用特定格式化形式从文本字符串获取LocalDate实例
//                String str = "2019-03-03";
//                DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate date = LocalDate.parse(str, fmt1);
//                System.out.println(date);

        //（3）使用特定格式化形式将LocalDate转为字符串
                LocalDate today = LocalDate.now();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String dateStr = today.format(fmt);
                System.out.println(dateStr);

        /**
         * LocalTime 与 String 之间的转换
         */
            //// （1）字符串转为LocalTime
            //        LocalTime localdate = LocalTime.parse("12:01:02");
            //        System.out.println(localdate);
            //
            //// （2）使用特定格式化将字符串转为LocalTime
            //        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("HH:mm:ss");
            //        LocalTime date = LocalTime.parse("12:01:02", fmt1);
            //        System.out.println(date);
            //
            //// （3）LocalTime转为字符串
            //        LocalTime toTime = LocalTime.now();
            //        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            //        String dateStr = toTime.format(fmt);
            //        System.out.println(dateStr);

        /**
         * LocalDateTime 与 String 之间的转换
         */

            //        //（1）字符串转为LocalDateTime
            //        LocalDateTime ldt2 = LocalDateTime.parse("2019-12-07T21:20:06.303995200");
            //        System.out.println(ldt2);
            //
            ////（2）使用特定格式化将字符串转为LocalDateTime
            //        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //        LocalDateTime ldt3 = LocalDateTime.parse("2019-12-07 21:20:06", df1);
            //        System.out.println(ldt3);
            //
            ////（3）LocalDateTime转为字符串
            //        LocalDateTime today = LocalDateTime.now();
            //        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            //        String dateStr = today.format(fmt);
            //        System.out.println(dateStr);


















    }


    /**
     * LocalDateTime 与 LocalDate 之间的转换
     */
    private void localDateTimeToLocalDate(){

    }

}
