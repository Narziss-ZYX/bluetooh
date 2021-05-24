package com.jcsim;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BlueToothGUI extends JFrame implements Runnable {
    private java.text.DecimalFormat temp;

    public BlueToothGUI() {
//        this.setSize(900, 500);
//        this.setLocation(500, 100);
//        this.setTitle("蓝牙上位机");
        initUI();
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUI() {
        String[] accelerate_str={"ax","ay","az",String.format("%d",ExtractNum.ax),String.format("%d",ExtractNum.ax),String.format("%d",ExtractNum.ax)};
        String[] angV_str={"gx","gy","gz",String.format("%d",ExtractNum.gx),String.format("%d",ExtractNum.gx),String.format("%d",ExtractNum.gx)};
        String[] altitude_str={"俯仰角","横滚角","航向角",String.format("%.1f",ExtractNum.fAX),String.format("%.1f",ExtractNum.fAY),String.format("%.1f",ExtractNum.fAZ)};

        Border emptyPanl, lineBorder, etchedBorder, matteBorder, titleBorder1, titleBorder2, titleBorderCenter_Left, titleBorderCenter_CENTER, titleBorderBottom_center, titleBorderBelowBottom_Center, titleWarnSta;
        emptyPanl = BorderFactory.createEmptyBorder(10, 10, 10, 10);  //带标题边框

        lineBorder = BorderFactory.createLineBorder(Color.red);
        etchedBorder = BorderFactory.createEtchedBorder();
        matteBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.darkGray);

        titleBorder1 = BorderFactory.createTitledBorder(lineBorder, "title");
        titleBorder2 = BorderFactory.createTitledBorder(etchedBorder, "title");
        titleBorderCenter_Left = BorderFactory
                .createTitledBorder(etchedBorder
                        , "温度", TitledBorder.CENTER
                        , TitledBorder.CENTER);

        titleBorderCenter_CENTER = BorderFactory
                .createTitledBorder(etchedBorder
                        , "三轴加速度", TitledBorder.CENTER
                        , TitledBorder.CENTER);

        titleBorderBottom_center = BorderFactory
                .createTitledBorder(etchedBorder
                        , "三轴角速度", TitledBorder.CENTER
                        , TitledBorder.CENTER);
        titleBorderBelowBottom_Center = BorderFactory
                .createTitledBorder(etchedBorder
                        , "姿态角", TitledBorder.CENTER
                        , TitledBorder.CENTER);
        titleWarnSta = BorderFactory
                .createTitledBorder(etchedBorder
                        , "报警状态", TitledBorder.CENTER
                        , TitledBorder.CENTER);

        JPanel simoleTitleBorder = new JPanel();
        simoleTitleBorder.setBorder(emptyPanl);
        simoleTitleBorder.setLayout(new BoxLayout(simoleTitleBorder, BoxLayout.Y_AXIS));
        addCompForBorder(titleBorder1, "title Border with line Border", simoleTitleBorder);
        addCompForBorder(titleBorder2, "title Border with etche Border", simoleTitleBorder);

        JPanel customTitleBorder = new JPanel();
        customTitleBorder.setBorder(emptyPanl);
        customTitleBorder.setLayout(
                new BoxLayout(customTitleBorder
                        , BoxLayout.Y_AXIS));
//        addCompForBorder(titleBorderCenter_Left
//                ,"标题在左上边且在边框里"
//                ,customTitleBorder);
        GUI_tempPanel(titleBorderCenter_Left, titleWarnSta, customTitleBorder);
        GUI_varyButton(titleBorderCenter_CENTER,customTitleBorder,accelerate_str);
        GUI_varyButton(titleBorderBottom_center,customTitleBorder,angV_str);
        GUI_varyButton(titleBorderBelowBottom_Center,customTitleBorder,altitude_str);
//        addCompForBorder(titleBorderCenter_CENTER
//                , "标题在上边框中间且在边框里"
//                , customTitleBorder);
//        addCompForBorder(titleBorderBottom_center
//                , "标题下边框中间且在边框里"
//                , customTitleBorder);
//        addCompForBorder(titleBorderBelowBottom_Center
//                , "标题在下边框外且在中间"
//                , customTitleBorder);

        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setToolTipText("simple");
        jTabbedPane.addTab("监测数据", customTitleBorder);
        jTabbedPane.addTab("参数设置", simoleTitleBorder);
        this.add(jTabbedPane);
        //this.getContentPane().add(jTabbedPane);
    }

    private void addCompForBorder(Border border, String lable, Container container) {
        JPanel comp = new JPanel(false);
        comp.setLayout(new GridLayout(1, 1));
        comp.setBorder(border);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }

    private static void GUI_tempPanel(Border border, Border titleWarnSta, Container container) {
        //颜色
        Color color1 = new Color(181, 181, 181);  //功能键和运算符颜色
        Color color2 = new Color(126, 192, 238);  //等于号专属颜色
        Color color3 = new Color(232, 232, 232);  //背景颜色

        JPanel contentPane = new JPanel();
        GridLayout gl_contentPane = new GridLayout(0, 2);
        gl_contentPane.setHgap(10);
        contentPane.setLayout(gl_contentPane);

        JPanel tempPanel = new JPanel();
        JButton tempButton = new JButton();
        tempButton.setPreferredSize(new Dimension(70,30));
        tempButton.setText(String.format("%.1f",ExtractNum.temp));
        tempButton.setBackground(color2);
        tempButton.setForeground(Color.black);
        tempButton.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        tempButton.setBorderPainted(false);  //去除按钮的边框
        tempPanel.add(tempButton);
        tempPanel.setBorder(border);

//        JPanel buttonPanel = new JPanel();
//        // 设置 bottomPanel 为水平布局
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS ));
//        buttonPanel.add(tempButton);
//        tempPanel.add(Box.createVerticalStrut (10));
//        tempPanel.add(buttonPanel);
//        tempPanel.add(Box.createVerticalStrut (10));
//        tempPanel.setBorder(border);

        JPanel warnPanel = new JPanel();
        JButton warnButton = new JButton();
        warnButton.setPreferredSize(new Dimension(70,30));
        warnButton.setText(String.format("%d",ExtractNum.Warn));
        warnButton.setBackground(color2);
        warnButton.setForeground(Color.black);
        warnButton.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        warnButton.setBorderPainted(false);  //去除按钮的边框
        warnPanel.add(warnButton);
        warnPanel.setBorder(titleWarnSta);

        contentPane.add(tempPanel);
        contentPane.add(warnPanel);

        container.add(contentPane);
    }

    private static void GUI_acceleratePanel(Border border, Container container, String[] str) {
        //颜色
        Color color1 = new Color(181, 181, 181);  //功能键和运算符颜色
        Color color2 = new Color(126, 192, 238);  //等于号专属颜色
        Color color3 = new Color(232, 232, 232);  //背景颜色
        JPanel acceleratePanel = new JPanel();
        acceleratePanel.setLayout(new BoxLayout(acceleratePanel, BoxLayout.Y_AXIS));  //垂直布局
        JPanel buttonPanel = new JPanel();  //创建包含两个按钮的面板
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); //水平布局
        JButton aButton[] = new JButton[3];

        for (int i = 0; i < aButton.length; i++) {
            aButton[i] = new JButton(str[i]);
            aButton[i].setBackground(color2);
            aButton[i].setForeground(Color.black);
            aButton[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
            aButton[i].setBorderPainted(false);  //去除按钮的边框
            aButton[i].setPreferredSize(new Dimension(100,150));
        }
        buttonPanel.add(Box.createHorizontalGlue()); //两个按钮之间间距
        buttonPanel.add(aButton[0]);
        buttonPanel.add(Box.createHorizontalGlue()); //两个按钮之间间距
        buttonPanel.add(aButton[1]);
        buttonPanel.add(Box.createHorizontalGlue()); //两个按钮之间间距
        buttonPanel.add(aButton[2]);
        buttonPanel.add(Box.createHorizontalGlue()); //两个按钮之间间距
        acceleratePanel.add(Box.createVerticalStrut (20));
        acceleratePanel.add(buttonPanel);
        acceleratePanel.add(Box.createVerticalStrut (20));
        acceleratePanel.setBorder(border);
        container.add(acceleratePanel);
    }

    private static void GUI_varyButton(Border border, Container container, String[] str)
    {
        //颜色
        Color color1 = new Color(181, 181, 181);  //功能键和运算符颜色
        Color color2 = new Color(126, 192, 238);  //等于号专属颜色
        Color color3 = new Color(232, 232, 232);  //背景颜色
        JPanel acceleratePanel = new JPanel();
        acceleratePanel.setLayout(new GridLayout(3,3,30,1));  //垂直布局
        JButton[] aButton = new JButton[3];
        JLabel[] aLabel = new JLabel[3];
        JLabel emptyLabel = new JLabel();
        for(int i=0;i<aLabel.length;i++)
        {
            aLabel[i] = new JLabel(str[i],JLabel.CENTER);
            aLabel[i].setFont(new Font(null, Font.PLAIN, 25));
            aButton[i] = new JButton(str[i+3]);
            aButton[i].setBackground(color2);
            aButton[i].setForeground(Color.black);
            aButton[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
            aButton[i].setBorderPainted(false);  //去除按钮的边框
            acceleratePanel.add(aLabel[i]);
        }
        acceleratePanel.add(aButton[0]);
        acceleratePanel.add(aButton[1]);
        acceleratePanel.add(aButton[2]);
        acceleratePanel.add(emptyLabel);
        acceleratePanel.add(emptyLabel);
        acceleratePanel.add(emptyLabel);
        acceleratePanel.setBorder(border);
        container.add(acceleratePanel);
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            //没有输入的时候总价为0.00
//            if (num.getText().equals("")) {
//                sum.setText("0.00");
//                this.repaint();
//            }
            try {
//                sum.setText(df.format(Double.valueOf(price.getText()) * Integer.parseInt(num.getText())));
                this.repaint();
            } catch (Exception e) {
            }
        }

    }


    public static void main(String[] args) {
        JFrame frame = new BlueToothGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 520));
        frame.pack();
        frame.setVisible(true);
    }
}

