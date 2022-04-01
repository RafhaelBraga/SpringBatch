package com.springbatch.batch.job.pessoas.listener.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.springbatch.batch.job.pessoas.listener.PessoasJobListener;
import com.springbatch.entity.Pessoas;

@Profile("pessoas")
@Component
public class PessoasItemReaderListener implements ItemReadListener<Pessoas>{

	private static final Logger log = LoggerFactory.getLogger(PessoasJobListener.class);

	@Override
	public void beforeRead() {
		log.info("ItemReaderListener - Before Read...");		
	}

	@Override
	public void afterRead(Pessoas item) {
		log.info("ItemReaderListener - After Read... ID: "+item.getId());
	}

	@Override
	public void onReadError(Exception ex) {
		log.error("ItemReaderListener - Error..."+ex.getMessage());
	}
}
