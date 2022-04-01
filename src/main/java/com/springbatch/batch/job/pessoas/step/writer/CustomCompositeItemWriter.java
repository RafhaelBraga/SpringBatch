package com.springbatch.batch.job.pessoas.step.writer;

import java.util.Arrays;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbatch.entity.Pessoas;


@Configuration
@Profile("pessoas")
public class CustomCompositeItemWriter {
	
	CompositeItemWriter<Pessoas> compositeItemWriter;
	
    public CustomCompositeItemWriter() {
    	this.compositeItemWriter = new CompositeItemWriter<>();
	}

    @Autowired
    @Qualifier(value = "PessoasJdbcBatchItemWriter")
	JdbcBatchItemWriter<Pessoas> pessoasWriter;
    
    @Autowired
    @Qualifier(value = "BatchParameterItemWriter")
    JdbcBatchItemWriter<Pessoas> parameterItemWriter;
    
	@Bean("CustomItemWriter")
//	@Retryable(value = Exception.class, maxAttemptsExpression = "${retriesWrite}", backoff = @Backoff(delayExpression = "${backoffWrite}"))   
	 public CompositeItemWriter<Pessoas> compositeWriter() throws Exception {
        compositeItemWriter.setDelegates(Arrays.asList(pessoasWriter, parameterItemWriter));
        return compositeItemWriter;
    }
	
}
