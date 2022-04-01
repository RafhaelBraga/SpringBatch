package com.springbatch.batch.job.pessoas.step.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbatch.entity.Pessoas;

@Profile("pessoas")
@Configuration
public class ParameterItemWriter {
	
    @Bean("BatchParameterItemWriter")
    public JdbcBatchItemWriter<Pessoas> batchTableWriter(DataSource dataSource) throws Exception {		
    	return new JdbcBatchItemWriterBuilder<Pessoas>()
			.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
			.sql("UPDATE parametros SET ultimo_id=:id WHERE tabela = 'pessoas';")
			.dataSource(dataSource)
			.build();
    }
    
}
