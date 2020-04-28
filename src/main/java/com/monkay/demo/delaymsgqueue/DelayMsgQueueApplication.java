package com.monkay.demo.delaymsgqueue;

import com.monkay.demo.delaymsgqueue.ring.RingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class DelayMsgQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DelayMsgQueueApplication.class, args);
	}

}
