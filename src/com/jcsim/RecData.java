package com.jcsim;

import org.jfree.data.time.Millisecond;

public class RecData {
    public Number data; //纵坐标数值
    public Millisecond ms; //时间轴

    public RecData(Number data, Millisecond ms) {
        this.data = data;
        this.ms = ms;
    }
}
