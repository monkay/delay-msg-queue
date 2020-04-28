package com.monkay.demo.delaymsgqueue.ring;

import com.monkay.demo.delaymsgqueue.entity.DelayTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class RingQueue {
    private List<DelayTask>[] items;
    private int maxSize;
    private volatile int currentIndex;   //当前执行到索引

    private int DEFAULT_SIZE = 3600;    //一小时遍历一遍

    public RingQueue() {
        items = new List[DEFAULT_SIZE];
        maxSize = DEFAULT_SIZE;
        currentIndex = 0;
    }

    /**
     * 放入延时执行队列
     * @param second 延迟多少秒执行
     * @param object
     */
    public void put(int second, Object object) {
        int delaySecond = currentIndex + second;
        int slot = delaySecond % maxSize;

        if (items[slot] == null) {
            items[slot] = new ArrayList<>();
        }

        int cycleNum = delaySecond / maxSize;
        DelayTask delayTask = new DelayTask(cycleNum, object);
        items[slot].add(delayTask);
        log.info("second:{}, currentIndex:{}, slot:{}, cycleNum:{}", second, currentIndex, slot, cycleNum);
    }

    /**
     * 定时执行
     */
    public void runTask() {
        List<DelayTask> list = items[currentIndex];
        int index = currentIndex;
        if ((currentIndex + 1) >= maxSize) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }

        if (list == null || list.isEmpty()) {
            log.info("slot:{} is empty", index);
            return;
        }

        Iterator<DelayTask> iterator = list.iterator();

        while (iterator.hasNext()) {
            DelayTask delayTask = iterator.next();
            if (delayTask.getCycleNum() == 0) {
                log.info("run delayTask:{}", delayTask.getObject());
                iterator.remove();
                continue;
            }

            log.info("task not run in time, cycleNum:{}, object:{}", delayTask.getCycleNum(), delayTask.getObject());
            delayTask.decrease();
        }
    }
}
