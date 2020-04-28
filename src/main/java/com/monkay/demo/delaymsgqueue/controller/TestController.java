package com.monkay.demo.delaymsgqueue.controller;

import com.monkay.demo.delaymsgqueue.ring.RingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private RingQueue ringQueue;

    @RequestMapping("/test")
    public String test(Integer seconds) {
        ringQueue.put(seconds, seconds);

        return "ok";
    }
}
