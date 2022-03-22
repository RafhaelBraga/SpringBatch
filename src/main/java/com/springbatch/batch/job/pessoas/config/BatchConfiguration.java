package com.springbatch.batch.job.pessoas.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbatch.batch.job.pessoas.step.processor.PessoasItemProcessor;
import com.springbatch.batch.job.pessoas.step.processor.PessoasItemProcessorListener;
import com.springbatch.batch.job.pessoas.step.reader.PessoasItemReaderListener;
import com.springbatch.batch.job.pessoas.step.writer.PessoasItemWriterListener;
import com.springbatch.entity.Pessoas;

@Configuration
@EnableBatchProcessing
@Profile("pessoas")
public class BatchConfiguration {

    @Autowired
    @Qualifier(value = "PessoasJdbcBatchItemWriter")
    JdbcBatchItemWriter<Pessoas> writer;

    @Value("${chunk}")
    private int chunk;
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    PessoasItemReaderListener pessoasItemReaderListener;
    
    @Autowired
    PessoasItemProcessorListener pessoasItemProcessorListener;
    
    @Autowired
    PessoasItemWriterListener pessoasItemWriterListener;

    @Autowired
    @Qualifier(value = "PessoasItemReader")
    ItemReader<Pessoas> reader;
    
    @Bean
    public PessoasItemProcessor processor() {
      return new PessoasItemProcessor();
    }
    
    @Bean
    public Job ChangeCryptJob(PessoasJobCompletionNotificationListener listener, Step step1) {
      return jobBuilderFactory.get("ChangeCpfEncryptJob")
        .start(step1)
        .listener(listener)
        .build();
    }

    @Bean
    public Step step1() {
      return stepBuilderFactory.get("step1")
        .<Pessoas, Pessoas> chunk(chunk)
        .reader(reader)
        .listener(pessoasItemReaderListener)
        .processor(processor())
        .listener(pessoasItemProcessorListener)
        .writer(writer)
        .listener(pessoasItemWriterListener)
        .build();
    }
}