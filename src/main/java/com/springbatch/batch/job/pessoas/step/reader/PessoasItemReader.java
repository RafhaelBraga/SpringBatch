package com.springbatch.batch.job.pessoas.step.reader;
import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.entity.Pessoas;

@Profile("pessoas")
@Configuration
public class PessoasItemReader {
	
	@Value("${maxRows}")
	private int maxRows;
	
    @Bean("PessoasItemReader")
    public ItemReader<Pessoas> reader(DataSource dataSource) {
      return new JdbcCursorItemReaderBuilder<Pessoas>()
        .name("pessoasItemReader")
        .dataSource(dataSource)
        .sql("select * from pessoas")
        .rowMapper(new BeanPropertyRowMapper<>(Pessoas.class))
        .maxRows(maxRows)
        .build();
    }
    
}
