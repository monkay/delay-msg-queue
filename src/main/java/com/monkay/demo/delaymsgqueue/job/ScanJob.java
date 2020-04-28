package com.monkay.demo.delaymsgqueue.job;

import com.monkay.demo.delaymsgqueue.ring.RingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ScanJob {

    @Autowired
    private RingQueue ringQueue;

    Random random = new Random();
    @Scheduled(fixedRate = 1000)
    public void scan() {
        ringQueue.runTask();

    }

//    @Scheduled(fixedRate = 2000)
    public void put() {
        int r = random.nextInt(100);
        r = Math.abs(r);

        ringQueue.put(r, r);
    }
}
