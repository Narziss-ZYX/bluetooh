package com.jcsim;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractNum {
    //全局变量
    public static float temp=0;
    public static short ax, ay, az=0; //加速度
    public static short gx, gy, gz=0; //角速度
    public static float fAX, fAY, fAZ=0; // 三个姿态角（pitch俯仰角, roll滚转角, yaw偏航角）
    public static int Warn;
    public static int g_mpustep; //震动检测灵敏度
    public static int g_warntime; //报警时长
    public static int tempLmt; //震动检测灵敏度
    public static float g_upstep; //上传时间

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * @param
     */
    public static boolean NumberType(String str) {
        //是否为整数
        if ((str.replace("-", "").matches("^[0-9]+$")) || (str.replace("-", "").matches("\\d+.\\d+"))) {
//            System.out.println("整数");
            return true;
        } else
            return false;
//        //是否为小数
//        else if (str.replace("-", "").matches("\\d+.\\d+")) {
//            System.out.println("小数");
//            return false;
//        }
    }
    public static boolean isNumeric(String str) {

        for (int i = str.length(); --i >= 0; ) {

            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void ExtractNumFromString(String str){
        String rgex = "T:(.*?),";
        String[] list;
        String result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        temp = Float.parseFloat(list[list.length - 1]);  //温度数据获取
        //加速度
        rgex = "A:(.*?),";
        result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        int count = 0;
        for (int i = 0; i < list.length; i++) {
            if (NumberType(list[i])) {
                count++;
                if (count == 1)
                    ax = Short.parseShort(list[i]);
                else if (count == 2)
                    ay = Short.parseShort(list[i]);
                else if (count == 3)
                    az = Short.parseShort(list[i]);
            }
        }
        //角速度
        rgex = "G:(.*?),";
        result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        count = 0;
        for (int i = 0; i < list.length; i++) {
            if (NumberType(list[i])) {
                count++;
                if (count == 1)
                    gx = Short.parseShort(list[i]);
                else if (count == 2)
                    gy = Short.parseShort(list[i]);
                else if (count == 3)
                    gz = Short.parseShort(list[i]);
            }
        }
        //姿态角
        rgex = "Z:(.*?),";
        result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        count = 0;
        for (int i = 0; i < list.length; i++) {
            if (NumberType(list[i])) {
                count++;
                if (count == 1)
                    fAX = Float.parseFloat(list[i]);
                else if (count == 2)
                    fAY = Float.parseFloat(list[i]);
                else if (count == 3)
                    fAZ = Float.parseFloat(list[i]);
            }
        }
        //参数
        rgex = "S:(.*?),";
        result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        count = 0;
        for (int i = 0; i < list.length; i++) {
            if (NumberType(list[i])) {
                count++;
                if (count == 1)
                    tempLmt = Integer.parseInt(list[i]);
                else if (count == 2)
                    g_mpustep = Integer.parseInt(list[i]);
                else if (count == 3)
                    g_warntime = Integer.parseInt(list[i]);
                else if (count == 4)
                    g_upstep = Float.parseFloat(list[i]);
            }
        }
        //报警
        rgex = "W:(.*?)\n";
        result = getSubUtilSimple(str, rgex);
        list = result.split(" ");
        Warn = Integer.parseInt(list[list.length - 1]);  //温度数据获取
        System.out.format("T:%4f,A:%6d %6d %6d,G:%6d %6d %6d,Z:%5f %5f %5f,S:%4d %4d %4d %f,W:%d\n", temp, ax, ay, az, gx, gy, gz, fAX, fAY, fAZ,tempLmt,g_mpustep,g_warntime,g_upstep, Warn);
    }



    public static void main(String[] args) {
        String str = "T: 24.9,A:   774   1350  16734,G:    -1     -1      1,Z:   0.1    0.0    0.4,S:  28  4  30  0.1,W:0\n";
        ExtractNumFromString(str);
    }
}
