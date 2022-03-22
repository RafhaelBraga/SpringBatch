package com.springbatch.batch.job.pessoas.step.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springbatch.entity.Pessoas;

@Profile("pessoas")
public class PessoasItemProcessor implements ItemProcessor<Pessoas,Pessoas>{

	@Autowired(required=true)
	@Qualifier("passwordEncoder")
	private PasswordEncoder encoder;
	
	@Override
	public Pessoas process(Pessoas pessoas) throws Exception {
		pessoas.setCpf(encoder.encode(pessoas.getCpf()));
		return pessoas;
	}

}
