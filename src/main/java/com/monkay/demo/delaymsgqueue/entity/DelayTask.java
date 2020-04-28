package com.monkay.demo.delaymsgqueue.entity;

public class DelayTask {

    private int cycleNum;
    private Object object;

    public DelayTask(int cycleNum, Object object) {
        this.cycleNum = cycleNum;
        this.object = object;
    }

    public int getCycleNum() {
        return cycleNum;
    }

    public Object getObject() {
        return object;
    }

    public void decrease() {
        cycleNum--;
    }

}
