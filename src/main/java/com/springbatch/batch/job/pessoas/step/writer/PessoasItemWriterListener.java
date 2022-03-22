package com.springbatch.batch.job.pessoas.step.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.springbatch.batch.job.pessoas.config.PessoasJobCompletionNotificationListener;
import com.springbatch.entity.Pessoas;

@Profile("pessoas")
@Component
public class PessoasItemWriterListener implements ItemWriteListener<Pessoas>{

	private static final Logger log = LoggerFactory.getLogger(PessoasJobCompletionNotificationListener.class);
	
	@Override
	public void beforeWrite(List<? extends Pessoas> items) {
		log.info("ItemWriterListener - Before Write..."+items.toString());		
	}

	@Override
	public void afterWrite(List<? extends Pessoas> items) {
		log.info("ItemWriterListener - After Write... ID: "+items.toString());
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Pessoas> items) {
		log.error("ItemWriterListener - Error on Write..."+ exception.getMessage());		
	}
}
