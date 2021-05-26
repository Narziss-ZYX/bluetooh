package com.jcsim;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractNum {
    //全局变量
    
    public static Number[] temp = new Number[1];
    public static Number[] ax= new Number[1], ay= new Number[1], az = new Number[1]; //加速度
    public static Number[] gx= new Number[1], gy= new Number[1], gz = new Number[1]; //角速度
    public static Number[] fAX= new Number[1], fAY= new Number[1], fAZ = new Number[1]; // 三个姿态角（pitch俯仰角, roll滚转角, yaw偏航角）
    public static int Warn;
    public static int g_mpustep; //震动检测灵敏度
    public static int g_warntime; //报警时长
    public static int tempLmt; //震动检测灵敏度
    public static int g_upstep; //上传时间
    //下发
    public static int config_mpustep;
    public static int config_warntime; //报警时长
    public static int config_tempLmt; //震动检测灵敏度
    public static int config_upstep; //上传时间
    public static int g_bUpingFlag = 0;
    //控制上传标志位
    public static boolean StartButtonFlag = false;
    public static boolean CloseButtonFlag = false;
    //设置参数标志位
    public static boolean ConfigNumFlag = false;

    public static void initNum(){
        temp[0]= 0;
        ax[0] =  0;
        ay[0] =  0;
        az[0] =  0;
        gx[0] =  0;
        gy[0] =  0;
        gz[0] =  0;
        fAX[0] = 0;
        fAY[0] = 0;
        fAZ[0] = 0;
    }
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

    /***
     * 去除String数组中的空值
     */
    public static String[] deleteArrayNull(String string[]) {
        String strArr[] = string;

        // step1: 定义一个list列表，并循环赋值
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0; i < strArr.length; i++) {
            strList.add(strArr[i]);
        }

        // step2: 删除list列表中所有的空值
        while (strList.remove(null)) ;
        while (strList.remove("")) ;

        // step3: 把list列表转换给一个新定义的中间数组，并赋值给它
        String strArrLast[] = strList.toArray(new String[strList.size()]);

        return strArrLast;
    }

    public static void ProcNumString(String str) {
        if (str.charAt(0) == 'T' && str.length() >= 60) {
            String[] list = deleteArrayNull(str.split("[:, \n]"));
            for (int i = 0; i < list.length; ++i) {
                switch (list[i]) {
                    case "T" -> {
                        temp[0] = Float.parseFloat(list[i + 1]);
                        ++i;
                    }
                    case "A" -> {
                        ax[0] = Short.parseShort(list[i + 1]);
                        ay[0] = Short.parseShort(list[i + 2]);
                        az[0] = Short.parseShort(list[i + 3]);
                        i += 3;
                    }
                    case "G" -> {
                        gx[0] = Short.parseShort(list[i + 1]);
                        gy[0] = Short.parseShort(list[i + 2]);
                        gz[0] = Short.parseShort(list[i + 3]);
                        i += 3;
                    }
                    case "Z" -> {
                        fAX[0] = Float.parseFloat(list[i + 1]);
                        fAY[0] = Float.parseFloat(list[i + 2]);
                        fAZ[0] = Float.parseFloat(list[i + 3]);
                        i += 3;
                    }
                    case "S" -> {
                        tempLmt = Integer.parseInt(list[i + 1]);
                        g_mpustep = Integer.parseInt(list[i + 2]);
                        g_warntime = Integer.parseInt(list[i + 3]);
                        g_upstep = Integer.parseInt(list[i + 4]);
                        i += 4;
                    }
                    case "W" -> {
                        Warn = Integer.parseInt(list[i + 1]);
                        ++i;
                    }
                }
            }
        }
    }



}
