package com.springbatch.batch.job.senhas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbatch.entity.Senhas;
@Component
@Profile("senhas")
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	  @Autowired
	  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }
	  
	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	      log.info("!!! JOB FINISHED! Time to verify the results");

	      jdbcTemplate.query("SELECT * FROM senhas",
	        (rs, row) -> new Senhas(
	          Long.valueOf(rs.getString(1)),
	          rs.getString(2),
	          rs.getString(3))
	      ).forEach(senha -> log.info("Found <" + senha + "> in the database."));
	    }
	  }

}
