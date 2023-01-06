/*
 * Created on Sep 21, 2006
 * 
 * This class is to run a scheduler with SimpleTrigger
 */
package com.smart.basic.quartz;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerRunner {
	public static void main(String args[]) {
		try {

			JobDetail jobDetail = new JobDetail("job1_1", "jgroup1",
					SimpleJob.class);

			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1", "tgroup1");
			simpleTrigger.setStartTime(new Date());
			simpleTrigger.setRepeatInterval(2000);
			simpleTrigger.setRepeatCount(5);
            
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();

			// option 1
//			scheduler.scheduleJob(jobDetail, simpleTrigger);

			// optoin 2
			simpleTrigger.setJobGroup("jgroup1");
			simpleTrigger.setJobName("job1_1");
			scheduler.addJob(jobDetail, true);
			scheduler.scheduleJob(simpleTrigger);

			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
