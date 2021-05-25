package com.jcsim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;

public class Test extends JFrame implements Runnable {
    private JTextField num;
    //用来规范保留两位小数
    private java.text.DecimalFormat df;
    private String productNo;
    private JLabel instock;
    private JLabel sum;
    private Thread thread;
    private JLabel price;
    /**
     * Launch the application.
     */

    /**
     * Create the application.
     *
     * @param
     */
    public static void main(String[] args) {
        Test h = new Test();
    }
    public Test() {
        df = new java.text.DecimalFormat("#.00");
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setVisible(true);
        this.setBounds(400, 200, 400, 240);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 240);
        getContentPane().add(panel);
        panel.setLayout(null);


        JLabel priceLabel = new JLabel("单价");
        priceLabel.setBounds(46, 30, 72, 18);
        panel.add(priceLabel);

        price = new JLabel("1.6");
        price.setBounds(176, 30, 72, 18);
        panel.add(price);

        JLabel numLabel = new JLabel("数量");
        numLabel.setBounds(46, 70, 72, 18);
        panel.add(numLabel);

        num = new JTextField();
        num.setBounds(176, 70, 101, 24);
        panel.add(num);
        num.setColumns(10);

        JLabel sumLabel = new JLabel("总价");
        sumLabel.setBounds(46, 110, 72, 18);
        panel.add(sumLabel);

        sum = new JLabel("0");
        sum.setBounds(176, 110, 167, 18);
        panel.add(sum);
        //开始线程，必须在sum定义之后
        thread = new Thread(this);
        thread.start();

        JButton cancle = new JButton("取消");
        cancle.setBounds(40, 150, 113, 27);
        panel.add(cancle);
        cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //终止线程
                thread.stop();
                Test.this.dispose();
            }
        });

        JButton ok = new JButton("确定");
        ok.setBounds(194, 150, 113, 27);
        panel.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String query = "update product set ";
                //终止线程
                thread.stop();
                Test.this.dispose();
            }
        });
        //监听窗口关闭时间，窗口关闭时终止线程
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thread.stop();
                Test.this.dispose();
            }
        });

    }
    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (true) {
            //没有输入的时候总价为0.00
            if (num.getText().equals("")) {
                sum.setText("0.00");
                this.repaint();
            }
            try {
                sum.setText(df.format(Double.valueOf(price.getText()) * Integer.parseInt(num.getText())));
                this.repaint();
            } catch (Exception e) {
            }
        }

    }
}

