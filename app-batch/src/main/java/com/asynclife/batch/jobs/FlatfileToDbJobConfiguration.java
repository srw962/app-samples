package com.asynclife.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.asynclife.batch.config.InfrastructureConfiguration;
import com.asynclife.batch.domain.Account;
import com.asynclife.batch.listener.LogProcessListener;
import com.asynclife.batch.listener.ProtocolListener;
import com.asynclife.batch.processor.LogItemProcessor;

/**
 * @author Tobias Flohre
 */
@Configuration
@EnableBatchProcessing
public class FlatfileToDbJobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilders;
	
	@Autowired
	private StepBuilderFactory stepBuilders;
	
	@Autowired
	private InfrastructureConfiguration infrastructureConfiguration;
	
	@Bean
	public Job flatfileToDbJob(){
		return jobBuilders.get("flatfileToDbJob")
				.listener(protocolListener())
				.start(step())
				.build();
	}
	
	@Bean
	public Step step(){
		return stepBuilders.get("step")
				.<Account,Account>chunk(1)
				.reader(reader(null))
				.processor(processor())
				.writer(writer())
				.listener(logProcessListener())
				.build();
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Account> reader(@Value("#{jobParameters[filepath]}") String file2Read){
		FlatFileItemReader<Account> itemReader = new FlatFileItemReader<Account>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setResource(new ClassPathResource("account/20150920_01.csv"));
		return itemReader;
	}
	
	@Bean
	public LineMapper<Account> lineMapper(){
		DefaultLineMapper<Account> lineMapper = new DefaultLineMapper<Account>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[]{"elecAccountNo","name","age","certNo","balance"});
		lineTokenizer.setIncludedFields(new int[]{0,1,2,3,4});
		BeanWrapperFieldSetMapper<Account> fieldSetMapper = new BeanWrapperFieldSetMapper<Account>();
		fieldSetMapper.setTargetType(Account.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	@Bean
	public ItemProcessor<Account,Account> processor(){
		return new LogItemProcessor<Account>();
	}
	
	@Bean
	public ItemWriter<Account> writer(){
		JdbcBatchItemWriter<Account> itemWriter = new JdbcBatchItemWriter<Account>();
		itemWriter.setSql("INSERT INTO Account (elecAccountNo,name,age,certNo,balance) "
				+ "VALUES (:elecAccountNo,:name,:age,:certNo,:balance)");
		itemWriter.setDataSource(infrastructureConfiguration.dataSource());
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Account>());
		return itemWriter;
	}
	
	@Bean
	public ProtocolListener protocolListener(){
		return new ProtocolListener();
	}
	
	@Bean
	public LogProcessListener logProcessListener(){
		return new LogProcessListener();
	}
}
