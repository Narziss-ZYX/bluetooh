package com.jcsim;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jcsim
 * @Date: 2020/11/25 15:17
 * @Description:蓝牙客户端业务类
 */

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.bluetooth.RemoteDevice;
import javax.microedition.io.ConnectionNotFoundException;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class BluetoothClientService {
    public static void main(String[] argv) {
        ExtractNum.initNum();
        final String serverUUID = "1000110100001000800000805F9B34FB"; //需要与服务端相同

        BluetoothClient client = new BluetoothClient();

        // 蓝牙设备集合
        Vector<RemoteDevice> remoteDevices = new Vector<>();

        final boolean[] isConnect = {false};

        // 设置发现类的监听  实现客户端类的onDiscover接口
        client.setOnDiscoverListener(new BluetoothClient.OnDiscoverListener() {

            @Override
            public void onDiscover(RemoteDevice remoteDevice) {
                remoteDevices.add(remoteDevice);
            }

        });

        //GUI
        FlatDarculaLaf.install();
        blueGUI BlueToothGUI = new blueGUI();
        BlueToothGUI.setVisible(true);
        BlueToothGUI.repaint();
//        (new Thread(BlueToothGUI)).start();


        // 设置客户端监听 实现客户端监听接口逻辑
        client.setClientListener(new BluetoothClient.OnClientListener() {
            //  连接成功逻辑接口
            @Override
            public void onConnected(DataInputStream inputStream, OutputStream outputStream) {
                System.out.printf("Connected");
                // 开启线程读蓝牙上接收的数据。
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("客户端开始监听...");
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                            while (true) {
                                Thread.sleep(1);
                                byte[] buffer = new byte[1024];
                                int bytes = 0; //字符串长度
                                int ch;  // 读取字符的变量
//                                inputStream.read(buffer)
                                while ((ch = inputStream.read()) != '\n') {
                                    if (ch != -1) {
                                        buffer[bytes] = (byte) ch; // 将读取到的字符写入
                                        bytes++;
                                    }
                                }
                                buffer[bytes] = (byte) '\n'; //最后加上一个换行
                                bytes++;
                                String s = new String(buffer);
//                                System.out.println("===========start=============");
                                System.out.println(s.trim());
//                                System.out.println("------------end------------");
                                String numString = s.trim();
                                if (numString.length() > 2) {
                                    if (numString.charAt(0) == 'T' && numString.charAt(1) == ':' && numString.length() > 20)
                                        if (numString.charAt(numString.length() - 1) != '\n')
                                            numString = numString + '\n';
                                    ExtractNum.ProcNumString(numString);
                                }
//                                inputStream.close();
//                                onClose();
                                try {
                                    BlueToothGUI.refreshUI();
                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
                // 开启线程向下位机发送数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            try {
                                System.out.println("客户端开始发送...");
                                while(true) {
                                    Thread.sleep(1);
                                    //向下位机发送
                                    if (ExtractNum.StartButtonFlag) {
                                        outputStream.write("Sta:1".getBytes());  //发送
                                        outputStream.flush();
                                        ExtractNum.StartButtonFlag = false;
                                        System.out.println("开始上传");
                                    }
                                    if (ExtractNum.CloseButtonFlag) {
                                        outputStream.write("Sta:0".getBytes());  //发送
                                        outputStream.flush();
                                        ExtractNum.CloseButtonFlag = false;
                                        System.out.println("停止上传");
                                    }
                                    if (ExtractNum.ConfigNumFlag) {
                                        outputStream.write(String.format("P1:%d P2:%d P3:%d P4:%d\n", ExtractNum.config_tempLmt, ExtractNum.config_mpustep, ExtractNum.config_warntime, ExtractNum.config_upstep).getBytes());  //发送
                                        outputStream.flush();
                                        ExtractNum.ConfigNumFlag = false;
                                        System.out.printf("P1:%d P2:%d P3:%d P4:%d\n", ExtractNum.config_tempLmt, ExtractNum.config_mpustep, ExtractNum.config_warntime, ExtractNum.config_upstep);
                                    }
                                }
                            } catch (Exception e) {
                            }

                        }
                }).start();
            }

            @Override
            public void onConnectedSend(OutputStream outputStream) {

            }

            // 连接失败的逻辑
            @Override
            public void onConnectionFailed() {
                System.out.printf("Connection failed");
            }

            // 断开连接的逻辑
            @Override
            public void onDisconnected() {

            }

            //关闭连接的逻辑
            @Override
            public void onClose() {

            }


        });
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 查找设备
                    client.find();
                    if (remoteDevices.size() > 0) {
                        for (int i = 0; i < remoteDevices.size(); i++) {
                            System.out.println("第" + i + "个地址为：" + remoteDevices.get(i).getBluetoothAddress());
                            String lad_632_bluetooth = "98DA2000420E";
                            String old_bluetooth = "98D331FD7AED";  //HC05地址
                            if (old_bluetooth.equals(remoteDevices.get(i).getBluetoothAddress())) {
                                isConnect[0] = true;
                                client.startClient(remoteDevices.get(i));
                                break;
                            }
                        }
                        if (!isConnect[0]) {
                            System.out.println("请打开传感器蓝牙设备。");
                        }
//                System.out.println("remoteDevices.firstElement="+remoteDevices.firstElement());
                    } else {
                        // 附近没有可发现蓝牙设备
                        System.out.println("附件没有蓝牙设备");
                    }
                } catch (ConnectionNotFoundException e) {
                    System.out.println("当前蓝牙不在线");
                    e.printStackTrace();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}