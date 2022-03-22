package com.springbatch.batch.job.pessoas.step.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.springbatch.batch.job.pessoas.config.PessoasJobCompletionNotificationListener;
import com.springbatch.entity.Pessoas;

@Profile("pessoas")
@Component
public class PessoasItemProcessorListener implements ItemProcessListener<Pessoas, Pessoas>{
	
	private static final Logger log = LoggerFactory.getLogger(PessoasJobCompletionNotificationListener.class);

	@Override
	public void beforeProcess(Pessoas item) {
		log.info("ItemProcessListener - Before Process Id: "+item.getId());		
	}

	@Override
	public void afterProcess(Pessoas item, Pessoas result) {
		log.info("ItemProcessListener - After Process Id: "+item.getId());				
	}

	@Override
	public void onProcessError(Pessoas item, Exception e) {
		log.info("ItemProcessListener - Error... Id: "
		+item.getId()
		+"\n"
		+e.getMessage());	
	}
//	
//	@AfterProcess
//	private void after() {
//		log.error("agora foi");
//	}
	
}
