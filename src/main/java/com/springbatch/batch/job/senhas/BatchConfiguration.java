package com.springbatch.batch.job.senhas;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.entity.Senhas;

@Configuration
@EnableBatchProcessing
@Profile("senhas")
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public ItemReader<Senhas> reader(DataSource dataSource) {
      return new JdbcCursorItemReaderBuilder<Senhas>()
        .name("personItemReader")
        .dataSource(dataSource)
        .sql("select * from senhas")
        .rowMapper(new BeanPropertyRowMapper<>(Senhas.class))
        .build();
    }

    @Bean
    public SenhasItemProcessor processor() {
      return new SenhasItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Senhas> writer(DataSource dataSource) {
      return new JdbcBatchItemWriterBuilder<Senhas>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("UPDATE senhas SET senha2=:senha2 WHERE id=:id")
        .dataSource(dataSource)
        .build();
    }
    
    @Bean
    public Job ChangeCryptJob(JobCompletionNotificationListener listener, Step step1) {
    	
      return jobBuilderFactory.get("ChangeCryptJob")
    	.listener(listener)
        .start(step1)
        .build();
      
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Senhas> writer, ItemReader<Senhas> reader) {
      return stepBuilderFactory.get("step1")
        .<Senhas, Senhas> chunk(10)
        .reader(reader)
        .processor(processor())
        .writer(writer)
        .build();
    }
}
