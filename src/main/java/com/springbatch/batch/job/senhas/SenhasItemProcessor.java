package com.springbatch.batch.job.senhas;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springbatch.entity.Senhas;

@Profile("senhas")
public class SenhasItemProcessor implements ItemProcessor<Senhas,Senhas>{

	@Autowired(required=true)
	@Qualifier("passwordEncoder")
	private PasswordEncoder encoder;
	
	@Override
	public Senhas process(Senhas senhas) throws Exception {
		return new Senhas(senhas.getId(), senhas.getSenha1(), encoder.encode(senhas.getSenha1()));
	}

}
