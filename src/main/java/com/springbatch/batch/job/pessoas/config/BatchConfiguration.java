package com.springbatch.batch.job.pessoas.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbatch.batch.job.pessoas.listener.PessoasJobListener;
import com.springbatch.batch.job.pessoas.listener.processor.PessoasItemProcessorListener;
import com.springbatch.batch.job.pessoas.listener.reader.PessoasItemReaderListener;
import com.springbatch.batch.job.pessoas.listener.writer.PessoasItemWriterListener;
import com.springbatch.batch.job.pessoas.step.processor.PessoasItemProcessor;
import com.springbatch.entity.Pessoas;

@Configuration
@EnableBatchProcessing
@Profile("pessoas")
public class BatchConfiguration {

    @Value("${chunk}")
    private int chunk;

    @Autowired
    @Qualifier(value = "CustomItemWriter")
    CompositeItemWriter<Pessoas> customItemWriter;
    
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
    public Job ChangeCryptJob(PessoasJobListener listener, Step step1) {
      return jobBuilderFactory.get("PessoasCpfEncryptJob")
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
        .writer(customItemWriter)
        .listener(pessoasItemWriterListener)
        .build();
    }
}