package io.github.thepcsahu.springbatchexample.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.java.Log;

@Configuration
@EnableScheduling
@Log
public class InterestAdderBatchScheduler {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	@Qualifier("firstBatchJob")
	private Job job;

	@Value("${interest-adder-batch.batch-enabled}")
	private boolean enableBatch;

	/*
	 * Entry point for batch job
	 */
	@Scheduled(fixedDelayString = "${interest-adder-batch.delay}")
	public void executeBatch() {
		if (enableBatch) {
			log.fine("Starting the batch job");
			try {
				JobExecution execution = jobLauncher.run(job,
						new JobParametersBuilder().addDate("now", new Date()).toJobParameters());
				log.fine("Job Status : " + execution.getStatus());
				this.jobRepository.update(execution);
				log.fine("Job completed");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
