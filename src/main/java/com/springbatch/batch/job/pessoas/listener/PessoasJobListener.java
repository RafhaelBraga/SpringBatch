package com.springbatch.batch.job.pessoas.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbatch.entity.Pessoas;

@Component
@Profile("pessoas")
public class PessoasJobListener extends JobExecutionListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(PessoasJobListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	  @Autowired
	  public PessoasJobListener(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }
	  
	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	      log.info("------------------ JOB FINISHED ------------------");

	      jdbcTemplate.query("SELECT * FROM pessoas",
	        (rs, row) -> new Pessoas(
	          Long.valueOf(rs.getString(1)),
	          rs.getString(2))
	      ).forEach(pessoa -> log.info("Found <" + pessoa + "> in the database."));
	    }
	  }
	  
	  @Override
	  public void beforeJob(JobExecution jobExecution) {
	      log.info("------------------ STARTING JOB ------------------");
	  }

}
