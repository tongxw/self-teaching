package com.smart.timer;

import com.smart.basic.timer.SimpleTimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import java.util.Timer;


@ContextConfiguration(locations = {"classpath:applicationContext-timer.xml"})
public class TestTimerScheduler {
	
	@Autowired
    private Timer timer;

	@Autowired
	private SimpleTimerTask task;

	@Test
	public void testScheduler() throws Throwable{
		System.out.println("begin...");
//		Thread.currentThread().sleep(10000);
//		System.out.println("end.");

	}
}
