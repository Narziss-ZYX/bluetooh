package com.jcsim;
//RealTimeChart .java

import com.formdev.flatlaf.FlatDarkLaf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;


public class DrawChart extends ChartPanel implements Runnable {
    private  TimeSeries timeSeries;
    private  float yaxisNum = 0.0f;
    private  String Line;
    private ArrayList<RecData> datas;
    private long value = 0;
    private Number[] src;
    public static DrawChart createDrawChart(Number[]src,ArrayList<RecData> datas,String chartContent, String title, String yaxisName){
        TimeSeries timeSeries = new TimeSeries(chartContent);
        return new DrawChart(src,datas,timeSeries,chartContent,title,yaxisName);
    }
    public DrawChart(Number[]src,ArrayList<RecData> datas,TimeSeries timeSeries,String chartContent, String title, String yaxisName) {
        super(createChart(timeSeries, title, yaxisName));
        Line = chartContent;
        this.timeSeries = timeSeries;
        this.datas = datas;
        this.src = src;
        for(int i = 0;i<this.datas.size();i++)
        {
            timeSeries.addOrUpdate(datas.get(i).ms,datas.get(i).data);
        }
//        for (RecData data : this.datas){
//            timeSeries.addOrUpdate(data.ms,data.data);
//        }
    }


//    public static DrawChart createDrawChart(float[] src,String chartContent, String title, String yaxisName){
//        TimeSeries timeSeries = new TimeSeries(chartContent);
//        return new DrawChart(timeSeries,chartContent,title,yaxisName);
//    }
//    public DrawChart(TimeSeries timeSeries,String chartContent, String title, String yaxisName) {
//        super(createChart(timeSeries, title, yaxisName));
//        Line = chartContent;
//        this.timeSeries = timeSeries;
//        datas = new ArrayList<>();
//    }

    private static JFreeChart createChart(TimeSeries timeSeries, String title, String yaxisName) {
//创建时序图对象


        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);

        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "time(minutes)", yaxisName, timeseriescollection, true, true, false);

        XYPlot xyplot = jfreechart.getXYPlot();

//纵坐标设定

        ValueAxis valueaxis = xyplot.getDomainAxis();

//自动设置数据轴数据范围

        valueaxis.setAutoRange(true);

//数据轴固定数据范围 30s

        valueaxis.setFixedAutoRange(30000D);

        valueaxis = xyplot.getRangeAxis();

//valueaxis.setRange(0.0D,200D);
//        DateAxis dateAxis = (DateAxis)xyplot.getDomainAxis();;
//        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MILLISECOND,10, new SimpleDateFormat("S")));
//        dateAxis.setRange(0,300);

        return jfreechart;

    }

    public void run() {
        while (true) {
            try {

//                configNum(Line);
                RecData data = new RecData( src[0],new Millisecond());
                timeSeries.addOrUpdate(data.ms,data.data);
                datas.add(data);
                while (datas.size() >= 500){
                    datas.remove(0);
                }
                Thread.sleep(100);

            } catch (InterruptedException e) {
            }

        }

    }

//    private  void configNum(String str) {
//        switch (str) {
//            case "temp":
//                yaxisNum = ExtractNum.temp;
//                break;
//            case "ax":
//                yaxisNum = ExtractNum.ax;
//                break;
//            case "ay":
//                yaxisNum = ExtractNum.ay;
//                break;
//            case "az":
//                yaxisNum = ExtractNum.az;
//                break;
//            case "gx":
//                yaxisNum = ExtractNum.gx;
//                break;
//            case "gy":
//                yaxisNum = ExtractNum.gy;
//                break;
//            case "gz":
//                yaxisNum = ExtractNum.gz;
//                break;
//            case "fAX":
//                yaxisNum = ExtractNum.fAX;
//                break;
//            case "fAY":
//                yaxisNum = ExtractNum.fAY;
//                break;
//            case "fAZ":
//                yaxisNum = ExtractNum.fAZ;
//                break;
//            default:
//                break;
//        }
//    }

    private long randomNum() {

        return (long) (Math.random() * 20 + 80);

    }


//    public static void main(String[] args) {
//        FlatDarkLaf.install();
//        JFrame frame = new JFrame("Test Chart");
//
//        DrawChart rtcp = createDrawChart("Random Data", "Random", "value");
//
//        frame.getContentPane().add(rtcp, new BorderLayout().CENTER);
//
//        frame.pack();
//
//        frame.setVisible(true);
//
//        (new Thread(rtcp)).start();
//
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent windowevent) {
//                System.exit(0);
//
//            }
//
//        });
//
//    }

}




