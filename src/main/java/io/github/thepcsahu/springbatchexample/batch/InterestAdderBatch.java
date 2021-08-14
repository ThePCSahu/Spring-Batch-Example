package io.github.thepcsahu.springbatchexample.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import io.github.thepcsahu.springbatchexample.dataacess.entity.AccountEntity;

@Configuration
@EnableBatchProcessing
public class InterestAdderBatch {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private MyTestTasklet myTasklet;
	
	@Autowired
	private TaskExecutor taskExecutor;

	@Value("${interest-adder-batch.chunk-size}")
	private int chunkSize;

	@Bean
	protected Step step1(ItemReader<AccountEntity> accountItemReader,
			ItemProcessor<AccountEntity, AccountEntity> accountItemProcessor,
			ItemWriter<AccountEntity> accountItemWriter) {
		return steps.get("step1").<AccountEntity, AccountEntity>chunk(this.chunkSize).reader(accountItemReader)
				.processor(accountItemProcessor).writer(accountItemWriter).build();
	}

	@Bean
	protected Step step2(Tasklet myTasklet) {
		return steps.get("step2").tasklet(this.myTasklet).build();
	}

	@Bean
	protected Flow flow1(@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		return (Flow) new FlowBuilder<>("flowbuilder").start(step1).next(step2).build();
	}

	@Bean
	protected Flow flow2(@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		return (Flow) new FlowBuilder<>("flowbuilder").start(step1).next(step2).build();
	}

	@Bean
	protected Flow flow3(@Qualifier("flow1") Flow flow1, @Qualifier("flow2") Flow flow2, TaskExecutor taskExecutor) {
		return (Flow) new FlowBuilder<>("flowbuilder").split(taskExecutor).add(flow1, flow2).build();
	}

	@Bean(name = "firstBatchJob")
	public Job job(@Qualifier("flow3") Flow flow3) {
		return jobs.get("firstBatchJob").incrementer(new RunIdIncrementer()).start(flow3).end().build();
	}

}
