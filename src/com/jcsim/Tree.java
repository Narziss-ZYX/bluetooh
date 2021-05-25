package com.jcsim;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/*创建新的线程：方法1==>继承Thread类*/
class Mythread_thread extends Thread{//1、继承Thread
    @Override
    public void run() {  //2、实现run方法
        Timer timer = new Timer();
        //表示在3秒之后开始执行，并且每2秒执行一次
        timer.schedule(new MyTask1(),10,5);
        // }
    }
}

/**
 * 类描述：这个类代表一个定时任务
 * @author xiezd
 * 自定义定时任务，继承TimerTask
 *
 */
class MyTask1 extends TimerTask{
    //在run方法中的语句就是定时任务执行时运行的语句。
//    public int ddd = 0;
    Tree text = new Tree();
    public void run() {
        String str = text.t.getText();
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(str);
        text.root.add(n);// 将新建节点添加到根节点中。
        text.root.remove(0);// 移除指定索引上的节点。
        text.dt.reload();// 重新装载树控件的内容。
//        System.out.println(" 现在是："+new Date().toLocaleString());
    }
}


public class Tree extends JFrame implements ActionListener {
    public JTree jt;
    public JSplitPane sp;
    public JPanel p2;
    public JTextField t;
    public JButton b;
    public DefaultTreeModel dt;
    DefaultMutableTreeNode root;

    Tree() {
        t = new JTextField(10);
        b = new JButton("提交");
        p2 = new JPanel();
        p2.setBackground(Color.blue);
        p2.add(t);
        p2.add(b);
        root = new DefaultMutableTreeNode("吉大远程");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode("电子商务");
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode("软件");
        root.add(n1);
        root.add(n2);
        dt = new DefaultTreeModel(root);
        jt = new JTree(dt);
        JScrollPane js = new JScrollPane(jt);
        sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, js, p2);
        this.add(sp);
        this.setSize(400, 200);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        b.addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0) {
        // 创建一个新节点，以输入的内容为准。
        String str = t.getText();
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(str);
        root.add(n);// 将新建节点添加到根节点中。
        root.remove(0);// 移除指定索引上的节点。

        dt.reload();// 重新装载树控件的内容。
        // root.getChildAt(0).toString();//获取指定索引上的子节点的内容。
        // root.getChildCount();//获取子节点的数量。
    }

//    public void action(){
//        Tree text = new Tree();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    String str = text.t.getText();
//                    DefaultMutableTreeNode n = new DefaultMutableTreeNode(str);
//                    text.root.add(n);// 将新建节点添加到根节点中。
//                    text.root.remove(0);// 移除指定索引上的节点。
//                    text.dt.reload();// 重新装载树控件的内容。
//                }
//            }
//        }).start();
//    }

    public static void main(String[] arg) {
        new Tree();

        //创建方法1的对象
        Mythread_thread firstThread=new Mythread_thread();
        firstThread.start();//开启线程
//        System.out.println("********************************");
      /*  //main线程方法
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程报数："+i);
        }*/
    }


}

