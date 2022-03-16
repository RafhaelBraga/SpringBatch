package com.springbatch.batch;

import org.springframework.batch.item.ItemReader;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.entity.Senhas;

@Configuration
@EnableBatchProcessing
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
        .sql("INSERT INTO senhas (senha1, senha2) VALUES (:senha1, :senha2)")
        .dataSource(dataSource)
        .build();
    }
    
    @Bean
    public Job ChangeCryptJob(JobCompletionNotificationListener listener, Step step1) {
    	
      return jobBuilderFactory.get("ChangeCryptJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
      
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Senhas> writer) {
      return stepBuilderFactory.get("step1")
        .<Senhas, Senhas> chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .build();
    }
}
