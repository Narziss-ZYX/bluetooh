/*
 * Created by JFormDesigner on Tue May 25 08:41:01 CST 2021
 */

package com.jcsim;

import java.awt.event.*;
import java.beans.*;
import javax.swing.event.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class blueGUI extends JFrame implements Runnable {
    private String[] tempString = {"temp", "The temp curve", "temp/℃"};
    private String[] axString = {"ax", "The ax curve", "ax"};
    private String[] ayString = {"ay", "The ay curve", "ay"};
    private String[] azString = {"az", "The az curve", "az"};
    private String[] gxString = {"gx", "The gx curve", "gx"};
    private String[] gyString = {"gy", "The gy curve", "gy"};
    private String[] gzString = {"gz", "The gz curve", "gz"};
    private String[] fAXString = {"fAX", "The fAX curve", "fAX/°"};
    private String[] fAYString = {"fAY", "The fAY curve", "fAY/°"};
    private String[] fAZString = {"fAZ", "The fAZ curve", "fAZ/°"};

    public blueGUI() {
        initComponents();
        //关闭窗口时结束程序
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                System.exit(0);
            }
        });
    }

//    private void drawChart(String[] str) {
//        JFrame frame = new JFrame("Chart");
//        DrawChart rtcp = DrawChart.createDrawChart(str[0], str[1], str[2]);
//        frame.getContentPane().add(rtcp, new BorderLayout().CENTER);
//        frame.pack();
//        frame.setVisible(true);
//        (new Thread(rtcp)).start();
//    }
    private void drawChart(Number[]src,ArrayList<RecData> ys,String[] str) {
        JFrame frame = new JFrame("Chart");
        DrawChart rtcp = DrawChart.createDrawChart(src,ys,str[0], str[1], str[2]);
        frame.getContentPane().add(rtcp, new BorderLayout().CENTER);
        frame.pack();
        frame.setSize(720,500);
//        frame.setPreferredSize(new Dimension(100,100));
        frame.setLocationRelativeTo(getOwner());
        frame.setVisible(true);
        (new Thread(rtcp)).start();
    }

    private ArrayList<RecData> temp = new ArrayList<>();
    private ArrayList<RecData> ax = new ArrayList<>();
    private ArrayList<RecData> ay = new ArrayList<>();
    private ArrayList<RecData> az = new ArrayList<>();
    private ArrayList<RecData> gx = new ArrayList<>();
    private ArrayList<RecData> gy = new ArrayList<>();
    private ArrayList<RecData> gz = new ArrayList<>();
    private ArrayList<RecData> fax = new ArrayList<>();
    private ArrayList<RecData> fay = new ArrayList<>();
    private ArrayList<RecData> faz = new ArrayList<>();
    //显示温度曲线
    private void tempButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.temp,temp,tempString);
    }

    private void axButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.ax,ax,axString);
    }

    private void ayButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.ay,ay,ayString);
    }

    private void azButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.az,az,azString);
    }

    private void gxButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.gx,gx,gxString);
    }

    private void gyButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.gy,gy,gyString);
    }

    private void gzButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.gz,gz,gzString);
    }

    private void fAXButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.fAX,fax,fAXString);
    }

    private void fAYButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        drawChart(ExtractNum.fAY,fay,fAYString);
    }

    private void fAZButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        warnTextField.setForeground(new Color(186, 187, 187));
        drawChart(ExtractNum.fAZ,faz,fAZString);
    }

    private void warnTextFieldPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
        System.out.println("change");
    }

    private void tempLmtSpinnerStateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void CongfigButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        ExtractNum.config_tempLmt = (int) tempLmtSpinner.getValue();
        ExtractNum.config_mpustep = (int) mpuStepSpinner.getValue();
        ExtractNum.config_warntime = (int) warnTimeSpinner.getValue();
        ExtractNum.config_upstep = (int)((Double) timeStepLabelSpinner.getValue() * 1000); //ms单位
        ExtractNum.ConfigNumFlag = true;
    }

    private void StartButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        ExtractNum.g_bUpingFlag = 1;  //启动上传
        ExtractNum.StartButtonFlag = true;
    }

    private void closeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        ExtractNum.g_bUpingFlag = 0;  //关闭上传
        ExtractNum.CloseButtonFlag = true;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("com.jcsim.form");
        tabbedPane2 = new JTabbedPane();
        dataPane = new JPanel();
        tempPanel = new JPanel();
        tempLabel = new JLabel();
        tempButton = new JButton();
        tempUnitLabel = new JLabel();
        warnPanel = new JPanel();
        warnStaLabel = new JLabel();
        warnTextField = new JTextField();
        accelerationPanel = new JPanel();
        axLabel = new JLabel();
        axButton = new JButton();
        ayButton = new JButton();
        azButton = new JButton();
        ayLabel = new JLabel();
        azLabel = new JLabel();
        angvPanel = new JPanel();
        gxLabel = new JLabel();
        gxButton = new JButton();
        gyButton = new JButton();
        gzButton = new JButton();
        gyLabel = new JLabel();
        gzLabel = new JLabel();
        accelerationPanel4 = new JPanel();
        fAXLabel = new JLabel();
        fAXButton = new JButton();
        fAYButton = new JButton();
        fAZButton = new JButton();
        fAYLabel = new JLabel();
        fAZLabel = new JLabel();
        angUnitLabel1 = new JLabel();
        angUnitLabel2 = new JLabel();
        angUnitLabel3 = new JLabel();
        configPane2 = new JPanel();
        configPanel = new JPanel();
        tempLmtLabel = new JLabel();
        tempLmtSpinner = new JSpinner();
        tempLmtUnitLabel = new JLabel();
        mpuStepLabel = new JLabel();
        mpuStepSpinner = new JSpinner();
        mpuStepUnitLabel = new JLabel();
        warnTimeLabel = new JLabel();
        warnTimeSpinner = new JSpinner();
        warnTimeUnitLabel = new JLabel();
        timeStepLabel = new JLabel();
        timeStepLabelSpinner = new JSpinner();
        timeStepUnitLabel = new JLabel();
        CongfigButton = new JButton();
        readPanel = new JPanel();
        tempLmtLabel1 = new JLabel();
        tempLmtText = new JTextField();
        tempLmtUnitLabel1 = new JLabel();
        mpuStepLabel1 = new JLabel();
        mpuStepText = new JTextField();
        mpuStepUnitLabel1 = new JLabel();
        warnTimeLabel1 = new JLabel();
        warnTimeText = new JTextField();
        warnTimeUnitLabel1 = new JLabel();
        timeStepLabel1 = new JLabel();
        timeStepText = new JTextField();
        timeStepUnitLabel1 = new JLabel();
        buttonBar3 = new JPanel();
        StartButton = new JButton();
        closeButton = new JButton();
        ZYXlabel = new JLabel();

        //======== this ========
        setTitle("BlueThooth");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane2 ========
        {

            //======== dataPane ========
            {
                dataPane.setBorder(new EtchedBorder());
                dataPane.setLayout(null);

                //======== tempPanel ========
                {
                    tempPanel.setBorder(new TitledBorder(new EtchedBorder(), "\u6e29\u5ea6", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    tempPanel.setLayout(null);

                    //---- tempLabel ----
                    tempLabel.setText(bundle.getString("tempLabel.text"));
                    tempPanel.add(tempLabel);
                    tempLabel.setBounds(26, 26, tempLabel.getPreferredSize().width, 30);

                    //---- tempButton ----
                    tempButton.setText("0");
                    tempButton.setAutoscrolls(true);
                    tempButton.addActionListener(e -> tempButtonActionPerformed(e));
                    tempPanel.add(tempButton);
                    tempButton.setBounds(65, 30, 75, tempButton.getPreferredSize().height);

                    //---- tempUnitLabel ----
                    tempUnitLabel.setText(bundle.getString("tempUnitLabel.text"));
                    tempPanel.add(tempUnitLabel);
                    tempUnitLabel.setBounds(150, 26, tempUnitLabel.getPreferredSize().width, 30);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < tempPanel.getComponentCount(); i++) {
                            Rectangle bounds = tempPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = tempPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        tempPanel.setMinimumSize(preferredSize);
                        tempPanel.setPreferredSize(preferredSize);
                    }
                }
                dataPane.add(tempPanel);
                tempPanel.setBounds(10, 20, 188, 68);

                //======== warnPanel ========
                {
                    warnPanel.setBorder(new TitledBorder(new EtchedBorder(), "\u62a5\u8b66\u72b6\u6001", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    warnPanel.setLayout(null);

                    //---- warnStaLabel ----
                    warnStaLabel.setText(bundle.getString("warnStaLabel.text"));
                    warnPanel.add(warnStaLabel);
                    warnStaLabel.setBounds(37, 26, warnStaLabel.getPreferredSize().width, 30);

                    //---- warnTextField ----
                    warnTextField.setHorizontalAlignment(SwingConstants.CENTER);
                    warnTextField.setEditable(false);
                    warnTextField.addPropertyChangeListener("foreground", e -> warnTextFieldPropertyChange(e));
                    warnPanel.add(warnTextField);
                    warnTextField.setBounds(95, 30, 70, warnTextField.getPreferredSize().height);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < warnPanel.getComponentCount(); i++) {
                            Rectangle bounds = warnPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = warnPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        warnPanel.setMinimumSize(preferredSize);
                        warnPanel.setPreferredSize(preferredSize);
                    }
                }
                dataPane.add(warnPanel);
                warnPanel.setBounds(200, 20, 188, 68);

                //======== accelerationPanel ========
                {
                    accelerationPanel.setBorder(new TitledBorder(new EtchedBorder(), "\u4e09\u8f74\u52a0\u901f\u5ea6", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    accelerationPanel.setLayout(null);

                    //---- axLabel ----
                    axLabel.setText(bundle.getString("axLabel.text"));
                    axLabel.setFont(axLabel.getFont().deriveFont(axLabel.getFont().getSize() + 6f));
                    accelerationPanel.add(axLabel);
                    axLabel.setBounds(55, 20, 20, axLabel.getPreferredSize().height);

                    //---- axButton ----
                    axButton.setText(bundle.getString("axButton.text"));
                    axButton.addActionListener(e -> axButtonActionPerformed(e));
                    accelerationPanel.add(axButton);
                    axButton.setBounds(30, 45, 75, axButton.getPreferredSize().height);

                    //---- ayButton ----
                    ayButton.setText(bundle.getString("ayButton.text"));
                    ayButton.addActionListener(e -> ayButtonActionPerformed(e));
                    accelerationPanel.add(ayButton);
                    ayButton.setBounds(150, 45, 80, ayButton.getPreferredSize().height);

                    //---- azButton ----
                    azButton.setText(bundle.getString("azButton.text"));
                    azButton.addActionListener(e -> azButtonActionPerformed(e));
                    accelerationPanel.add(azButton);
                    azButton.setBounds(275, 45, 80, azButton.getPreferredSize().height);

                    //---- ayLabel ----
                    ayLabel.setText(bundle.getString("ayLabel.text"));
                    ayLabel.setFont(ayLabel.getFont().deriveFont(ayLabel.getFont().getSize() + 6f));
                    accelerationPanel.add(ayLabel);
                    ayLabel.setBounds(180, 20, 20, 24);

                    //---- azLabel ----
                    azLabel.setText(bundle.getString("azLabel.text"));
                    azLabel.setFont(azLabel.getFont().deriveFont(azLabel.getFont().getSize() + 6f));
                    accelerationPanel.add(azLabel);
                    azLabel.setBounds(305, 20, 20, 24);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < accelerationPanel.getComponentCount(); i++) {
                            Rectangle bounds = accelerationPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = accelerationPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        accelerationPanel.setMinimumSize(preferredSize);
                        accelerationPanel.setPreferredSize(preferredSize);
                    }
                }
                dataPane.add(accelerationPanel);
                accelerationPanel.setBounds(10, 105, 379, 85);

                //======== angvPanel ========
                {
                    angvPanel.setBorder(new TitledBorder(new EtchedBorder(), "\u4e09\u8f74\u89d2\u901f\u5ea6", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    angvPanel.setLayout(null);

                    //---- gxLabel ----
                    gxLabel.setText(bundle.getString("gxLabel.text"));
                    gxLabel.setFont(gxLabel.getFont().deriveFont(gxLabel.getFont().getSize() + 5f));
                    angvPanel.add(gxLabel);
                    gxLabel.setBounds(55, 20, 20, gxLabel.getPreferredSize().height);

                    //---- gxButton ----
                    gxButton.setText(bundle.getString("gxButton.text"));
                    gxButton.addActionListener(e -> gxButtonActionPerformed(e));
                    angvPanel.add(gxButton);
                    gxButton.setBounds(30, 45, 75, gxButton.getPreferredSize().height);

                    //---- gyButton ----
                    gyButton.setText(bundle.getString("gyButton.text"));
                    gyButton.addActionListener(e -> gyButtonActionPerformed(e));
                    angvPanel.add(gyButton);
                    gyButton.setBounds(150, 45, 80, gyButton.getPreferredSize().height);

                    //---- gzButton ----
                    gzButton.setText(bundle.getString("gzButton.text"));
                    gzButton.addActionListener(e -> gzButtonActionPerformed(e));
                    angvPanel.add(gzButton);
                    gzButton.setBounds(275, 45, 80, gzButton.getPreferredSize().height);

                    //---- gyLabel ----
                    gyLabel.setText(bundle.getString("gyLabel.text"));
                    gyLabel.setFont(gyLabel.getFont().deriveFont(gyLabel.getFont().getSize() + 5f));
                    angvPanel.add(gyLabel);
                    gyLabel.setBounds(180, 20, 25, 24);

                    //---- gzLabel ----
                    gzLabel.setText(bundle.getString("gzLabel.text"));
                    gzLabel.setFont(gzLabel.getFont().deriveFont(gzLabel.getFont().getSize() + 5f));
                    angvPanel.add(gzLabel);
                    gzLabel.setBounds(305, 20, 20, 24);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < angvPanel.getComponentCount(); i++) {
                            Rectangle bounds = angvPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = angvPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        angvPanel.setMinimumSize(preferredSize);
                        angvPanel.setPreferredSize(preferredSize);
                    }
                }
                dataPane.add(angvPanel);
                angvPanel.setBounds(10, 205, 379, 85);

                //======== accelerationPanel4 ========
                {
                    accelerationPanel4.setBorder(new TitledBorder(new EtchedBorder(), "\u59ff\u6001\u89d2", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    accelerationPanel4.setLayout(null);

                    //---- fAXLabel ----
                    fAXLabel.setText(bundle.getString("fAXLabel.text"));
                    fAXLabel.setFont(fAXLabel.getFont().deriveFont(fAXLabel.getFont().getSize() + 1f));
                    accelerationPanel4.add(fAXLabel);
                    fAXLabel.setBounds(45, 25, 45, fAXLabel.getPreferredSize().height);

                    //---- fAXButton ----
                    fAXButton.setText(bundle.getString("fAXButton.text"));
                    fAXButton.addActionListener(e -> fAXButtonActionPerformed(e));
                    accelerationPanel4.add(fAXButton);
                    fAXButton.setBounds(30, 45, 75, fAXButton.getPreferredSize().height);

                    //---- fAYButton ----
                    fAYButton.setText(bundle.getString("fAYButton.text"));
                    fAYButton.addActionListener(e -> fAYButtonActionPerformed(e));
                    accelerationPanel4.add(fAYButton);
                    fAYButton.setBounds(150, 45, 80, fAYButton.getPreferredSize().height);

                    //---- fAZButton ----
                    fAZButton.setText(bundle.getString("fAZButton.text"));
                    fAZButton.addActionListener(e -> fAZButtonActionPerformed(e));
                    accelerationPanel4.add(fAZButton);
                    fAZButton.setBounds(275, 45, 80, fAZButton.getPreferredSize().height);

                    //---- fAYLabel ----
                    fAYLabel.setText(bundle.getString("fAYLabel.text"));
                    fAYLabel.setFont(fAYLabel.getFont().deriveFont(fAYLabel.getFont().getSize() + 1f));
                    accelerationPanel4.add(fAYLabel);
                    fAYLabel.setBounds(new Rectangle(new Point(170, 25), fAYLabel.getPreferredSize()));

                    //---- fAZLabel ----
                    fAZLabel.setText(bundle.getString("fAZLabel.text"));
                    fAZLabel.setFont(fAZLabel.getFont().deriveFont(fAZLabel.getFont().getSize() + 1f));
                    accelerationPanel4.add(fAZLabel);
                    fAZLabel.setBounds(new Rectangle(new Point(295, 25), fAZLabel.getPreferredSize()));

                    //---- angUnitLabel1 ----
                    angUnitLabel1.setText(bundle.getString("angUnitLabel1.text"));
                    accelerationPanel4.add(angUnitLabel1);
                    angUnitLabel1.setBounds(new Rectangle(new Point(105, 50), angUnitLabel1.getPreferredSize()));

                    //---- angUnitLabel2 ----
                    angUnitLabel2.setText(bundle.getString("angUnitLabel2.text"));
                    accelerationPanel4.add(angUnitLabel2);
                    angUnitLabel2.setBounds(new Rectangle(new Point(230, 50), angUnitLabel2.getPreferredSize()));

                    //---- angUnitLabel3 ----
                    angUnitLabel3.setText(bundle.getString("angUnitLabel3.text"));
                    accelerationPanel4.add(angUnitLabel3);
                    angUnitLabel3.setBounds(new Rectangle(new Point(355, 50), angUnitLabel3.getPreferredSize()));

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < accelerationPanel4.getComponentCount(); i++) {
                            Rectangle bounds = accelerationPanel4.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = accelerationPanel4.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        accelerationPanel4.setMinimumSize(preferredSize);
                        accelerationPanel4.setPreferredSize(preferredSize);
                    }
                }
                dataPane.add(accelerationPanel4);
                accelerationPanel4.setBounds(10, 300, 379, 85);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < dataPane.getComponentCount(); i++) {
                        Rectangle bounds = dataPane.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = dataPane.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    dataPane.setMinimumSize(preferredSize);
                    dataPane.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("dataPane.tab.title"), dataPane);

            //======== configPane2 ========
            {
                configPane2.setBorder(new EtchedBorder());
                configPane2.setLayout(null);

                //======== configPanel ========
                {
                    configPanel.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("configPanel.border"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    configPanel.setLayout(null);

                    //---- tempLmtLabel ----
                    tempLmtLabel.setText(bundle.getString("tempLmtLabel.text"));
                    configPanel.add(tempLmtLabel);
                    tempLmtLabel.setBounds(25, 25, 72, 30);

                    //---- tempLmtSpinner ----
                    tempLmtSpinner.setModel(new SpinnerNumberModel(30, 0, 90, 1));
                    tempLmtSpinner.addChangeListener(e -> tempLmtSpinnerStateChanged(e));
                    configPanel.add(tempLmtSpinner);
                    tempLmtSpinner.setBounds(115, 25, 126, tempLmtSpinner.getPreferredSize().height);

                    //---- tempLmtUnitLabel ----
                    tempLmtUnitLabel.setText(bundle.getString("tempLmtUnitLabel.text"));
                    configPanel.add(tempLmtUnitLabel);
                    tempLmtUnitLabel.setBounds(245, 25, tempLmtUnitLabel.getPreferredSize().width, 30);

                    //---- mpuStepLabel ----
                    mpuStepLabel.setText(bundle.getString("mpuStepLabel.text"));
                    configPanel.add(mpuStepLabel);
                    mpuStepLabel.setBounds(25, 60, mpuStepLabel.getPreferredSize().width, 30);

                    //---- mpuStepSpinner ----
                    mpuStepSpinner.setModel(new SpinnerNumberModel(7, 0, 9, 1));
                    configPanel.add(mpuStepSpinner);
                    mpuStepSpinner.setBounds(115, 60, 126, mpuStepSpinner.getPreferredSize().height);

                    //---- mpuStepUnitLabel ----
                    mpuStepUnitLabel.setText(bundle.getString("mpuStepUnitLabel.text"));
                    configPanel.add(mpuStepUnitLabel);
                    mpuStepUnitLabel.setBounds(245, 60, mpuStepUnitLabel.getPreferredSize().width, 30);

                    //---- warnTimeLabel ----
                    warnTimeLabel.setText(bundle.getString("warnTimeLabel.text"));
                    configPanel.add(warnTimeLabel);
                    warnTimeLabel.setBounds(25, 95, 72, 30);

                    //---- warnTimeSpinner ----
                    warnTimeSpinner.setModel(new SpinnerNumberModel(30, 0, 60, 1));
                    configPanel.add(warnTimeSpinner);
                    warnTimeSpinner.setBounds(115, 95, 126, warnTimeSpinner.getPreferredSize().height);

                    //---- warnTimeUnitLabel ----
                    warnTimeUnitLabel.setText(bundle.getString("warnTimeUnitLabel.text"));
                    configPanel.add(warnTimeUnitLabel);
                    warnTimeUnitLabel.setBounds(245, 95, warnTimeUnitLabel.getPreferredSize().width, 30);

                    //---- timeStepLabel ----
                    timeStepLabel.setText(bundle.getString("timeStepLabel.text"));
                    configPanel.add(timeStepLabel);
                    timeStepLabel.setBounds(25, 130, 72, 30);

                    //---- timeStepLabelSpinner ----
                    timeStepLabelSpinner.setModel(new SpinnerNumberModel(0.1F, 0.0F, 10.0F, 0.1F));
                    configPanel.add(timeStepLabelSpinner);
                    timeStepLabelSpinner.setBounds(115, 130, 126, timeStepLabelSpinner.getPreferredSize().height);

                    //---- timeStepUnitLabel ----
                    timeStepUnitLabel.setText(bundle.getString("timeStepUnitLabel.text"));
                    configPanel.add(timeStepUnitLabel);
                    timeStepUnitLabel.setBounds(245, 130, timeStepUnitLabel.getPreferredSize().width, 30);

                    //---- CongfigButton ----
                    CongfigButton.setText(bundle.getString("CongfigButton.text"));
                    CongfigButton.addActionListener(e -> CongfigButtonActionPerformed(e));
                    configPanel.add(CongfigButton);
                    CongfigButton.setBounds(275, 75, 85, 30);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < configPanel.getComponentCount(); i++) {
                            Rectangle bounds = configPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = configPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        configPanel.setMinimumSize(preferredSize);
                        configPanel.setPreferredSize(preferredSize);
                    }
                }
                configPane2.add(configPanel);
                configPanel.setBounds(10, 20, 370, 190);

                //======== readPanel ========
                {
                    readPanel.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("readPanel.border"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                        new Font("\u534e\u5149\u7c97\u5706_CNKI", Font.PLAIN, 14), SystemColor.activeCaption));
                    readPanel.setLayout(null);

                    //---- tempLmtLabel1 ----
                    tempLmtLabel1.setText(bundle.getString("tempLmtLabel1.text"));
                    readPanel.add(tempLmtLabel1);
                    tempLmtLabel1.setBounds(21, 42, tempLmtLabel1.getPreferredSize().width, 30);

                    //---- tempLmtText ----
                    tempLmtText.setEditable(false);
                    readPanel.add(tempLmtText);
                    tempLmtText.setBounds(85, 45, 50, tempLmtText.getPreferredSize().height);

                    //---- tempLmtUnitLabel1 ----
                    tempLmtUnitLabel1.setText(bundle.getString("tempLmtUnitLabel1.text"));
                    readPanel.add(tempLmtUnitLabel1);
                    tempLmtUnitLabel1.setBounds(140, 42, tempLmtUnitLabel1.getPreferredSize().width, 30);

                    //---- mpuStepLabel1 ----
                    mpuStepLabel1.setText(bundle.getString("mpuStepLabel1.text"));
                    readPanel.add(mpuStepLabel1);
                    mpuStepLabel1.setBounds(182, 42, mpuStepLabel1.getPreferredSize().width, 30);

                    //---- mpuStepText ----
                    mpuStepText.setEditable(false);
                    readPanel.add(mpuStepText);
                    mpuStepText.setBounds(260, 45, 48, mpuStepText.getPreferredSize().height);

                    //---- mpuStepUnitLabel1 ----
                    mpuStepUnitLabel1.setText(bundle.getString("mpuStepUnitLabel1.text"));
                    readPanel.add(mpuStepUnitLabel1);
                    mpuStepUnitLabel1.setBounds(313, 42, mpuStepUnitLabel1.getPreferredSize().width, 30);

                    //---- warnTimeLabel1 ----
                    warnTimeLabel1.setText(bundle.getString("warnTimeLabel1.text"));
                    readPanel.add(warnTimeLabel1);
                    warnTimeLabel1.setBounds(21, 77, warnTimeLabel1.getPreferredSize().width, 30);

                    //---- warnTimeText ----
                    warnTimeText.setEditable(false);
                    readPanel.add(warnTimeText);
                    warnTimeText.setBounds(85, 80, 50, warnTimeText.getPreferredSize().height);

                    //---- warnTimeUnitLabel1 ----
                    warnTimeUnitLabel1.setText(bundle.getString("warnTimeUnitLabel1.text"));
                    readPanel.add(warnTimeUnitLabel1);
                    warnTimeUnitLabel1.setBounds(140, 77, warnTimeUnitLabel1.getPreferredSize().width, 30);

                    //---- timeStepLabel1 ----
                    timeStepLabel1.setText(bundle.getString("timeStepLabel1.text"));
                    readPanel.add(timeStepLabel1);
                    timeStepLabel1.setBounds(182, 77, 72, 30);

                    //---- timeStepText ----
                    timeStepText.setEditable(false);
                    readPanel.add(timeStepText);
                    timeStepText.setBounds(260, 80, 48, timeStepText.getPreferredSize().height);

                    //---- timeStepUnitLabel1 ----
                    timeStepUnitLabel1.setText(bundle.getString("timeStepUnitLabel1.text"));
                    readPanel.add(timeStepUnitLabel1);
                    timeStepUnitLabel1.setBounds(313, 77, timeStepUnitLabel1.getPreferredSize().width, 30);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < readPanel.getComponentCount(); i++) {
                            Rectangle bounds = readPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = readPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        readPanel.setMinimumSize(preferredSize);
                        readPanel.setPreferredSize(preferredSize);
                    }
                }
                configPane2.add(readPanel);
                readPanel.setBounds(10, 245, 370, 135);

                //======== buttonBar3 ========
                {
                    buttonBar3.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar3.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar3.getLayout()).columnWidths = new int[] {0, 85, 80};
                    ((GridBagLayout)buttonBar3.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                    //---- StartButton ----
                    StartButton.setText(bundle.getString("StartButton.text"));
                    StartButton.addActionListener(e -> StartButtonActionPerformed(e));
                    buttonBar3.add(StartButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- closeButton ----
                    closeButton.setText(bundle.getString("closeButton.text"));
                    closeButton.addActionListener(e -> closeButtonActionPerformed(e));
                    buttonBar3.add(closeButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                configPane2.add(buttonBar3);
                buttonBar3.setBounds(5, 205, 374, 42);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < configPane2.getComponentCount(); i++) {
                        Rectangle bounds = configPane2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = configPane2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    configPane2.setMinimumSize(preferredSize);
                    configPane2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("configPane2.title"), configPane2);
        }
        contentPane.add(tabbedPane2, BorderLayout.NORTH);

        //---- ZYXlabel ----
        ZYXlabel.setText(bundle.getString("ZYXlabel.text"));
        ZYXlabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(ZYXlabel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane2;
    private JPanel dataPane;
    private JPanel tempPanel;
    private JLabel tempLabel;
    private JButton tempButton;
    private JLabel tempUnitLabel;
    private JPanel warnPanel;
    private JLabel warnStaLabel;
    private JTextField warnTextField;
    private JPanel accelerationPanel;
    private JLabel axLabel;
    private JButton axButton;
    private JButton ayButton;
    private JButton azButton;
    private JLabel ayLabel;
    private JLabel azLabel;
    private JPanel angvPanel;
    private JLabel gxLabel;
    private JButton gxButton;
    private JButton gyButton;
    private JButton gzButton;
    private JLabel gyLabel;
    private JLabel gzLabel;
    private JPanel accelerationPanel4;
    private JLabel fAXLabel;
    private JButton fAXButton;
    private JButton fAYButton;
    private JButton fAZButton;
    private JLabel fAYLabel;
    private JLabel fAZLabel;
    private JLabel angUnitLabel1;
    private JLabel angUnitLabel2;
    private JLabel angUnitLabel3;
    private JPanel configPane2;
    private JPanel configPanel;
    private JLabel tempLmtLabel;
    private JSpinner tempLmtSpinner;
    private JLabel tempLmtUnitLabel;
    private JLabel mpuStepLabel;
    private JSpinner mpuStepSpinner;
    private JLabel mpuStepUnitLabel;
    private JLabel warnTimeLabel;
    private JSpinner warnTimeSpinner;
    private JLabel warnTimeUnitLabel;
    private JLabel timeStepLabel;
    private JSpinner timeStepLabelSpinner;
    private JLabel timeStepUnitLabel;
    private JButton CongfigButton;
    private JPanel readPanel;
    private JLabel tempLmtLabel1;
    private JTextField tempLmtText;
    private JLabel tempLmtUnitLabel1;
    private JLabel mpuStepLabel1;
    private JTextField mpuStepText;
    private JLabel mpuStepUnitLabel1;
    private JLabel warnTimeLabel1;
    private JTextField warnTimeText;
    private JLabel warnTimeUnitLabel1;
    private JLabel timeStepLabel1;
    private JTextField timeStepText;
    private JLabel timeStepUnitLabel1;
    private JPanel buttonBar3;
    private JButton StartButton;
    private JButton closeButton;
    private JLabel ZYXlabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        FlatDarkLaf.install();
        blueGUI BlueToothGUI = new blueGUI();
        BlueToothGUI.setVisible(true);
//        new blueGUI().setVisible(true);
        (new Thread(BlueToothGUI)).start();
    }

    public void refreshUI(){
        tempButton.setText(String.format("%.1f", ExtractNum.temp[0]));
        String warnText = "";
        switch (ExtractNum.Warn){
            case 0 : warnTextField.setText("无");break;
            case 1:warnTextField.setText("温度报警");break;
            case 2:warnTextField.setText("震动报警");break;
        }
        axButton.setText(String.format("%d", ExtractNum.ax[0]));
        ayButton.setText(String.format("%d", ExtractNum.ay[0]));
        azButton.setText(String.format("%d", ExtractNum.az[0]));
        gxButton.setText(String.format("%d", ExtractNum.gx[0]));
        gyButton.setText(String.format("%d", ExtractNum.gy[0]));
        gzButton.setText(String.format("%d", ExtractNum.gz[0]));
        fAXButton.setText(String.format("%.1f", ExtractNum.fAX[0]));
        fAYButton.setText(String.format("%.1f", ExtractNum.fAY[0]));
        fAZButton.setText(String.format("%.1f", ExtractNum.fAZ[0]));
        tempLmtText.setText(String.format("%d", ExtractNum.tempLmt));
        mpuStepText.setText(String.format("%d", ExtractNum.g_mpustep));
        warnTimeText.setText(String.format("%d", ExtractNum.g_warntime));
        timeStepText.setText(String.format("%.1f", (float)(ExtractNum.g_upstep)/1000));
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
//        while (true) {
//            try {
//
//            } catch (Exception e) {
//            }
//        }
    }
}
